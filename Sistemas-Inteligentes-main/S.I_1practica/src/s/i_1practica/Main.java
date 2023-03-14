/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s.i_1practica;

import s.i_1practica.control.Controlador;
import s.i_1practica.model.Modelo;
import s.i_1practica.vista.Frame;

/**
 *
 * @author juanf gerardm xavm
 */
public class Main implements By_events {

    /**
     * @param args the command line arguments
     */
    private Modelo mod;    // Punter al Model del patró
    private Frame vis;    // Punter a la Vista del patró
    private Controlador con;  // punter al Control del patró

    /*
        Construcció de l'esquema MVC
     */
    private void inicio() {
        mod = new Modelo(this);
        con = null;
        vis = new Frame(this);
        vis.mostrar();
    }

    public static void main(String[] args) {
        (new Main()).inicio();
    }

    @Override
    public void notificar(String s) {
        switch (s.split(" - ")[0]) {
            case "ChangeBoardSize":
                if (con != null) {
                    con.notificar("Para");
                }
                mod.setSize(Integer.parseInt(s.split(" - ")[1]));
                mod.notificar("Reset");
                mod.notificar("Reset_Pieza");
                break;
            case "Computa":
                mod.notificar("Reset");
                if (con != null) {
                    con.notificar("Para");
                }
                con = new Controlador(this, 0);
                con.notificar("Comenzar");
                break;
            case "Computa_n":
                mod.notificar("Reset");
                if (con != null) {
                    con.notificar("Para");
                }
                con = new Controlador(this, 1);
                con.notificar("Comenzar");
                break;
            case "Para":
                if (con != null) {
                    con.notificar("Para");
                }
                break;
            case "Borrar":
                if (con != null) {
                    con.notificar("Para");
                }
                mod.notificar("Reset");
                mod.notificar("Reset_Pieza");
                vis.notificar("Set_bar - 0");
                break;
        }
    }

    //metodo para conseguir el modelo de datos
    public Modelo getModelo() {
        return mod;
    }

    //metodo para devolver la vista 
    public Frame getVista() {
        return vis;
    }

    //metodo que devuelve el controlador
    public Controlador getControlador() {
        return con;
    }

}
