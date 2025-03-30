package com.mycompany.projpoo23bim;
import java.util.Scanner;

public class Contas {
    public static Scanner scanner = new Scanner(System.in);
    public static ContaBancaria[] contas = new ContaBancaria[100];
    private static int numeroContas = 0;

    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=====================================");
            System.out.println("          MENU PRINCIPAL         ");
            System.out.println("=====================================");
            System.out.println("1. Criar Conta Bancária");
            System.out.println("2. Criar Conta Poupança");
            System.out.println("3. Criar Conta Especial");
            System.out.println("4. Criar Conta Salário");
            System.out.println("5. Sacar de Conta");
            System.out.println("6. Depósito");
            System.out.println("7. Transferir via PIX");
            System.out.println("8. Aplicar Rendimento na Conta Poupança");
            System.out.println("9. Mostrar Informações das Contas");
            System.out.println("0. Sair");
            int opcao = getIntInput("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    criarContaBancaria();
                    break;
                case 2:
                    criarContaPoupanca();
                    break;
                case 3:
                    criarContaEspecial();
                    break;
                case 4:
                    criarContaSalario();
                    break;
                case 5:
                    sacarDeConta();
                    break;
                case 6:
                    deposito();
                    break;
                case 7:
                    transferirPIX();
                    break;
                case 8:
                    aplicarRendimento();
                    break;
                case 9:
                    mostrarInformacoes();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("OPS! Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void criarContaBancaria() {
        String nome = getNonEmptyString("Nome do Cliente: ");
        int numeroConta = getIntInput("Número da Conta: ");
        double saldoInicial = getDoubleInput("Saldo Inicial: ");
        ContaBancaria contaBancaria = new ContaBancaria(nome, numeroConta, saldoInicial);
        adicionarConta(contaBancaria);
        System.out.println("Conta Bancária criada com sucesso!");
    }

    private static void criarContaPoupanca() {
        String nome = getNonEmptyString("Nome do Cliente: ");
        int numeroConta = getIntInput("Número da Conta: ");
        double saldoInicial = getDoubleInput("Saldo Inicial: ");
        int diaRendimento = getIntInput("Dia de Rendimento: ");
        double taxaRendimento = getDoubleInput("Taxa de rendimento (%): ");
        ContaPoupanca contaPoupanca = new ContaPoupanca(nome, numeroConta, saldoInicial, diaRendimento, taxaRendimento);
        adicionarConta(contaPoupanca);
        System.out.println("Conta Poupança criada com sucesso!");
    }

    private static void criarContaEspecial() {
        String nome = getNonEmptyString("Nome do Cliente: ");
        int numeroConta = getIntInput("Número da Conta: ");
        double saldoInicial = getDoubleInput("Saldo Inicial: ");
        double limite = getDoubleInput("Limite: ");
        ContaEspecial contaEspecial = new ContaEspecial(nome, numeroConta, saldoInicial, limite);
        adicionarConta(contaEspecial);
        System.out.println("Conta Especial criada com sucesso!");
    }

    private static void criarContaSalario() {
        String nome = getNonEmptyString("Nome do Cliente: ");
        int numeroConta = getIntInput("Número da Conta: ");
        double saldoInicial = getDoubleInput("Saldo Inicial: ");
        ContaSalario contaSalario = new ContaSalario(nome, numeroConta, saldoInicial);
        adicionarConta(contaSalario);
        System.out.println("Conta Salário criada com sucesso!");
    }

    private static void adicionarConta(ContaBancaria conta) {
        if (numeroContas < contas.length) {
            contas[numeroContas++] = conta;
        } else {
            System.out.println("Não é possível adicionar mais contas. Limite alcançado.");
        }
    }

    private static void sacarDeConta() {
        int numeroConta = getIntInput("Número da Conta para saque: ");
        ContaBancaria conta = encontrarContaPorNumero(numeroConta);
        if (conta != null) {
            double valorSaque = getDoubleInput("Valor para saque: ");
            conta.sacar(valorSaque);
            System.out.println("Saldo após saque: " + conta.getSaldo());
        } else {
            System.out.println("Conta inválida.");
        }
    }

    private static void deposito() {
        int numeroConta = getIntInput("Número da Conta para depósito: ");
        ContaBancaria conta = encontrarContaPorNumero(numeroConta);
        if (conta != null) {
            double valorDeposito = getDoubleInput("Valor para depósito: ");
            conta.depositar(valorDeposito);
            System.out.println("Saldo após depósito: " + conta.getSaldo());
        } else {
            System.out.println("Conta inválida.");
        }
    }

    private static void transferirPIX() {
        int numeroContaOrigem = getIntInput("Número da Conta de ORIGEM para transferência via PIX: ");
        ContaBancaria origem = encontrarContaPorNumero(numeroContaOrigem);
        if (origem != null) {
            int numeroContaDestino = getIntInput("Número da Conta de DESTINO para transferência via PIX: ");
            ContaBancaria destino = encontrarContaPorNumero(numeroContaDestino);
            if (destino != null) {
                double valorPIX = getDoubleInput("Valor para transferência via PIX: ");
                if (origem.transferirPIX(valorPIX, destino)) {
                    System.out.println("Transferência via PIX realizada com sucesso!");
                } else {
                    System.out.println("Falha na transferência via PIX.");
                }
            } else {
                System.out.println("Conta de destino inválida.");
            }
        } else {
            System.out.println("Conta de origem inválida.");
        }
    }

    private static void aplicarRendimento() {
        int numeroConta = getIntInput("Número da Conta Poupança para aplicar rendimento: ");
        ContaBancaria conta = encontrarContaPorNumero(numeroConta);
        if (conta instanceof ContaPoupanca) {
            ((ContaPoupanca) conta).aplicarRendimento();
            System.out.println("Rendimento aplicado. Novo saldo: " + conta.getSaldo());
        } else {
            System.out.println("Conta inválida ou não é uma Conta Poupança.");
        }
    }

    private static void mostrarInformacoes() {
        for (ContaBancaria conta : contas) {
            if (conta != null) {
                System.out.println(conta);
            }
        }
    }

    private static ContaBancaria encontrarContaPorNumero(int numeroConta) {
        for (ContaBancaria conta : contas) {
            if (conta != null && conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    private static String getNonEmptyString(String mensagem) {
        String input;
        do {
            System.out.print(mensagem);
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    private static int getIntInput(String mensagem) {
        int valor;
        while (true) {
            try {
                System.out.print(mensagem);
                valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private static double getDoubleInput(String mensagem) {
        double valor;
        while (true) {
            try {
                System.out.print(mensagem);
                valor = Double.parseDouble(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número decimal.");
            }
        }
    }
}
