package interfaz.controller.tareas;

import algoempires.entidad.edificio.Castillo;
import algoempires.jugador.Jugador;
import algoempires.tablero.Casillero;
import algoempires.tablero.Posicion;
import interfaz.view.CasilleroView;

public class TareaCrearArmaDeAsedio implements Tarea {
    private Jugador jugadorActual;
    private Casillero casilleroEmisor;

    public TareaCrearArmaDeAsedio(Jugador jugadorActual, Casillero casilleroEmisor) {
        this.jugadorActual = jugadorActual;
        this.casilleroEmisor = casilleroEmisor;
    }

    @Override
    public void ejecutar(Posicion posicionACrear) {
        jugadorActual.crearArmaDeAsedio((Castillo) casilleroEmisor.getEntidadContenida(), posicionACrear);
    }

    @Override
    public void realizarTareasOpcionales(CasilleroView casilleroView) {

    }
}
