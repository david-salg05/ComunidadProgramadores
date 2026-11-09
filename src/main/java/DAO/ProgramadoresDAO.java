package DAO;

import Entidades.Programadores;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.ArrayList;

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

    //Funcion que modifica contraseña, genero y lenguaje de un programador existente
    public static boolean modificarUsuario(String nombreUsuario, String contrasenha, String genero, String lenguaje) {
        //Abro la conexion con la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        boolean modificado = false;

        //Busco al programador por nombre
        Programadores progBuscar = new Programadores();
        progBuscar.setNombre(nombreUsuario);

        ObjectSet<Programadores> resultado = bd.queryByExample(progBuscar);

        if (resultado.hasNext()) {
            Programadores p = resultado.next();
            p.setContrasenha(contrasenha);
            p.setGenero(genero);
            p.setLenguaje(lenguaje);

            //Guardo y actualizo el usuario modificado
            bd.store(p);
            bd.commit();
            modificado = true;
        }

        //Cierro la bd
        bd.close();

        return modificado;
    }

    //Funcion que devuelve todos los datos de un programador
    public static Programadores obtenerUsuario(String nombreUsuario) {
        //Abro la conexion con la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Creo un programador y le asigno el nombre de usuario pasado como parametro
        Programadores pr = new Programadores();
        pr.setNombre(nombreUsuario);

        //Busco el programador y lo devuelvo con toda su informacion
        ObjectSet<Programadores> res = bd.queryByExample(pr);
        Programadores encontrado = null;
        if (res.hasNext()) {
            encontrado = res.next();
        }

        bd.close();
        return encontrado;
    }

    //Funcion que devuelve de la bd la informacion de todos los usuarios con un lenguaje pasado por parametro
    public static ArrayList<Programadores> devolverLista(String lenguaje){
        //Creo el arrayList
        ArrayList<Programadores> lista = new ArrayList<>();

        //Abro la conexion con la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Si el elnguaje es TODOS, devuelvo la lista entera de los programadores
        if(lenguaje.equalsIgnoreCase("Todos")){
            lista = new ArrayList<>(bd.query(Programadores.class));
        }else{
            //Creo un programador y le asigno el nombre de usuario pasado como parametro
            Programadores pr = new Programadores();
            pr.setLenguaje(lenguaje);

            //Busco el programador y lo añado a la lista
            ObjectSet<Programadores> res = bd.queryByExample(pr);

            //Voy guardando en la lista todos los usuarios con esa condicion
            if (res.hasNext()) {
                for(Programadores p : res){
                    lista.add(p);
                }
            }
        }

        //Cierro la base de datos
        bd.close();

        return lista;
    }
}
