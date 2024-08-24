// autor: Paulo Sérgio Ferreira de Sousa
// Objetivo: Desenvolva um programa que receba o nome do livro, o número total de ​páginas e o número de páginas lidas.
//           O programa deve calcular e retornar a progressão de leitura em ​porcentagem, junto com o nome do livro.
fun main() {
    println("Calcule seu progresso de leitura.")

    print("Nome do livro: ")
    val livroNome = readLine();

    var paginaTotal1:String? = ""
    var paginaTotal2:Double = 0.0
    print("Páginas total: ")
    paginaTotal1 = readLine()
    if (paginaTotal1 != null) {
        paginaTotal2 = paginaTotal1.toDouble()
    }

    var leituraPercentual1:Double = 0.0
    var leituraPercentual2:String = ""
    var paginaAtual1:String = ""
    var paginaAtual2:Double = 0.0
    var mensagem1 = ""
    var mensagem2 = ""

    while (paginaAtual1.lowercase() != "sair") {
        mensagem1=""
        mensagem2=""
        print("Página atual ou 'sair': ")
        paginaAtual1 = readLine().toString()
        try {
            paginaAtual2 = paginaAtual1.toDouble()
            if (paginaAtual2 > paginaTotal2 || paginaAtual2 <= 0) {
                throw Exception()
            }
            leituraPercentual1 = (paginaAtual2 / paginaTotal2) * 100
            leituraPercentual2 = String.format("%.2f", leituraPercentual1)
            mensagem1 = "Você leu ${paginaAtual1} de ${paginaTotal1} páginas do livro ${livroNome}, referente a ${leituraPercentual2}% "
            when {
                leituraPercentual1 in 1.00..20.00 -> mensagem2 = "O inicio da leitura é como conhecer uma nova amizade. Continue!"
                leituraPercentual1 in 21.00..40.00 -> mensagem2 = "As próximas páginas guardam surpresas. Você será surpreendido!"
                leituraPercentual1 in 41.00..60.00 -> mensagem2 = "Você já tem conteúdos para observações e criticas. Anote!"
                leituraPercentual1 in 61.00..80.00 -> mensagem2 = "Suas interpretações são importantes. Contribua!"
                leituraPercentual1 in 81.00..99.00 -> mensagem2 = "Você está chegando a conclusão da leitura. Parabéns!"
                leituraPercentual1 in 99.01..100.00 -> mensagem2 = "Você concluiu a leitura. Sucesso!"
            }
        } catch (e: Exception) {
            if (paginaAtual2 > paginaTotal2 || paginaAtual2 <= 0) {
                mensagem1 = "Digite uma numeração entre ${1} e ${paginaTotal1}."
            }
            if (paginaAtual1.lowercase() == "sair") {
                mensagem1 = "Programa encerrado."
            }
        }
        println(mensagem1)
        if (mensagem2 != "") {
            println(mensagem2)
        }
    }
}