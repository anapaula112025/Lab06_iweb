package daos;

import beans.Worker;

import java.sql.*;
import java.util.ArrayList;

public class WorkerDao {
    public ArrayList<Worker> listar(){

        ArrayList<Worker> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/bicicentro";
        String username = "root";
        String password = "123456";

        String sql = "select * from bicicentro.trabajadores";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Worker worker = new Worker();
                worker.setNombre(rs.getString(1));
                worker.setApellidos(rs.getString(2));
                worker.setCorreo(rs.getString(3));
                worker.setDni(rs.getString(4));

                lista.add(worker);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void crear(String nombre, String apellido, String correo, String dni, int idSede){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/bicicentro";
        String username = "root";
        String password = "123456";

        String sql = "insert into trabajadores (nombres, apellidos, correo, dni, idSede) values (?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,nombre);
            pstmt.setString(2,apellido);
            pstmt.setString(3,correo);
            pstmt.setString(4,dni);
            pstmt.setInt(5,(idSede));

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Worker buscarPorDni(String dni){

        Worker worker = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/bicicentro";
        String username = "root";
        String password = "123456";

        String sql = "select * from trabajadores where dni = ?";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,dni);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    worker = new Worker();
                    worker.setNombre(rs.getString(1));
                    worker.setApellidos(rs.getString(2));
                    worker.setCorreo(rs.getString(3));
                    worker.setDni(rs.getString(4));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return worker;
    }

    public void actualizar(Worker worker){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/bicicentro";
        String username = "root";
        String password = "123456";

        String sql = "update trabajadores set nombres = ?, apellidos = ?, correo = ? , dni = ? where dni = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,worker.getNombre());
            pstmt.setString(2,worker.getApellidos());
            pstmt.setString(3,worker.getCorreo());
            pstmt.setString(4,worker.getDni());
            pstmt.setString(5,worker.getDni());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrar(String dni) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/bicicentro";
        String username = "root";
        String password = "123456";

        String sql = "delete from trabajadores where dni = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,dni);
            pstmt.executeUpdate();

        }
    }
}
