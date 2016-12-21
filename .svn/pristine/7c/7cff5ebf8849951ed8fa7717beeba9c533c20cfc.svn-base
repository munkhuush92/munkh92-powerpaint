/*
 * TCSS 305 - Power Paint Interface tool class.
 */
package model;
import java.awt.Shape;

/**
 *  An interface for tools which will be used to display shapes
 *   and objects in the drawing panel.
 * 
 * @author Munkhbayar Ganbold
 * @version October 2015
 */
public interface Tool {
    /**
     * Starts drawing the shape using tool's given x and y coordinates.
     * @param theX coordinate of starting point.
     * @param theY coordinate of starting point.
     * @return Shape 
     */
    Shape start(final int theX, final int theY);
    
    /**
     * Shape hovers in the drawing panel with given x and y coordinates.
     * @param theX coordinate of moving point.
     * @param theY coordinate of moving point.
     * @return Hovering Shape
     */
    Shape hover(final int theX, final int theY);
    
    /**
     * Ends hovering and creates the shape with given ending point.
     * @param theX coordinate of ending point.
     * @param theY coordinate of ending point.
     * @return Shape
     */
    Shape end(final int theX, final int theY);
    
    /**
     * Accessor method for getting name of tool.
     * @return theName of tool.
     */
    String getToolName();
    
}
