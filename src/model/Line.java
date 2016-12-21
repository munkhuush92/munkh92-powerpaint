/*
 * TCSS 305 - Power Paint - Line Tool Class.
 */
package model;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 *  Line tool class which uses Line2D to draw the line.
 * 
 * @author Munkhbayar Ganbold
 * @version November 2015
 */
public class Line extends AbstractTool {
    /** A current Line shape being constructed. */
    private Shape myShape;
    
    /** X coordinate of starting point of line.  */
    private int myStartX;
    
    /** Y coordinate of starting point of line.  */
    private int myStartY;
    
    /** Y coordinate of ending point of line.  */
    private int myEndX;
    
    /** Y coordinate of staring point of line.  */
    private int myEndY;
    
    /**
     * Initializes the instance variables and creates new Line2D line.
     */
    public Line() {
        super();
        myStartX = 0;
        myStartY = 0;
        myEndX = myStartX;
        myEndY = myStartY;
        myShape = new Line2D.Double();
    }   
    
    /**
     * Everytime the user pressed mouse it should create new line. 
     * Therefore when it starts, it creates new 2D line with given x and y coordinate.
     * @param theX coordinate of starting point.
     * @param theY coordinate of starting point.
     * @return new Line2D
     */
    @Override
    public Shape start(final int theX, final int theY) {
        myStartX = theX;
        myStartY = theY;
        myEndX = myStartX; 
        myEndY = myStartY;
        myShape = new Line2D.Double(myStartX, myStartY, myEndX, myEndY);
        return myShape;
    }
    
    /**
     * This method moves ending point of 2D line with given x and y coordinate.
     * @param theX coordinate of moving point.
     * @param theY coordinate of moving point.
     * @return Line2D with different ending point.
     */
    @Override
    public Shape hover(final int theX, final int theY) {
        myEndX = theX;
        myEndY = theY;
        ((Line2D) myShape).setLine(myStartX, myStartY, myEndX, myEndY);
        return myShape;
    }
    
    /**
     * This method ends 2D line with given x and y coordinate.
     * @param theX coordinate of ending point.
     * @param theY coordinate of ending point.
     * @return Line2D
     */
    @Override
    public Shape end(final int theX, final int theY) {
        ((Line2D) myShape).setLine(myStartX, myStartY, theX, theY);
        return myShape;
    }
    
    /**
     * Accessor method for getting name of tool.
     * @return theName of Tool.
     */
    @Override
    public String getToolName() {
        return getClass().getSimpleName();
    }
}
