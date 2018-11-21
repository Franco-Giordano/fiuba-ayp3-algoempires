package algoempires.jugador;

import algoempires.entidad.edificio.Castillo;
import algoempires.entidad.edificio.Cuartel;
import algoempires.entidad.edificio.Edificio;
import algoempires.entidad.edificio.PlazaCentral;
import algoempires.entidad.unidad.SoloUnidadesSePuedenDesplazarException;
import algoempires.entidad.unidad.Unidad;
import algoempires.entidad.unidad.guerrero.ArmaDeAsedio;
import algoempires.entidad.unidad.guerrero.Arquero;
import algoempires.entidad.unidad.guerrero.Espadachin;
import algoempires.entidad.unidad.guerrero.Guerrero;
import algoempires.entidad.unidad.utilero.Aldeano;
import algoempires.tablero.Posicion;
import algoempires.tablero.PosicionInvalidaException;
import algoempires.tablero.Terreno;
import algoempires.tablero.direccion.Direccion;

import java.util.HashSet;

public class Jugador {

    private Poblacion poblacion;
    private HashSet<Edificio> edificiosPropios;
    private Terreno terrenoDeJuego;
    //private Posicion posicionSeleccionada; // en teoría esto va a servir cuando implementemos la interfaz.
    private Jugador jugadorContrincante;
    private Monedero monedero;

    public Jugador(Terreno terrenoDeJuego) {
        this.poblacion = new Poblacion();
        this.edificiosPropios = new HashSet<>();
        this.terrenoDeJuego = terrenoDeJuego;
        this.monedero = new Monedero();
    }

    public void setContrincante(Jugador jugador) {
        jugadorContrincante = jugador;
    }

    //TODO de momento se asume que TODAS las posiciones de entidades recibidas son propietarias de este jugador, habra que chequearlo

    public void moverUnidad(Posicion posicionRecibida, Direccion direccionRecibida) {

        try {
            terrenoDeJuego.moverUnidad(posicionRecibida, direccionRecibida);
        } catch (PosicionInvalidaException | SoloUnidadesSePuedenDesplazarException e) {
            //TODO Avisar que fallo la operacion mediante el Controlador
        }
    }

    public void crearAldeano(PlazaCentral plazaCentral, Posicion posicionDeCreacion) {

        terrenoDeJuego.puedeEdificioVerA(plazaCentral, posicionDeCreacion);

        Aldeano aldeanoCreado = plazaCentral.crearAldeano();

        try {
            aldeanoCreado.cobrar(monedero);

            poblacion.agregar(aldeanoCreado);

            terrenoDeJuego.ocupar(posicionDeCreacion, aldeanoCreado);
        } catch (SeIntentoSuperarPoblacionMaximaException | OroInsuficienteException e) {
            //TODO Avisar que fallo la operacion mediante el Controlador
        }

    }

    //TODO esto IGUAL al crearAldeano, ver forma de juntarlos
    public void crearEspadachin(Cuartel cuartel, Posicion posicionDeCreacion) {

        terrenoDeJuego.puedeEdificioVerA(cuartel, posicionDeCreacion);

        Espadachin espadachinCreado = cuartel.crearEspadachin();

        try {
            espadachinCreado.cobrar(monedero);

            poblacion.agregar(espadachinCreado);
            terrenoDeJuego.ocupar(posicionDeCreacion, espadachinCreado);
        } catch (SeIntentoSuperarPoblacionMaximaException | OroInsuficienteException e) {
            //TODO Avisar que fallo la operacion mediante el Controlador
        }

    }

    //TODO IDEM
    public void crearArquero(Cuartel cuartel, Posicion posicionDeCreacion) {

        terrenoDeJuego.puedeEdificioVerA(cuartel, posicionDeCreacion);

        Arquero arqueroCreado = cuartel.crearArquero();

        try {
            arqueroCreado.cobrar(monedero);

            poblacion.agregar(arqueroCreado);
            terrenoDeJuego.ocupar(posicionDeCreacion, arqueroCreado);
        } catch (SeIntentoSuperarPoblacionMaximaException | OroInsuficienteException e) {
            //TODO Avisar que fallo la operacion mediante el Controlador
        }
    }

    //TODO AAAAAAAAAAAAAAAAAAAAA
    public void crearArmaDeAsedio(Castillo castillo, Posicion posicionDeCreacion) {

        terrenoDeJuego.puedeEdificioVerA(castillo, posicionDeCreacion);

        ArmaDeAsedio armaDeAsedio = castillo.crearArmaDeAsedio();

        try {
            armaDeAsedio.cobrar(monedero);

            poblacion.agregar(armaDeAsedio);
            terrenoDeJuego.ocupar(posicionDeCreacion, armaDeAsedio);
        } catch (SeIntentoSuperarPoblacionMaximaException | OroInsuficienteException e) {
            //TODO Avisar que fallo la operacion mediante el Controlador
        }
    }

    public Jugador jugarTurnoYDevolverSiguienteJugador() {

        //El controlador/view ejecuta las elecciones que hace el jugador

        this.actualizarEntreTurnos();

        return jugadorContrincante;
    }

    private void actualizarEntreTurnos() {
        poblacion.actualizarUnidades();
        edificiosPropios.forEach((e) -> e.actualizarEntreTurnos());
    }

    public void atacar(Guerrero miGuerrero, Edificio victima) {
        miGuerrero.atacar(victima);
    }

    public void atacar(Guerrero miGuerrero, Unidad victima) {
        miGuerrero.atacar(victima);
    }

    public void sumarOro(int oro) {
        monedero.sumarOro(oro);
    }

    public void informarDestruccion(Edificio entidad) {
        edificiosPropios.remove(entidad);
        terrenoDeJuego.remover(entidad);
    }

    public void informarDestruccion(Unidad entidad) {
        poblacion.quitar(entidad);
        terrenoDeJuego.remover(entidad);
    }

    //METODO DE TESTEO
    public int getOro() {
        return monedero.getOro();
    }
}
