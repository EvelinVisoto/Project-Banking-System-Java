package com.mycompany.projpoo23bim;
public class ContaBancaria {
    protected String nome;
    protected int numeroConta;
    protected double saldo;

    public ContaBancaria(String nome, int numeroConta, double saldo) {
        this.nome = nome;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public boolean transferirPIX(double valor, ContaBancaria destino) {
        if (valor <= saldo) {
            saldo -= valor;
            destino.depositar(valor);
            return true;
        } else {
            System.out.println("Saldo insuficiente para transferência.");
            return false;
        }
    }

    public String toString() {
        return "Conta: " + numeroConta + " | Nome: " + nome + " | Saldo: " + saldo;
    }
}
