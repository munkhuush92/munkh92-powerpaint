/*
 * TCSS 305 - Power Paint Drawing Storage class.
 */
package model;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

/**
 *  A Drawing Storage class which stores the drawing's shape, color, stroke width.
 * 
 * @author Munkhbayar Ganbold
 * @version November 2015
 */
public class DrawingStorage {
    //instance variables
    /** A current shape of the drawing. */
    private final Shape myShape;
    
    /** A current color of the drawing. */
    private final Color myColor;
    
    /** A current stroke width of the drawing.  */
    private final Stroke myStroke;
    
    /**
     *  Constructs instance variables of drawing storage with given datas.
     * @param theShape of drawing
     * @param theColor of drawing
     * @param theStroke width of drawing
     */
    public DrawingStorage(final Shape theShape, final Color theColor, final int theStroke) {
        myStroke = new BasicStroke(theStroke);
        myShape = theShape;
        myColor = theColor;
    }
    
    /**
     * Accessor method for getting current Shape of Drawing.
     * @return Shape
     */
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * Accessor method for getting current color of drawing.
     * @return Color
     */
    public Color getColor() {
        return myColor;
    }
    
    /**
     *  Accessor method for getting current stroke width of drawing.
     * @return Stroke width
     */
    public Stroke getStroke() {
        return myStroke;
    }
}
