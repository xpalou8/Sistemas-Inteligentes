/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s.i_1practica.model;

/**
 *
 * @author XAMAP
 */
public class Queen extends Pieza {

    private int tableroSize;

    public Queen(int sizeT) {
        this.tableroSize = sizeT;
        this.setMovimientos();
    }

    public void setMovimientos() {
        for (int i = -tableroSize +1 ; i < tableroSize; i++) {
            for (int j = -tableroSize +1; j < tableroSize; j++) {
                if ((i != 0) && ((i == j) || i == - j) ) {
                    super.movimientos.add(new Movimiento(i, j));
                }               
            }
            super.movimientos.add(new Movimiento(i, 0));
            super.movimientos.add(new Movimiento(0, i));
        }
    }
}
