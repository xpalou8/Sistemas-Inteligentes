/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s.i_1practica.model;

import s.i_1practica.model.Pieza;

/**
 *
 * @author XAMAP
 */
public class Knight extends Pieza{

    public Knight() {
        this.setMovimientos();
    }

    @Override
    public void setMovimientos() {
        super.movimientos.add(new Movimiento(1, 2));
        super.movimientos.add(new Movimiento(2, 1));
        super.movimientos.add(new Movimiento(-1, 2));
        super.movimientos.add(new Movimiento(-2, 1));
        super.movimientos.add(new Movimiento(1, -2));
        super.movimientos.add(new Movimiento(2, -1));
        super.movimientos.add(new Movimiento(-1, -2));
        super.movimientos.add(new Movimiento(-2, -1));
    }
}
