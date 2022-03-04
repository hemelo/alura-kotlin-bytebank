package br.com.alura.bytebank.models.conta

import br.com.alura.bytebank.models.cliente.Cliente
import br.com.alura.bytebank.infra.Autenticavel

abstract class Conta(
    val titular: Cliente,
    val numero: Int
) : Autenticavel by titular{
    var saldo = 0.0
        protected set

    companion object Contador {
        var totalContas = 0
            private set;
    }

    init{
        totalContas++
    }

    fun deposita(valor: Double) = if (valor < 0) throw DepositoNegativoException() else this.saldo += valor

    abstract fun saca(valor: Double)

    fun transfere(valor: Double, destino: Conta) {
        if (saldo < valor) throw SaldoInsuficienteException(message = "O saldo é insuficiente, saldo atual: $saldo")
        if( valor < 0 )  throw DepositoNegativoException(message = "Está tentando transferir um valor inválido")

        saldo -= valor
        destino.deposita(valor)
    }
}

class SaldoInsuficienteException(message: String = "O saldo é insuficiente") : Exception(message)

class DepositoNegativoException(message: String = "O deposito que está tentando fazer é inválido") : Exception(message)