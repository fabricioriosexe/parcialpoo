package com.githubfabriciorios.javasqlserver;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fabricio Rios
 */
public class CAlumnos {

    public void mostrarAlumno(JTable paramTablaAlumno) {
        CConexion objetoconexion = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";
        modelo.addColumn("CODIGO");
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDOS");
        modelo.addColumn("EDAD");

        paramTablaAlumno.setModel(modelo);

        sql = "select * from alumno;";

        String[] datos = new String[5];
        Statement st;

        try {
            st = objetoconexion.establecerConeccion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
            }
            paramTablaAlumno.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se mostraron los registros" + e.toString());
        }
    }

    public void seleccionarAlumno(JTable paramTablaAlumno, JTextField paramCodigo, JTextField paramDNI, JTextField paramNombre, JTextField paramApellido, JTextField paramEdad) {
        try {
            int fila = paramTablaAlumno.getSelectedRow();
            if (fila >= 0) {
                paramCodigo.setText(paramTablaAlumno.getValueAt(fila, 0).toString());
                paramDNI.setText(paramTablaAlumno.getValueAt(fila, 1).toString());
                paramNombre.setText(paramTablaAlumno.getValueAt(fila, 2).toString());
                paramApellido.setText(paramTablaAlumno.getValueAt(fila, 3).toString());
                paramEdad.setText(paramTablaAlumno.getValueAt(fila, 4).toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error de seleccion" + e.toString());
        }
    }

    public void insertarAlumnos(JTextField paramDNI, JTextField paramNombre, JTextField paramApellido, JTextField paramEdad) {
        CConexion objetoConexion = new CConexion();
        String consulta = "insert into alumno(dni,nombres,apellidos,edad) values(?,?,?,?);";
        try {
            CallableStatement cs = objetoConexion.establecerConeccion().prepareCall(consulta);
            cs.setString(1, paramDNI.getText());
            cs.setString(2, paramNombre.getText());
            cs.setString(3, paramApellido.getText());
            cs.setInt(4, Integer.parseInt(paramEdad.getText()));
            cs.execute();
            JOptionPane.showMessageDialog(null, "se inserto correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error de insercion" + e.toString());
        }
    }

    public void modificarAlumno(JTextField paramCodigo, JTextField paramDNI, JTextField paramNombre, JTextField paramApellido, JTextField paramEdad) {
        CConexion objetoConexion = new CConexion();
        String consulta = "UPDATE alumno SET alumno.dni =?,alumno.nombres =?,alumno.apellidos =?,alumno.edad = ? WHERE alumno.codigo = ?;";
        try {
            CallableStatement cs = objetoConexion.establecerConeccion().prepareCall(consulta);
            cs.setString(1, paramDNI.getText());
            cs.setString(2, paramNombre.getText());
            cs.setString(3, paramApellido.getText());
            cs.setInt(4, Integer.parseInt(paramEdad.getText()));
            cs.setInt(5, Integer.parseInt(paramCodigo.getText()));
            cs.execute();
            JOptionPane.showMessageDialog(null, "se modifico correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error en modificacion" + e.toString());
        }
    }
    
    public void eliminarAlumno(JTextField paramCodigo){
        CConexion objetoConexion = new CConexion();
        String consulta = "DELETE FROM alumno where alumno.codigo = ?;";
         try {
            CallableStatement cs = objetoConexion.establecerConeccion().prepareCall(consulta);
            cs.setInt(1, Integer.parseInt(paramCodigo.getText()));
            cs.execute();
            JOptionPane.showMessageDialog(null, "se elimino correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error en eliminacion" + e.toString());
        }
    }
}
