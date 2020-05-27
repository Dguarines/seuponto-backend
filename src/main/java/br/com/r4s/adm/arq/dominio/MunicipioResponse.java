package br.com.r4s.adm.arq.dominio;

import org.springframework.beans.factory.annotation.Value;

public interface MunicipioResponse {

    public Long getId();

    public String getNome();

    @Value("#{target.iduf}")
    public Long getIdUnidadeFederativa();
    
}