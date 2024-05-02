package co.ao.BotaoDePanico.EndPoint;

import co.ao.BotaoDePanico.error.ResourceNotFoundException;
import co.ao.BotaoDePanico.Repository.AgenteRepository;
import co.ao.BotaoDePanico.model.TbAgente;
import co.ao.BotaoDePanico.security.jwt.config.PasswordEncoder;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

/**
 *
 * @author Airton Leal
 */
@RestController
@RequestMapping("/v1")
public class AgenteEndPoint {

    private final AgenteRepository agenteRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public AgenteEndPoint(AgenteRepository agenteRepository) {
        this.agenteRepository = agenteRepository;
    }

    @GetMapping(path = "protected/agente")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Retorna a lista dos agentes cadastrados na base de dados", response = TbAgente[].class)
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(agenteRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "protected/agente/{id}")
    @ApiOperation(value = "Permite pesquisar um (1) agente pelo seu ID cadastrado na base de dados", response = TbAgente[].class)
    public Object findAgenteById(@PathVariable Long id) {
        this.verificaSeAgenteExistePeloId(id);
        return new ResponseEntity<>(agenteRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "protected/agente/findByName/{nome}")
    @ApiOperation(value = "Permite pesquisar um (1) agente pelo seu nome cadastrado na base de dados", response = TbAgente[].class)
    public Object findByName(@PathVariable String nome) {
        this.VerificaSeAgenteExistePeloNome(nome);
        List<TbAgente> agente = agenteRepository.findByNomeIgnoreCaseContaining(nome);
        return new ResponseEntity<>(agente, HttpStatus.OK);
    }

    @PutMapping(path = "admin/agente")
    @ApiOperation(value = "Permite fazer alterações aos dados de um agente selecionado", response = TbAgente[].class)
    public ResponseEntity<?> update(@Valid @RequestBody TbAgente agente) {
        this.verificaSeAgenteExistePeloId(agente.getIdAgente());
        agente.setPassword(encoder.encodePassword(agente.getPassword()));
        return new ResponseEntity<>(agenteRepository.save(agente), HttpStatus.OK);
    }

    @PostMapping(path = "admin/agente")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Permite salvar um (1) novo agente na base de dados", response = TbAgente[].class)
    public ResponseEntity<?> save(@Valid @RequestBody TbAgente agente) {
        agente.setPassword(encoder.encodePassword(agente.getPassword()));
        return new ResponseEntity<>(agenteRepository.save(agente), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "admin/agente/{id}")
    @ApiOperation(value = "Permite eliminar um (1) agente da base de dados", response = TbAgente[].class)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            this.verificaSeAgenteExistePeloId(id);
            agenteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            throw new ResourceNotFoundException(e.getMessage() + " " + e.getStatusText());
        }
    }

    private void verificaSeAgenteExistePeloId(Long id) {
        if (!agenteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agente " + id + " Não encontrado");
        }
    }

    private void VerificaSeAgenteExistePeloNome(String nome) {
        if (agenteRepository.findByNomeIgnoreCaseContaining(nome).isEmpty()) {
            throw new ResourceNotFoundException("Agente " + nome + " Não encontrado");
        }
    }
}
