package pedido;

public class PedidoEstadoRealizado implements PedidoEstado {

    private PedidoEstadoRealizado() {};
    private static PedidoEstadoRealizado instance = new PedidoEstadoRealizado();
    public static PedidoEstadoRealizado getInstance() {
        return instance;
    }

    public String getNomeEstado() {
        return "Realizado";
    }

}
