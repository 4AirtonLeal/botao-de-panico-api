package co.ao.BotaoDePanico.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
//import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Airton Leal
 * 
 */
@Entity
@Table(name = "tb_emergencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TbEmergencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emergencia")
    private Long idEmergencia;
    
    @Column(name = "data_hora", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_localizacao", nullable = false)
    private TbLocalizacao idLocalizacao;
    
    @ManyToOne
    @JoinColumn(name = "id_cidadao", nullable = false)
    private TbCidadao idCidadao;
    
    @OneToOne
    @JoinColumn(name = "id_tipo_emergencia", nullable = false)
    private TbTipoEmergencia tipoDeEmergencia;
    
    @NotNull
    @Column(name = "status", nullable = false)
    private boolean status = false;

    
    public TbEmergencia(Long idEmergencia) {
       this.idEmergencia = idEmergencia;
    }  
    
}
