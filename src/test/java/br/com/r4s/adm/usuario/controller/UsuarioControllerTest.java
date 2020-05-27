package br.com.r4s.adm.usuario.controller;

import static java.util.stream.Collectors.toList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.r4s.adm.arq.autenticacao.jwt.JWTConstants;
import br.com.r4s.adm.arq.dominio.Perfil;
import br.com.r4s.adm.arq.dominio.Usuario;
import br.com.r4s.adm.arq.messages.SourceMessage;
import br.com.r4s.adm.arq.service.UsuarioService;
import br.com.r4s.adm.usuarios.controller.UsuarioController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {


	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private ObjectMapper mapper;

    private List<Usuario> usuarios;

    Usuario usuario;

    private String token;
    
    private final String AUTHORIZATION = "Authorization";

    @SpyBean
    private SourceMessage sourceMessage;

    @Before
	public void init() {
        initObjectMapper();
        initUsuario();
        initListaUsuarios();
        obterTokenAutorizacao();

    }

    private void initObjectMapper() {
        mapper = new ObjectMapper();
    }

    private void initUsuario() {
        List<SimpleGrantedAuthority> papeis = Arrays.asList(
                                    new SimpleGrantedAuthority("CADASTRAR_USUARIO"),
                                    new SimpleGrantedAuthority("LISTAR_USUARIO"),
                                    new SimpleGrantedAuthority("ALTERAR_USUARIO"));
        usuario = Usuario.builder().id(1l).login("admin").senha("senhateste").authorities(papeis).build();
    }

    private void obterTokenAutorizacao() {
        this.token = criarToken(usuario);
    }

    private void initListaUsuarios() {
        usuarios = Stream
                .of(
                    Usuario.builder().id(1l).login("admin").senha("senhateste").build(),
                    Usuario.builder().id(2l).login("usertest").senha("senhateste1").build())
                .collect(toList());
    }

    public String criarToken(Usuario usuario) {

        byte signingKey[] = JWTConstants.JWT_SECRET.getBytes();
        usuario.setPerfis(new ArrayList<Perfil>());
        List<String> roles = usuario.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(toList());

        String token = Jwts.builder()
        .signWith(SignatureAlgorithm.HS512, signingKey)
        .setHeaderParam("typ", JWTConstants.TOKEN_TYPE)
        .setIssuer(JWTConstants.TOKEN_ISSUER)
        .setAudience(JWTConstants.TOKEN_AUDIENCE)
        .setSubject(usuario.getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + 864000000))
        .claim("rol", roles)
        .compact();
        return token;
    }

    @Test
    public void deveRetornarTodosUsuarios__quandoUsuarioAutorizado() throws Exception {
        given(usuarioService.findAll()).willReturn(usuarios);

        this.mockMvc.perform(
            get("/usuarios")
            .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + this.token))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(usuarios.size()));
    }

    @Test
    public void deveRetornarUsuario__quandoBuscaPeloIdentificador() throws Exception {
        given(usuarioService.findById(usuario.getId())).willReturn(usuario);

        this.mockMvc.perform(
            get("/usuarios/"+usuario.getId())
            .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + this.token))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$.login").value(usuario.getLogin()));
    }

    @Test
    public void deveRetornarStatusAceito__quandoRemoverUsuario() throws Exception {
        this.mockMvc.perform(
            delete("/usuarios/"+usuario.getId())
            .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + this.token))
			.andExpect(status().isAccepted());

        Mockito.verify(usuarioService).deleteById(usuario.getId());
    }

    @Test
    public void deveRetornarStatusCriado_quandoCriarUsuario() throws Exception {
        given(passwordEncoder.encode(usuario.getPassword())).willReturn(usuario.getPassword());
        given(usuarioService.save(usuario)).willReturn(usuario);

        this.mockMvc.perform(
            post("/usuarios")
            .content(mapper.writeValueAsString(usuario))
            .contentType(MediaType.APPLICATION_JSON)
            .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + this.token))
			.andExpect(status().isCreated());

    }

    @Test
    public void deveRetornarUsuario__quandoUsuarioAtualizado() throws Exception {
        given(usuarioService.findById(usuario.getId())).willReturn(usuario);
        given(passwordEncoder.encode(usuario.getPassword())).willReturn(usuario.getPassword());
        given(usuarioService.save(usuario)).willReturn(usuario);
        
        this.mockMvc.perform(
            put("/usuarios/"+usuario.getId())
            .content(mapper.writeValueAsString(usuario))
            .contentType(MediaType.APPLICATION_JSON)
            .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + this.token))
			.andExpect(status().isAccepted())
            .andExpect(jsonPath("$.login").value(usuario.getLogin()));
    }


    @Test
    public void deveRetornarStatusNaoAutorizado__quandoObterTodosUsuariosSemAutorizacao() throws Exception {
        given(usuarioService.findAll()).willReturn(usuarios);

        this.mockMvc.perform(
            get("/usuarios")
            .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + "#tokenfake#"))
			.andExpect(status().isForbidden());
    }

    
}