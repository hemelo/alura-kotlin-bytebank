package br.com.alura.bytebank.infra

import br.com.alura.bytebank.infra.Autenticavel
import br.com.alura.bytebank.models.funcionario.Funcionario
import br.com.alura.bytebank.models.funcionario.FuncionarioAdmin

class SistemaInterno {

    fun entra(admin: Autenticavel, senha: Int, autenticado: SistemaInterno.() -> Unit = {}) {
        if (admin.autentica(senha)) {
            println("Bem vindo ao Bytebank")
            autenticado()
        } else {
            println("Falha na autenticação")
        }

        when (admin) {
            is FuncionarioAdmin -> {
                println("Opções admin")
            }
            is Funcionario -> {
                println("Opções comum")
            }
            else -> {
                println("Opções reduzidas")
            }
        }
    }

}