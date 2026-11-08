package com.example.comunidadprogramadores;

import DAO.ProgramadoresDAO;
import Entidades.Programadores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

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
        nombreUsuario.setDisable(true);
    }

    //Funcion que carga los datos del usuario en los campos para poder modificarlos
    public void cargarDatos(String usuario) {
        //Voy a la bd y pido todos los datos del programador
        Programadores p = ProgramadoresDAO.obtenerUsuario(usuario);

        //Los escribo en los campos
        nombreUsuario.setText(p.getNombre());
        contrasenha.setText(p.getContrasenha());
        comboBox.setValue(p.getLenguaje());

        if (p.getGenero().equalsIgnoreCase(hombre.getText())){
            hombre.setSelected(true);
        } else if (p.getGenero().equalsIgnoreCase(mujer.getText())){
            mujer.setSelected(true);
        }else{
            pnd.setSelected(true);
        }
    }

    //Funcion que comprueba si los datos cumplen los requisitos y los actualiza
    public void guardarCambios(ActionEvent actionEvent) throws IOException {
        //Variables
        String nombre = nombreUsuario.getText();
        String contrasenha = this.contrasenha.getText();
        String sexo = "", lenguaje = "";

        //Creo la alerta
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");

        //Aplico un tamaño a la alerta
        alert.getDialogPane().setPrefWidth(450);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(450);

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

        //Localizo al usuario en la bd y actualizo sus datos
        if(ProgramadoresDAO.modificarUsuario(nombre, contrasenha, sexo, lenguaje)){
            //Imprimo mensaje de que se ha actualizado correctamente
            alert.setContentText("Los datos se han actualizado correctamente!");
            alert.showAndWait();

            //Vuelvo al menu de inicio
            volverInicio(actionEvent);

        }else{
            alert.setContentText("No se han podido actualizar los datos");
            alert.showAndWait();
        }
    }

    //Funcion que regresa al menu de inicio
    public void cancelar(ActionEvent actionEvent) throws IOException {
        //Vuelvo al menu de inicio
        volverInicio(actionEvent);
    }

    //Funcion que vuelve al menu de inicio pasandole el usuario
    private void volverInicio(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        //Le devuelvo a Inicio el usuario que ha iniciado sesión
        Inicio_Controller ic = fxmlLoader.getController();
        ic.recibirDatos(nombreUsuario.getText());

        Stage stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();

        stage.setTitle("Comunidad de programadores");
        stage.setScene(scene);
        stage.show();
    }
}
