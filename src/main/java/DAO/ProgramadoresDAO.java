package DAO;

import Entidades.Programadores;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class ProgramadoresDAO {

    //Hago la conexion global con la base de datos
    static ConexionDb4o conexionBD = new ConexionDb4o();

    //Funcion que comprueba si ya existe el nombre de usuario pasado como parametro
    public static boolean existeUsuario(String nombreUsuario) {

        // Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        // Abro la lista de usuarios
        ObjectSet<Programadores> listaProgramadores = bd.query(Programadores.class);

        boolean existe = false;

        // Recorro la lista
        for (Programadores p : listaProgramadores) {
            if (p.getNombre().equalsIgnoreCase(nombreUsuario)) {
                existe = true;
                break;
            }
        }

        // Cierro la bd
        bd.close();

        return existe;
    }

    //Funcion que registra y guarda un nuevo usuario en la bd
    public static void registrarUsuario(Programadores p) {
        //Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Guardo el programador
        bd.store(p);

        //Cierro la bd
        bd.close();
    }
}
