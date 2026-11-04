package DAO;

import Entidades.Programadores;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class ProgramadoresDAO {

    //Hago la conexion global con la base de datos
    static ConexionDb4o conexionBD = new ConexionDb4o();

    //Funcion que comprueba si ya existe el nombre de usuario pasado como parametro
    public static boolean existeUsuario(String nombreUsuario) {
        //Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Abro la lista de usuarios
        ObjectSet <Programadores> listaProgramadores = bd.query(Programadores.class);

        //Recorro la lista y compruebo si su usuario corresponde con el que he pasado
        for (Programadores p : listaProgramadores) {
            if (p.getNombre().equalsIgnoreCase(nombreUsuario)) {
                return true;
                //Cierro la bd
            }
            //Cierro la bd
            bd.close();
        }

        //Cierro la bd
        bd.close();

        return false;
    }
}
