public class Pedido {
    //ID_Cliente (FK)
    public int codPedido;
    public String dataPedido;
    public double valorTotal;
    public String statusPedido;
}


// * ItemPedido
//Atributos: ID_ItemPedido (PK), ID_Pedido (FK), ID_Livro (FK), Quantidade.