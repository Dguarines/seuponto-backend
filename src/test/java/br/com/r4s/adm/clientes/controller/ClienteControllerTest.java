package br.com.r4s.adm.clientes.controller;

//@RunWith(SpringRunner.class)
//@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
	/*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @SpyBean
    private SourceMessage sourceMessage;

    private ObjectMapper mapper;

    @Mock
    private Usuario usuario;

    @SpyBean
    private JWTUtil jwtUtil;

    private Cliente cliente;
    
    private final String AUTHORIZATION = "Authorization";

    @Before
    public void setUp() {
        cliente = Cliente.builder()
                         .id(1L)
                         .medicoPediatra("medicoPediatra")
                         .outroMedico("outroMedico")
                         .escola("escola Teste 77")
                         .relacaoFinanceira(RelacaoFinanceira.builder().id(1L).build())
                         .pessoa(Pessoa.builder().id(1L).build())
                         .build();
        mapper = new ObjectMapper();
    }

    @Test
    public void deveRetornarStatusNaoAutorizado__quandoTentarListarClientesSemPermissao() throws Exception {
        setUpUsuarioSemPermissao();
        mockMvc.perform(get("/clientes")
               .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + getToken()))
			   .andExpect(status().isForbidden());
    }

    @Test
    public void deveRetornarStatusNaoAutorizado__quandoTentarObterClientePorIdSemPermissao() throws Exception {
        setUpUsuarioSemPermissao();
        mockMvc.perform(get("/clientes/1")
               .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + getToken()))
			   .andExpect(status().isForbidden());
    }

    @Test
    public void deveRetornarStatusNaoAutorizado__quandoTentarCadastrarClienteSemPermissao() throws Exception {
        setUpUsuarioSemPermissao();
        mockMvc.perform(post("/clientes")
               .content(mapper.writeValueAsString(cliente))
               .contentType(MediaType.APPLICATION_JSON)
               .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + getToken()))
			   .andExpect(status().isForbidden());
    }

    @Test
    public void deveRetornarStatusNaoAutorizado__quandoTentarAtualizarClienteSemPermissao() throws Exception {
        setUpUsuarioSemPermissao();
        mockMvc.perform(put("/clientes/1")
               .content(mapper.writeValueAsString(cliente))
               .contentType(MediaType.APPLICATION_JSON)
               .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + getToken()))
			   .andExpect(status().isForbidden());
    }

    @Test
    public void deveRetornarStatusNaoAutorizado__quandoTentarRemoverClienteSemPermissao() throws Exception {
        setUpUsuarioSemPermissao();
        mockMvc.perform(delete("/clientes/1")
               .contentType(MediaType.APPLICATION_JSON)
               .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + getToken()))
			   .andExpect(status().isForbidden());
    }

    @Test
    public void deveRetornarTodosClientes__quandoUsuarioAutorizado() throws Exception {
        setUpUsuarioComPermissoes("VISUALIZAR_CLIENTE");
        setUpClienteServiceListaClientes();
        mockMvc.perform(get("/clientes")
               .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + getToken()))
			    .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void deveRetornarCliente__quandoUsuarioAutorizadoBuscarPeloIdentificador() throws Exception {
        setUpUsuarioComPermissoes("VISUALIZAR_CLIENTE");
        setUpClienteServiceFindById();
        this.mockMvc.perform(
            get("/clientes/" + cliente.getId())
            .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + getToken()))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(cliente.getId()));
    }

    @Test
    public void deveRetornarStatusOk__quandoRemoverCliente() throws Exception {
        setUpUsuarioComPermissoes("REMOVER_CLIENTE");
        this.mockMvc.perform(
            delete("/clientes/" + cliente.getId())
            .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX +getToken()))
			.andExpect(status().isOk());

        verify(clienteService).deleteById(cliente.getId());
    }

    @Test
    public void deveRetornarStatusCriado_quandoCriarCliente() throws Exception {
        setUpUsuarioComPermissoes("CADASTRAR_CLIENTE");
        setUpClienteServiceSave();
        this.mockMvc.perform(
            post("/clientes")
            .content(mapper.writeValueAsString(cliente))
            .contentType(MediaType.APPLICATION_JSON)
            .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + getToken()))
			.andExpect(status().isCreated());

    }

    @Test
    public void deveRetornarCliente__quandoClienteAtualizado() throws Exception {
        setUpUsuarioComPermissoes("ALTERAR_CLIENTE");
        setUpClienteServiceFindById();
        setUpClienteServiceSave();        
        this.mockMvc.perform(
            put("/clientes/" + cliente.getId())
            .content(mapper.writeValueAsString(cliente))
            .contentType(MediaType.APPLICATION_JSON)
            .header(AUTHORIZATION, JWTConstants.TOKEN_PREFIX + getToken()))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$.escola").value("escola Teste 77"));
    }

    public void setUpUsuarioSemPermissao() {
        setUpUsuario();
    }

    public void setUpUsuarioComPermissoes(String... permissoes) {
        setUpUsuario();
        setUpPermissoesUsuario(permissoes);
    }

    public void setUpClienteServiceListaClientes() {
        ClienteResponse cliente1 = new ClienteResponse(){
            @Override
            public String getTelefone() {return null;}
            @Override
            public String getNome() {return null;}
            @Override
            public Long getId() {return null;}
            @Override
            public String getRelacaoFinanceira() {return null;}
            @Override
            public String getCPF() {return null;}
        };
        ClienteResponse cliente2 = cliente1;
        doReturn(Arrays.asList(cliente1, cliente2)).when(clienteService).findAllProjetado();
    }

    public void setUpClienteServiceFindById() {
        doReturn(cliente).when(clienteService).findById(1L);
    }

    public void setUpClienteServiceSave() {
        doReturn(cliente).when(clienteService).save(cliente);
    }

    public void setUpUsuario() {
        given(usuario.getId()).willReturn(1L);
        given(usuario.getUsername()).willReturn("admin");
        given(usuario.getLogin()).willReturn("admin");
        given(usuario.getPassword()).willReturn("admin");
    }

    public void setUpPermissoesUsuario(String... papeis) {
        List<SimpleGrantedAuthority> authorities = null;
        authorities = Stream.of(papeis)
                            .map(papel -> new SimpleGrantedAuthority(papel))
                            .collect(Collectors.toList());

        doReturn(authorities).when(usuario).getAuthorities();
    }

    public String getToken() {
        return jwtUtil.generateJsonWebToken(usuario);
    }
    */
}