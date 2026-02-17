package Logica;

import java.util.Scanner;

import Conexao.DAO;

public class Cadastro {
	DAO<Usuario> dao = new DAO<>();
	Scanner entrada;
	
	public Cadastro() {
		entrada = new Scanner(System.in);
		String borda = "==".repeat(15);
		System.out.println("Qual ação deseja realizar?");
		System.out.println(" 1 - Cadastrar \n 2 - Atualizar cadastro \n 3 - Buscar Cadastro"
				+ "\n 4 - Deletear cadastro \n 5 - Sair");
		Integer opcao = entrada.nextInt();
		switch(opcao) {
		case 1: 
			System.out.println(" Nome: ");
			String nome = entrada.next();
			System.out.println("\n Email: ");
			String email = entrada.next();
			System.out.println("\n Idade: ");
			int idade = entrada.nextInt();
			System.out.println("\n Senha: ");
			String senha = entrada.next();
			
			Usuario usuario = new Usuario(nome, idade, email, senha);
			cadastrarUsuario(usuario);
			
		case 2:
		case 3:
		case 4:
		case 5:
		}
	}
	
	
	private void cadastrarUsuario(Usuario usuario) {
		dao.abrirT().incluir(usuario).fecharT();
	}
	
	private void atualizarCadastro(Usuario usuario) {
		 dao.abrirT().Atualizar(usuario).fecharT();
	}
	
	private void deletarCadastro(Usuario usuario){
		if(usuario.getNome().isEmpty() && (usuario.getId() != null)) {
			dao.abrirT().deletarCadastro(usuario.getId()).fecharT();
		}
	}
	
	private Usuario bucarCadastro(Usuario usuario){
		return (Usuario) dao.abrirT().obterPorID(usuario.getId());
	}
	
}
