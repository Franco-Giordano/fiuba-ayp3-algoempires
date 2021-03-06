package algoempires;

import algoempires.excepciones.OroInsuficienteException;
import algoempires.jugador.Monedero;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MonederoTest {

    private Monedero monedero;

    @Before
    public void inicializar() {
        monedero = new Monedero();
    }

    @Test
    public void testMonederoSeCreaVacio() {

        assertEquals(100, monedero.getOro());
    }

    @Test
    public void testMonederoSumaOro() {

        monedero.sumarOro(10);

        assertEquals(110, monedero.getOro());

    }

    @Test
    public void testNoSePuedeSumarOroNegativo() {
        monedero.sumarOro(-3);

        assertEquals(monedero.getOro(), 100);
    }

    @Test
    public void testRestaOroCorrectamente() {
        monedero.sumarOro(10);
        monedero.restarOro(6);

        assertEquals(monedero.getOro(), 104);
    }

    @Test(expected = OroInsuficienteException.class)
    public void testNoSePuedeRestarOroNegativo() {
        monedero.sumarOro(10);
        monedero.restarOro(115);

    }

    @Test(expected = OroInsuficienteException.class)
    public void testNoSePuedeDebitarMasDeLoPoseido() {
        monedero.sumarOro(100);
        monedero.restarOro(354);

    }
}
