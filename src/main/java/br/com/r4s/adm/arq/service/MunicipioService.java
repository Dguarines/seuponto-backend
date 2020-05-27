package br.com.r4s.adm.arq.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import br.com.r4s.adm.arq.dominio.Municipio;
import br.com.r4s.adm.arq.dominio.MunicipioResponse;


public interface MunicipioService extends EntityService<Municipio> {

    public List<MunicipioResponse> findAllProjetado();

    public List<MunicipioResponse> findByIdEstado(@Param("idEstado") Long idEstado);
}