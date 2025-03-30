package com.mycompany.projpoo23bim;
public class ContaPoupanca extends ContaBancaria {
    private int diaDeRendimento;
    private double taxaRendimento;

    public ContaPoupanca(String nome, int numeroConta, double saldo, int diaDeRendimento, double taxaRendimento) {
        super(nome, numeroConta, saldo);
        this.diaDeRendimento = diaDeRendimento;
        this.taxaRendimento = taxaRendimento;
    }

    public int getDiaDeRendimento() {
        return diaDeRendimento;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void aplicarRendimento() {
        saldo += saldo * (taxaRendimento / 100);
    }  
//TEM A MSSM COISA NA BANCARIA 
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
        return super.toString() + " | Dia de Rendimento: " + diaDeRendimento + " | Taxa de Rendimento: " + taxaRendimento + "%";
    }
}
