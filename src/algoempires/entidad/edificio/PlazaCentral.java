package algoempires.entidad.edificio;

import algoempires.entidad.unidad.utilero.Aldeano;

public class PlazaCentral extends Edificio {

	private static int vidaDeLaPlaza=450;
	private static int costoDeLaPlaza=100;
	private static int turnosDeConstruccion=4;
	private static int tamanio=4; 
	
	public PlazaCentral() {	
		super(vidaDeLaPlaza, costoDeLaPlaza, turnosDeConstruccion, tamanio);
	}

}