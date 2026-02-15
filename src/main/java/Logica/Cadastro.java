package Logica;

import Conexao.DAO;

public class Cadastro<Usuario> {
	DAO<Usuario> dao = new DAO<>();
	
	public Cadastro<Usuario> cadastrarUsuario(Usuario usuario) {
		dao.abrirT().incluir(usuario).fecharT();
		return this;
	}
	
	public Cadastro<Usuario> atualizarCadastro(Usuario usuario) {
		dao.abrirT().Atualizar(usuario).fecharT();
		return this;
	}
	
	//Contruir demais aplicações para a logica
	
}
