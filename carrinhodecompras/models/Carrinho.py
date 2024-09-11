from models.Produtos import Produtos

class Carrinho:
    def __init__(self):
        self.itens = []

    def adicionarAoCarrinho(self, produto_codigo, produtosDisponiveis):
        produto_encontrado = None
        for produto in produtosDisponiveis:
            if produto.codigo == produto_codigo:
                produto_encontrado = produto
                break

        if produto_encontrado:
            self.itens.append(produto_encontrado)
            print(f"Produto de código: {produto_codigo} adicionado ao carrinho.")
        else:
            print("Esse produto não está disponível.")

    def descontoProdutos(self, descontoTotal):
        if len(self.itens) == 0:
            print("O carrinho está vazio. Não é possível aplicar desconto.")
            return

        desconto_por_produto = descontoTotal / len(self.itens)
        for produto in self.itens:
            if desconto_por_produto > produto.valor:
                desconto_aplicado = produto.valor
                produto.valor = 0
            else:
                desconto_aplicado = desconto_por_produto
                produto.valor -= desconto_aplicado

            produto.desconto += desconto_aplicado


        print(f"Desconto total de {descontoTotal} aplicado. Cada produto recebeu um desconto proporcional.")

    def descontoProdutoEspecifico(self, produto_codigo, desconto):
        produto_encontrado = None
        for produto in self.itens:
            if produto.codigo == produto_codigo:
                produto_encontrado = produto
                break

        if produto_encontrado:
            produto_encontrado.valor -= desconto
            produto_encontrado.desconto += desconto
            print(f"Desconto de {desconto} aplicado ao produto de código: {produto_codigo}. Valor atual: {produto_encontrado.valor}")
        else:
            print(f"Produto de código: {produto_codigo} não encontrado no carrinho.")

    def acrescimoProdutoEspecifico(self, produto_codigo, acrescimo):
        produto_encontrado = None
        for produto in self.itens:
            if produto.codigo == produto_codigo:
                produto_encontrado = produto
                break

        if produto_encontrado:
            produto_encontrado.valor -= acrescimo
            produto_encontrado.acrescimo += acrescimo
            print(f"Desconto de {acrescimo} aplicado ao produto de código: {produto_codigo}. Valor atual: {produto_encontrado.valor}")
        else:
            print(f"Produto de código: {produto_codigo} não encontrado no carrinho.")

    def acrescimoProdutos(self, acrescimoTotal):
        if len(self.itens) == 0:
            print("O carrinho está vazio. Não é possível aplicar desconto.")
            return

        acrescimo_por_produto = acrescimoTotal / len(self.itens)
        for produto in self.itens:
            if acrescimo_por_produto > produto.valor:
                desconto_aplicado = produto.valor
                produto.valor = 0
            else:
                acrescimo_aplicado = acrescimo_por_produto
                produto.valor += acrescimo_aplicado

            produto.acrescimo += acrescimo_aplicado

        print(f"Acrescimo total de {acrescimoTotal} aplicado. Cada produto recebeu um desconto proporcional.")

    def calcular_valor_total(self):
        valor_total = 0
        for produto in self.itens:
            valor_total += produto.valor
        return valor_total
