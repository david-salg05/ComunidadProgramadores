package com.example.comunidadprogramadores;

import DAO.ProgramadoresDAO;
import Entidades.Programadores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class Registro_Controller {

    //Inicializacion de variables
    @FXML
    TextField regUsu;

    @FXML
    TextField regContr;

    @FXML
    ComboBox <String> comboBox;

    @FXML
    RadioButton hombre, mujer, pnd;

    @FXML
    public void initialize(){
        comboBox.getItems().addAll("Java", "Python", "JavaScript");
    }

    public void registrarUsuario(ActionEvent actionEvent) throws IOException {
        //Variables
        String nombre = regUsu.getText();
        String contrasenha = regContr.getText();
        String sexo = "", lenguaje = "";

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

        //Compruebo que algún radioButton haya sido seleccionado y guardo la elección
        if(hombre.isSelected()){
            sexo = "hombre";
        } else if(mujer.isSelected()){
            sexo = "mujer";
        }else if(pnd.isSelected()){
            sexo = "prefiero no decirlo";
        }else{
            alert.setContentText("No has seleccionado ningún género");
            alert.showAndWait();
            return;
        }

        //Guardo el programa elegido en el comboBox
        if(comboBox.getValue() == null){
            alert.setContentText("No has elegido ningún lenguaje de programación");
            alert.showAndWait();
            return;
        }else{
            lenguaje = comboBox.getValue();
        }

        //Compruebo que el nombre de usuario este disponible
        if(ProgramadoresDAO.existeUsuario(nombre)){
            alert.setContentText("El nombre de usuario ya está registrado en la bd, introduzca otro diferente.");
            alert.showAndWait();
            return;
        }else{
            Programadores p = new Programadores(nombre,contrasenha,sexo,lenguaje);
            ProgramadoresDAO.registrarUsuario(p);

            //Imprimo mensaje de que se ha registrad ocorrectamente
            alert.setContentText("Usuario registrado correctamente!");
            alert.showAndWait();

            //Vuelvo al menu de login
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 450);
            Stage stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();

            stage.setTitle("Comunidad de programadores");
            stage.setScene(scene);
            stage.show();
        }
    }

    //Funcion que se ejecuta al pulsar el boton de volver al login
    public void volverLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        Stage stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();

        stage.setTitle("Comunidad de programadores");
        stage.setScene(scene);
        stage.show();
    }
}
