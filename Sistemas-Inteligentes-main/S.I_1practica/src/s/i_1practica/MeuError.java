/*
Autor: 
Miquel Mascaro Portells
Clase importada de de los recursos de la asignatura
 */
package s.i_1practica;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import javax.swing.JOptionPane;

/**
 * Classe per homogeneizar l'amissió d'errors.
 *
 * @author mascport
 */
public class MeuError {
    
    //metodo que devuelve el string del error
    static public void toStringError(Exception ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        String slogs = writer.toString();
        JOptionPane.showMessageDialog(null,ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
    }
}

