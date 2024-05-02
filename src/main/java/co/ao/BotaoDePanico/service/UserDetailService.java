/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.BotaoDePanico.service;

import co.ao.BotaoDePanico.Repository.AgenteRepository;
import co.ao.BotaoDePanico.model.TbAgente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Airton Leal
 */
@Service
public class UserDetailService implements UserDetailsService {

    private final AgenteRepository agenteRepository;
    private TbAgente agente;

    @Autowired
    public UserDetailService(AgenteRepository agenteRepository) {
        this.agenteRepository = agenteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (agenteRepository.findByUsername(username) == null) {
            throw new UsernameNotFoundException("Utilizador não existe. Verifica os teus dados de acesso.");
        }
        agente = this.agenteRepository.findByUsername(username);
        System.out.println(agente.getUsername() + " Logado");
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
        return new User(agente.getUsername(), agente.getPassword(), agente.getTipo().equals("admin") ? authorityListAdmin : authorityListUser);
        
    }

}
