package br.com.r4s.adm.arq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.r4s.adm.arq.dominio.Municipio;
import br.com.r4s.adm.arq.dominio.MunicipioResponse;
import br.com.r4s.adm.arq.repository.MunicipioRepository;

@Service
public class MunicipioServiceImpl extends AbstractService<MunicipioRepository, Municipio> implements MunicipioService {

    public MunicipioServiceImpl(MunicipioRepository repository) {
        super(repository);
    }

    @Override
    public List<MunicipioResponse> findAllProjetado() {
        return this.repository.findAllProjetadoByOrderByNome();
    }

    @Override
    public List<MunicipioResponse> findByIdEstado(Long id) {
        return this.repository.findByIdEstado(id);
    }
}