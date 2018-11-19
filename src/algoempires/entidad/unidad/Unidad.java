package algoempires.entidad.unidad;

import algoempires.entidad.Entidad;
import algoempires.tablero.Posicion;
import algoempires.tablero.PosicionInvalidaException;
import algoempires.tablero.Region;

import java.util.ArrayList;


public abstract class Unidad extends Entidad {

    private boolean movioEsteTurno;

    public Unidad() throws PosicionInvalidaException {
        super();
        this.movioEsteTurno = false;
    }

    public ArrayList<Posicion> generarRangoAPartirDePosicion(Posicion posicionRecibida) {
        return Region.generarRegionCentradaEn(posicionRecibida, this);
    }

    public int getRango() {
        return RANGO_VISION;
    }

}
