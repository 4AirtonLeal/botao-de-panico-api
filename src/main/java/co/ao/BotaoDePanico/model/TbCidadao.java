package co.ao.BotaoDePanico.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@Table(name = "tb_cidadao")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TbCidadao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidadao")
    private Long idCidadao;

    @NotNull
    @Size(min = 8, max = 255, message = "Seu nome deve conter no mínimo 8 caracteres e máximo 255")
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dtNascimento;

    @NotNull
    @Column(name = "telefone", unique = true)
    private int telefone;

    public TbCidadao(Long idCidadao) {
        this.idCidadao = idCidadao;
    }


}
