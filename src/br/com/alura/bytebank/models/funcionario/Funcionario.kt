package br.com.alura.bytebank.models.funcionario

abstract class Funcionario(
    val nome: String,
    val cpf: String,
    val salario: Double
){
    abstract val bonificacao: Double


}