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
public class Rook extends Pieza {

    private int tableroSize;

    public Rook(int sizeT) {
        this.tableroSize = sizeT;
        this.setMovimientos();
    }

    @Override
    public void setMovimientos() {
        for (int i = -tableroSize; i < tableroSize; i++) {
            if (i != 0) {
                super.movimientos.add(new Pieza.Movimiento(i, 0));
            }
        }
        for (int j = -tableroSize; j < tableroSize; j++) {
            if (j != 0) {
                super.movimientos.add(new Pieza.Movimiento(0, j));
            }
        }
    }
}
