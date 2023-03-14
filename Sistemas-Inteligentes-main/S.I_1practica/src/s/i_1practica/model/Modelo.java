
/*
Autores : 
Juan Francisco Sánchez García
Xavier Matas Perelló
Gerard Medina Martorell
 */
package s.i_1practica.model;

import s.i_1practica.Main;
import s.i_1practica.By_events;

/**
 *
 * @authors Juan Francisco Sánchez, Xavier Matas Perrelo, Gerard Medina
 * Martorell
 */
public class Modelo implements By_events {

    //atributos de la clase
    private Main prog;
    private String posicion;
    private int size = 2;
    private Pieza p_elegida;
    private int[][] num_moves = new int[size][size];
    private boolean[][] obstaculos = new boolean[size][size];
    public boolean isObstaculo = false;

    public Modelo(Main prog) {
        this.prog = prog;
        posicion = null;
        size = 2;
        p_elegida = null;
        num_moves = new int[size][size];
        obstaculos = new boolean[size][size];
    }
    
    public boolean[][] getObstaculos(){
        return this.obstaculos;
    }
    
    public void addObstaculo(int i, int j){
        if(!obstaculos[i][j]) this.obstaculos[i][j] = true;
        else this.obstaculos[i][j] = false;
    }
    
    public void removeObstaculo(int i, int j){
        this.obstaculos[i][j] = false;
    }

    public Pieza getPieza() {
        return this.p_elegida;
    }

    public int getNumMove(int i, int j) {
        return this.num_moves[i][j];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int s) {
        size = s;
        //array de numeros solucion
        num_moves = new int[size][size];
        //arraay de los obstaculos
        obstaculos = new boolean[size][size];
    }

    public void addnumMove(int x, int y, int n) {
        this.num_moves[x][y] = n;
    }

    public String getPosiscionInicial() {
        return posicion;
    }

    @Override
    public void notificar(String s) {
        switch (s.split(" - ")[0]) {
            case "Pos_inicial":
                posicion = s.split(" - ")[1] + " - " + s.split(" - ")[2];
                prog.getVista().notificar("Pos_pieza - " + s.split(" - ")[1] + " - " + s.split(" - ")[2]);
                break;
            case "Select_Pieza":
                switch (s.split(" - ")[1]) {
                    case "King":
                        this.p_elegida = new King();
                        this.isObstaculo = true;
                        break;
                    case "Queen":
                        this.p_elegida = new Queen(size);
                        this.isObstaculo = false;
                        break;
                }
                break;
            case "Reset":
                num_moves = new int[size][size];
                obstaculos = new boolean[size][size];
                break;
            case "Reset_Pieza":
                p_elegida = null;
                posicion = null;
                prog.getVista().notificar("Reset_Pieza");
                break;
        }
    }

}
