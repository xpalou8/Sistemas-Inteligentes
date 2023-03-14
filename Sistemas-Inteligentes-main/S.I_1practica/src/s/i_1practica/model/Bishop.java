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
public class Bishop extends Pieza {

    private int tableroSize;

    public Bishop(int sizeT) {
        this.tableroSize = sizeT;
        this.setMovimientos();
    }

    @Override
    public void setMovimientos() {
        super.movimientos.add(new Pieza.Movimiento(0, -1));
        super.movimientos.add(new Pieza.Movimiento(0, 1));
        for (int i = -tableroSize + 1; i < tableroSize; i++) {
            for (int j = -tableroSize + 1; j < tableroSize; j++) {
                if ((i != 0) && ((i == j) || i == -j)) {
                    super.movimientos.add(new Pieza.Movimiento(i, j));
                }
            }
        }
    }
}
