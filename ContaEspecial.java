package com.mycompany.projpoo23bim;
public class ContaEspecial extends ContaBancaria {
    private double limite;

    public ContaEspecial(String nome, int numeroConta, double saldo, double limite) {
        super(nome, numeroConta, saldo);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
    
    public void sacar(double valor) {
        if (valor <= saldo + limite) {
            if (valor <= saldo) {
                saldo -= valor;
            } else {
                limite -= (valor - saldo);
                saldo = 0;
            }
        } else {
            System.out.println("Valor excede o limite disponível.");
        }
    }

    public String toString() {
        return super.toString() + " | Limite: " + limite;
    }
}
