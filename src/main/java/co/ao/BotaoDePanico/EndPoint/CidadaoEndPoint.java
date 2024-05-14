package co.ao.BotaoDePanico.EndPoint;

import co.ao.BotaoDePanico.error.ResourceNotFoundException;
import co.ao.BotaoDePanico.Repository.CidadaoRepository;
//import co.ao.BotaoDePanico.security.config.PasswordEncoder;
import co.ao.BotaoDePanico.model.TbCidadao;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Airton Leal
 */
@RestController
@RequestMapping("/v1")
public class CidadaoEndPoint {

    private final CidadaoRepository cidadaoDAO;
//    private PasswordEncoder encoder;

    @Autowired
    public CidadaoEndPoint(CidadaoRepository cidadaoDAO) {
        this.cidadaoDAO = cidadaoDAO;
    }

    @GetMapping(path = "protected/cidadao")
    @ApiOperation(value = "Permite listar todos cidadãos cadastrados na base de dados", response = TbCidadao[].class)
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(cidadaoDAO.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "protected/cidadao/{id}")
    @ApiOperation(value = "Permite pesquisar um (1) Cidadão pelo seu ID cadastrado na base de dados", response = TbCidadao[].class)
    public Object findCidadaoById(@PathVariable Long id) {
        this.verificaSeCidadaoExistePeloId(id);
        return new ResponseEntity<>(cidadaoDAO.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "protected/cidadao/findByName/{nome}")
    @ApiOperation(value = "Permite pesquisar um (1) Cidadão pelo seu ID cadastrado na base de dados", response = TbCidadao[].class)
    public Object findByName(@PathVariable String nome) {
        this.verificaSeCidadaoExistePeloNome(nome);
        List<TbCidadao> cidadao = cidadaoDAO.findByNomeIgnoreCaseContaining(nome);
        return new ResponseEntity<>(cidadao, HttpStatus.OK);
    }

    @PutMapping(path = "protected/cidadao")
    @ApiOperation(value = "Permite fazer alterações aos dados de um (1) determinado Cidadão", response = TbCidadao[].class)
    public ResponseEntity<?> update(@Valid @RequestBody TbCidadao cidadao) {
        this.verificaSeCidadaoExistePeloId(cidadao.getIdCidadao());
        return new ResponseEntity<>(cidadaoDAO.save(cidadao), HttpStatus.OK);
    }

    @PostMapping(path = "/cidadao")
    @ApiOperation(value = "Permite salvar um (1) novo cidadão na base de dados", response = TbCidadao[].class)
    public ResponseEntity<?> save(@Valid @RequestBody TbCidadao cidadao) {
        return new ResponseEntity<>(cidadaoDAO.save(cidadao), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "protected/cidadao/{id}")
    @ApiOperation(value = "Permite eliminar um determinado cidadão pelo ID", response = TbCidadao[].class)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.verificaSeCidadaoExistePeloId(id);
        cidadaoDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void verificaSeCidadaoExistePeloId(Long id) {
        if (!cidadaoDAO.existsById(id)) {
            throw new ResourceNotFoundException("Cidadão " + id + " Não encontrado");
        }
    }

    public void verificaSeCidadaoExistePeloNome(String nome) {
        if (cidadaoDAO.findByNomeIgnoreCaseContaining(nome).isEmpty()) {
            throw new ResourceNotFoundException("Cidadão " + nome + " Não encontrado");
        }
    }

}
