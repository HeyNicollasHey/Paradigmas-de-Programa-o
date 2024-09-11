class Produtos:

    def __init__(self, nome, descricao, valor):
        self.nome = nome
        self.descricao = descricao
        self.valor = valor
        self.acrescimo = 0
        self.desconto = 0

    def __repr__(self):
        return f"Produtos(nome={self.nome}, descicao={self.descricao}, valor={self.valor}"


produtosDisponiveis = [
    Produtos("Arroz", "Arroz branco", 5),
    Produtos("Feijao", "Feijao carioca", 10),
    Produtos("Biscoito", "Biscoito Bauducco", 4),
    Produtos("Carne", "Carne de Boi", 20),
    Produtos("Aveia", "Aveia em flocos", 8),
    Produtos("Sabao", "Sabão em Pó", 5)
]
