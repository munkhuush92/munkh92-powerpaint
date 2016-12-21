/*
 * TCSS 305 - Power Paint - Pencil Tool Class.
 */
package model;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

/**
 *  Pencil tool class which uses General Path to generate the drawing.
 * 
 * @author Munkhbayar Ganbold
 * @version November 2015
 */
public class Pencil extends AbstractTool {
    /** A current path drawing shape being constructed. */
    private Shape myShape;
    
   
    @Override
    public Shape start(final int theX, final int theY) {
        myShape = new GeneralPath();
        ((GeneralPath) myShape).moveTo(theX, theY);
        return  myShape;
    }

    @Override
    public Shape hover(final int theX, final int theY) {
        ((GeneralPath) myShape).lineTo(theX, theY);
        return myShape;
    }

    @Override
    public Shape end(final int theX, final int theY) {
        ((GeneralPath) myShape).lineTo(theX, theY);
        return myShape;
    }
    @Override
    public String getToolName() {
        return getClass().getSimpleName();
    }
}
