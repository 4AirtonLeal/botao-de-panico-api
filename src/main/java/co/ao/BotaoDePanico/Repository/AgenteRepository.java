package co.ao.BotaoDePanico.Repository;

import co.ao.BotaoDePanico.model.TbAgente;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Airton Leal
 */
public interface AgenteRepository extends PagingAndSortingRepository<TbAgente, Long> {

    public List<TbAgente> findByNomeIgnoreCaseContaining(String nome);

    public TbAgente findByUsername(String username);
}
