package br.com.alura.bytebank.models.cliente

import br.com.alura.bytebank.infra.Autenticavel
import br.com.alura.bytebank.infra.AuthException

class Cliente(
    var nome: String,
    val cpf: String,
    val endereco: Endereco = Endereco(),
    private val senha: Int
) : Autenticavel {

    override fun autentica(senha: Int): Boolean = if (this.senha != senha) throw AuthException() else true;
}



