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
public class King extends Pieza{

    public King() {
        this.setMovimientos();
    }

    @Override
    public void setMovimientos() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!((i == 0) && (j == 0))) {
                    super.movimientos.add(new Movimiento(i, j));
                }
            }
        }
    }
}
