package algoempires.entidad;

import algoempires.jugador.Jugador;

public abstract class Entidad {

    protected int vida;

    public Entidad() {
        this.vida = getVidaInicial();
    }

    protected abstract int getVidaInicial();

    public abstract void actualizarEntreTurnos(Jugador jugador);

    //Metodo de testeo
    public boolean tieneEstaVida(int vidaRecibida) {
        return this.vida == vidaRecibida;
    }

}
