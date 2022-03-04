package br.com.alura.bytebank.models.funcionario

import br.com.alura.bytebank.infra.Autenticavel
import br.com.alura.bytebank.infra.AuthException

abstract class FuncionarioAdmin(
    nome: String,
    cpf: String,
    salario: Double,
    protected val senha: Int
) : Funcionario(
    nome = nome,
    cpf = cpf,
    salario = salario
), Autenticavel {
    override fun autentica(senha: Int): Boolean = if (this.senha != senha) throw AuthException() else true;
}