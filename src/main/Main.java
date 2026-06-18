package main;

import construtores.Animal;
import construtores.Consulta;
import construtores.Tutor;
import construtores.GerenciamentoAnimais;
import construtores.GerenciamentoConsultas;
import construtores.GerenciamentoTutores;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static GerenciamentoTutores  gerTutores   = new GerenciamentoTutores();
    static GerenciamentoAnimais  gerAnimais   = new GerenciamentoAnimais(gerTutores);
    static GerenciamentoConsultas gerConsultas = new GerenciamentoConsultas(gerAnimais);

    public static void main(String[] args) {

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerInt("Escolha uma opção");
            switch (opcao) {
                case 1 -> menuTutores();
                case 2 -> menuAnimais();
                case 3 -> menuConsultas();
                case 0 -> System.out.println("\n  Encerrando o sistema. Até logo!\n");
                default -> System.out.println("  ✗ Opção inválida.\n");
            }
        } while (opcao != 0);
    }


    //  MENUS


    static void exibirMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      CLÍNICA VETERINÁRIA           ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║  1 - Gerenciar Tutores             ║");
        System.out.println("║  2 - Gerenciar Animais             ║");
        System.out.println("║  3 - Gerenciar Consultas           ║");
        System.out.println("║  0 - Sair                          ║");
        System.out.println("╚════════════════════════════════════╝");
    }

    //MENU TUTORES
    static void menuTutores() {
        int opcao;
        do {
            System.out.println("\n TUTORES ");
            System.out.println("  1 - Incluir tutor ");
            System.out.println("  2 - Listar todos ");
            System.out.println("  3 - Buscar por ID ");
            System.out.println("  4 - Alterar tutor ");
            System.out.println(" 5 - Excluir tutor");
            System.out.println("  0 - Voltar \n");

            opcao = lerInt("Escolha uma opção");
            switch (opcao) {
                case 1 -> incluirTutor();
                case 2 -> listarTutores();
                case 3 -> buscarTutorPorId();
                case 4 -> alterarTutor();
                case 5 -> excluirTutor();
                case 0 -> {}
                default -> System.out.println("  ✗ Opção inválida.");
            }
        } while (opcao != 0);
    }

    //  MENU ANIMAIS
    static void menuAnimais() {
        int opcao;
        do {
            System.out.println("\n ANIMAIS");
            System.out.println("  1 - Incluir animal               ");
            System.out.println("  2 - Listar todos                 ");
            System.out.println("  3 - Buscar por ID                ");
            System.out.println("  4 - Alterar animal               ");
            System.out.println("  5 - Excluir animal               ");
            System.out.println("  0 - Voltar                       ");
            System.out.println("");
            opcao = lerInt("Escolha uma opção");
            switch (opcao) {
                case 1 -> incluirAnimal();
                case 2 -> listarAnimais();
                case 3 -> buscarAnimalPorId();
                case 4 -> alterarAnimal();
                case 5 -> excluirAnimal();
                case 0 -> {}
                default -> System.out.println(" Opção inválida.");
            }
        } while (opcao != 0);
    }

    // MENU CONSULTAS
    static void menuConsultas() {
        int opcao;
        do {
            System.out.println("\nCONSULTAS  ");
            System.out.println("  1 - Incluir consulta             ");
            System.out.println("  2 - Listar todas                 ");
            System.out.println("  3 - Buscar por ID               ");
            System.out.println("  4 - Consultas de um animal       ");
            System.out.println("  5 - Alterar consulta             ");
            System.out.println("  6 - Excluir consulta             ");
            System.out.println("  0 - Voltar                       ");
            System.out.println("");
            opcao = lerInt("Escolha uma opção");
            switch (opcao) {
                case 1 -> incluirConsulta();
                case 2 -> listarConsultas();
                case 3 -> buscarConsultaPorId();
                case 4 -> listarConsultasPorAnimal();
                case 5 -> alterarConsulta();
                case 6 -> excluirConsulta();
                case 0 -> {}
                default -> System.out.println("  ✗ Opção inválida.");
            }
        } while (opcao != 0);
    }


    //  OPERAÇÕES DE TUTORES


    static void incluirTutor() {
        System.out.println("\n── Incluir Tutor ──");
        String nome      = lerString("Nome");
        String telefone  = lerString("Telefone");
        String email     = lerString("Email");
        String cpf       = lerString("CPF");
        gerTutores.incluirTutor(nome, telefone, email, cpf);
    }

    static void listarTutores() {
        List<Tutor> lista = gerTutores.listarTodos();
        if (lista.isEmpty()) { System.out.println("\n  Nenhum tutor cadastrado."); return; }
        System.out.println("\n── Lista de Tutores (" + lista.size() + ") ──");
        lista.forEach(System.out::println);
    }

    static void buscarTutorPorId() {
        int id = lerInt("\nID do tutor");
        Tutor tutor = gerTutores.buscarPorId(id);
        if (tutor != null) System.out.println(tutor);
        else System.out.println("  ✗ Tutor com ID " + id + " não encontrado.");
    }

    static void alterarTutor() {
        int id = lerInt("\nID do tutor a alterar");
        Tutor tutor = gerTutores.buscarPorId(id);
        if (tutor == null) { System.out.println("  Tutor não encontrado."); return; }

        System.out.println("  Deixe em branco para manter o valor atual.\n");
        String nome     = lerStringOpcional("Nome      [" + tutor.getNome() + "]");
        String telefone = lerStringOpcional("Telefone  [" + tutor.getTelefone() + "]");
        String email    = lerStringOpcional("Email     [" + tutor.getEmail() + "]");
        String cpf      = lerStringOpcional("CPF       [" + tutor.getCpf() + "]");
        gerTutores.alterarTutor(id, nome, telefone, email, cpf);
    }

    static void excluirTutor() {
        int id = lerInt("\nID do tutor a excluir");
        System.out.print("  Confirma exclusão do tutor ID " + id + "? (s/n): ");
        String confirmacao = sc.nextLine().trim();
        if (confirmacao.equalsIgnoreCase("s")) gerTutores.excluirTutor(id);
        else System.out.println("  Operação cancelada.");
    }


    //  OPERAÇÕES DE ANIMAIS


    static void incluirAnimal() {
        System.out.println("\n── Incluir Animal ──");
        // Exibe tutores disponíveis para facilitar a escolha
        List<Tutor> tutores = gerTutores.listarTodos();
        if (tutores.isEmpty()) {
            System.out.println("  ✗ Nenhum tutor cadastrado. Cadastre um tutor primeiro.");
            return;
        }
        System.out.println("  Tutores disponíveis:");
        tutores.forEach(t -> System.out.printf("    [%d] %s — %s | %s%n",
                t.getId(), t.getNome(), t.getTelefone(), t.getEmail()));

        String nome    = lerString("Nome do animal");
        String especie = lerString("Espécie (ex: Cão, Gato)");
        String raca    = lerString("Raça");
        int    idade   = lerInt("Idade (anos)");
        int    tutorId = lerInt("ID do tutor");
        gerAnimais.incluirAnimal(nome, especie, raca, idade, tutorId);
    }

    static void listarAnimais() {
        List<Animal> lista = gerAnimais.listarTodos();
        if (lista.isEmpty()) { System.out.println("\n  Nenhum animal cadastrado."); return; }
        System.out.println("\n── Lista de Animais (" + lista.size() + ") ──");
        for (Animal a : lista) {
            Tutor tutor = gerTutores.buscarPorId(a.getTutorId());
            System.out.printf(
                    "╔══════════════════════════════════════╗%n" +
                            "  Animal ID: %d%n" +
                            "  Nome:      %s%n" +
                            "  Espécie:   %s%n" +
                            "  Raça:      %s%n" +
                            "  Idade:     %d ano(s)%n" +
                            "  ── Tutor ──────────────────────────%n" +
                            "  Nome:      %s%n" +
                            "  Telefone:  %s%n" +
                            "  Email:     %s%n" +
                            "╚══════════════════════════════════════╝%n",
                    a.getId(), a.getNome(), a.getEspecie(), a.getRaca(), a.getIdade(),
                    tutor != null ? tutor.getNome()     : "—",
                    tutor != null ? tutor.getTelefone() : "—",
                    tutor != null ? tutor.getEmail()    : "—"
            );
        }
    }

    static void buscarAnimalPorId() {
        int id = lerInt("\nID do animal");
        Animal animal = gerAnimais.buscarPorId(id);
        if (animal == null) { System.out.println("  ✗ Animal com ID " + id + " não encontrado."); return; }
        Tutor tutor = gerTutores.buscarPorId(animal.getTutorId());
        System.out.printf(
                "%n╔══════════════════════════════════════╗%n" +
                        "  Animal ID: %d%n" +
                        "  Nome:      %s%n" +
                        "  Espécie:   %s%n" +
                        "  Raça:      %s%n" +
                        "  Idade:     %d ano(s)%n" +
                        "  ── Tutor ──────────────────────────%n" +
                        "  Nome:      %s%n" +
                        "  Telefone:  %s%n" +
                        "  Email:     %s%n" +
                        "╚══════════════════════════════════════╝%n",
                animal.getId(), animal.getNome(), animal.getEspecie(), animal.getRaca(), animal.getIdade(),
                tutor != null ? tutor.getNome()     : "—",
                tutor != null ? tutor.getTelefone() : "—",
                tutor != null ? tutor.getEmail()    : "—"
        );
    }

    static void alterarAnimal() {
        int id = lerInt("\nID do animal a alterar");
        Animal animal = gerAnimais.buscarPorId(id);
        if (animal == null) { System.out.println("  ✗ Animal não encontrado."); return; }

        System.out.println("  Deixe em branco para manter o valor atual.\n");
        String nome    = lerStringOpcional("Nome     [" + animal.getNome() + "]");
        String especie = lerStringOpcional("Espécie  [" + animal.getEspecie() + "]");
        String raca    = lerStringOpcional("Raça     [" + animal.getRaca() + "]");
        String idadeStr = lerStringOpcional("Idade    [" + animal.getIdade() + "]");
        String tutorStr = lerStringOpcional("Tutor ID [" + animal.getTutorId() + "]");
        int novaIdade  = idadeStr.isBlank() ? -1 : Integer.parseInt(idadeStr);
        int novoTutor  = tutorStr.isBlank()  ? -1 : Integer.parseInt(tutorStr);
        gerAnimais.alterarAnimal(id, nome, especie, raca, novaIdade, novoTutor);
    }

    static void excluirAnimal() {
        int id = lerInt("\nID do animal a excluir");
        System.out.print("  Confirma exclusão do animal ID " + id + "? (s/n): ");
        String confirmacao = sc.nextLine().trim();
        if (confirmacao.equalsIgnoreCase("s")) gerAnimais.excluirAnimal(id);
        else System.out.println("  Operação cancelada.");
    }


    //  OPERAÇÕES DE CONSULTAS


    static void incluirConsulta() {
        System.out.println("\n── Incluir Consulta ──");
        int    animalId    = lerInt("ID do animal");
        String data        = lerString("Data (dd/mm/aaaa)");
        String horario     = lerString("Horário (hh:mm)");
        String veterinario = lerString("Nome do veterinário");
        String motivo      = lerString("Motivo da consulta");
        String obs         = lerStringOpcional("Observações (opcional)");
        gerConsultas.incluirConsulta(animalId, data, horario, veterinario, motivo, obs);
    }

    static void listarConsultas() {
        List<Consulta> lista = gerConsultas.listarTodas();
        if (lista.isEmpty()) { System.out.println("\n  Nenhuma consulta cadastrada."); return; }
        System.out.println("\n── Lista de Consultas (" + lista.size() + ") ──");
        lista.forEach(System.out::println);
    }

    static void buscarConsultaPorId() {
        int id = lerInt("\nID da consulta");
        Consulta consulta = gerConsultas.buscarPorId(id);
        if (consulta != null) System.out.println(consulta);
        else System.out.println("  ✗ Consulta com ID " + id + " não encontrada.");
    }

    static void listarConsultasPorAnimal() {
        int animalId = lerInt("\nID do animal");
        Animal animal = gerAnimais.buscarPorId(animalId);
        if (animal == null) { System.out.println("  ✗ Animal não encontrado."); return; }
        List<Consulta> lista = gerConsultas.listarPorAnimal(animalId);
        if (lista.isEmpty()) {
            System.out.println("  Nenhuma consulta para " + animal.getNome() + ".");
            return;
        }
        System.out.println("\n── Consultas de " + animal.getNome() + " (" + lista.size() + ") ──");
        lista.forEach(System.out::println);
    }

    static void alterarConsulta() {
        int id = lerInt("\nID da consulta a alterar");
        Consulta consulta = gerConsultas.buscarPorId(id);
        if (consulta == null) { System.out.println("  ✗ Consulta não encontrada."); return; }

        System.out.println("  Deixe em branco para manter o valor atual.\n");
        String data        = lerStringOpcional("Data       [" + consulta.getData() + "]");
        String horario     = lerStringOpcional("Horário    [" + consulta.getHorario() + "]");
        String veterinario = lerStringOpcional("Veterinário[" + consulta.getVeterinario() + "]");
        String motivo      = lerStringOpcional("Motivo     [" + consulta.getMotivo() + "]");
        String obs         = lerStringOpcional("Observações[" + consulta.getObservacoes() + "]");
        gerConsultas.alterarConsulta(id, data, horario, veterinario, motivo, obs);
    }

    static void excluirConsulta() {
        int id = lerInt("\nID da consulta a excluir");
        System.out.print("  Confirma exclusão da consulta ID " + id + "? (s/n): ");
        String confirmacao = sc.nextLine().trim();
        if (confirmacao.equalsIgnoreCase("s")) gerConsultas.excluirConsulta(id);
        else System.out.println("  Operação cancelada.");
    }


    //  UTILITÁRIOS


    static String lerString(String label) {
        System.out.print("  " + label + ": ");
        String valor = sc.nextLine().trim();
        while (valor.isBlank()) {
            System.out.print("  Campo obrigatório. " + label + ": ");
            valor = sc.nextLine().trim();
        }
        return valor;
    }

    static String lerStringOpcional(String label) {
        System.out.print("  " + label + ": ");
        return sc.nextLine().trim();
    }

    static int lerInt(String label) {
        while (true) {
            System.out.print("  " + label + ": ");
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  ✗ Informe um número inteiro válido.");
            }
        }
    }


    //  dados de teste que ja vem no sistema


    static void popularDadosDeTeste() {
        System.out.println("  (Dados de exemplo carregados)");
        gerTutores.incluirTutor("Carlos Silva", "(81) 99999-1111", "carlos@email.com", "111.222.333-44");
        gerTutores.incluirTutor("Ana Souza",    "(81) 98888-2222", "ana@email.com",    "555.666.777-88");
        gerTutores.incluirTutor("Luga Lima",    "(81) 97777-3333", "Luga@email.com",   "999.000.111-22");

        gerAnimais.incluirAnimal("Rex",     "Cão",  "Labrador", 3, 1);
        gerAnimais.incluirAnimal("Mimi",    "Gato", "Siamês",   5, 2);
        gerAnimais.incluirAnimal("Bolinha", "Cão",  "Poodle",   2, 3);

        gerConsultas.incluirConsulta(1, "10/06/2025", "09:00", "Dra. Paula", "Vacinação anual", "");
        gerConsultas.incluirConsulta(2, "11/06/2025", "14:30", "Dr. Marcos", "Rotina",          "Verificar peso");
        gerConsultas.incluirConsulta(1, "15/06/2025", "10:00", "Dra. Paula", "Retorno",         "");
    }
}