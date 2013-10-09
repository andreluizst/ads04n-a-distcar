package teste;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.ItemSerieCarro;
import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Situacao;
import classesBasicas.VersaoModeloCarro;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unitPSC");
		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();
				
		
		et.begin();
		
		MarcaCarro marcaCarro = new MarcaCarro();
		marcaCarro.setDescricaoMarca("Chevrolet");
		
		
		VersaoModeloCarro versaoModeloCarro = new VersaoModeloCarro();
		versaoModeloCarro.setSituacao(Situacao.ATIVO );
		//versaoModeloCarro.setDescricaoVersaoModeloCarro("1.0 LTZ");

		ItemSerieCarro is = new ItemSerieCarro();
		is.setDescricao("Arcondicionado");
		is.setValorItemSerie(1500);
		
		ItemSerieCarro is1 = new ItemSerieCarro();
		is1.setDescricao("Direção Hidraulica");
		is1.setValorItemSerie(1500);
		
		AcessorioCarro ac = new AcessorioCarro();
		ac.setDescricao("Adesivo");
		ac.setValorAcessorioCarro(200);
		
		AcessorioCarro ac1 = new AcessorioCarro();
		ac1.setDescricao("Farol Mascara Negra");
		ac1.setValorAcessorioCarro(150);
		
		ModeloCarro modelo = new ModeloCarro();
		modelo.setAnoModelo(2014);
		modelo.setDescricaoModelocarro("Celta");
		List<AcessorioCarro> acessorio= new ArrayList<AcessorioCarro>();
		acessorio.add(0, ac);
		acessorio.add(1,ac1 );
		modelo.setAcessorioCarros(acessorio);
		List<ItemSerieCarro> itemSerie= new ArrayList<ItemSerieCarro>();
		itemSerie.add(0, is);
		itemSerie.add(1, is1);
		modelo.setItemSerieCarros(itemSerie);
		
		Carro carro = new Carro();
		carro.setAnoFabricacao(2014);
		carro.setChassi("121dawew323");
		carro.setCor("azul");
		carro.setValorCarro(30000.00);
		carro.setMarca(marcaCarro);
		carro.setModeloCarro(modelo);
		carro.setVersaoModeloCarro(versaoModeloCarro);
		em.persist(carro);
		et.commit();
		em.close();
		emf.close();
	}

}
