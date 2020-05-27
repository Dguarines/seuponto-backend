package br.com.r4s.adm.arq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.r4s.adm.arq.dominio.UnidadeFederativa;
import br.com.r4s.adm.arq.dominio.UnidadeFederativaResponse;
import br.com.r4s.adm.arq.repository.UnidadeFederativaRepository;

@Service
public class UnidadeFederativaServiceImpl extends AbstractService<UnidadeFederativaRepository, UnidadeFederativa>
        implements UnidadeFederativaService {

    public UnidadeFederativaServiceImpl(UnidadeFederativaRepository repository) {
        super(repository);
    }

    @Override
    public List<UnidadeFederativaResponse> findAllProjetado() {
        return this.repository.findAllProjetadoByOrderByNome();
    }
}