/*
 * TCSS 305 - Power Paint Abstract Rectangle class.
 */
package model;

import java.awt.Shape;
import java.awt.geom.RectangularShape;
/**
 *  Abstract Rectangular Shape shape which is used by Ellipse and Rectangle tools.
 * 
 * @author Munkhbayar Ganbold
 * @version November 2015
 */
public abstract class AbstractRectangle extends AbstractTool {
    /** A current rectangular shape which is being drawn.   */
    private final Shape myShape;
    
    /** A starting x coordinate of the shape.   */
    private int myStartX;
    
    /** A starting y coordinate of the shape.   */
    private int myStartY;
    
    /** A height of the current Shape.  */
    private int myHeight;
    
    /** A width of the current Shape.  */
    private int myWidth;
    
    /**
     * It initializes the shape with given rectangular shape
     * and constructs that shape.
     * @param theShape new Rectangular Shape
     */
    public AbstractRectangle(final RectangularShape theShape) {
        super();
        myShape = theShape;
    }
    
    /**
     *  Creates rectangular shape on given x and y coordinate.
     * @param theX Starting x coordinate of Shape
     * @param theY Starting y coordinate of Shape
     * @return new Rectangular Shape.
     */
    public Shape start(final int theX, final int theY) {
        myStartX = theX;
        myStartY = theY;
        myWidth = 0;
        myHeight = 0;
        //size should be zero because it starts with 0 size.
        ((RectangularShape) myShape).setFrame(myStartX, myStartY, myWidth, myHeight);
        return myShape;
    }
    
    /**
     *  Determines the width and height of currently drawing Shape and creates that shape.
     *  If the user draws opposite direction of original starting point, then 
     *   given point will be new x and y coordinate.
     * @param theX coordinate of Shape
     * @param theY coordinate of Shape
     * @return new Rectangular Shape.
     */
    public Shape hover(final int theX, final int theY) {
        final int diffX = theX - myStartX;
        final int diffY = theY - myStartY;
        myWidth = diffX;
        myHeight = diffY;
        if (diffX > 0) { 
            ((RectangularShape) myShape).setFrame(myStartX, myStartY,
                                                 Math.abs(diffX), Math.abs(diffY));
        } else {
            ((RectangularShape) myShape).setFrame(theX, theY, 
                                                  Math.abs(diffX), Math.abs(diffY));
        }
        return myShape;
    }
    
    /**
     * Creates shape with starting point and finishes drawing shape 
     * with given x and y coordinates.
     * @param theX coordinate of ending point
     * @param theY coordinate of ending point
     * @return new Rectangular shape
     */
    public Shape end(final int theX, final int theY) {

        return myShape;
    }
}
