/*
 * TCSS 305 - Power Paint - Rectangle Tool Class.
 */
package model;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 *  A parent class (Abstract) that get inherited all tools.
 * 
 * @author Munkhbayar Ganbold
 * @version October 2015
 */
public class Rectangle extends AbstractRectangle {

    /**
     * Provides a 2D Rectangle shape to its parent Abstract Rectangular class.
     * Therefore, when it gets created, it draws 2D rectangle.
     */
    public Rectangle() {
        super(new Rectangle2D.Double()); 
    }
    @Override
    public String getToolName() {
        return getClass().getSimpleName();
    }

    /**
     * Overrides its parent class's end method because it has its own Rectangule2D shape.
     * Creates a rectangle shape with given coordinates.
     * @param theX coordinate of ending point
     * @param theY coordinate of ending point
     * @return Rectangle2D Shape
     */
    @Override 
    public Shape end(final int theX, final int theY) {
        final Rectangle2D s = (Rectangle2D) super.end(theX, theY);
        return new Rectangle2D.Double(s.getX(), s.getY(), s.getWidth(), s.getHeight());   
    }
}
