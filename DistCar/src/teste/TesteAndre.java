package teste;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.swing.JOptionPane;

import classesBasicas.*;

public class TesteAndre {
	public static void execute(EntityManagerFactory emf){
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
			//**** persistindo PF ***
			PessoaFisica pf = new PessoaFisica("Maria Lima", "22233344455", "111222333", "SSPPE");
			pf.setEndereco(new Endereco(new TipoLogradouro("Rua"), "Rua 8", "s/n", "teste1",
										new Cidade("Recife", new UnidadeFederativa("Pernambuco", "PE"))));
			pf.setDataUltimaAtualizacao(Calendar.getInstance());
			pf.setSituacao(Situacao.ATIVO);
			em.persist(pf);
			
			//***** persistindo PJ *****
			PessoaJuridica pj = new PessoaJuridica("FIAT", "10111222000100", "111222333", Calendar.getInstance().getTime());
			pj.setEndereco(new Endereco(new TipoLogradouro("Av"), "Carros Novos", "102", "endereço 2",
										new Cidade("Natal", new UnidadeFederativa("Rio Grande do Norte", "RN"))));
			pj.setDataUltimaAtualizacao(Calendar.getInstance());
			pj.setSituacao(Situacao.ATIVO);
			em.persist(pj);
			
			// *** persistindo Funcao ***
			Funcao funcao1 = new Funcao("Atendente", 820.50);
			funcao1.setDataUltimaAtualizacao(Calendar.getInstance());
			funcao1.setSituacao(Situacao.ATIVO);
			em.persist(funcao1);
			em.persist(new Funcao("Telefonista", 850.33));
			
			//**** persistindo Fabricante e Marca ***
			MarcaCarro marca1 = new MarcaCarro();
			marca1.setDescricaoMarca("BMW");
			em.persist(marca1);
			
			MarcaCarro marcaCarro = new MarcaCarro();
			marcaCarro.setDescricaoMarca("Honda");
			Fabricante fabricante = new Fabricante(
							new PessoaJuridica("Honda Co", "00222444000101", "44444444", Calendar.getInstance().getTime(), 
								new Endereco(new TipoLogradouro("Rod"), "Carros Novos km 21", "1000", "endereço GM",
									new Cidade("Belém", new UnidadeFederativa("Pará", "PA"))
								)
							),
							marcaCarro, 10);
			em.persist(fabricante);
			
			//*** persistindo Centro ***
			Centro centro = new Centro(
						new PessoaJuridica("Matriz Distribuidora", "44555666000101", "555555", Calendar.getInstance().getTime(), 
							new Endereco(new TipoLogradouro("Praça"), "Vitrine Automotiva", "10", "Logradouro matriz",
								new Cidade("Salvador", new UnidadeFederativa("Bahia", "BA"))
							)
						), "Central distribuição", 100, TipoCentro.DISTRIBUIÇÃO, Calendar.getInstance(), Situacao.ATIVO
					);
			em.persist(centro);
			
			et.commit();
		}catch(Exception ex){
			et.rollback();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}finally{
			em.close();
		}
	}
}
