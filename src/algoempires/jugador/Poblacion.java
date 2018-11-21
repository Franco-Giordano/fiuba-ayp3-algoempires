package algoempires.jugador;

import algoempires.entidad.unidad.Unidad;

import java.util.HashSet;

public class Poblacion {

    private HashSet<Unidad> unidades;
    private final int MAXIMO_POBLACION=50;

    public Poblacion(){

        unidades = new HashSet<>();
    }


    public void agregar(Unidad unidad) {

        if (this.cantidadDeUnidades() >= MAXIMO_POBLACION) {
            throw new SeIntentoSuperarPoblacionMaximaException();
        }

        unidades.add(unidad);
    }

    public void quitar(Unidad unidad) {
        unidades.remove(unidad);
    }

    public void actualizarUnidades() {
        unidades.forEach(unidad -> unidad.actualizarEntreTurnos());
    }

    public int cantidadDeUnidades() {
        return unidades.size();
    }
}
