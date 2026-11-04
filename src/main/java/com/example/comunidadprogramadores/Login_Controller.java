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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class Login_Controller {

    //Inicializacion de variables
    @FXML
    TextField idUsuarioLogin;

    @FXML
    TextField idContrasenhaLogin;

    public void login(ActionEvent actionEvent) throws IOException {

        //Variables
        String nombre = idUsuarioLogin.getText();
        String contrasenha = idContrasenhaLogin.getText();

        //Creo la alerta
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.getDialogPane().setMaxHeight(50);
        alert.getDialogPane().setMaxWidth(3000);

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
        if(contrasenha.equals(contrasenha.toLowerCase(Locale.ROOT))){
            alert.setContentText("La contraseña es incorrecta. Debe contener al menos una letra mayúscula");
            alert.showAndWait();
            return;
        }
        if(contrasenha.equals(contrasenha.toUpperCase(Locale.ROOT))){
            alert.setContentText("La contraseña es incorrecta. Debe contener al menos una letra minúscula");
            alert.showAndWait();
            return;
        }


        if(ProgramadoresDAO.existeUsuario(nombre)){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inicio.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 450);
            Stage stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();

            stage.setTitle("Comunidad de programadores");
            stage.setScene(scene);
            stage.show();
        }else{
            alert.setContentText("El usuario introducido aún no existe en la bd, debes registrarte primero");
            alert.showAndWait();
            return;
        }

    }

    public void registrarse(ActionEvent actionEvent) {
    }
}
