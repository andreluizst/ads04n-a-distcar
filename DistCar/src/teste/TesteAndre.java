package teste;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.swing.JOptionPane;

import classesBasicas.*;
import dao.DAOUnidadeFederativa;
import dao.IDAOUnidadeFederativa;

public class TesteAndre {
	public static void execute(EntityManagerFactory emf){
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		IDAOUnidadeFederativa daoUF = new DAOUnidadeFederativa(em);
		try{
			et.begin();
			
			// **** persistindo unidades federativas **** 
			daoUF.inserirSemTratamento(new UnidadeFederativa("Acre", "AC", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Alagoas", "AL", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Amazonas", "AM", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Amapá", "AP", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Bahia", "BA", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Ceará", "CE", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Maranhão", "MA", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Minas Gerais", "MG", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Mato Grosso do Sul", "MS", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Mato Grosso", "MT", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Pará", "PA", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Paraíba", "PB", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Pernambuco", "PE", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Piauí", "PI", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Paraná", "PR", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Rio de Janeiro", "RJ", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Rio Grande do Norte", "RN", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Rondonia", "RO", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Roraima", "RR", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Rio Grande do Sul", "RS", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Santa Catarina", "SC", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Sergipe", "SE", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("São Paulo", "SP", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Tocantins", "TO", Calendar.getInstance(), Situacao.ATIVO));
			
			//**** persistindo PF ***
			PessoaFisica pf = new PessoaFisica("Maria Lima", "22233344455", "111222333", "SSPPE");
			pf.setEndereco(new Endereco(new TipoLogradouro("Rua"), "Rua 8", "s/n", "teste1",
											new Cidade("Recife", daoUF.pegarUF("Pernambuco", "PE")),"51245000"
										)
								);
			pf.setDataUltimaAtualizacao(Calendar.getInstance());
			pf.setSituacao(Situacao.ATIVO);
			em.persist(pf);
			
			//***** persistindo PJ *****
			PessoaJuridica pj = new PessoaJuridica("FIAT", "10111222000100", "111222333", Calendar.getInstance().getTime());
			pj.setEndereco(new Endereco(new TipoLogradouro("Av"), "Carros Novos", "102", "endereço 2",
											new Cidade("Olinda", daoUF.pegarUF("Pernambuco", "PE")), "50000650"
										)
								);
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
			marca1.setDescricao("BMW");
			em.persist(marca1);
			
			MarcaCarro marcaCarro = new MarcaCarro();
			marcaCarro.setDescricao("Honda");
			Fabricante fabricante = new Fabricante(
							new PessoaJuridica("Honda Co", "00222444000101", "44444444", Calendar.getInstance().getTime(), 
								new Endereco(new TipoLogradouro("Rod"), "Carros Novos km 21", "1000", "endereço GM",
									new Cidade("Belém", daoUF.pegarUF("Pará", "PA")), "51030540"
								)
							),
							marcaCarro, 10);
			em.persist(fabricante);	
		
			//*** persistindo Centro ***
			Centro centro = new Centro(
						new PessoaJuridica("Carros Brasil", "44555666000101", "555555", Calendar.getInstance().getTime(), 
							new Endereco(new TipoLogradouro("Praça"), "Vitrine Automotiva", "10", "Bairro matriz",
								new Cidade("Salvador", daoUF.pegarUF("Bahia", "BA")), "55060020"
							)
						), "Central distribuição", 100, TipoCentro.DISTRIBUIÇÃO, Calendar.getInstance(), Situacao.ATIVO
					);
			em.persist(centro);
			
			Centro centro2 = new Centro(
					new PessoaJuridica("Carros Brasil", "44555666000222", "555555", Calendar.getInstance().getTime(), 
						new Endereco(new TipoLogradouro("Praça teste"), "Logradouro teste", "20", "Bairro filial",
							new Cidade("Campinas", daoUF.pegarUF("São Paulo", "SP")), "54652000"
						)
					), "Filial 0002-22", 100, TipoCentro.LOJA, Calendar.getInstance(), Situacao.ATIVO
				);
		em.persist(centro2);
			
			et.commit();
		}catch(Exception ex){
			et.rollback();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}finally{
			em.close();
		}
	}
}
