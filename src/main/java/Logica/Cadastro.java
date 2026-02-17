package Logica;

import Conexao.DAO;

public class Cadastro<E> {
	DAO<E> dao = new DAO<>();
	
	public Cadastro<E> cadastrarUsuario(E usuario) {
		dao.abrirT().incluir(usuario).fecharT();
		return this;
	}
	
	public Cadastro<E> atualizarCadastro(E usuario) {
		 dao.abrirT().Atualizar(usuario).fecharT();
		 return this;
	}
	
	public Cadastro<E> deletarCadastro(Usuario usuario){
		if(usuario.getNome().isEmpty() && (usuario.getId() != null)) {
			dao.abrirT().deletarCadastro(usuario.getId()).fecharT();
		}
		return this;
	}
	
	public Usuario bucarCadastro(Usuario usuario){
		return (Usuario) dao.abrirT().obterPorID(usuario.getId());
	}
	
}
