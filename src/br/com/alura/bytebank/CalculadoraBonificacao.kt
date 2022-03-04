package br.com.alura.bytebank

import br.com.alura.bytebank.models.funcionario.Funcionario

class CalculadoraBonificacao {

    var total: Double = 0.0
        private set

    fun registra(funcionario: Funcionario){
        this.total += funcionario.bonificacao
    }

}