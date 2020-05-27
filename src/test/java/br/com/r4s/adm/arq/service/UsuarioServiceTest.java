package br.com.r4s.adm.arq.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.r4s.adm.arq.dominio.Usuario;
import br.com.r4s.adm.arq.repository.UsuarioRepository;
import br.com.r4s.adm.usuarios.service.UsuarioPadraoService;


@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;
    
    @InjectMocks
    UsuarioPadraoService usuarioService;

    private List<Usuario> usuarios;

    Usuario usuario;

    @Before
	public void init() {
        initUsuario();
        initListaUsuarios();
    }

    private void initUsuario() {
        usuario = Usuario.builder().id(1l).login("admin").senha("senhateste").build();
    }

    private void initListaUsuarios() {
        usuarios = Stream
                .of(
                    Usuario.builder().id(1l).login("admin").senha("senhateste").build(),
                    Usuario.builder().id(2l).login("usertest").senha("senhateste1").build())
                .collect(Collectors.toList());
    }

    @Test
    public void deveRetornarTodosUsuarios__quandoBuscarPorTodos() {
        given(usuarioRepository.findAll()).willReturn(usuarios);

        List<Usuario> usuariosRetorno = usuarioService.findAll();

        assertThat(usuariosRetorno, samePropertyValuesAs(usuarios));
    }
    
    @Test
    public void deveRetornarUsuario__quandoCadastrarUsuario() {
        given(usuarioRepository.save(usuario)).willReturn(usuario);

        Usuario usuarioRetorno = usuarioService.save(usuario);

        assertThat(usuarioRetorno, equalTo(usuario));
    }

    @Test
    public void deveRetornarUsuario__quandoBuscarPorIdentificador() {
        Optional<Usuario> usuarioOptional = Optional.of(usuario);

        given(usuarioRepository.findById(usuario.getId())).willReturn(usuarioOptional);

        Usuario usuarioOptionalRetorno = usuarioService.findById(usuario.getId());

        assertThat(usuarioOptionalRetorno, equalTo(usuario));        
    }

    @Test
    public void deveFazerNada__quandoRemoverUsuario() {
        willDoNothing().given(usuarioRepository).deleteById(usuario.getId());

        usuarioService.deleteById(usuario.getId());

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(usuarioRepository, Mockito.times(1)).deleteById(captor.capture());
        Long paramId = captor.getValue();

        assertThat(paramId, equalTo(usuario.getId()));

    }

    @Test(expected=Exception.class)
    public void deveRetornarExcecao__RemoverUsuario() {
        willThrow(Exception.class).given(usuarioRepository).deleteById(null);

        usuarioService.deleteById(null);
    }



}