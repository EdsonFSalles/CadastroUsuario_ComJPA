package Conexao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAO<E> {
	fabricaConexao conexao = new fabricaConexao();
	private Class<E> classe;

	public DAO() {
		this(null);
	}

	public DAO(Class<E> classe) {
		this.classe = classe;
		conexao.Conexao();
	}

	public DAO<E> abrirT() {
		conexao.setSQl.getTransaction().begin();
		return this;
	}

	public DAO<E> fecharT() {
		conexao.setSQl.getTransaction().commit();
		return this;
	}

	public DAO<E> incluir(E entidade) {
		conexao.setSQl.persist(entidade);
		return this;
	}

	public DAO<E> incluirAtomico(E entidade) {
		return this.abrirT().incluir(entidade).fecharT();
	}
	
	public DAO<E> Atualizar(E entidade){
		 conexao.setSQl.merge(entidade);
		 return this;
	}
	
	public DAO<E> deletarCadastro( Long ID){
		E entidade = conexao.setSQl.find(classe, ID);
		if(entidade != null) {
		conexao.setSQl.remove(entidade);
		}
		return this;
	}

	public E obterPorID(Object id) {
		return conexao.setSQl.find(classe, id);
	}

	public List<E> obterTodos() {
		return this.obterTodos(10, 0);
	}

	public List<E> obterTodos(int qtde, int deslocamento) {
		if (classe == null) {
			throw new UnsupportedOperationException("Classe Nula");
		}
		String jpql = "Select e from " + classe.getName() + " e";
		TypedQuery<E> query = conexao.setSQl.createQuery(jpql, classe);
		query.setMaxResults(qtde);
		query.setFirstResult(deslocamento);
		return query.getResultList();
	}

	public List<E> consultar(String nomeConsulta, Object... parametros) {
		TypedQuery<E> query = conexao.setSQl.createNamedQuery(nomeConsulta, classe);

		for (int i = 0; i < parametros.length; i += 2) {
			query.setParameter(parametros[i].toString(), parametros[i + 1]);
		}
		return query.getResultList();
	}
	
	
	public List<E> consultarUm(String nomeConsulta) {
		String jpql = "SELECT u FROM Usuario u WHERE u.nome LIKE :nomeConsulta";
		TypedQuery<E> query = conexao.setSQl.createQuery(jpql, classe);
		query.setParameter("nomeConsulta", "%" + nomeConsulta + "%");
		return query.getResultList();
	}

	public void fecharConexao() {
		conexao.fecharConexao();;
		conexao.fecharSql();;
	}
}
