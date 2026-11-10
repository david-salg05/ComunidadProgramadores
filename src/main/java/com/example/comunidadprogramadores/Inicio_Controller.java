package com.example.comunidadprogramadores;

import DAO.ProgramadoresDAO;
import Entidades.Programadores;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Inicio_Controller {

    @FXML
    ComboBox<String> comboBox;

    @FXML
    TableView<Programadores> tabla;

    @FXML
    TableColumn<Programadores, String> colUsuario;

    @FXML
    TableColumn<Programadores, String> colGenero;

    @FXML
    TableColumn<Programadores, String> colLenguaje;

    @FXML
    Label mensaje;

    @FXML
    public void initialize(){
        comboBox.getItems().addAll("Todos","Java", "Python", "JavaScript");

        //Indico que atributo de programadores tengo que mostrar en cada fila
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colLenguaje.setCellValueFactory(new PropertyValueFactory<>("lenguaje"));

        //Selecciono TODOS por defecto
        comboBox.setValue("Todos");

        //Pongo la funcion a null para que cargue TODOS
        elegirLenguaje(null);
    }

    //Usuario que ha iniciado sesión
    String usuario;

    public void recibirDatos(String usuario) {
        this.usuario = usuario;

        // Muestro el nombre del usuario que ha iniciado sesión
        mensaje.setText("Bienvenido/a, " + usuario);
    }

    public void elegirLenguaje(ActionEvent actionEvent){

        // Guardo el lenguaje elegido en el comboBox
        String lenguaje = comboBox.getValue();

        // Obtengo la lista de programadores
        ArrayList<Programadores> listaUsuarios = ProgramadoresDAO.devolverLista(lenguaje);

        // Limpio la tabla para eliminar los resultados anteriores
        tabla.getItems().clear();

        // Añado los nuevos resultados a la tabla
        tabla.getItems().addAll(listaUsuarios);
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

    public void generarPDF(ActionEvent actionEvent){
        //Creo la alerta
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NOTIFICACIÓN");

        //Aplico un tamaño a la alerta
        alert.getDialogPane().setPrefWidth(450);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(450);

        // Obtengo los programadores que aparecen en la tabla
        ArrayList<Programadores> lista = new ArrayList<>(tabla.getItems());

        try {

            //Genero el pdf
            GenerarPDF.crearPDF(lista);

            //Muestro la alerta
            alert.setContentText("PDF creado correctamente");
            alert.showAndWait();

        } catch (Exception e) {

            //MUestro una alerta de error
            alert.setContentText("Error al crear el PDF, vuelva a intentarlo");
            alert.showAndWait();
        }
    }


    public void generarExcel(ActionEvent actionEvent) {

        //Creo la alerta
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NOTIFICACIÓN");

        //Aplico un tamaño a la alerta
        alert.getDialogPane().setPrefWidth(450);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(450);

        // Obtengo los programadores que aparecen en la tabla
        ArrayList<Programadores> lista = new ArrayList<>(tabla.getItems());

        try {

            // Genero el Excel
            GenerarExcel.crearExcel(lista);

            //Muestro la alerta
            alert.setContentText("Excel creado correctamente");
            alert.showAndWait();

        } catch (Exception e) {

            //MUestro una alerta de error
            alert.setContentText("Error al crear el PDF, vuelva a intentarlo");
            alert.showAndWait();
        }
    }
}
