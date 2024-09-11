from models.Carrinho import Carrinho
from models.Produtos import produtosDisponiveis

def Menu():
    carrinho = Carrinho()  # Inicializa o carrinho uma vez
    while True:
        print("1 - Inserir item ao carrinho\n2 - Acréscimo de item\n3 - Desconto de item\n4 - Acréscimo total\n5 - Desconto total\n6 - Finalizar venda")
        digito = input("Digite uma opcao: ")

        if digito == "1":
            codigo = int(input("Digite o código do produto a ser adicionado: "))
            carrinho.adicionarAoCarrinho(codigo, produtosDisponiveis)
            print("\nItens no carrinho:")
            for item in carrinho.itens:
                print(item)

        elif digito == "2":  # Acréscimo de item específico
            codigo = int(input("Digite o código do produto para acréscimo: "))
            acrescimo = float(input("Digite o valor do acréscimo: "))
            carrinho.acrescimoProdutoEspecifico(codigo, acrescimo)

        elif digito == "3":  # Desconto de item específico
            codigo = int(input("Digite o código do produto para desconto: "))
            desconto = float(input("Digite o valor do desconto: "))
            carrinho.descontoProdutoEspecifico(codigo, desconto)

        elif digito == "4":  # Acréscimo total
            acrescimoTotal = float(input("Digite o valor total de acréscimo a ser distribuído: "))
            carrinho.acrescimoProdutos(acrescimoTotal)

        elif digito == "5":  # Desconto total
            descontoTotal = float(input("Digite o valor total de desconto a ser distribuído: "))
            carrinho.descontoProdutos(descontoTotal)

        elif digito == "6":  # Finalizar venda
            print("Venda finalizada.")
            valor_total = carrinho.calcular_valor_total()
            print(f"Valor total da compra: {valor_total}")
            break  # Sai do loop e finaliza a função

        else:
            print("Opção inválida. Tente novamente.")
