package algoempires;

import interfaz.VistaPartidaController;

public class InformadorDeExcepciones {

    private VistaPartidaController vistaPartidaController;

    public void setVistaPartidaController(VistaPartidaController vista) {
        this.vistaPartidaController = vista;
    }

    public void informar(RuntimeException e) {
        if (vistaPartidaController != null) {
            vistaPartidaController.mostrarMensaje(e.getMessage());
        }
    }
}
