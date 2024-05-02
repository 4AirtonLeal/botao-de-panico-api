package co.ao.BotaoDePanico.EndPoint;

import co.ao.BotaoDePanico.error.ResourceNotFoundException;
import co.ao.BotaoDePanico.Repository.EmergenciaAtendidaRepository;
import co.ao.BotaoDePanico.model.TbEmergenciaAtendida;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Airton Leal
 */
@RestController
@RequestMapping("/v1")
public class EmergenciaAtendidaEndPoint {

    private final EmergenciaAtendidaRepository atendidaRepository;

    @Autowired
    public EmergenciaAtendidaEndPoint(EmergenciaAtendidaRepository atendidaRepository) {
        this.atendidaRepository = atendidaRepository;
    }

    @GetMapping(path = "protected/emergencia_atendida")
    @ApiOperation(value = "Permite listar todas emergências atendidas", response = TbEmergenciaAtendida[].class)
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(atendidaRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "protected/emergencia_antendida/{id}")
    @ApiOperation(value = "Apresenta uma (1) emergência atendida selecionada pelo seu ID", response = TbEmergenciaAtendida[].class)
    public Object findEmergenciaAtendidaById(@PathVariable Long id) {
        this.verificaSeExisteEmergenciaAtendidaPeloId(id);
        return new ResponseEntity<>(atendidaRepository.findById(id), HttpStatus.OK);
    }

    @PutMapping(path = "admin/emergencia_antendida")
    @ApiOperation(value = "Permite fazer alterações nos dados de uma emergência selecionada", response = TbEmergenciaAtendida[].class)
    public ResponseEntity<?> update(@Valid @RequestBody TbEmergenciaAtendida emergenciaAtendida) {
        this.findEmergenciaAtendidaById(emergenciaAtendida.getId_emergencia_atendida());
        return new ResponseEntity<>(atendidaRepository.save(emergenciaAtendida), HttpStatus.OK);
    }

    @DeleteMapping(path = "admin/emergencia_antendida/{id}")
    @ApiOperation(value = "Permite eliminar uma emergência pelo seu ID", response = TbEmergenciaAtendida[].class)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.verificaSeExisteEmergenciaAtendidaPeloId(id);
        atendidaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public String getUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("USERNAME: " + username);
        return username;
    }

    public void verificaSeExisteEmergenciaAtendidaPeloId(Long id) {
        if (!atendidaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhuma emergencia com ID " + id + " Foi atendida");
        }
    }

}
