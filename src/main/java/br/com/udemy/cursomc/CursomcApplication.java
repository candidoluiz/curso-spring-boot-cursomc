package br.com.udemy.cursomc;

import br.com.udemy.cursomc.domain.*;
import br.com.udemy.cursomc.domain.enums.EstadoPagamento;
import br.com.udemy.cursomc.domain.enums.TipoCliente;
import br.com.udemy.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Informatica");
        Categoria cat2 = new Categoria(null, "Escritorio");

        Produto p1 = new Produto(null,"Computador",2000.00);
        Produto p2 = new Produto(null,"Impressora",800.00);
        Produto p3 = new Produto(null,"Mouse",80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        Estado estado1 = new Estado(null, "Minas Gerais");
        Estado estado2 = new Estado(null, "São Paulo");

        Cidade cidade1 = new Cidade(null, "Uberlandia", estado1);
        Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
        Cidade cidade3 = new Cidade(null, "Campinas", estado2);

        categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

        estado1.getCidades().addAll(Arrays.asList(cidade1));
        estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

        estadoRepository.saveAll(Arrays.asList(estado1,estado2));
        cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2,cidade3));

        Cliente cliente1 = new Cliente(null,"Maria Silva","maria@gmail.com","323", TipoCliente.PESSOAFISICA);

        cliente1.getTelefones().addAll(Arrays.asList("32323","9879779"));

        Endereco endereco1 = new Endereco(null,"Rua flores","300","Apto 303","Jardim","32185",cliente1, cidade1);
        Endereco endereco2 = new Endereco(null,"Avenida Matos","105","Sala 800", "Centro","38777012",cliente1,cidade2);

        cliente1.getEnderecos().addAll(Arrays.asList(endereco1,endereco2));

        clienteRepository.saveAll(Arrays.asList(cliente1));
        enderecoRepository.saveAll(Arrays.asList(endereco1,endereco2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido pedido1 = new Pedido(null,sdf.parse("30/09/2020 10:32"),cliente1,endereco1);
        Pedido pedido2 = new Pedido(null,sdf.parse("10/10/2020 19:35"),cliente1,endereco2);

        Pagamento pagamento1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,pedido1,6);
        pedido1.setPagamento(pagamento1);

        Pagamento pagamento2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,pedido2,sdf.parse("20/10/2017 00:00"), null);
        pedido2.setPagamento(pagamento2);

        cliente1.getPedidos().addAll(Arrays.asList(pedido1,pedido2));

        pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2));
        pagamentoRepository.saveAll(Arrays.asList(pagamento1,pagamento2));

        ItemPedido itemPedido1 = new ItemPedido(pedido1,p1, 0.00,1,2000.00);
        ItemPedido itemPedido2 = new ItemPedido(pedido1,p3, 0.00,2,80.00);
        ItemPedido itemPedido3 = new ItemPedido(pedido2,p2, 100.00,1,800.00);

        pedido1.getItens().addAll(Arrays.asList(itemPedido1,itemPedido2));
        pedido2.getItens().addAll(Arrays.asList(itemPedido1));

        p1.getItens().addAll(Arrays.asList(itemPedido1));
        p2.getItens().addAll(Arrays.asList(itemPedido3));
        p3.getItens().addAll(Arrays.asList(itemPedido2));

        itemPedidoRepository.saveAll(Arrays.asList(itemPedido1,itemPedido2,itemPedido3));
    }
}
