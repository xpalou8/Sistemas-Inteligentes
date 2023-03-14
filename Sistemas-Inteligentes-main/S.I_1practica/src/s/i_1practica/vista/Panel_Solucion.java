/*
Autores:
Juan Francisco Sánchez García
Xavier Matas Perelló
Gerard Medina Martorell
 */
package s.i_1practica.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import s.i_1practica.MeuError;
import s.i_1practica.model.Modelo;
import java.awt.Font;

public class Panel_Solucion extends JPanel {

    private int w;
    private int h;
    private Modelo mod;
    private Frame vis;
    protected final int FPS = 24;  // 24 frames per segon
    private ProcesPintat procpin = new ProcesPintat(this);
    private BufferedImage bima;
    private String posiciones = null;

    @Override
    public void setPreferredSize(Dimension dmnsn) {
        super.setPreferredSize(dmnsn); //To change body of generated methods, choose Tools | Templates.
    }

    public Panel_Solucion(Frame vis, Modelo m) {
        this.mod = m;
        this.vis = vis;
        procpin = new ProcesPintat(this);
        procpin.start();
    }

    @Override
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        } else {
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics gr;
        bima = new BufferedImage(560, 560, BufferedImage.TYPE_INT_ARGB);
        bima.getGraphics().setColor(Color.white);
        bima.getGraphics().fillRect(0, 0, bima.getWidth(), bima.getHeight());

        gr = bima.getGraphics();
        int x = 0, y = 0, y_string = 560 / mod.getSize();
        boolean[][] obstaculos = mod.getObstaculos();

        for (int i = 0; i < mod.getSize(); i++) {
            for (int j = 0; j < mod.getSize(); j++) {
                gr.setColor(Color.BLACK);
                gr.drawRect(x, y, 560 / mod.getSize(), 560 / mod.getSize());
                gr.drawRect(x + 1, y + 1, 560 / mod.getSize() - 1, 560 / mod.getSize() - 1);
                gr.drawRect(x + 2, y + 2, 560 / mod.getSize() - 2, 560 / mod.getSize() - 2);
                
                if (obstaculos[i][j]) {
                    //insertar un obstaculo en el panel de graficos
                    gr.fillRect((i)*(560/mod.getSize()), (j)*(560/mod.getSize()), 560 / mod.getSize(), 560 / mod.getSize());
                }

                x += 560 / mod.getSize();
            }
            x = 0;
            y += 560 / mod.getSize();
            y_string += 560 / mod.getSize();
        }

        if (posiciones != null) {
            gr.drawImage(vis.get_Image(), Integer.parseInt(posiciones.split(" - ")[0]), Integer.parseInt(posiciones.split(" - ")[1]),
                    560 / mod.getSize(), 560 / mod.getSize(), this);
        }

        g.drawImage(bima, 0, 0, this);
    }

    public void setImage(int x, int y) {
        int i = (int) Math.floor(x / (560 / mod.getSize()));
        int j = (int) Math.floor(y / (560 / mod.getSize()));
        mod.notificar("Pos_inicial - " + i + " - " + j);
        posiciones = i * (560 / mod.getSize()) + " - " + j * (560 / mod.getSize());
    }
    
    public void setObstaculo(int x, int y){
        System.out.println("Añadiendo osbtaculo");
        int i = (int) Math.floor(x / (560 / mod.getSize()));
        int j = (int) Math.floor(y / (560 / mod.getSize()));
        mod.addObstaculo(i, j);
    }

    public void setPospieza(int x, int y) {
        posiciones = x * (560 / mod.getSize()) + " - " + y * (560 / mod.getSize());
    }

    public void resetPieza() {
        posiciones = null;
    }
}

class ProcesPintat extends Thread {

    private Panel_Solucion pan;

    public ProcesPintat(Panel_Solucion pd) {
        pan = pd;
    }

    @Override
    public void run() {
        long temps = System.nanoTime();
        long tram = 1000000000L / pan.FPS;
        while (true) {
            if ((System.nanoTime() - temps) > tram) {
                pan.repaint();
                temps = System.nanoTime();
                espera((long) (tram / 2000000));
            } else {

            }
        }
    }

    private void espera(long t) {
        try {
            Thread.sleep(t);
        } catch (Exception e) {
            MeuError.toStringError(e);
        }
    }

}
