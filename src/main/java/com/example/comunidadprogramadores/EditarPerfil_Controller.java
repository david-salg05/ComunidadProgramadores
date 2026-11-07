package com.example.comunidadprogramadores;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

import java.awt.*;

public class EditarPerfil_Controller {

    @FXML
    TextField nombreUsuario;

    @FXML
    TextField contrasenha;

    @FXML
    RadioButton hombre, mujer, pnd;

    @FXML
    ComboBox <String> comboBox;

    @FXML
    public void initialize(){

        comboBox.getItems().addAll("Java", "Python", "JavaScript");

        //El nombre de usuario no se puede modificar
        nombreUsuario.setVisible(true);
    }


}
