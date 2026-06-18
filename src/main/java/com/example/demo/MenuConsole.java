package com.example.demo;

import com.example.demo.model.Animal;
import com.example.demo.model.Consulta;
import com.example.demo.model.Tutor;
import com.example.demo.service.AnimalService;
import com.example.demo.service.ConsultaService;
import com.example.demo.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MenuConsole implements CommandLineRunner {

    private final Scanner sc = new Scanner(System.in);

    @Autowired
    private TutorService tutorService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private ConsultaService consultaService;

    @Override
    public void run(String... args) throws Exception {
        // Roda o menu em uma Thread separada para não travar a inicialização do Spring
        new Thread(this::executarMenuPrincipal).start();
    }

    private void executarMenuPrincipal() {
        // Aguarda 2 segundos para os logs iniciais do Spring terminarem
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerInt("Escolha uma opção");
            switch (opcao) {
                case 0:
                    System.out.println("\n  Encerrando o sistema. Até logo!\n");
                    System.exit(0);
                    break;
                case 1:
                    menuTutores();
                    break;
                case 2:
                    menuAnimais();
                    break;
                case 3:
                    menuConsultas();
                    break;
                default:
                    System.out.println("  ✗ Opção inválida.\n");
            }
        } while (opcao != 0);
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      CLÍNICA VETERINÁRIA (BD)      ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║  1 - Gerenciar Tutores             ║");
        System.out.println("║  2 - Gerenciar Animais             ║");
        System.out.println("║  3 - Gerenciar Consultas           ║");
        System.out.println("║  0 - Sair                          ║");
        System.out.println("╚════════════════════════════════════╝");
    }


    // GERENCIAMENTO DE TUTORES

    private void menuTutores() {
        int opcao;
        do {
            System.out.println("\n TUTORES ");
            System.out.println("  1 - Incluir tutor ");
            System.out.println("  2 - Listar todos ");
            System.out.println("  3 - Buscar por ID ");
            System.out.println("  4 - Alterar tutor ");
            System.out.println("  5 - Excluir tutor");
            System.out.println("  0 - Voltar \n");
            opcao = lerInt("Escolha uma opção");
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    incluirTutor();
                    break;
                case 2:
                    listarTutores();
                    break;
                case 3:
                    buscarTutorPorId();
                    break;
                case 4:
                    alterarTutor();
                    break;
                case 5:
                    excluirTutor();
                    break;
                default:
                    System.out.println("  ✗ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void incluirTutor() {
        System.out.println("\n── Incluir Tutor ──");
        String nome = lerString("Nome");
        String telefone = lerString("Telefone");
        String email = lerString("Email");
        String cpf = lerString("CPF");

        Tutor novoTutor = new Tutor(null, nome, telefone, email, cpf);
        tutorService.incluir(novoTutor);
        System.out.println(" Tutor incluído com sucesso no PostgreSQL!");
    }

    private void listarTutores() {
        List<Tutor> lista = tutorService.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("\n  Nenhum tutor cadastrado.");
        } else {
            System.out.println("\n── Lista de Tutores (" + lista.size() + ") ──");
            lista.forEach(t -> System.out.printf("  ID: %d | Nome: %s | Tel: %s | CPF: %s%n", t.getId(), t.getNome(), t.getTelefone(), t.getCpf()));
        }
    }

    private void buscarTutorPorId() {
        Long id = lerLong("\nID do tutor");
        Optional<Tutor> tutorOpt = tutorService.buscarPorId(id);
        if (tutorOpt.isPresent()) {
            Tutor t = tutorOpt.get();
            System.out.printf("  ID: %d | Nome: %s | Tel: %s | Email: %s | CPF: %s%n", t.getId(), t.getNome(), t.getTelefone(), t.getEmail(), t.getCpf());
        } else {
            System.out.println("  ✗ Tutor com ID " + id + " não encontrado.");
        }
    }

    private void alterarTutor() {
        Long id = lerLong("\nID do tutor a alterar");
        Optional<Tutor> tutorOpt = tutorService.buscarPorId(id);
        if (tutorOpt.isEmpty()) {
            System.out.println("  ✗ Tutor não encontrado.");
        } else {
            Tutor tutor = tutorOpt.get();
            System.out.println("  Deixe em branco para manter o valor atual.\n");
            String nome = lerStringOpcional("Nome      [" + tutor.getNome() + "]");
            String telefone = lerStringOpcional("Telefone  [" + tutor.getTelefone() + "]");
            String email = lerStringOpcional("Email     [" + tutor.getEmail() + "]");
            String cpf = lerStringOpcional("CPF       [" + tutor.getCpf() + "]");

            Tutor novosDados = new Tutor(null, nome, telefone, email, cpf);
            tutorService.alterar(id, novosDados);
            System.out.println("  ✓ Tutor alterado com sucesso!");
        }
    }

    private void excluirTutor() {
        Long id = lerLong("\nID do tutor a excluir");
        System.out.print("  Confirma exclusão do tutor ID " + id + "? (s/n): ");
        String confirmacao = sc.nextLine().trim();
        if (confirmacao.equalsIgnoreCase("s")) {
            boolean excluido = tutorService.excluir(id);
            if (excluido) {
                System.out.println("  ✓ Tutor removido com sucesso.");
            } else {
                System.out.println("  ✗ Tutor não encontrado.");
            }
        } else {
            System.out.println("  Operação cancelada.");
        }
    }


    // GERENCIAMENTO DE ANIMAIS

    private void menuAnimais() {
        int opcao;
        do {
            System.out.println("\n ANIMAIS");
            System.out.println("  1 - Incluir animal               ");
            System.out.println("  2 - Listar todos                 ");
            System.out.println("  3 - Buscar por ID                ");
            System.out.println("  4 - Alterar animal               ");
            System.out.println("  5 - Excluir animal               ");
            System.out.println("  0 - Voltar                       ");
            opcao = lerInt("Escolha uma opção");
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    incluirAnimal();
                    break;
                case 2:
                    listarAnimais();
                    break;
                case 3:
                    buscarAnimalPorId();
                    break;
                case 4:
                    alterarAnimal();
                    break;
                case 5:
                    excluirAnimal();
                    break;
                default:
                    System.out.println(" Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void incluirAnimal() {
        System.out.println("\n── Incluir Animal ──");
        List<Tutor> tutores = tutorService.listarTodos();
        if (tutores.isEmpty()) {
            System.out.println("  Nenhum tutor cadastrado. Cadastre um tutor primeiro.");
        } else {
            System.out.println("  Tutores disponíveis:");
            tutores.forEach((t) -> System.out.printf("    [%d] %s — %s | %s%n", t.getId(), t.getNome(), t.getTelefone(), t.getEmail()));
            String nome = lerString("Nome do animal");
            String especie = lerString("Espécie (ex: Cão, Gato)");
            String raca = lerString("Raça");
            int idade = lerInt("Idade (anos)");
            Long tutorId = lerLong("ID do tutor");

            try {
                Animal novoAnimal = new Animal(null, nome, especie, raca, idade, null);
                animalService.incluir(novoAnimal, tutorId);
                System.out.println("  Animal incluído e vinculado com sucesso!");
            } catch (Exception e) {
                System.out.println("   Erro ao salvar: " + e.getMessage());
            }
        }
    }

    private void listarAnimais() {
        List<Animal> lista = animalService.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("\n  Nenhum animal cadastrado.");
        } else {
            System.out.println("\n── Lista de Animais (" + lista.size() + ") ──");
            for (Animal a : lista) {
                Tutor tutor = a.getTutor();
                System.out.printf("╔══════════════════════════════════════╗%n  Animal ID: %d%n  Nome:      %s%n  Espécie:   %s%n  Raça:      %s%n  Idade:     %d ano(s)%n  ── Tutor ──────────────────────────%n  Nome:      %s%n  Telefone:  %s%n  Email:     %s%n╚══════════════════════════════════════╝%n",
                        a.getId(), a.getNome(), a.getEspecie(), a.getRaca(), a.getIdade(),
                        tutor != null ? tutor.getNome() : "—", tutor != null ? tutor.getTelefone() : "—", tutor != null ? tutor.getEmail() : "—");
            }
        }
    }

    private void buscarAnimalPorId() {
        Long id = lerLong("\nID do animal");
        Optional<Animal> animalOpt = animalService.buscarPorId(id);
        if (animalOpt.isEmpty()) {
            System.out.println("  ✗ Animal com ID " + id + " não encontrado.");
        } else {
            Animal animal = animalOpt.get();
            Tutor tutor = animal.getTutor();
            System.out.printf("%n╔══════════════════════════════════════╗%n  Animal ID: %d%n  Nome:      %s%n  Espécie:   %s%n  Raça:      %s%n  Idade:     %d ano(s)%n  ── Tutor ──────────────────────────%n  Nome:      %s%n  Telefone:  %s%n  Email:     %s%n╚══════════════════════════════════════╝%n",
                    animal.getId(), animal.getNome(), animal.getEspecie(), animal.getRaca(), animal.getIdade(),
                    tutor != null ? tutor.getNome() : "—", tutor != null ? tutor.getTelefone() : "—", tutor != null ? tutor.getEmail() : "—");
        }
    }

    private void alterarAnimal() {
        Long id = lerLong("\nID do animal a alterar");
        Optional<Animal> animalOpt = animalService.buscarPorId(id);
        if (animalOpt.isEmpty()) {
            System.out.println("  ✗ Animal não encontrado.");
        } else {
            Animal animal = animalOpt.get();
            System.out.println("  Deixe em branco para manter o valor atual.\n");
            String nome = lerStringOpcional("Nome     [" + animal.getNome() + "]");
            String especie = lerStringOpcional("Espécie  [" + animal.getEspecie() + "]");
            String raca = lerStringOpcional("Raça     [" + animal.getRaca() + "]");
            String idadeStr = lerStringOpcional("Idade    [" + animal.getIdade() + "]");
            String tutorStr = lerStringOpcional("Tutor ID [" + (animal.getTutor() != null ? animal.getTutor().getId() : "—") + "]");

            int novaIdade = idadeStr.isBlank() ? -1 : Integer.parseInt(idadeStr);
            Long novoTutorId = tutorStr.isBlank() ? null : Long.parseLong(tutorStr);

            Animal novosDados = new Animal(null, nome, especie, raca, novaIdade, null);
            animalService.alterar(id, novosDados, novoTutorId);
            System.out.println("  ✓ Animal alterado com sucesso!");
        }
    }

    private void excluirAnimal() {
        Long id = lerLong("\nID do animal a excluir");
        System.out.print("  Confirma exclusão do animal ID " + id + "? (s/n): ");
        String confirmacao = sc.nextLine().trim();
        if (confirmacao.equalsIgnoreCase("s")) {
            animalService.excluir(id);
            System.out.println("  Animal excluído com sucesso.");
        } else {
            System.out.println("  Operação cancelada.");
        }
    }


    // GERENCIAMENTO DE CONSULTAS

    private void menuConsultas() {
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
            opcao = lerInt("Escolha uma opção");
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    incluirConsulta();
                    break;
                case 2:
                    listarConsultas();
                    break;
                case 3:
                    buscarConsultaPorId();
                    break;
                case 4:
                    listarConsultasPorAnimal();
                    break;
                case 5:
                    alterarConsulta();
                    break;
                case 6:
                    excluirConsulta();
                    break;
                default:
                    System.out.println("  ✗ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void incluirConsulta() {
        System.out.println("\n── Incluir Consulta ──");
        Long animalId = lerLong("ID do animal");
        String data = lerString("Data (dd/mm/aaaa)");
        String horario = lerString("Horário (hh:mm)");
        String veterinario = lerString("Nome do veterinário");
        String motivo = lerString("Motivo da consulta");
        String obs = lerStringOpcional("Observações (opcional)");

        Consulta novaConsulta = new Consulta(null, data, horario, veterinario, motivo, obs, null);
        try {
            consultaService.incluir(novaConsulta, animalId);
            System.out.println("   Consulta agendada com sucesso!");
        } catch (Exception e) {
            System.out.println("   Erro: " + e.getMessage());
        }
    }

    private void listarConsultas() {
        List<Consulta> lista = consultaService.listarTodas();
        if (lista.isEmpty()) {
            System.out.println("\n  Nenhuma consulta cadastrada.");
        } else {
            System.out.println("\n── Lista de Consultas (" + lista.size() + ") ──");
            lista.forEach(c -> System.out.printf("  ID: %d | Data: %s %s | Vet: %s | Motivo: %s | Pet: %s%n",
                    c.getId(), c.getData(), c.getHorario(), c.getVeterinario(), c.getMotivo(),
                    c.getAnimal() != null ? c.getAnimal().getNome() : "—"));
        }
    }

    private void buscarConsultaPorId() {
        Long id = lerLong("\nID da consulta");
        Optional<Consulta> consultaOpt = consultaService.buscarPorId(id);
        if (consultaOpt.isPresent()) {
            Consulta c = consultaOpt.get();
            System.out.printf("  ID: %d | Data: %s %s | Vet: %s | Motivo: %s | Obs: %s | Pet: %s%n",
                    c.getId(), c.getData(), c.getHorario(), c.getVeterinario(), c.getMotivo(), c.getObservacoes(),
                    c.getAnimal() != null ? c.getAnimal().getNome() : "—");
        } else {
            System.out.println("  Consulta com ID " + id + " não encontrada.");
        }
    }

    private void listarConsultasPorAnimal() {
        Long animalId = lerLong("\nID do animal");
        Optional<Animal> animalOpt = animalService.buscarPorId(animalId);
        if (animalOpt.isEmpty()) {
            System.out.println("  Animal não encontrado.");
        } else {
            Animal animal = animalOpt.get();
            List<Consulta> lista = consultaService.listarPorAnimal(animalId);
            if (lista.isEmpty()) {
                System.out.println("  Nenhuma consulta para " + animal.getNome() + ".");
            } else {
                System.out.println("\n── Consultas de " + animal.getNome() + " (" + lista.size() + ") ──");
                lista.forEach(c -> System.out.printf("  ID: %d | Data: %s àas %s | Vet: %s | Motivo: %s%n",
                        c.getId(), c.getData(), c.getHorario(), c.getVeterinario(), c.getMotivo()));
            }
        }
    }

    private void alterarConsulta() {
        Long id = lerLong("\nID da consulta a alterar");
        Optional<Consulta> consultaOpt = consultaService.buscarPorId(id);
        if (consultaOpt.isEmpty()) {
            System.out.println("  ✗ Consulta não encontrada.");
        } else {
            Consulta consulta = consultaOpt.get();
            System.out.println("  Deixe em branco para manter o valor atual.\n");
            String data = lerStringOpcional("Data       [" + consulta.getData() + "]");
            String horario = lerStringOpcional("Horário    [" + consulta.getHorario() + "]");
            String veterinario = lerStringOpcional("Veterinário[" + consulta.getVeterinario() + "]");
            String motivo = lerStringOpcional("Motivo     [" + consulta.getMotivo() + "]");
            String obs = lerStringOpcional("Observações[" + consulta.getObservacoes() + "]");

            Consulta novosDados = new Consulta(null, data, horario, veterinario, motivo, obs, null);
            consultaService.alterar(id, novosDados);
            System.out.println("  ✓ Consulta alterada com sucesso!");
        }
    }

    private void excluirConsulta() {
        Long id = lerLong("\nID da consulta a excluir");
        System.out.print("  Confirma exclusão da consulta ID " + id + "? (s/n): ");
        String confirmacao = sc.nextLine().trim();
        if (confirmacao.equalsIgnoreCase("s")) {
            consultaService.excluir(id);
            System.out.println("  Consulta excluída.");
        } else {
            System.out.println(" Operação cancelada.");
        }
    }


    // FUNÇÕES DE LEITURA E VALIDAÇÃO

    private String lerString(String label) {
        System.out.print("  " + label + ": ");
        String valor = sc.nextLine().trim();
        while (valor.isBlank()) {
            System.out.print(" Campo obrigatório. " + label + ": ");
            valor = sc.nextLine().trim();
        }
        return valor;
    }

    private String lerStringOpcional(String label) {
        System.out.print("  " + label + ": ");
        return sc.nextLine().trim();
    }

    private int lerInt(String label) {
        while (true) {
            System.out.print("  " + label + ": ");
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(" Informe um número inteiro válido.");
            }
        }
    }

    private Long lerLong(String label) {
        while (true) {
            System.out.print("  " + label + ": ");
            try {
                return Long.parseLong(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Informe um ID numérico válido.");
            }
        }
    }
}