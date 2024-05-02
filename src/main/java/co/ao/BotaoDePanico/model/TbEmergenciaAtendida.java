/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.BotaoDePanico.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 *
 * @author Airton Leal
 */
@Entity
@Table(name = "tb_emergencia_atendida")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
public class TbEmergenciaAtendida implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emergencia_atendida", nullable = false)
    private Long id_emergencia_atendida;
    
    //@Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime DataHora;
    
    @OneToOne
    //@UniqueElements
    @JoinColumn(name = "id_emergencia", nullable = false, unique = true)
    private TbEmergencia IdEmergencia;
    
    @OneToOne
    @JoinColumn(name = "id_agente", nullable = false)
    private TbAgente IdAgente;

}
