/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.gui;

import TomHopper.utility.PrinceAngle;
import TomHopper.utility.vector.Vector;
import TomHopper.utility.vector.Vector2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * 
 * @author cdwan
 */
public class OGraphics {
    
    private final Graphics2D g2d;
    private final Vector2D o;
    
    public OGraphics(Graphics2D g2d) {
        this.g2d = g2d;
        o = Vector2D.getNullVector2D();
    }
    
    public OGraphics(int x, int y, Graphics2D g2d) {
        this.g2d = g2d;
        o = Vector.makeVector(x, y);
    }
    
    public OGraphics (Vector2D v2d, Graphics2D g2d) {
        this.g2d = g2d;
        o = Vector2D.copyOf(v2d);
    }
    
    public OGraphics create() {
        return new OGraphics(Vector2D.copyOf(o), copyOf(g2d));
    }
    
    public OGraphics create(int x, int y) {
        return new OGraphics(Vector2D.copyOf(o).add(Vector.makeVector(x, y)), copyOf(g2d));
    }
    
    public OGraphics create(Vector2D v2d) {
        return new OGraphics(Vector2D.copyOf(o).add(v2d), copyOf(g2d));
    }
    
    public OGraphics create(PrinceAngle pa) {
        OGraphics ret = create();
        ret.rotate(pa.getInRad());
        return ret;
    }
    
    public Graphics2D getGraphics2D() {
        return g2d;
    }
    
    public Vector2D getOrigin() {
        return o;
    }
    
    public Font getFont() {
        return g2d.getFont();
    }

    public void setFont(Font font) {
        g2d.setFont(font);
    }
    
    public void translate(float x, float y) {
        o.add(Vector.makeVector(x, y));
    }
    
    public void translate(Vector2D v2d) {
        o.add(v2d);
    }
    
    /**
     * rotates about origin
     * @param theta in radians
     */
    public void rotate(double theta) {
        g2d.rotate(theta);
    }
    
    /**
     * rotates about x, y
     * @param theta in radians
     * @param x
     * @param y 
     */
    public void rotate(double theta, double x, double y) {
        g2d.rotate(theta, x, y);
    }
    
    public void setColor(Color c) {
        g2d.setColor(c);
    }
    
    public void drawRect(int x, int y, int w, int h) {
        g2d.drawRect(x, y, w, h);
    }
    
    public void fillRect(int x, int y, int w, int h) {
        g2d.fillRect(x, y, w, h);
    }
    
    public void drawString(String s, int x, int y) {
        g2d.drawString(s, x, y);
    }
    
    public void drawLine(int x1, int y1, int x2, int y2) {
        g2d.drawLine(x1, y1, x2, y2);
    }
    
    public void drawOval(int x, int y, int w, int h) {
        g2d.drawOval(x, y, w, h);
    }
    
    public void fillOval(int x, int y, int w, int h) {
        g2d.fillOval(x, y, w, h);
    }
    
    public void drawImage(Image img, int x, int y) {
        g2d.drawImage(img, x, y, null);
    }
    
    public void drawImage(Image img, int x, int y, int w, int h) {
        g2d.drawImage(img, x, y, w, h, null);
    }
    
    public static Graphics2D copyOf(Graphics2D g2d) {
        return (Graphics2D) g2d.create();
    }
}
