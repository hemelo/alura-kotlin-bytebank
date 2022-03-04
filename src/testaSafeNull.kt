import br.com.alura.bytebank.models.cliente.Endereco

fun testaSafeNull() {
    var endereco: Endereco? = Endereco(logradouro = "Rua Nascimento")

    println(endereco?.logradouro?.length)

    endereco?.let { endereco: Endereco ->
        println(endereco.logradouro?.length ?: 0 )
    }
}