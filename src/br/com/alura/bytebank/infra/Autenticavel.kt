package br.com.alura.bytebank.infra

interface Autenticavel {
    fun autentica(senha: Int): Boolean
}

class AuthException(message: String = "Ocorreu um erro de autenticação"): Exception(message)