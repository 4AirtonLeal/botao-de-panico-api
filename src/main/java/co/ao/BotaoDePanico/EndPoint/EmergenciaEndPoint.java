package co.ao.BotaoDePanico.EndPoint;

import co.ao.BotaoDePanico.Repository.EmergenciaCustomRepository;
import co.ao.BotaoDePanico.error.ResourceNotFoundException;
import co.ao.BotaoDePanico.Repository.EmergenciaRepository;
import co.ao.BotaoDePanico.model.TbEmergencia;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class EmergenciaEndPoint {

    private final EmergenciaRepository emergenciaDAO;
    private final EmergenciaCustomRepository emergenciaCustomRepository;

    @Autowired
    public EmergenciaEndPoint(EmergenciaCustomRepository customRepository, EmergenciaRepository ermergenciaDAO) {
        this.emergenciaCustomRepository = customRepository;
        this.emergenciaDAO = ermergenciaDAO;
    }

    @GetMapping(path = "protected/emergencia")
    @ApiOperation(value = "Permite listar todas emergências presentes na base de dados", response = TbEmergencia[].class)
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(emergenciaDAO.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "protected/emergencia/{id}")
    @ApiOperation(value = "Apresenta uma (1) emergência pesquisada pelo seu ID", response = TbEmergencia[].class)
    public Object atenderUmaEmergencia(@PathVariable Long id) {
        this.verificaSeCidadaoExistePeloId(id);
        return new ResponseEntity<>(emergenciaDAO.findById(id), HttpStatus.OK);
    }

    @PutMapping(path = "admin/emergencia")
    @ApiOperation(value = "Permite actualizar os dados de uma certa emergência", response = TbEmergencia[].class)
    public ResponseEntity<?> update(@RequestBody TbEmergencia emergencia) {
        this.verificaSeCidadaoExistePeloId(emergencia.getIdEmergencia());
        emergenciaDAO.save(emergencia);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/emergencia")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Salva uma nova emergência sempre que o botão de pânico é clicked", response = TbEmergencia[].class)
    public ResponseEntity<?> ButtonClicked(@Valid @RequestBody TbEmergencia emergencia) {
        emergenciaDAO.save(emergencia);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("protected/emergencia/{id}")
    @ApiOperation(value = "Remove uma emergencia da tabela Emergencia, selecionada pelo ID e cadastra na tabela EmergenciaAtendida", response = TbEmergencia[].class)
    public ResponseEntity<?> finalizarAtendimento(@PathVariable Long id) {
        this.verificaSeCidadaoExistePeloId(id);
        if (!emergenciaDAO.findById(id).get().isStatus()) {
            emergenciaDAO.findById(id).get().setStatus(true);
        }
        this.emergenciaCustomRepository.saveInTbEmergenciaAtendida(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void verificaSeCidadaoExistePeloId(Long id) {
        if (!emergenciaDAO.existsById(id)) {
            throw new ResourceNotFoundException("Cidadão " + id + " Não encontrado");
        }
    }
}
