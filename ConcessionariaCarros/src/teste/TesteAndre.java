package teste;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.swing.JOptionPane;

import classesBasicas.Cidade;
import classesBasicas.Pessoa;
import classesBasicas.PessoaFisica;
import classesBasicas.Endereco;
import classesBasicas.PessoaJuridica;
import classesBasicas.Situacao;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;

public class TesteAndre {
	public static void execute(EntityManagerFactory emf){
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
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
			
			et.commit();
		}catch(Exception ex){
			et.rollback();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}finally{
			em.close();
		}
	}
}
