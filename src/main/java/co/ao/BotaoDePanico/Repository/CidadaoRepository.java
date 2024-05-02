package co.ao.BotaoDePanico.Repository;

import co.ao.BotaoDePanico.model.TbCidadao;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Airton Leal
 */
public interface CidadaoRepository extends PagingAndSortingRepository<TbCidadao, Long>{
    
    public List<TbCidadao> findByNomeIgnoreCaseContaining(String nome);
        
}
