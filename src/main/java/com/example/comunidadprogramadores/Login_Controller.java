package com.example.comunidadprogramadores;

import DAO.ProgramadoresDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class Login_Controller {

    //Inicializacion de variables
    @FXML
    TextField idUsuarioLogin;

    @FXML
    TextField idContrasenhaLogin;

    //Funcion que se ejecuta al presionar el boton de login
    public void login(ActionEvent actionEvent) throws IOException {

        //Variables
        String nombre = idUsuarioLogin.getText();
        String contrasenha = idContrasenhaLogin.getText();

        //Creo la alerta
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");

        //Aplico un tamaño a la alerta
        alert.getDialogPane().setPrefWidth(450);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(450);

        //Compruebo los requisitos de nombre
        if(nombre.isEmpty()){
            alert.setContentText("El campo nombre no puede estar vacío");
            alert.showAndWait();
            return;
        }
        if(nombre.length() < 5){
            alert.setContentText("La longitud de nombre no es válida. Mínimo 5 caracteres");
            alert.showAndWait();
            return;
        }

        //Compruebo los requisitos de contrasenha
        if (contrasenha.isEmpty()){
            alert.setContentText("El campo contrasenha no puede estar vacío");
            alert.showAndWait();
            return;
        }
        if(contrasenha.length() < 8){
            alert.setContentText("La longitud de contrasenha no es válida. Mínimo 8 caracteres");
            alert.showAndWait();
            return;
        }
        if(contrasenha.equals(contrasenha.toLowerCase())){
            alert.setContentText("La contraseña es incorrecta. Debe contener al menos una letra mayúscula");
            alert.showAndWait();
            return;
        }
        if(contrasenha.equals(contrasenha.toUpperCase())){
            alert.setContentText("La contraseña es incorrecta. Debe contener al menos una letra minúscula");
            alert.showAndWait();
            return;
        }

        //Compruebo que el usuario que intenta loguearse está registrado en la bd
        if(ProgramadoresDAO.UsuarioValido(nombre, contrasenha)){
            //Abro la ventana de inicio
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inicio.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 450);

            //Le paso a Inicio el usuario que ha iniciado sesión
            Inicio_Controller ic = fxmlLoader.getController();
            ic.recibirDatos(nombre);

            Stage stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();

            stage.setTitle("Comunidad de programadores");
            stage.setScene(scene);
            stage.show();
        }else{
            //Muestro un mensaje de error
            alert.setContentText("Usuario y/o contraseña incorrectos. Vuelva a intentarlo");
            alert.showAndWait();
        }
    }

    //Funcion que se ejecuta al presionar el boton de registrarse
    public void registrarse(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        Stage stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();

        stage.setTitle("Registro de usuario");
        stage.setScene(scene);
        stage.show();
    }
}
