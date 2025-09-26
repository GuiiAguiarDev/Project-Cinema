package com.movie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.model.Compra;
import com.movie.model.Session;
import com.movie.model.Ticket;
import com.movie.repository.CompraRepository;
import com.movie.repository.SessionRepository;
import com.movie.repository.TicketRepository;

import jakarta.transaction.Transactional;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Transactional
	public void comprar(Compra comp, Session session, int qtdTickts) {

		// Na minha classe session, eu tenho uma lista e tickets, por isso eu chamo
		// session primeiro, eae esses tickets eu estou cadastrando setando eles pelo
		// meu Application, no main, a quantidade que eu passar lá salvar eu estou
		// falando
		// abaixo que vai ser jogadoo, para dentro da lista ticketsDisponiveis.
		// na classe apllciation, é a parte que tem session.addTicket(ticket);
		// session.addTicket(ticket2); então oq eu passar la estou falando que vou jogar
		// para dentro dos ticketsDisponiveis
		// eae uso stream para conseguir codar o filter ou map forEach e etc
		// uso o filter para falar que se o ticket ainda nao foi comprado que no caso é
		// null
		// por isso == null, quer dizer que ele nao foi comprado ainda
		// pq nao foi relacinado ou seja nao teve relacionamento tanto que se for no
		// banco vai ta null por exemplo, então significa que nao foi comprado ainda
		List<Ticket> ticketsDisponiveis = session.getTicket().stream().filter(t -> t.getCompra() == null)
				.collect(Collectors.toList());

		// Verificando se a quantidade que quero comprar nao é maior que a quantidade
		// disponivel
		// para venda, envolvendo qualquer tipo de venda ou seja se o joao comprou e o
		// pedro
		// e a julia vai comprar vai interferir ou seja tem 10 ingressos joao comprou 5
		// e pedro 5 a juliana nao vai conseguir, uma interfere na outra
		if (qtdTickts > ticketsDisponiveis.size()) {
			System.out.println("Não há Tickets suficientes para compra");
			return;
		}

		// Aqui ja vai ser os que eu comprar um lista
		List<Ticket> ticketsComprados = new ArrayList<>();

		// Lógica
		// esse qtdTickets é a quantidade de tickets que vou querer comprar
		// quando chamar o metodo no Apllication eu vou passar ele, eae se o i for menor
		// que ele (qtdTickts), ele simplesmente roda o metodo de novo, assim
		// consigo ter o controle, pois ele vai ficar rodando até chegar na qtd de
		// tickets que quero comprar
		// pois o remove(0) mesmo, ele sempre vai removendo a primeira linha da lista
		// eae como
		// quero remover
		// conforme for comprando, o for vai fazendo isso pra mim, pq se falo que quero
		// comprar 5
		// ele vai ficar indo exemplo, i é < 5 sim, entao ele entra no for
		// e remove, o i = 0 na primeira rodada
		// depois o i agr vale 1 1 < 5 sim entao entra de novo e assim vai e vai
		// removendo
		// e adicionando na lista de comprados tbm oks, entendi
		// então essa parte do for é apenas para remover e adicionar o fluxo

		for (int i = 0; i < qtdTickts; i++) {

			// Estamos removendo o tickets da lista ticketsDisponiveis
			// que contem os tickets relacionados a sessao, toda vez que passar aqui ele
			// remove
			// e joga para dentro do t, então um ticket removido dos disponiveis, significa
			// um comprado
			Ticket t = ticketsDisponiveis.remove(0);
			// aqui eu pego o total dos que foram removidos e jogados para dentro e t e seto
			// dentro do compra que passei como paramentro para chamar aqui
			// e no caso eu tenho que chamar t e depois set.compra, porque o t vem do ticket
			// e o ticket que tem relacionamento com compra então está tudo ligago
			t.setCompra(comp);
			// por fim a lista idependente que criamos aqui nos adicionados a ela o t, que
			// no
			// caso é os tickets removidos de ticketsDisponiveis que é os comprados tbm, vai
			// justamente para a lista de comprados
			ticketsComprados.add(t);

		}

		// DEPOIS QUE ACABA O FLUXO ACIMA E NOS JÀ RESOLVEMOS A QUESTÂO DO FOR DE
		// ADICIONAR E REMOVER
		// comp é nossa compra, aqui eu passa a quantidade que foi comparada que vem da
		// nossa list
		// ticketcs comprados que fizemos asim
		comp.setQtd(ticketsComprados.size());
		// Aqui eu passo o valor final dessa compra de ingressos que
		// comprei
		// stream permiti fazer operações como map, filter, sum e etc,
		// .mapToDouble(Ticket::getValor) - transforma a lista de ticketcs em uma lista
		// de numeros double
		// .sum soma todos os valores dos tickets ou seja 2,50 + 3 + 50 e etc
		// eae pego esse valor e jogo dentro de setValorFinal
		comp.setValorFinal(ticketsComprados.stream().mapToDouble(Ticket::getValor).sum());
		// Compra tem uma lista de tickets, então abaixo estou adicionado na minha lista
		// de tickets
		// os tickets comprados. aqui é a parte do relacionamento entre eles tem que ter
		comp.addTickets(ticketsComprados);
		// Aqui salvamos tudo
		compraRepository.save(comp);

		ticketRepository.saveAll(ticketsComprados);
		sessionRepository.save(session);

	}

}
