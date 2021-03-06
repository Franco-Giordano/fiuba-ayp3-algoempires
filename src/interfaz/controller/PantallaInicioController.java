package interfaz.controller;

import algoempires.AlgoEmpires;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PantallaInicioController {

    @FXML
    private TextField textoJugador1;

    @FXML
    private TextField textoJugador2;

    @FXML
    private Button botonSalir;

    @FXML
    private TextField textoTamHorizontal;

    @FXML
    private TextField textoTamVertical;
    @FXML
    private Button botonInicio;

    public Button getBotonInicio() {
        return botonInicio;
    }

    @FXML
    void salirHandler(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }


    public AlgoEmpires crearModelo() {

        String tamH = textoTamHorizontal.getText();
        String tamV = textoTamVertical.getText();

        if (!tamH.equals("") && !tamV.equals(""))
            return new AlgoEmpires(Integer.parseInt(tamH), Integer.parseInt(tamV), textoJugador1.getText(), textoJugador2.getText());

        else
            return new AlgoEmpires(20, 20, textoJugador1.getText(), textoJugador2.getText());
    }

}