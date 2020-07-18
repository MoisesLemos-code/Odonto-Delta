package com.moises.odontoDelta.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.moises.odontoDelta.domain.Categoria;
import com.moises.odontoDelta.domain.Cidade;
import com.moises.odontoDelta.domain.Cliente;
import com.moises.odontoDelta.domain.Dente;
import com.moises.odontoDelta.domain.DenteItem;
import com.moises.odontoDelta.domain.Endereco;
import com.moises.odontoDelta.domain.Estado;
import com.moises.odontoDelta.domain.Finalizador;
import com.moises.odontoDelta.domain.Orcamento;
import com.moises.odontoDelta.domain.Pagamento;
import com.moises.odontoDelta.domain.Peca;
import com.moises.odontoDelta.domain.PecaItem;
import com.moises.odontoDelta.domain.Servico;
import com.moises.odontoDelta.domain.ServicoItem;
import com.moises.odontoDelta.domain.Usuario;
import com.moises.odontoDelta.domain.enums.Permissao_usuario;
import com.moises.odontoDelta.domain.enums.StatusDente;
import com.moises.odontoDelta.domain.enums.StatusOrcamento;
import com.moises.odontoDelta.domain.enums.StatusPagamento;
import com.moises.odontoDelta.domain.enums.TipoCliente;
import com.moises.odontoDelta.domain.enums.TipoOrcamento;
import com.moises.odontoDelta.repositories.CategoriaRepository;
import com.moises.odontoDelta.repositories.CidadeRepository;
import com.moises.odontoDelta.repositories.ClienteRepository;
import com.moises.odontoDelta.repositories.DenteItemRepository;
import com.moises.odontoDelta.repositories.DenteRepository;
import com.moises.odontoDelta.repositories.EnderecoRepository;
import com.moises.odontoDelta.repositories.EstadoRepository;
import com.moises.odontoDelta.repositories.FinalizadorRepository;
import com.moises.odontoDelta.repositories.OrcamentoRepository;
import com.moises.odontoDelta.repositories.PagamentoRepository;
import com.moises.odontoDelta.repositories.PecaItemRepository;
import com.moises.odontoDelta.repositories.PecaRepository;
import com.moises.odontoDelta.repositories.ServicoItemRepository;
import com.moises.odontoDelta.repositories.ServicoRepository;
import com.moises.odontoDelta.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder  pe;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private PecaRepository pecaRepository;
	@Autowired
	private ServicoRepository servicoRepository;
	@Autowired
	private DenteRepository denteRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private OrcamentoRepository orcamentoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private FinalizadorRepository finalizadorRepository;
	@Autowired
	private PecaItemRepository pecaItemRepository;
	@Autowired
	private ServicoItemRepository servicoItemRepository;
	@Autowired
	private DenteItemRepository denteItemRepository;

	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Peca p1 = new Peca(null,"Computador", 2000.00);
		Peca p2 = new Peca(null, "Impressora", 800.00);
		Peca p3 = new Peca(null, "Mouse", 80.00);
		Peca p4 = new Peca(null, "Mesa de escritório", 300.00);
		Peca p5 = new Peca(null, "Toalha", 50.00);
		Peca p6 = new Peca(null, "Colcha", 200.00);
		Peca p7 = new Peca(null,"TV true color", 1200.00);
		Peca p8 = new Peca(null,"Roçadeira", 800.00);
		Peca p9 = new Peca(null,"Abajur", 100.00);
		Peca p10 = new Peca(null,"Pendente", 100.00);
		Peca p11 = new Peca(null,"Shampoo", 90.00);
		
		cat1.getPecas().addAll(Arrays.asList(p1,p2,p3));
		cat2.getPecas().addAll(Arrays.asList(p2, p4));
		cat3.getPecas().addAll(Arrays.asList(p5, p6));
		cat4.getPecas().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getPecas().addAll(Arrays.asList(p8));
		cat6.getPecas().addAll(Arrays.asList(p9, p10));
		cat7.getPecas().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2, cat3, cat4, cat5, cat6, cat7));
		pecaRepository.saveAll(Arrays.asList(p1,p2,p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Servico s1 = new Servico(null, "Limpeza", 50);
		Servico s2 = new Servico(null, "Manutenção", 100);
		
		servicoRepository.saveAll(Arrays.asList(s1,s2));
		
		Dente d1 = new Dente(null,"11", "Dente de cavalo");
		Dente d2 = new Dente(null,"14", "Molar");
		Dente d3 = new Dente(null,"23", "Terceiro molar");
		
		denteRepository.saveAll(Arrays.asList(d1,d2,d3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Usuario user1 = new Usuario(null, "MASTER","Administrador", pe.encode("2143"), "moisesconta4@outlook.com");
		user1.addPermissao(Permissao_usuario.ADMINISTRADOR);
		
		Usuario user2 = new Usuario(null, "PADRAO","Padrão",pe.encode("123"), "moises_lemos@outlook.com");
		
		usuarioRepository.saveAll(Arrays.asList(user1, user2));
		
		Cliente cli1 = new Cliente(null,"Maria Silva", "koutakmoi@gmail.com", "44139491027", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("1498203100","6798482100"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "45200220",cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Orcamento orc1 = new Orcamento(null,TipoOrcamento.ORCAMENTO, sdf.parse("25/02/2020 10:32"), null, StatusOrcamento.ANDAMENTO, 0, 0, cli1, e1);
		Orcamento orc2 = new Orcamento(null,TipoOrcamento.ORCAMENTO, sdf.parse("01/03/2020 14:40"), sdf.parse("01/03/2020 15:05"), StatusOrcamento.FECHADO, 0, 0, cli1, e2);
		
		cli1.getOrcamentos().addAll(Arrays.asList(orc1, orc2));
		
		Finalizador f1 = new Finalizador(null,"Dinheiro", false);
		Finalizador f2 = new Finalizador(null,"Cartão de Crédito", true);
		
		Pagamento pag1 = new Pagamento(null,StatusPagamento.CONCLUIDO, 100, 100, 1, null, sdf.parse("01/03/2020 15:05"), orc2, f1);
		Pagamento pag2 = new Pagamento(null,StatusPagamento.PENDENTE, 150, 75, 1, sdf.parse("25/03/2020 10:00"), null, orc1, f2);
		Pagamento pag3 = new Pagamento(null,StatusPagamento.PENDENTE, 150, 0, 2, sdf.parse("25/04/2020 10:00"), null, orc1, f2);
		
		
		f1.getPagamentos().addAll(Arrays.asList(pag1));
		f2.getPagamentos().addAll(Arrays.asList(pag2, pag3));
	
		orc1.getPagamentos().addAll(Arrays.asList(pag2, pag3));
		orc2.getPagamentos().addAll(Arrays.asList(pag1));
		
		finalizadorRepository.saveAll(Arrays.asList(f1,f2));
		orcamentoRepository.saveAll(Arrays.asList(orc1, orc2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2,pag3));
		
		PecaItem pi1 = new PecaItem(orc1, p1, 0.00, 0.00, 100, 1 );
		PecaItem pi2 = new PecaItem(orc1, p2, 30, 0.00, 80, 2 );
		PecaItem pi3 = new PecaItem(orc2, p3, 9, 10, 99, 1 );
		
		ServicoItem si1 = new ServicoItem(orc1, s1, 0.00, 0.00, 50, 1);
		ServicoItem si2 = new ServicoItem(orc1, s2, 0.00, 0.00, 50, 2);

		
		DenteItem di1 = new DenteItem(orc1, null, si2, d1, StatusDente.RESTAURADO);
		DenteItem di2 = new DenteItem(orc2, pi3, null, d1, StatusDente.RESTAURADO);
		
		orc1.getPecasItens().addAll(Arrays.asList(pi1,pi2));
		orc1.getServicosItens().addAll(Arrays.asList(si1, si2));
		orc1.getDentesItens().addAll(Arrays.asList(di1));
		
		orc2.getPecasItens().addAll(Arrays.asList(pi3));
		
		p1.getItens().addAll(Arrays.asList(pi1));
		p2.getItens().addAll(Arrays.asList(pi2));
		p3.getItens().addAll(Arrays.asList(pi3));
		
		s1.getItens().add(si1);
		s2.getItens().add(si2);
		
		d1.getItens().addAll(Arrays.asList(di1, di2));
		
		pecaItemRepository.saveAll(Arrays.asList(pi1,pi2,pi3));
		servicoItemRepository.saveAll(Arrays.asList(si1, si2));
		denteItemRepository.saveAll(Arrays.asList(di1, di2));
		
	}
}
