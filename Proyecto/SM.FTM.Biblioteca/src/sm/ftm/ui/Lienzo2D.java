/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ftm.ui;


import java.util.List;
import java.awt.Graphics;
import sm.ftm.graficos.*;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * Clase para pintar las figuras con sus atributos
 * 
 * @author thejoker
 */
public class Lienzo2D extends javax.swing.JPanel {

    /**
     * Creates new form Lienzo2D
     */
    public Lienzo2D() {
        initComponents();
        
        this.s = new sm.ftm.graficos.Shape();
        this.mover = false;
    }

    /**
     * @return the s
     */
    public sm.ftm.graficos.Shape getShape() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setShape(sm.ftm.graficos.Shape s) {
        this.s = s;
    }
    
    /**
     * @return the mover
     */
    public boolean isMover() {
        return mover;
    }

    /**
     * @param mover the mover to set
     */
    public void setMover(boolean mover) {
        this.mover = mover;
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        for (sm.ftm.graficos.Shape shape : vShape) {
            shape.draw(g2d);
        }
    }
     
    /**
     * Envia la figura a la primera posición del vector
     * @param index
     */
    public void EnviarFondo(int index){
        
    }
    
    /**
     * Envia la figura a la última posición del vector
     * @param index
     */
    public void EnviarFrente(int index){
    
    }
    
    /**
     * Envia la figura una posición menos del vector
     * @param index
     */
    public void EnviarAtras(int index){
    
    }
    
    /**
     * Envia la figura una posición mas del vector
     * @param index
     */
    public void EnviarAdelante(int index){
    
    }
    
    /**
     * Función que devuelve si el @param p esta contenida en la figura
     * @param p
     * @return
     */
    public Shape getSelectedShape(Point2D p){
        for(sm.ftm.graficos.Shape sv:vShape){
            if(sv.contains(p))
                return sv;
              
            if(s instanceof Linea){
                if(((Linea)s).contains(p)){
                    return sv;
                }   
            } 
        }
        
        return null;
    }
    
    /**
     * Función para crear la figura
     * @param num
     * @param p
     */
    public void CreateShape(int num, Point2D p){  
        sm.ftm.graficos.Shape s = null;
        
        switch(this.getShape().getForma()){
            case POINT:
                s = new Punto(this.getShape(), p);
            break;
            
            case LINE:
                 s = new Linea(this.getShape(), p, p);
            break;
            
            case RECTANGLE:
                s = new Rectangulo(this.getShape(), p, p);
            break;
            
            case ELLIPSE:
                s = new Elipse(this.getShape(), p, p);
            break;
            
            case CURVE:
                if (this.getShape() != null && this.getShape() instanceof Curva){
                    ((Curva) this.getShape()).setPCtrl(p);
                    this.UpdateShape(p);
                }else
                    s = new Curva(this.getShape(),p);
            break;
            
            default:
                s = null;
            break;
        }
        
        this.s = s;
        
    }
    
    /**
     * Función utilizada en el dragged para asignar el último punto o punto de control
     * @param p
     */
    public void UpdateShape(Point2D p){
        switch(this.getShape().getForma()){
            case LINE:
                ((Linea) this.getShape()).setPF(p);
            break;
            
            case RECTANGLE:
                ((Rectangulo) this.getShape()).setPf(p);
            break;
            
            case ELLIPSE:
                ((Elipse) this.getShape()).setPf(p);
            break;
            
            case CURVE:
                if(((Curva) this.getShape()).getPCtrl() == null)
                    CreateShape(1,p);
                else
                    ((Curva) this.getShape()).setPf(p);
        }
    }
    
    /**
     * Función que actualiza la posición de cada figura
     * @param p
     */
    public void UpdatePositionShape(Point2D p){ 
        if(this.getShape() instanceof Punto){
            ((Punto)getShape()).setLocation(p);
        }
        
        if(this.getShape() instanceof Linea){
            ((Linea)getShape()).setLocation(p);
        }
        
        if(this.getShape() instanceof Rectangulo){
            ((Rectangulo)getShape()).setLocation(p);
        }
        
        if(this.getShape() instanceof Elipse){
            ((Elipse)getShape()).setLocation(p);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if(this.isMover()){
            this.s = this.getSelectedShape(evt.getPoint());
        }else{
            pAux = evt.getPoint();
            int num = evt.getClickCount();
            this.CreateShape(num,pAux);
            if( this.s != null)
                vShape.add(this.s);
            
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if(this.isMover()){
            if(this.s != null){
                this.UpdatePositionShape(evt.getPoint());
            }    
        }else
            this.UpdateShape(evt.getPoint());
        this.repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        this.formMouseDragged(evt);
    }//GEN-LAST:event_formMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    /**
     * Punto auxiliar para almacenar el evento
     */
    private Point2D pAux;
    
    /**
     * Vector de formas
     */
    private List<sm.ftm.graficos.Shape> vShape = new ArrayList();
    
    /**
     * Figura actual
     */
    private sm.ftm.graficos.Shape s;
    
    /**
     * Mover la figuras
     */
    private boolean mover;
}
