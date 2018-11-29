package interfaz;

import algoempires.AlgoEmpires;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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


    AlgoEmpires crearJuego() {

        int tamH = Integer.parseInt(textoTamHorizontal.getText());
        int tamV = Integer.parseInt(textoTamVertical.getText());

        return new AlgoEmpires(tamH, tamV, textoJugador1.getText(), textoJugador2.getText());
    }

    void iniciarJuego(Stage stage, Scene escenaDePartida) {
        stage.setScene(escenaDePartida);
    }


}