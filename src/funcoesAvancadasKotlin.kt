import br.com.alura.bytebank.infra.Autenticavel
import br.com.alura.bytebank.infra.SistemaInterno
import br.com.alura.bytebank.models.cliente.Cliente
import br.com.alura.bytebank.models.cliente.Endereco
import br.com.alura.bytebank.models.conta.Conta
import br.com.alura.bytebank.models.conta.ContaPoupanca
import java.util.*
import kotlin.random.Random

fun testafuncoesanonimasLambdaEHighorder(){
    val testeClass = Teste()
    testeClass()

    val testeFunction = ::teste
    testeFunction()

    val funcaoAnonima: () -> Unit = fun() {
        println("Função anônima")
    }

    val funcaoLambda: (Int, Int) -> Int = { a, b ->
        println("Executa como lambda")
        a + b // --> Retorna ultima linha
    }

    val calculaBonificacaoLambda: (salario: Double) -> Double = lambda@{ salario ->
        if(salario > 1000.0) { return@lambda salario + 50.0; }
        salario + 100.0
    }

    listOf(1, 2, 3).highOrderFunction(1, Int::times).also { println(it) }
    listOf(1, 2, 3).highOrderFunction(0) { acc, i -> acc + i }.also { println(it) }

    Endereco(logradouro = "Rua Vergueiro", numero = 308).let {
        "${it.logradouro}, ${it.numero}".uppercase()
    }.let(::println) // --> Printa o retorno do lambda

    Endereco(logradouro = "Rua Vergueiro", numero = 308).run { // Acesso direto como THIS
        "$logradouro, $numero".uppercase()
    }.let(::println) // --> Printa o retorno do lambda

    Endereco()
        .also{
            println("Criando endereço!")
        }
        .apply {
            logradouro = "Rua Vergueiro"
            numero = 308
            estado = "Minas Gerais"
            cidade = "Contagem"
            bairro = "Eldorado"
            complemento = "A"
            cep = "3102021-301"
    }.let(::println) // -> Printa o Endereco.toString()

    with(Endereco()){
        logradouro = "Rua Vergueiro"
        numero = 308
        estado = "Minas Gerais"
        cidade = "Contagem"
        bairro = "Eldorado"
        complemento = "A"
        cep = "3102021-301"
        toString()
    }.let(::println)


    val autenticavel = object : Autenticavel {
        val senha = 1234

        override fun autentica(senha: Int) = this.senha == senha
    }

    SistemaInterno().entra(autenticavel, 1234, autenticado = {
        println("Após autenticação correta")
    })

    val randomInt: () -> Int = {
        Random.nextInt(100).also {
            println("Valor gerado $it")
        }
    }

    val evenOrNull = randomInt().takeIf { it % 2 == 0 }.also { println("Número Par: $it") }
    val oddOrNull = randomInt().takeUnless { it % 2 == 0 }.also { println("Número Ímpar: $it") }

    val conta : Conta = Cliente(nome = "Alex", cpf = "111.111.111-11", senha = 1234)
        .let { ContaPoupanca(it, 1000) }
        .apply {
            deposita(1000.0)
            println(saldo)
        }

    val taxaAnual = 0.05
    val taxaMensal = taxaAnual / 12

    run {
        var saldo = conta.saldo
        repeat(12) {
            saldo += saldo * taxaMensal
        }
        saldo
    }.also { println("Rendimento anual: $it") }

    somaReceiver(1, 5){
        println(this)
    }
}

class Teste {
    operator fun invoke() {
        println("executa invoke do Teste")
    }
}

fun teste(){}

fun <T, R> Collection<T>.highOrderFunction(
    initial: R,
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

fun somaReceiver(a: Int, b: Int, resultado: Int.() -> Unit){
    val total = a + b
    total.resultado()
}