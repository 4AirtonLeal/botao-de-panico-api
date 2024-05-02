/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.BotaoDePanico.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Airton Leal
 */
@Entity
@Table(name = "tb_agente")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class TbAgente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agente", nullable = false)
    private Long IdAgente;

    @Column(name = "nome", nullable = false, length = 255)
    @Size(min = 10, max = 255, message = "Seu nome de conter um valor mínimo de 10 caracteres e máximo de 255")
    private String nome;

    @Column(name = "contacto", nullable = false, unique = true)
    private int contacto;

    @Column(name = "foto", nullable = false, unique = true)
    private String foto;

    @Column(name = "username", nullable = false)
    @Size(min = 8, max = 50, message = "Seu username deve conter um valor mínimo de 8 caracteres e máximo de 50")
    private String username;

    @Column(name = "password", nullable = false, unique = true)
    @Size(min = 8, message = "Sua password deve conter no mínimo 8 caracteres")
    private String password;

    @Column(name = "tipo", nullable = false)
    private String tipo;

}
