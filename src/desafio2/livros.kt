package desafio2

var campos = arrayOf("              nome: ","            gênero: ","             autor: ",
    "    páginas: total: ","    páginas: lidas: ", "páginas: progresso:")
var tipos = arrayOf("String","String","String","Integer","Integer","Double")

fun livrosCadastro(livros:MutableList<List<Any>>) {
    var vetorLivro = mutableListOf<Any>()
    println("  Cadastro de livro  ")
    println("=====================")
    var digitacao1 = ""
    var digitacao2:Any = ""

    var campo = ""
    var sair = false
    var contador = 0
    var mensagem = ""
    var orientacao = ""
    var paginasTotal:Int = 0
    var paginasLidas:Int = 0
    var progresso:Double = 0.0

    while (sair == false) {
        campo = campos[contador]
        mensagem = "${campo.trimEnd()} ${orientacao}"
        if (campo.trim() == "páginas: progresso:") {
            progresso = ((paginasLidas.toDouble() / paginasTotal.toDouble()) * 100) //.toDouble()
            print("${mensagem} ${String.format("%.2f", progresso)}%")
            vetorLivro.add(progresso)
        } else {
            print("${mensagem} ")
            digitacao1 = readLine().toString()
        }
        if (tipos[contador].lowercase().trim() == "integer") {
            try {
                digitacao2 = digitacao1.toInt()
                if (campo.trim() == "páginas: total:") {
                    if (digitacao2 < 0) {
                        throw Exception("Digite números inteiros positivos:")
                    } else if (digitacao2 == 0) {
                        throw Exception("O livro precisa de páginas acima de 0 (zero):")
                    }
                }
                if (campo.trim() == "páginas: lidas:") {
                    if (digitacao2 < 0) {
                        throw Exception("Digite números inteiros positivos:")
                    }
                    if (digitacao2 > paginasTotal) {
                        throw Exception("Permitido: 0 até ${paginasTotal}:")
                    }
                }
                vetorLivro.add(digitacao2)
                orientacao = ""
                contador++
                if (campo.trim() == "páginas: total:") {
                    paginasTotal = digitacao2
                }
                if (campo.trim() == "páginas: lidas:") {
                    paginasLidas = digitacao2
                }
            } catch (e: NumberFormatException) {
                digitacao2 = 0
                orientacao = "Somente número inteiro positivos são permitidos:"
            } catch (e: Exception) {
                digitacao2 = 0
                orientacao = e.message.toString()
            }
        } else if (tipos[contador].lowercase().trim() == "string") {
            digitacao2 = digitacao1
            vetorLivro.add(digitacao2)
            contador++
        } else if (tipos[contador].lowercase().trim() == "double") {
            contador++
        }
        if (contador == campos.size) {sair = true}
    }
    println("vetorLivro.size: ${vetorLivro.size}")
    println("campos.size: ${campos.size}")
    if (vetorLivro.size == campos.size) {
        livros.add(vetorLivro)
    }
}

fun livrosConsulta(livros:MutableList<List<Any>>) {
    println("  Consulta de livro  ")
    println("=====================")
    var pesquisa1 = ""
    var pesquisa2:Any = ""
    var valor:Any = ""
    var item:ArrayList<*>
    var mensagens = mutableListOf<List<Any>>()
    var colunasPesquisa = intArrayOf(0,1,2)
    var camposEncontrados = ""
    var encontrados = 0

    while (pesquisa1.lowercase().trim() != "sair") {
        limparTela()
        mensagens.clear()
        println("Digite um conteúdo para ser encontrado nos livros cadastrados:")
        println("Campos de pesquisa: 'nome', 'gênero' e 'autor'.")
        println("Total de livros: ${livros.size}")
        print("Digite a pesquisa ou 'sair' para voltar: ")
        pesquisa1 = readLine().toString()
        if (pesquisa1.lowercase().trim() == "sair" || pesquisa1 == "") {
            continue
        }
        mensagens.add(mutableListOf(-1,-1,"Resultado da pesquisa de '${pesquisa1}':"))
        for (tipo in tipos) {
            for (linha in livros.indices) {
                if (tipo.lowercase().trim() == "integer") {
                    try {
                        pesquisa2 = pesquisa1.toInt()
                    } catch (e: Exception) {
                        pesquisa2 = 0
                    }
                } else if (tipo.lowercase().trim() == "string") {
                    pesquisa2 = pesquisa1.lowercase().trim()
                }
                for (coluna in livros[linha].indices) {
                    if (!colunasPesquisa.contains(coluna)) {
                        continue
                    }
                    if (tipo.lowercase().trim() == "string") {
                        valor = livros[linha][coluna].toString().lowercase().trim()
                        if (valor is String && valor.contains(pesquisa2.toString())) {
                            if (mensagens.none { it[0] == linha }) {
                                mensagens.add(mutableListOf(linha,coluna,livros[linha]))
                            }
                        }
                    } else if (tipo.lowercase().trim() == "integer") {
                        valor = livros[linha][coluna]
                        if (valor is Integer && valor == pesquisa2) {
                            if (mensagens.none { it[0] == linha }) {
                                mensagens.add(mutableListOf(linha,coluna,livros[linha]))
                            }
                        }
                    }
                }
            }
        }
        if (mensagens.size == 1) {
            mensagens.add(mutableListOf(-1,-1,"       Conteúdo não encontrado."))
        }
        encontrados = 0
        for (mensagem in mensagens) {
            for (subitem in mensagem) {
                camposEncontrados = ""
                if (subitem is ArrayList<*>) {
                    for (coluna in subitem.indices) {
                        if (!colunasPesquisa.contains(coluna)) {
                            continue
                        }
                        valor = subitem[coluna]
                        if (tipos[coluna].lowercase().trim() == "string") {
                            pesquisa2 = pesquisa1.lowercase().trim()
                            valor = valor.toString().lowercase().trim()
                            if (valor.contains(pesquisa2)) {
                                camposEncontrados += campos[coluna].replace(":","").trim() + ", "
                            }
                        }
                    }
                }
            }
            if (camposEncontrados != "") {
                item = mensagem[2] as ArrayList<*>
                if (encontrados == 0) {
                    println("%-35s %-20s %-30s %-10s %-10s %-10s".format("Livro", "Gênero", "Autor", "Páginas", "Lidas", "Percentual"))
                    println("-".repeat(120))
                }
                if (item.size > 4) {
                    println("%-35s %-20s %-30s %-10d %-10d %-10s".format(item[0], item[1], item[2], item[3], item[4], String.format("%.2f", item[5])+"%"))
                }
                camposEncontrados = camposEncontrados.trim().substring(0, camposEncontrados.length-2)
                if (camposEncontrados.split(",").size > 1) {
                    println("       campos onde foram encontrados a pesquisa: ${camposEncontrados}")
                } else {
                    println("       campo onde foi encontrado a pesquisa: ${camposEncontrados}")
                }
                encontrados++
            } else {
                println("${mensagem[2]}")
            }
        }
        if (encontrados > 0) {
            println("-".repeat(120))
            println("${encontrados} ${if (encontrados==1) "registro encontrado." else "registros encontrados."}")
        }
        println("Pressione uma tecla para continuar.")
        readLine()
    }
}