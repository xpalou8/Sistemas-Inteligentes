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
public class Pawnn extends Pieza{

    public Pawnn() {
        this.setMovimientos();
    }

    @Override
    public void setMovimientos() {
        super.movimientos.add(new Movimiento(0, 1));
        super.movimientos.add(new Movimiento(0, -1));
        super.movimientos.add(new Movimiento(1, 0));
        super.movimientos.add(new Movimiento(-1, 0));
    }
}
