package algoempires.jugador;

import algoempires.InformadorDeExcepciones;
import algoempires.entidad.Atacante;
import algoempires.entidad.Entidad;
import algoempires.entidad.edificio.Castillo;
import algoempires.entidad.edificio.Cuartel;
import algoempires.entidad.edificio.Edificio;
import algoempires.entidad.edificio.PlazaCentral;
import algoempires.entidad.unidad.Unidad;
import algoempires.entidad.unidad.guerrero.Arquero;
import algoempires.entidad.unidad.guerrero.Espadachin;
import algoempires.entidad.unidad.guerrero.armadeasedio.ArmaDeAsedio;
import algoempires.entidad.unidad.utilero.Aldeano;
import algoempires.excepciones.*;
import algoempires.tablero.Posicion;
import algoempires.tablero.Terreno;
import algoempires.tablero.direccion.Direccion;
import javafx.scene.media.AudioClip;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class Jugador {

    private String nombre;
    private Poblacion poblacion;
    private HashSet<Edificio> edificiosPropios;
    private Terreno terrenoDeJuego;
    private Monedero monedero;
    private boolean castilloConVida;
    private InformadorDeExcepciones informanteDeExcepciones;

    public Jugador(String nombreRecibido, Terreno terrenoDeJuego) {
        this.nombre = nombreRecibido;
        this.poblacion = new Poblacion();
        this.edificiosPropios = new HashSet<>();
        this.terrenoDeJuego = terrenoDeJuego;
        this.monedero = new Monedero();
        this.castilloConVida = true;
        this.informanteDeExcepciones = new InformadorDeExcepciones();
    }

    private void lanzarExcepcionSiNoEsDeMiPropiedad(Posicion posicionRecibida) {

        Entidad entidadAInteractuar = terrenoDeJuego.obtenerEntidadEnPosicion(posicionRecibida);

        this.lanzarExcepcionSiNoEsDeMiPropiedad(entidadAInteractuar);
    }

    private void lanzarExcepcionSiNoEsDeMiPropiedad(Entidad entidad) {


        if (!entidad.esDelEquipo(this)) {
            NoSePuedeInteractuarConEntidadesEnemigasException e = new NoSePuedeInteractuarConEntidadesEnemigasException("Se intento emitir una orden a una entidad enemiga");
            informanteDeExcepciones.informar(e);
            throw e;
        }

    }

    private void lanzarExcepcionSiPosicionFueraDeRango(Edificio edificio, Posicion posicion) {
        if (!terrenoDeJuego.puedeEdificioVerA(edificio, posicion)) {
            throw new PosicionFueraDeRangoException();
        }
    }

    private void lanzarExcepcionSiNoSePuedeReparar(Aldeano aldeano, Posicion posAReparar) {
        if (terrenoDeJuego.obtenerEntidadEnPosicion(posAReparar) == null) {
            throw new SoloSePuedeRepararEdificiosException();
        }
    }

    public void lanzarExcepcionSiPosicionFueraDeRango(Aldeano aldeano, Posicion posAConstruir) {

        if (!terrenoDeJuego.puedeUnidadVerA(aldeano, posAConstruir)) {
            throw new PosicionFueraDeRangoException();
        }
    }

    public void lanzarExcepcionSiPosicionFueraDeRango(Aldeano aldeano, ArrayList<Posicion> posiciones) {

        if (!terrenoDeJuego.puedeUnidadVerA(aldeano, posiciones)) {
            throw new PosicionFueraDeRangoException();
        }
    }

    public void moverUnidad(Posicion posicionRecibida, Direccion direccionRecibida) {

        try {

            this.lanzarExcepcionSiNoEsDeMiPropiedad(posicionRecibida);

            terrenoDeJuego.moverUnidad(posicionRecibida, direccionRecibida);

            reproducirSonido("src/interfaz/recursos/sonidos/sonidoCaminar.mp3");
        } catch (PosicionInvalidaException | SoloUnidadesSePuedenDesplazarException | UnidadNoPuedeMoverseException |
                NoSePuedeInteractuarConEntidadesEnemigasException e) {
            informanteDeExcepciones.informar(e);
        }
    }


    public void crearAldeano(PlazaCentral plazaCentral, Posicion posicionDeCreacion) {


        try {

            this.lanzarExcepcionSiNoEsDeMiPropiedad(plazaCentral);

            this.lanzarExcepcionSiPosicionFueraDeRango(plazaCentral, posicionDeCreacion);

            Aldeano aldeanoCreado = plazaCentral.crearAldeano();

            terrenoDeJuego.ocupar(posicionDeCreacion, aldeanoCreado);

            reproducirSonido("src/interfaz/recursos/sonidos/sonidoSuccess.mp3");
        } catch (SeIntentoSuperarPoblacionMaximaException | OroInsuficienteException |
                NoSePuedeInteractuarConEntidadesEnemigasException | EdificioNoFuncionalException |
                PosicionFueraDeRangoException e) {
            informanteDeExcepciones.informar(e);
        }

    }

    public void crearEspadachin(Cuartel cuartel, Posicion posicionDeCreacion) {

        try {

            this.lanzarExcepcionSiNoEsDeMiPropiedad(cuartel);

            this.lanzarExcepcionSiPosicionFueraDeRango(cuartel, posicionDeCreacion);

            Espadachin espadachinCreado = cuartel.crearEspadachin();

            terrenoDeJuego.ocupar(posicionDeCreacion, espadachinCreado);

            reproducirSonido("src/interfaz/recursos/sonidos/sonidoSuccess.mp3");
        } catch (SeIntentoSuperarPoblacionMaximaException | OroInsuficienteException |
                NoSePuedeInteractuarConEntidadesEnemigasException | EdificioNoFuncionalException |
                PosicionFueraDeRangoException e) {
            informanteDeExcepciones.informar(e);
        }

    }

    public void crearArquero(Cuartel cuartel, Posicion posicionDeCreacion) {


        try {
            this.lanzarExcepcionSiNoEsDeMiPropiedad(cuartel);

            this.lanzarExcepcionSiPosicionFueraDeRango(cuartel, posicionDeCreacion);

            Arquero arqueroCreado = cuartel.crearArquero();

            terrenoDeJuego.ocupar(posicionDeCreacion, arqueroCreado);

            reproducirSonido("src/interfaz/recursos/sonidos/sonidoSuccess.mp3");

        } catch (SeIntentoSuperarPoblacionMaximaException | OroInsuficienteException |
                NoSePuedeInteractuarConEntidadesEnemigasException | EdificioNoFuncionalException |
                PosicionFueraDeRangoException e) {
            informanteDeExcepciones.informar(e);
        }
    }

    public void crearArmaDeAsedio(Castillo castillo, Posicion posicionDeCreacion) {


        try {
            this.lanzarExcepcionSiNoEsDeMiPropiedad(castillo);

            this.lanzarExcepcionSiPosicionFueraDeRango(castillo, posicionDeCreacion);
            ArmaDeAsedio armaDeAsedio = castillo.crearArmaDeAsedio();

            terrenoDeJuego.ocupar(posicionDeCreacion, armaDeAsedio);

            reproducirSonido("src/interfaz/recursos/sonidos/sonidoSuccess.mp3");
        } catch (SeIntentoSuperarPoblacionMaximaException | OroInsuficienteException |
                NoSePuedeInteractuarConEntidadesEnemigasException | EdificioNoFuncionalException |
                PosicionFueraDeRangoException e) {
            informanteDeExcepciones.informar(e);
        }
    }

    public void reparar(Aldeano aldeano, Posicion posAReparar) {

        try {

            this.lanzarExcepcionSiNoEsDeMiPropiedad(aldeano);

            this.lanzarExcepcionSiPosicionFueraDeRango(aldeano, posAReparar);

            this.lanzarExcepcionSiNoSePuedeReparar(aldeano, posAReparar);

            Edificio edificio = (Edificio) terrenoDeJuego.obtenerEntidadEnPosicion(posAReparar);

            aldeano.ordenarReparacion(edificio);

            reproducirSonido("src/interfaz/recursos/sonidos/sonidoReparacion.mp3");

        } catch (SoloUnAldeanoReparaALaVezException | NoSePuedeInteractuarConEntidadesEnemigasException
                | PosicionFueraDeRangoException | SoloSePuedeRepararEdificiosException e) {

            informanteDeExcepciones.informar(e);
        }
    }


    public void crearPlazaCentral(Aldeano aldeano, Posicion posAConstruir) {

        PlazaCentral plazaCentral = new PlazaCentral();
        ArrayList<Posicion> posiciones = plazaCentral.calcularPosicionesAOcupar(posAConstruir);


        try {
            this.lanzarExcepcionSiPosicionFueraDeRango(aldeano, posiciones);

            terrenoDeJuego.todasLasPosicionesEstanDisponibles(posiciones);

            this.lanzarExcepcionSiNoEsDeMiPropiedad(aldeano);

            PlazaCentral plaza = aldeano.construirPlazaCentral();

            terrenoDeJuego.ocupar(posAConstruir, plaza);

            reproducirSonido("src/interfaz/recursos/sonidos/sonidoReparacion.mp3");

        } catch (PosicionInvalidaException | OroInsuficienteException | NoSePuedeInteractuarConEntidadesEnemigasException |
                AldeanoOcupadoException | AlMenosUnCasilleroEstaOcupadoException | PosicionFueraDeRangoException e) {
            informanteDeExcepciones.informar(e);
        }

    }

    public void crearCuartel(Aldeano aldeano, Posicion posAConstruir) {

        Cuartel cuartelGenerico = new Cuartel();
        ArrayList<Posicion> posiciones = cuartelGenerico.calcularPosicionesAOcupar(posAConstruir);

        try {

            this.lanzarExcepcionSiPosicionFueraDeRango(aldeano, posiciones);

            terrenoDeJuego.todasLasPosicionesEstanDisponibles(posiciones);

            this.lanzarExcepcionSiNoEsDeMiPropiedad(aldeano);

            Cuartel cuartel = aldeano.construirCuartel();

            terrenoDeJuego.ocupar(posAConstruir, cuartel);

            reproducirSonido("src/interfaz/recursos/sonidos/sonidoReparacion.mp3");


        } catch (PosicionInvalidaException | OroInsuficienteException | NoSePuedeInteractuarConEntidadesEnemigasException |
                AldeanoOcupadoException | AlMenosUnCasilleroEstaOcupadoException | PosicionFueraDeRangoException e) {
            informanteDeExcepciones.informar(e);
        }

    }


    public boolean atacar(Atacante atacante, Posicion posicionDeLaVictima) {

        try {

            if (!atacante.puedeVerA(terrenoDeJuego, posicionDeLaVictima)) {
                throw new EntidadFueraDeRangoException();
            }

            this.lanzarExcepcionSiNoEsDeMiPropiedad((Entidad) atacante);

            terrenoDeJuego.obtenerEntidadEnPosicion(posicionDeLaVictima).recibirAtaqueDe(atacante);

            String rutaSonido;

            if (atacante.getClass() == Arquero.class) {
                rutaSonido = ("src/interfaz/recursos/sonidos/sonidoArco.mp3");
            } else if (atacante.getClass() == Espadachin.class) {
                rutaSonido = ("src/interfaz/recursos/sonidos/sonidoEspada.mp3");
            } else {
                rutaSonido = ("src/interfaz/recursos/sonidos/sonidoCatapulta.mp3");
            }

            reproducirSonido(rutaSonido);

            return true;

        } catch (EntidadFueraDeRangoException | NoSePuedeInteractuarConEntidadesEnemigasException | GuerreroYaAtacoEsteTurnoException
                | ArmaDeAsedioNoPuedeAtacarSinEstarMontadaException | ArmaDeAsedioNoPuedeAtacarUnidadesException e) {
            informanteDeExcepciones.informar(e);

            return false;
        }

    }

    public void desmontarArmaDeAsedio(ArmaDeAsedio armaDeAsedio) {

        this.lanzarExcepcionSiNoEsDeMiPropiedad(armaDeAsedio);

        armaDeAsedio.desmontar();
    }

    public void montarArmaDeAsedio(ArmaDeAsedio armaDeAsedio) {

        this.lanzarExcepcionSiNoEsDeMiPropiedad(armaDeAsedio);

        armaDeAsedio.montar();
    }

    public void agregar(Edificio edificio) {

        edificiosPropios.add(edificio);
    }

    public void agregar(Unidad unidad) {

        poblacion.agregar(unidad);
    }

    public void jugarTurno() {

        this.actualizarEntreTurnos();
    }

    public void actualizarEntreTurnos() {

        poblacion.actualizarUnidades();
        edificiosPropios.forEach((e) -> e.actualizarEntreTurnos());
    }


    public void sumarOro(int oro) {

        monedero.sumarOro(oro);
    }

    public void cobrar(int costo) {

        this.monedero.restarOro(costo);
    }

    public HashSet<Entidad> calcularCercanosA(Castillo castillo) {

        return terrenoDeJuego.calcularCercanosA(castillo);
    }

    public void posicionarLaEntidad(Posicion posicion, Edificio entidad) {

        terrenoDeJuego.ocupar(posicion, entidad);
    }

    public void posicionarLaEntidad(Posicion posicion, Unidad entidad) {

        terrenoDeJuego.ocupar(posicion, entidad);
    }

    public String getNombre() {
        return nombre;
    }

    public int getOro() {
        return monedero.getOro();
    }

    public boolean tieneCastilloEn(Posicion posicion) {
        return terrenoDeJuego.obtenerEntidadEnPosicion(posicion).getClass() == Castillo.class;
    }

    public boolean tienePlazaCentralEn(Posicion posicion) {
        return terrenoDeJuego.obtenerEntidadEnPosicion(posicion).getClass() == PlazaCentral.class;
    }

    public boolean tieneAldeanoEn(Posicion posicion) {
        return terrenoDeJuego.obtenerEntidadEnPosicion(posicion).getClass() == Aldeano.class;
    }

    public boolean tieneEstaCantidadDePropiedades(int cantPobladores, int cantEdificios) {
        return poblacion.getCantidadDeHabitantes() == cantPobladores && edificiosPropios.size() == cantEdificios;
    }

    public int getCantidadDeHabitantes() {

        return poblacion.getCantidadDeHabitantes();
    }


    public boolean tieneCastilloConVida() {

        return castilloConVida;
    }

    public void informarDestruccionDeCastillo() {

        this.castilloConVida = false;
    }

    public InformadorDeExcepciones getInformanteDeExcepciones() {
        return this.informanteDeExcepciones;

    }

    public void murio(Unidad unidad) {
        poblacion.quitar(unidad);
        reproducirSonido("src/interfaz/recursos/sonidos/sonidoMuerte.mp3");
    }

    public void murio(Edificio edificio) {
        reproducirSonido("src/interfaz/recursos/sonidos/sonidoMuerteEdificio.mp3");
    }

    public void reproducirSonido(String ruta) {
        AudioClip sonidoAReproducir = new AudioClip(Paths.get(ruta).toUri().toString());
        sonidoAReproducir.play();
    }
}
