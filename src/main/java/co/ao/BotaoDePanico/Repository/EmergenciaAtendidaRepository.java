/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.BotaoDePanico.Repository;

import co.ao.BotaoDePanico.model.TbEmergenciaAtendida;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Airton Leal
 */
public interface EmergenciaAtendidaRepository extends PagingAndSortingRepository<TbEmergenciaAtendida, Long>, JpaSpecificationExecutor<TbEmergenciaAtendida> {
    
}
