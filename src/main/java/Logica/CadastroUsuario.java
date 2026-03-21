package Logica;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Conexao.DAO;

public class CadastroUsuario {
	DAO<Usuario> dao = new DAO<>(Usuario.class);
	Scanner entrada = new Scanner(System.in);;

	public CadastroUsuario() {
		iniciar();
	}

	private void iniciar() {
		boolean fechar = false;
		while (!fechar) {
			Integer opcao = opcao();

			switch (opcao) {
			case 1:
				cadastro();
				break;

			case 2:
				atualizar();
				break;

			case 3:
				buscarRegistroUnico();
				break;
			case 4:
				buscarTodosRegistros();
				break;
			case 5:
				deletarCadastro();
				break;
			case 6:
				fechar = true;
				fecharConexão();
				entrada.close();
				System.out.println("Finalizando programa...");
				break;
			}
		}

	}

	private Usuario retornarUsuario() {
		while (true) {
			long idUsuario;
			try {
				idUsuario = entrada.nextLong();
			} catch (NumberFormatException e) {
				System.out.println("Valor digitado é invalido!");
				continue;
			}
			Usuario usuarioNoBanco = buscarPorIdDAO(idUsuario);
			if (usuarioNoBanco != null) {
				System.out.println("== USUARIO CADASTRADO ==");
				System.out.println("Nome: " + usuarioNoBanco.getNome());
				System.out.println("Email: " + usuarioNoBanco.getEmail());
				System.out.println("Idade: " + usuarioNoBanco.getIdade());
				return usuarioNoBanco;
			}else {
				System.out.println("Usuario não encontrado!");
				System.out.println("Digite novamente o ID para busca:");
			}

		}
	}

	private void atualizar() {
		System.out.println("Digite o Id do usuario: ");

		Usuario usuarioNoBanco = retornarUsuario();
		System.out.println("\nConfirme para seguir atualizando: ");

		String Nome = atualizarEtapa("Nome", usuarioNoBanco.getNome());
		String Email = atualizarEtapa("Email", usuarioNoBanco.getEmail());
		String ValorIdade = String.valueOf(usuarioNoBanco.getIdade());
		int Idade = Integer.parseInt(atualizarEtapa("Idade", ValorIdade));
		String novaSenha = atualizarEtapa("Senha", usuarioNoBanco.getSenha());

		usuarioNoBanco.setNome(Nome);
		usuarioNoBanco.setEmail(Email);
		usuarioNoBanco.setIdade(Idade);
		usuarioNoBanco.setSenha(novaSenha);
		atualizarCadastroDAO(usuarioNoBanco);
		System.out.println("\nUsuario atualizado com sucesso!");
	}

	private String atualizarEtapa(String entrada, String getValor) {
		while (true) {
			System.out.println("Deseja atualizar " + entrada + "?(sim | nao):");
			String confirmNome = lerString().toLowerCase();
			if (confirmNome.equals("nao")) {
				return getValor;
			}
			if (confirmNome.equals("sim")) {
				System.out.println("Digite novo valor:");
				return entrada = lerString();
			}
			System.out.println("Escolha invalida! Tente novamente.");
		}
	}

	private Integer opcao() {
		String borda = "==".repeat(15);
		System.out.println(borda + "\n Qual ação deseja realizar?");
		System.out.println(" 1 - Cadastrar \n 2 - Atualizar cadastro \n 3 - Buscar Cadastro"
				+ "\n 4 - Buscar todos registros \n 5 - Deletar cadastro \n 6 - Sair" + "\n" + borda);

		while (true) {
			String opcao = lerString();
			if (opcao.matches("[1-6]")) {
				return Integer.parseInt(opcao);
			}
			System.out.println("Opçao invalida! Escolha uma opcao valida!");
		}
	}

	private String lerString() {
		try {
			return entrada.next();
		} catch (InputMismatchException e) {
			System.out.println("Erro de digitação. \n Reinicie o processo de cadastro");
			e.getMessage();
			entrada.nextLine();
			return "";
		}
	}

	private void cadastro() {
		System.out.println("Digite dados para cadastro:");
		System.out.println("Nome: ");
		String nome = lerString();

		System.out.println("Idade: ");
		String idade = lerString();
		int idadeInt = Integer.parseInt(idade);

		System.out.println("Email: ");
		String email = lerString();

		System.out.println("Senha: ");
		String senha = lerString();

		Usuario usuario = new Usuario(nome, idadeInt, email, senha);
		cadastrarUsuarioDAO(usuario);
	}

	private void deletarCadastro() {

		boolean condicao = false;
		while (condicao != true) {
			System.out
					.println("Digite o Id do cadastro para exclusão: (para voltar ao menu de opçoes escreva 'sair') ");
			String dadoId = lerString().toLowerCase();

			if (dadoId.equals("sair")) {
				break;
			}

			long idUsuario;
			try {
				idUsuario = Long.parseLong(dadoId);
			} catch (NumberFormatException e) {
				System.out.println("Valor digitado é invalido!");
				continue;
			}

			Usuario cadastros = dao.obterPorID(idUsuario);

			if (cadastros == null) {
				System.out.println("Cadastro não encontrado!");
				continue;
			}

			if (cadastros.getId() == idUsuario) {
				System.out.printf("Id: %d \nNome: %s \nEmail: %s", cadastros.getId(), cadastros.getNome(),
						cadastros.getEmail());
				System.out.println("\nTem certeza que deseja excluir cadastro? (sim | nao)");
				String confirm = lerString().toLowerCase();

				if (confirm.equals("sim")) {
					dao.abrirT().deletarCadastro(idUsuario).fecharT();
					System.out.println("Cadastro excluido com sucesso!");
					condicao = true;
				}
			}

		}
	}

	private void cadastrarUsuarioDAO(Usuario usuario) {
		dao.incluirAtomico(usuario);
	}

	private void atualizarCadastroDAO(Usuario usuario) {
		dao.abrirT().Atualizar(usuario).fecharT();
	}

	private Usuario buscarPorIdDAO(long idUsuario) {
		return dao.obterPorID(idUsuario);
	}

	private List<Usuario> buscarCadastroDAO(String String) {
		return dao.consultarUm(String);
	}

	private void buscarRegistroUnico() {
		while (true) {
			System.out.println("Digite o usuario que deseja encontrar: ");
			String busca = lerString();
			List<Usuario> listCadastro = buscarCadastroDAO(busca);

			if (listCadastro.isEmpty()) {
				System.out.println("Usuario não encontrado.");

			} else {
				for (Usuario listCoerente : listCadastro) {
					System.out.printf("Usuario cadastrado: \nNome: %s \nEmail: %s \nIdade: %d", listCoerente.getNome(),
							listCoerente.getEmail(), listCoerente.getIdade());
				}
			}

			System.out.println("\nDeseja encontrar outro usuario? (sim | nao)");
			String confirm = lerString();
			if (confirm.equals("nao")) {
				break;
			}
			if (!confirm.equals("sim")) {
				System.out.println("Resposta invalida! digite novamente");
				System.out.println("\nDeseja encontrar outro usuario? (sim | nao)");
				confirm = lerString();
			}
		}
	}

	private void buscarTodosRegistros() {

		List<Usuario> lista = dao.obterTodos();

		for (Usuario listaCompleta : lista) {
			String separar = "=".repeat(30);

			System.out.printf(separar + "\nId: %d \nNome: %s \nEmail: %s \nIdade: %d \n" + separar,
					listaCompleta.getId(), listaCompleta.getNome(), listaCompleta.getEmail(), listaCompleta.getIdade());
		}

	}

	private void fecharConexão() {
		dao.fecharConexao();
	}
}
