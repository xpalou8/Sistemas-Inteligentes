/*
Autores : 
Juan Francisco Sánchez García
Xavier Matas Perelló
Gerard Medina Martorell
 */
package s.i_1practica.control;

import s.i_1practica.By_events;
import s.i_1practica.Main;
import s.i_1practica.MeuError;

public class Controlador extends Thread implements By_events {

    private Main prog;
    private boolean seguir = true, acabar = false;
    private int soluciones, modo;

    public Controlador(Main p, int modo) {
        prog = p;
        this.modo = modo;
    }

    @Override
    public void run() {
        switch (modo) {
            case 0:
                if (prog.getModelo().getPieza() == null) {
                    prog.getVista().notificar("no_seleccionada");
                } else if (prog.getModelo().getPosiscionInicial() == null) {
                    prog.getVista().notificar("no_colocada");
                } else {
                    int pos_x = Integer.parseInt(prog.getModelo().getPosiscionInicial().split(" - ")[0]);
                    int pos_y = Integer.parseInt(prog.getModelo().getPosiscionInicial().split(" - ")[1]);
                    prog.getModelo().addnumMove(pos_x, pos_y, 1);
                    if (Solve(pos_x, pos_y, 1)) {
                        prog.getVista().notificar("si_solucion");
                    } else if (!acabar) {
                        prog.getVista().notificar("no_solucion");
                    } else {
                        prog.getVista().notificar("parado");
                    }
                }
                break;
            case 1:
                soluciones = 0;
                if (prog.getModelo().getPieza() == null) {
                    prog.getVista().notificar("no_seleccionada");
                } else if (prog.getModelo().getPosiscionInicial() == null) {
                    prog.getVista().notificar("no_colocada");
                } else {
                    int pos_x = Integer.parseInt(prog.getModelo().getPosiscionInicial().split(" - ")[0]);
                    int pos_y = Integer.parseInt(prog.getModelo().getPosiscionInicial().split(" - ")[1]);
                    prog.getModelo().addnumMove(pos_x, pos_y, 1);
                    Solve_n(pos_x, pos_y, 1);
                    prog.getVista().notificar("Muestra_soluciones - " + soluciones);
                }
                break;
        }

    }

    //solucion del problema con backtracking primera solucion
    public boolean Solve(int i, int j, int contador) {
        int move_x = 0;
        int move_y = 0;

        if (contador == (prog.getModelo().getSize() * prog.getModelo().getSize())) {
            return true;
        }

        for (int n = 0; n < prog.getModelo().getPieza().getSize() && !acabar; n++) {

            move_x = prog.getModelo().getPieza().getMove(n).getX() + i;
            move_y = prog.getModelo().getPieza().getMove(n).getY() + j;

            if (se_puede_mover(move_x, move_y)) {

                prog.getModelo().addnumMove(move_x, move_y, contador + 1);
                prog.getModelo().notificar("Pos_inicial - " + move_x + " - " + move_y);
                prog.getVista().notificar("Set_bar - " + (contador + 1));
                espera((long) 0.5, 0);

                if (Solve(move_x, move_y, contador + 1)) {
                    return true;
                }

                prog.getVista().notificar("Set_bar - " + (contador));
                prog.getModelo().addnumMove(move_x, move_y, 0);
                prog.getModelo().notificar("Pos_inicial - " + i + " - " + j);

            }
        }
        return false;
    }

    //solucion al problema con backtracking n soluciones
    public void Solve_n(int i, int j, int contador) {
        int move_x = 0;
        int move_y = 0;

        if (contador == (prog.getModelo().getSize() * prog.getModelo().getSize())) {
            soluciones += 1;
        } else {
            for (int n = 0; n < prog.getModelo().getPieza().getSize() && !acabar; n++) {

                move_x = prog.getModelo().getPieza().getMove(n).getX() + i;
                move_y = prog.getModelo().getPieza().getMove(n).getY() + j;

                if (se_puede_mover(move_x, move_y)) {
                    prog.getModelo().addnumMove(move_x, move_y, contador + 1);
                    Solve_n(move_x, move_y, contador + 1);
                    prog.getModelo().addnumMove(move_x, move_y, 0);
                }
            }
        }
    }

    public boolean se_puede_mover(int x, int y) {
        if ((x < 0 || x >= prog.getModelo().getSize()
                || y < 0 || y >= prog.getModelo().getSize()
                || prog.getModelo().getNumMove(x, y) != 0)) {
            return false;
        }
        return true;
    }

    private void espera(long m, int n) {
        try {
            Thread.sleep(m, n);
        } catch (Exception e) {
            MeuError.toStringError(e);
        }
    }

    @Override
    public void notificar(String s) {
        switch (s.split(" - ")[0]) {
            case "Para":
                acabar = true;
                espera(10, 0);
                break;
            case "Comenzar":
                acabar = false;
                this.start();
                break;
        }

    }

}
