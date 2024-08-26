/*
autor: Paulo Sérgio Ferreira de Sousa
objetivo:  Desenvolva um programa que permita o cadastro de diversos livros com as seguintes informações:
           Nome do livro, Gênero, Autor, Número total de páginas, ​Número de páginas lidas.

           O programa deve possibilitar a consulta de livros por nome, gênero e autor,
           além de acompanhar a progressão de leitura de ​cada livro.

           Entradas: Nome do livro, Gênero, ​Autor, Número total de ​páginas, Número de ​páginas lidas
           Saídas: Nome do livro, Número total de ​páginas, Número de páginas ​lidas, Classificação por gênero, ​Autor
*/
package desafio2

fun main() {
    var opcao = ""
    var vetorLivros = mutableListOf<List<Any>>()
    // Nome do livro, Gênero, Autor, Número total de páginas, ​Número de páginas lidas.
    vetorLivros = mutableListOf(
        mutableListOf("Os sofrimentos do jovem Werther","Romance","Johann Wolfgang Von Goethe", 200, 57, 28.50),
        mutableListOf("A Lebre e a Tartaruga","Fábula","Ana Oom", 32, 23, 71.87),
        mutableListOf("Madame Bovary","Romance","Gustave Flaubert", 368, 107, 29.07),
        mutableListOf("Dom Casmurro","Romance","Machado de Assis",192, 31, 16.14),
        mutableListOf("A raposa e a cegonha","Fábula","Ciranda Cultural", 16, 15, 93.75),
        mutableListOf("Macunaíma","Romance","Mario de Andrade", 192, 49, 25.52),
        mutableListOf("A raposa e as uvas","Fábula","Ciranda Cultural", 12, 12, 100.00),
        mutableListOf("Ulisses","Romance","James Joyce", 1074, 635, 59.12),
        mutableListOf("1984","Romance","George Orwell", 336, 88, 26.19),
        mutableListOf("O Rato e o Leão","Fábula","Ana Oom", 32, 30, 93.75)
    )

    while (opcao.lowercase().trim() != "3") {
        println("Biblioteca com controle de leitura.")
        println("1) Cadastro de livros")
        println("2) Consulta de livros")
        println("3) sair")
        print("Digite uma opção: ")

        opcao = readLine().toString()
        limparTela()
        if (opcao == "1") {
            livrosCadastro(vetorLivros)
        } else if (opcao == "2") {
            livrosConsulta(vetorLivros)
        }
        limparTela()
    }
}

fun limparTela() {
    repeat(5) {
        println(" ".repeat(80))
    }
}
