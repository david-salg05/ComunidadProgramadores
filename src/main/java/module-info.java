module com.example.comunidadprogramadores {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires db4o;
    requires java.desktop;
    requires com.github.librepdf.openpdf;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    exports com.example.comunidadprogramadores;
    opens com.example.comunidadprogramadores to javafx.fxml;
    opens Entidades to javafx.base, db4o;
}