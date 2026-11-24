package pedido;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void deveArmazenarEstados() {
        Pedido pedido = new Pedido();
        pedido.setEstado(PedidoEstadoRealizado.getInstance());
        pedido.setEstado(PedidoEstadoPago.getInstance());
        assertEquals(2, pedido.getEstados().size());
    }

    @Test
    void deveRetornarEstadoInicial() {
        Pedido pedido = new Pedido();
        pedido.setEstado(PedidoEstadoRealizado.getInstance());
        pedido.setEstado(PedidoEstadoPago.getInstance());
        pedido.restauraEstado(0);
        assertEquals(PedidoEstadoRealizado.getInstance(), pedido.getEstado());
    }

    @Test
    void deveRetornarEstadoAnterior() {
        Pedido pedido = new Pedido();
        pedido.setEstado(PedidoEstadoRealizado.getInstance());
        pedido.setEstado(PedidoEstadoPago.getInstance());
        pedido.setEstado(PedidoEstadoEnviado.getInstance());
        pedido.setEstado(PedidoEstadoDevolvido.getInstance());
        pedido.restauraEstado(2);
        assertEquals(PedidoEstadoEnviado.getInstance(), pedido.getEstado());
    }

    @Test
    void deveRetornarExcecaoIndiceInvalido() {
        try {
            Pedido pedido = new Pedido();
            pedido.restauraEstado(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Índice inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoIndiceNegativo() {
        Pedido pedido = new Pedido();
        pedido.setEstado(PedidoEstadoRealizado.getInstance());
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> pedido.restauraEstado(-1));
        assertEquals("Índice inválido", e.getMessage());
    }

    @Test
    void deveRetornarExcecaoIndiceAcimaDoUltimo() {
        Pedido pedido = new Pedido();
        pedido.setEstado(PedidoEstadoRealizado.getInstance());
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> pedido.restauraEstado(2));
        assertEquals("Índice inválido", e.getMessage());
    }

    @Test
    void deveRestaurarUltimoEstadoNoLimite() {
        Pedido pedido = new Pedido();
        pedido.setEstado(PedidoEstadoRealizado.getInstance());
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        pedido.restauraEstado(1);
        assertEquals("Entregue", pedido.getEstado().getNomeEstado());
    }

}
