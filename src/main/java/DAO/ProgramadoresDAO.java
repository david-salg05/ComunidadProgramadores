package DAO;

import Entidades.Programadores;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class ProgramadoresDAO {

    //Hago la conexion global con la base de datos
    static ConexionDb4o conexionBD = new ConexionDb4o();

    //Funcion que comprueba el usuario y la contraseña de un programador
    public static boolean UsuarioValido(String nombreUsuario, String contrasenha) {
        // Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Creo el programador con los datos pasados como parametro
        Programadores pr = new Programadores();
        pr.setNombre(nombreUsuario);
        pr.setContrasenha(contrasenha);

        //Miro si los datos coinciden con los que hay en la bd
        ObjectSet<Programadores> buscarProgramador = bd.queryByExample(pr);

        boolean existe = false;

        if(buscarProgramador.hasNext()){
            existe = true;
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

    //Funcion que comprueba si un usuario esta dado de alta en la bd
    public static boolean existeUsuario(String usuario){
        // Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Creo el programador con los datos pasados como parametro
        Programadores pr = new Programadores();
        pr.setNombre(usuario);

        //Miro si los datos coinciden con los que hay en la bd
        ObjectSet<Programadores> buscarProgramador = bd.queryByExample(pr);

        boolean existe = false;

        if(buscarProgramador.hasNext()){
            existe = true;
        }

        // Cierro la bd
        bd.close();

        return existe;
    }
}
