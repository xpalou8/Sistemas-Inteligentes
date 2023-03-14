/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s.i_1practica.model;

import java.util.ArrayList;

/**
 *
 * @author juanf
 */
public abstract class Pieza {

    //atributos de la clase
    public ArrayList<Movimiento> movimientos = new ArrayList<>();
    public String nombre;
    
    @Override
    public String toString(){
       return nombre;
    }

    public class Movimiento {

        private int x, y;

        public Movimiento(int x, int y) {
            this.x = x;
            this.y = y;
        }

        //getters
        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }
    
    public Movimiento getMove(int i) {
        return movimientos.get(i);
    }
    
    public int getSize(){
        return movimientos.size();
    }
    
    public abstract void setMovimientos();

}
