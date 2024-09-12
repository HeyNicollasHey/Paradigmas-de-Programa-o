produtos_disponiveis = [
    {"codigo": 1, "nome": "Arroz", "descricao": "Arroz branco", "valor": 5, "acrescimo": 0, "desconto": 0},
    {"codigo": 2, "nome": "Feijão", "descricao": "Feijão carioca", "valor": 10, "acrescimo": 0, "desconto": 0},
    {"codigo": 3, "nome": "Biscoito", "descricao": "Biscoito Bauducco", "valor": 4, "acrescimo": 0, "desconto": 0},
    {"codigo": 4, "nome": "Carne", "descricao": "Carne de Boi", "valor": 20, "acrescimo": 0, "desconto": 0},
    {"codigo": 5, "nome": "Aveia", "descricao": "Aveia em flocos", "valor": 8, "acrescimo": 0, "desconto": 0},
    {"codigo": 6, "nome": "Sabão", "descricao": "Sabão em Pó", "valor": 5, "acrescimo": 0, "desconto": 0}
]

carrinho = []

def adicionar_ao_carrinho(codigo_produto, produtos_disponiveis, carrinho):
    produto_encontrado = None
    for produto in produtos_disponiveis:
        if produto["codigo"] == codigo_produto:
            produto_encontrado = produto.copy()
            break

    if produto_encontrado:
        carrinho.append(produto_encontrado)
        print(f"Produto de código {codigo_produto} adicionado ao carrinho.")
    else:
        print("Esse produto não está disponível.")

def aplicar_desconto_total(carrinho, desconto_total):
    if len(carrinho) == 0:
        print("O carrinho está vazio. Não é possível aplicar desconto.")
        return

    desconto_por_produto = desconto_total / len(carrinho)
    for produto in carrinho:
        if desconto_por_produto > produto["valor"]:
            desconto_aplicado = produto["valor"]
            produto["valor"] = 0
        else:
            desconto_aplicado = desconto_por_produto
            produto["valor"] -= desconto_aplicado
        produto["desconto"] += desconto_aplicado

    print(f"Desconto total de {desconto_total} aplicado. Cada produto recebeu um desconto proporcional.")

def aplicar_desconto_produto(carrinho, codigo_produto, desconto):
    produto_encontrado = None
    for produto in carrinho:
        if produto["codigo"] == codigo_produto:
            produto_encontrado = produto
            break

    if produto_encontrado:
        if desconto > produto_encontrado["valor"]:
            desconto_aplicado = produto_encontrado["valor"]
            produto_encontrado["valor"] = 0
        else:
            desconto_aplicado = desconto
            produto_encontrado["valor"] -= desconto_aplicado
        produto_encontrado["desconto"] += desconto_aplicado
        print(f"Desconto de {desconto} aplicado ao produto de código {codigo_produto}. Valor atual: {produto_encontrado['valor']}")
    else:
        print(f"Produto de código {codigo_produto} não encontrado no carrinho.")

def aplicar_acrescimo_produto(carrinho, codigo_produto, acrescimo):
    produto_encontrado = None
    for produto in carrinho:
        if produto["codigo"] == codigo_produto:
            produto_encontrado = produto
            break

    if produto_encontrado:
        produto_encontrado["valor"] += acrescimo
        produto_encontrado["acrescimo"] += acrescimo
        print(f"Acréscimo de {acrescimo} aplicado ao produto de código {codigo_produto}. Valor atual: {produto_encontrado['valor']}")
    else:
        print(f"Produto de código {codigo_produto} não encontrado no carrinho.")

def aplicar_acrescimo_total(carrinho, acrescimo_total):
    if len(carrinho) == 0:
        print("O carrinho está vazio. Não é possível aplicar acréscimo.")
        return

    acrescimo_por_produto = acrescimo_total / len(carrinho)
    for produto in carrinho:
        produto["valor"] += acrescimo_por_produto
        produto["acrescimo"] += acrescimo_por_produto

    print(f"Acréscimo total de {acrescimo_total} aplicado. Cada produto recebeu um acréscimo proporcional.")

def calcular_valor_total(carrinho):
    valor_total = sum(produto["valor"] for produto in carrinho)
    return valor_total

def exibir_menu():
    while True:
        print("1 - Inserir item ao carrinho\n2 - Acréscimo de item\n3 - Desconto de item\n4 - Acréscimo total\n5 - Desconto total\n6 - Finalizar venda")
        opcao = input("Digite uma opção: ")

        if opcao == "1":
            codigo = int(input("Digite o código do produto a ser adicionado: "))
            adicionar_ao_carrinho(codigo, produtos_disponiveis, carrinho)
            print("\nItens no carrinho:")
            for item in carrinho:
                print(f"{item['nome']} - Valor: {item['valor']}")

        elif opcao == "2":
            codigo = int(input("Digite o código do produto para acréscimo: "))
            acrescimo = float(input("Digite o valor do acréscimo: "))
            aplicar_acrescimo_produto(carrinho, codigo, acrescimo)

        elif opcao == "3":
            codigo = int(input("Digite o código do produto para desconto: "))
            desconto = float(input("Digite o valor do desconto: "))
            aplicar_desconto_produto(carrinho, codigo, desconto)

        elif opcao == "4":
            acrescimo_total = float(input("Digite o valor total de acréscimo a ser distribuído: "))
            aplicar_acrescimo_total(carrinho, acrescimo_total)

        elif opcao == "5":
            desconto_total = float(input("Digite o valor total de desconto a ser distribuído: "))
            aplicar_desconto_total(carrinho, desconto_total)

        elif opcao == "6":
            print("Venda finalizada.")
            valor_total = calcular_valor_total(carrinho)
            print(f"Valor total da compra: {valor_total}")
            break

        else:
            print("Opção inválida. Tente novamente.")

exibir_menu()
