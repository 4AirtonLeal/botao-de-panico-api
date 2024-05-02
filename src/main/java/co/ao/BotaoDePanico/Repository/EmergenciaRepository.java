package co.ao.BotaoDePanico.Repository;

import co.ao.BotaoDePanico.model.TbEmergencia;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Airton Leal
 */
public interface EmergenciaRepository extends PagingAndSortingRepository<TbEmergencia, Long> {
    
}
