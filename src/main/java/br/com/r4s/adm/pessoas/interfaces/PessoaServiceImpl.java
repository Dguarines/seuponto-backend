package br.com.r4s.adm.pessoas.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.r4s.adm.arq.service.AbstractService;
import br.com.r4s.adm.pessoas.dominio.Pessoa;
import br.com.r4s.adm.pessoas.repository.PessoaRepository;

@Service
public class PessoaServiceImpl extends AbstractService<PessoaRepository, Pessoa> implements PessoaService {

	@Autowired
	public PessoaServiceImpl(PessoaRepository repository) {
		super(repository);
	}

	@Override
	public Boolean jaExistePessoaComEsteCPF(String cpf) {
		List<Pessoa> pessoasComCpf = repository.findPessoasByCpf(cpf);
		return pessoasComCpf != null && !pessoasComCpf.isEmpty();
	}

	@Override
    public Pessoa findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
