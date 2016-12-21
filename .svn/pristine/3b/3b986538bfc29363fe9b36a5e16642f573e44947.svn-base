/*
 * TCSS 305 - Power Paint - Ellipse Class.
 */
package model;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 *  Ellipse tool class which extends AbstractRectangle class.
 *  This class uses Ellipse2D to create the ellipse shape.
 * @author Munkhbayar Ganbold
 * @version November 2015
 */
public class Ellipse extends AbstractRectangle {
    
    /**
     * Provides Ellipse2D shape to its parent Abstract Rectangular class.
     */
    public Ellipse() {
        super(new Ellipse2D.Double());
    }
    /** 
     * Overrides the parent method to provide its own name.
     * @return name of Shape.
     */
    @Override
    public String getToolName() {
        return getClass().getSimpleName();
    }
    
    /**
     * Overrides its parent class's end method because it has its own ellipse shape.
     * Creates an ellipse shape with given coordinates.
     * @param theX coordinate of ending point
     * @param theY coordinate of ending point
     * @return Ellipse Shape
     */
    @Override 
    public Shape end(final int theX, final int theY) {
        final Ellipse2D s = (Ellipse2D) super.end(theX, theY);
        return new Ellipse2D.Double(s.getX(), s.getY(), s.getWidth(), s.getHeight());
        
    }

}
