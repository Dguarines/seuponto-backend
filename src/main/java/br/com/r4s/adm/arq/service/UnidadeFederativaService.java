package br.com.r4s.adm.arq.service;

import java.util.List;

import br.com.r4s.adm.arq.dominio.UnidadeFederativa;
import br.com.r4s.adm.arq.dominio.UnidadeFederativaResponse;

public interface UnidadeFederativaService extends EntityService<UnidadeFederativa> {

    public List<UnidadeFederativaResponse> findAllProjetado();
}