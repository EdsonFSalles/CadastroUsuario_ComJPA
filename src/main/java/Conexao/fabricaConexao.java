package Conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class fabricaConexao {
	
	public EntityManager setSQl;
	private static EntityManagerFactory conexao;
	
	public EntityManager Conexao() {
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("DesafioHerancaJPA");
			EntityManager em = emf.createEntityManager();
			conexao = emf;
			return setSQl = em;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return setSQl;
	}
	
	public void fecharConexao() {
		conexao.close();
	}
	
	public void fecharSql() {
		setSQl.close();
	}
}
