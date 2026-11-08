package com.example.comunidadprogramadores;

import Entidades.Programadores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class Inicio_Controller {

    @FXML
    ComboBox<String> comboBox;

    @FXML
    public void initialize(){
        comboBox.getItems().addAll("Todos","Java", "Python", "JavaScript");
    }

    @FXML
    TableView<Programadores> tabla;

    @FXML
    TableColumn<Programadores, String> colUsuario;

    @FXML
    TableColumn<Programadores, String> colGenero;

    @FXML
    TableColumn<Programadores, String> colLenguaje;

    //Usuario que ha iniciado sesión
    String usuario;

    public void recibirDatos(String usuario) {
        this.usuario = usuario;
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        Stage stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();

        stage.setTitle("Comunidad de programadores");
        stage.setScene(scene);
        stage.show();
    }


    public void editarPerfil(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editarPerfil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        //Le paso los datos a editarPerfil
        EditarPerfil_Controller ep = fxmlLoader.getController();
        ep.cargarDatos(usuario);

        Stage stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();

        stage.setTitle("Editar Perfil");
        stage.setScene(scene);
        stage.show();
    }
}
