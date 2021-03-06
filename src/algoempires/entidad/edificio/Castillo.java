package algoempires.entidad.edificio;

import algoempires.entidad.Atacante;
import algoempires.entidad.Entidad;
import algoempires.entidad.unidad.Unidad;
import algoempires.entidad.unidad.guerrero.armadeasedio.ArmaDeAsedio;
import algoempires.jugador.Jugador;
import algoempires.tablero.Posicion;
import algoempires.tablero.Terreno;

import java.util.HashSet;

public class Castillo extends Edificio implements Atacante {

    private final int VIDA_MAXIMA = 450;
    private final int COSTO = 0;
    private final int DANIO_A_TODO = 20;
    private final int RANGO_CONSTRUCCION = 1;

    private HashSet<Entidad> cercanos = new HashSet<>();

    public Castillo(Jugador jugador) {
        super(jugador);

        TAMANIO_HORIZONTAL = 4;
        TAMANIO_VERTICAL = 4;
        VELOCIDAD_DE_REPARACION = 15;
        RANGO_VISION = 3;
        this.terminarConstruccion();
    }

    @Override
    public void restarVida(int vidaARestar) {
        super.restarVida(vidaARestar);
        if (getVida() <= 0) {
            jugadorPropietario.informarDestruccionDeCastillo();
        }
    }

    @Override
    protected int getTurnosDeConstruccionInicial() {
        return 0;
    }

    @Override
    protected int getVidaInicial() {
        return VIDA_MAXIMA;
    }

    @Override
    protected int getCosto() {
        return COSTO;
    }

    @Override
    public boolean esDelEquipo(Jugador jugador) {
        return jugador == jugadorPropietario;
    }

    @Override
    public int getVidaMaxima() {
        return VIDA_MAXIMA;
    }

    @Override
    public void actualizarEntreTurnos() {
        cercanos = jugadorPropietario.calcularCercanosA(this);
        this.atacarCercanos();
    }

    public int getRangoContruccion(){
        return RANGO_CONSTRUCCION;
    }

    public ArmaDeAsedio crearArmaDeAsedio() {
        this.esFuncional();
        return new ArmaDeAsedio(jugadorPropietario);
    }

    public void atacar(Unidad unidad) {
        unidad.restarVida(DANIO_A_TODO);
    }

    public void atacar(Edificio edificio) {
        edificio.restarVida(DANIO_A_TODO);
    }

    public boolean puedeVerA(Terreno terreno, Posicion posicionDeLaVictima) {
        return terreno.puedeEdificioVerA(this, posicionDeLaVictima);
    }

    private void atacarCercanos() {

        for (Entidad entidad : cercanos) {
            entidad.recibirAtaqueDe(this);

        }
    }

    public void informarDeAtaque() {
        cercanos = jugadorPropietario.calcularCercanosA(this);
        if (cercanos.size() > 0) {

            jugadorPropietario.reproducirSonido("src/interfaz/recursos/sonidos/sonidoCastillo.mp3");

            for (Entidad entidad : cercanos) {
                jugadorPropietario.informarAtaque(entidad);
            }
        }
    }
}
