/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.BotaoDePanico.Adapter;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Airton Leal
 */
@Configuration
public class PaginacaoConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver argumentResolver = new PageableHandlerMethodArgumentResolver();
        argumentResolver.setFallbackPageable(new PageRequest(0, 5, Sort.unsorted()) {});
        argumentResolvers.add(argumentResolver); 
    }

}
