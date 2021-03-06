package interfaz.controller.botoneras;


import algoempires.jugador.Jugador;
import algoempires.tablero.Casillero;
import algoempires.tablero.direccion.*;
import interfaz.controller.VistaPartidaController;
import interfaz.controller.tareas.*;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class BotoneraUnidadController implements BotoneraController {

    private Casillero casillero;
    private Jugador jugadorActual;
    private VistaPartidaController vistaController;

    public void setCasillero(Casillero casillero) {
        this.casillero = casillero;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public void moverArriba(MouseEvent mouseEvent) {
        Direccion direccion = new DireccionArriba();
        jugadorActual.moverUnidad(casillero.getPosicion(), direccion);

        vistaController.crearCasilleros();
    }

    public void moverIzquierda(MouseEvent mouseEvent) {

        Direccion direccion = new DireccionIzquierda();
        jugadorActual.moverUnidad(casillero.getPosicion(), direccion);

        vistaController.crearCasilleros();
    }

    public void moverDerecha(MouseEvent mouseEvent) {

        Direccion direccion = new DireccionDerecha();
        jugadorActual.moverUnidad(casillero.getPosicion(), direccion);

        vistaController.crearCasilleros();
    }

    public void moverAbajo(MouseEvent mouseEvent) {


        Direccion direccion = new DireccionAbajo();
        jugadorActual.moverUnidad(casillero.getPosicion(), direccion);

        vistaController.crearCasilleros();

    }

    public void moverArribaIzquierda(MouseEvent mouseEvent) {

        Direccion direccion = new DireccionArribaIzquierda();
        jugadorActual.moverUnidad(casillero.getPosicion(), direccion);

        vistaController.crearCasilleros();

    }

    public void moverArribaDerecha(MouseEvent mouseEvent) {

        Direccion direccion = new DireccionArribaDerecha();
        jugadorActual.moverUnidad(casillero.getPosicion(), direccion);

        vistaController.crearCasilleros();

    }

    public void moverAbajoIzquierda(MouseEvent mouseEvent) {

        Direccion direccion = new DireccionAbajoIzquierda();
        jugadorActual.moverUnidad(casillero.getPosicion(), direccion);

        vistaController.crearCasilleros();

    }

    public void moverAbajoDerecha(MouseEvent mouseEvent) {

        Direccion direccion = new DireccionAbajoDerecha();
        jugadorActual.moverUnidad(casillero.getPosicion(), direccion);

        vistaController.crearCasilleros();

    }

    public void construirPlaza() {

        Tarea tareaConstruirPlaza = new TareaConstruirPlazaCentral(jugadorActual, casillero);

        vistaController.setHandlersEnEsperaConTarea(tareaConstruirPlaza);

    }


    public void setVistaController(VistaPartidaController vistaController) {
        this.vistaController = vistaController;

    }

    public void construirCuartel(ActionEvent actionEvent) {

        Tarea tareaConstruirCuartel = new TareaConstruirCuartel(jugadorActual, casillero);

        vistaController.setHandlersEnEsperaConTarea(tareaConstruirCuartel);
    }


    public void atacar(ActionEvent actionEvent) {

        Tarea tareaAtacar = new TareaAtacar(jugadorActual, casillero);

        vistaController.setHandlersEnEsperaConTarea(tareaAtacar);
    }

    public void reparar(ActionEvent actionEvent) {

        Tarea tareaReparar = new TareaRepararEdificio(jugadorActual, casillero);

        vistaController.setHandlersEnEsperaConTarea(tareaReparar);
    }

}
