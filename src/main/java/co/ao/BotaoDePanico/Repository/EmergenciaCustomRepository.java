package co.ao.BotaoDePanico.Repository;

import co.ao.BotaoDePanico.model.TbEmergenciaAtendida;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Airton Leal
 */
@Repository
@AllArgsConstructor
public class EmergenciaCustomRepository {

    private final AgenteRepository agenteRepository;
    private final EmergenciaRepository emergenciaRepository;
    private final TbEmergenciaAtendida emergenciaAtendida;
    private final EmergenciaAtendidaRepository emergenciaAtendidaRepository;

    public ResponseEntity<?> saveInTbEmergenciaAtendida(Long idEmergencia) {
        emergenciaAtendida.setIdAgente(agenteRepository.findByUsername(this.getUsuarioLogado()));
        emergenciaAtendida.setDataHora(LocalDateTime.now().plusHours(1)); 
        emergenciaAtendida.setIdEmergencia(this.emergenciaRepository.findById(idEmergencia).get());
        return new ResponseEntity<>(emergenciaAtendidaRepository.save(emergenciaAtendida), HttpStatus.CREATED);
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
}