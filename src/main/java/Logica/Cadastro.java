package Logica;

import java.util.Scanner;

import Conexao.DAO;

public class Cadastro {
	DAO<Usuario> dao = new DAO<>();
	Scanner entrada;

	public Cadastro() {
		entrada = new Scanner(System.in);
		String borda = "==".repeat(15);
		System.out.println(borda + "\n Qual ação deseja realizar?");
		System.out.println(" 1 - Cadastrar \n 2 - Atualizar cadastro \n 3 - Buscar Cadastro"
				+ "\n 4 - Deletear cadastro \n 5 - Sair" + "\n" + borda);
		Integer opcao = entrada.nextInt();

		switch (opcao) {
		case 1:
			String nome = lerString("Nome");
			String email = lerString("Email");
			int idade = lerInt("Idade");
			String senha = lerString("Senha");

			Usuario usuario = new Usuario(nome, idade, email, senha);
			cadastrarUsuario(usuario);

		case 2:
			System.out.println("Digite o Id do usuario: ");
			long IdUsuario = entrada.nextLong();
			Usuario usuarioNoBanco = bucarCadastro(IdUsuario);

			if (usuarioNoBanco == null) {
				System.out.println("Usuario não encontrado!");
				return;
			} else {
				System.out.println("== USUARIO CADASTRADO ==");
				System.out.println("Nome: " + usuarioNoBanco.getNome());
				System.out.println("Email: " + usuarioNoBanco.getEmail());
				System.out.println("Idade: " + usuarioNoBanco.getIdade());
			}

			System.out.println("Digite Atualização: (Pule o registro com enter)");
			String Nome = lerString("Nome");
			String Email = lerString("Email");
			int Idade = lerInt("Idade");

			String pergunta = lerString("\n Deseja atualizar senha? sim | não").toLowerCase();
			String novaSenha = null;
			
			if (pergunta.equals("sim")) {
				String Senha = lerString("Nova Senha");
				usuarioNoBanco.setSenha(Senha);
				novaSenha = Senha;
			}

			Usuario usuarioAtualizado = new Usuario(Nome, Idade, Email, novaSenha);
			atualizarCadastro(usuarioAtualizado);

		case 3:
		case 4:
		case 5:
		}
	}

	private String lerString(String texto) {
		System.out.println("\n " + texto + ":");
		return entrada.next();
	}

	private int lerInt(String texto) {
		System.out.println("\n " + texto + ":");
		return entrada.nextInt();
	}

	private void cadastrarUsuario(Usuario usuario) {
		dao.abrirT().incluir(usuario).fecharT();
	}

	private void atualizarCadastro(Usuario usuario) {
		dao.abrirT().Atualizar(usuario).fecharT();
	}

	private void deletarCadastro(Usuario usuario) {
		if (usuario.getNome().isEmpty() && (usuario.getId() != null)) {
			dao.abrirT().deletarCadastro(usuario.getId()).fecharT();
		}
	}

	private Usuario bucarCadastro(Long Id) {
		return dao.abrirT().obterPorID(Id);

	}

}
