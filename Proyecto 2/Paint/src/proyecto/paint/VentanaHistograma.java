/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Clase de la ventana interna asociada al histograma
 * Código extraido de: https://stackoverflow.com/questions/12518496/drawing-a-graphical-histogram
 * @author thejoker
 */
public class VentanaHistograma extends javax.swing.JInternalFrame{

    /**
     * Establece los valores al map
     * @param dataDouble
     */
    public VentanaHistograma(double[][] dataDouble) {
        
        Map<Double, Integer> mapHistory = new TreeMap();
        
        for (double[] data1 : dataDouble)
            for (int r = 0; r < data1.length; r++){
                double value = data1[r];
                int amount = 0;
                if (mapHistory.containsKey(value)){
                    amount = mapHistory.get(value);
                    amount++;
                }else
                    amount = 1;
                mapHistory.put(value, amount);
            }
        JFrame frame = new JFrame("Histograma");
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(new Graph(mapHistory)));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Clase JPanel para crear el gráfico
     */
    protected class Graph extends JPanel {

        protected static final int MIN_BAR_WIDTH = 4;
        private Map<Double, Integer> mapHistory;

        /**
         * Ventana para crear la gráfica
         * @param mapHistory
         */
        public Graph(Map<Double, Integer> mapHistory) {
            this.mapHistory = mapHistory;
            int width = (mapHistory.size() * MIN_BAR_WIDTH) + 11;
            Dimension minSize = new Dimension(width, 128);
            Dimension prefSize = new Dimension(width, 256);
            setMinimumSize(minSize);
            setPreferredSize(prefSize);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (mapHistory != null) {
                int xOffset = 5;
                int yOffset = 5;
                int width = getWidth() - 1 - (xOffset * 2);
                int height = getHeight() - 1 - (yOffset * 2);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawRect(xOffset, yOffset, width, height);
                int barWidth = Math.max(MIN_BAR_WIDTH,
                        (int) Math.floor((float) width
                        / (float) mapHistory.size()));
                int maxValue = 0;
                for (Double key : mapHistory.keySet()) {
                    int value = mapHistory.get(key);
                    maxValue = Math.max(maxValue, value);
                }
                int xPos = xOffset;
                for (Double key : mapHistory.keySet()) {
                    int value = mapHistory.get(key);
                    int barHeight = Math.round(((float) value
                            / (float) maxValue) * height);
                    g2d.setColor(Color.WHITE);
                    int yPos = height + yOffset - barHeight;
                    Rectangle2D bar = new Rectangle2D.Float(
                            xPos, yPos, barWidth, barHeight);
                    g2d.fill(bar);
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.draw(bar);
                    xPos += barWidth;
                }
                g2d.dispose();
            }
        }
    }
}