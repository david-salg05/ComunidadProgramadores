module com.example.comunidadprogramadores {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires db4o;
    requires java.desktop;
    requires com.github.librepdf.openpdf;

    exports com.example.comunidadprogramadores;
    opens com.example.comunidadprogramadores to javafx.fxml;
    opens Entidades to javafx.base, db4o;
}