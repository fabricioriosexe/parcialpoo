package com.githubfabriciorios.javasqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CConexion {

    Connection conectar = null;

    String usuario = "root";
    String contrasenia = "1234";
    String bd = "alumnosDB";
    String ip = "localhost";
    String puerto = "1433";

    public Connection establecerConeccion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String Cadena = "jdbc:sqlserver://"+ip+":"+puerto+";"+"databaseName="+bd+";"+
                     "encrypt-true;trustServerCertificate=true";
           
            conectar = DriverManager.getConnection(Cadena,usuario,contrasenia);
            JOptionPane.showMessageDialog(null, "Se conecto correctamente a la base de dato");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No se conecto correctamente"+e.toString());
        }
        return conectar;
    }
}
