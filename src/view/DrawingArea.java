/*
 * TCSS 305 - Power Paint - Drawing Area class.
 */
package view;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JPanel;

import model.DrawingStorage;
import model.Tool;
/**
 *  Drawing Panel where the user uses variety of tools to draw things.
 *  @author Munkhbayar Ganbold
 *  @version November 2015 
 */
public class DrawingArea extends JPanel {
    
    /** A property name of change.   */
    public static final String UNDO_PROPERTY_CHANGE = "Shape has been created";
    
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 3316811765624525730L;
    
    /** The default width value. */
    private static final int WIDTH = 400; 
    
    /** The default height value. */
    private static final int HEIGHT = 200;

    /** The default background color for drawing panel. */
    private static final Color DEFAULT_BACK_COLOR = Color.WHITE;
    
    /** The default stroke width for drawing tool. */
    private static final int DEFAULT_STROKE_WIDTH = 1;
    
    /** The minimum size this component should be. */
    private static final Dimension MIN_SIZE = new Dimension(WIDTH, HEIGHT);
    
    /** A default grid line space.  */
    private static final int GRID_DEFAULT_SPACING = 10;
    
    /** A default cross hair cursor for drawing panel.  */
    private static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.CROSSHAIR_CURSOR);
    
    
    
    /** boolean for the user is currently drawing or not.   */
    private boolean myDrawingHovering;
    
    /** the width of drawing tool's stroke. */
    private int myStrokeWidth;
    
    /** A current tool is being used to create the drawings.    */
    private Tool myTool;
    
    /** A current color for drawing panel.  */
    private Color myCurrentColor;
    
    /** A stack collection of my drawing storages which contains shape, color, stroke.  */
    private final Stack<DrawingStorage> myDrawingThings;
    
    /** A stack collection of my redo drawings. */
    private final Stack<DrawingStorage> myRedoDrawings;
    
    /** A current Drawing Storage that is being used on drawing panel.  */
    private DrawingStorage myCurrentDrawing;
    
    /** A space between the grid lines on drawing panel.    */
    private int myGridLineSpacing;
    
    /** boolean value for if the grid is on or off on the drawing panel.    */
    private boolean myGridIsOn;
   

    /**
     * Constructs empty drawing panel with default color white and default stroke size of 1.
     * initializes all the necessary fields.
     */
    public DrawingArea() {
        super();
        myStrokeWidth = DEFAULT_STROKE_WIDTH;
        myDrawingThings = new Stack<DrawingStorage>();
        myRedoDrawings = new Stack<DrawingStorage>();
        setBackground(DEFAULT_BACK_COLOR);
        myDrawingHovering = false;
        myGridLineSpacing = GRID_DEFAULT_SPACING;
        myCurrentDrawing = new DrawingStorage(null, myCurrentColor, DEFAULT_STROKE_WIDTH);
        myCurrentColor = Color.BLACK; 
        addListenerToPanel(); 
    }  
    
    /** Adds mouse listeners to the drawing panel.
     */
    private void addListenerToPanel() {
        //set the cursor to cross hair before drawing.
        setCursor(DEFAULT_CURSOR); 
        addMouseListener(new MouseEventHandler());
        //mouse motion listener
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(final MouseEvent theEvent) {    
                if (!isMyThicknessZero()) {
                    final DrawingStorage currentDrawingStorage = new DrawingStorage(
                           myTool.hover(theEvent.getX(), theEvent.getY()), 
                           myCurrentColor, myStrokeWidth);
                    setCurrentDrawing(currentDrawingStorage);
                    repaint();
                }
            } 
        });
        
    }
  
    /**
     * Changes grid line spacing for drawing panel.
     * @param theNewSpace for grid lines
     */
    public void changeGridSpaing(final int theNewSpace) {
        myGridLineSpacing = theNewSpace;
    }
    
    
    
    /**
     *  Mutator method for changing the current color of drawing.
     * @param theColor new Color of drawing.
     */
    public void setCurrentColor(final Color theColor) {
        myCurrentColor = theColor;
    }
    
    /**
     * Clears the all shapes and drawings on the drawing panel.
     */
    public void clearScreen() {
        final Iterator<DrawingStorage> it = myDrawingThings.iterator();
        while (it.hasNext()) {
            myDrawingThings.pop();
        }
        repaint();
    }
    
    /**
     *  Mutator method for changing the current drawing.
     * @param theDrawData of current Drawing
     */
    public void setCurrentDrawing(final DrawingStorage theDrawData) {
        myCurrentDrawing = theDrawData;
    }

    /** (EXTRA CREDIT)
     * It undo the last change on the drawing panel.
     */
    public void undoChange() {
        if (isUndoneable()) {
            myRedoDrawings.push(myDrawingThings.pop());
        }
        repaint();
    }
    
    /** (EXTRA CREDIT)
     * It redo the last change on the drawing panel.
     */
    public void redo() {
        if (isReDoable()) {
            myDrawingThings.push(myRedoDrawings.pop());
        }
        repaint();
    }
    
    /**
     * Checks if the user can redo the last change depending 
     * on stack.
     * @return boolean Whether it is redoable or not.
     */
    public boolean isReDoable() {
        return !myRedoDrawings.isEmpty();
    }

    /**
     * Checks if the user can undo the last change depending 
     * on stack.
     * @return boolean Whether it is undoable or not.
     */
    public boolean isUndoneable() {
        return !myDrawingThings.isEmpty();
    }
    /** Checks the thickness of the current drawing's stroke.
     * @return boolean whether to draw or not.
     */
    public boolean isMyThicknessZero() {
        return myStrokeWidth == 0;
    }
    
    /**
     *  Mutator method for changing the drawing state. 
     * @param theDrawingState of tool.
     */
    public void setDrawingHovering(final boolean theDrawingState) {
        myDrawingHovering = theDrawingState;
    }
    
    /** 
     * Sets the grid (gray lines) on the drawing panel. 
     * @param theGridIsOn the drawing panel.
     */
    public void setGridOn(final boolean theGridIsOn) {
        myGridIsOn = theGridIsOn;
    }
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (final DrawingStorage s : myDrawingThings) {
            g2d.setPaint(s.getColor());
            g2d.setStroke(s.getStroke());
            g2d.draw(s.getShape());
        } 
     
        // if my stroke size is 0, then it should produce any drawing.
        // if the user drags the mouse, there should be current shape.
        if (myDrawingHovering) {
            g2d.setPaint(myCurrentDrawing.getColor());
            g2d.setStroke(new BasicStroke(myStrokeWidth));
            g2d.draw(myCurrentDrawing.getShape());
        } 
        
        // if grid is on, then drawing the lines
        int xCoordinate = 0;
        int yCoordinate = 0;
        
        if (myGridIsOn) {
            g2d.setStroke(new BasicStroke(DEFAULT_STROKE_WIDTH));
            g2d.setPaint(Color.gray);
            while (xCoordinate < getWidth()) {
                xCoordinate += myGridLineSpacing; 
                g2d.drawLine(xCoordinate, 0, xCoordinate, getHeight());
            }
            
            while (yCoordinate < getHeight()) {
                yCoordinate += myGridLineSpacing; 
                g2d.drawLine(0, yCoordinate, getWidth(), yCoordinate);
            }
        }
    };
    
    /**
     * Mutator method for changing width of tool's stroke.
     * @param theStrokeWidth of drawing.
     */
    public void setStrokeWidth(final int theStrokeWidth) {
        myStrokeWidth = theStrokeWidth;
    }
 
    /**
     * Accessor method for getting minimum size for drawing panel.
     */
    @Override
    public Dimension getMinimumSize() {
        return MIN_SIZE;
    }
 
    /**
     * Accessor method for getting preferred size for drawing panel.
     */
    @Override
    public Dimension getPreferredSize() {
        return MIN_SIZE;
    }
    
    /**
     *  Mutator method for changing current drawing tool.
     * @param theCurrentTool of drawing panel
     */
    public void setTool(final Tool theCurrentTool) { 
        myTool = theCurrentTool;
    }
    /**
     * Accessor method for getting current drawing tool.
     * @return Tool of drawing panel.
     */
    public Tool getTool() {
        return myTool;
    }
    
    /**
     *  Adds the drawing storage to stack of drawing storage.
     * @param theItem Drawing Storage.
     */
    public void addItemToStack(final DrawingStorage theItem) {
        myDrawingThings.add(theItem);
    }
    
    /**
     * Accessor method for getting current Drawing Storage.
     * @return Current DrawingStorage
     */
    public DrawingStorage getCurrentDrawing() {
        return myCurrentDrawing;
    }
    
    /**
     * Accesor method for getting stroke width.
     * @return Integer width of Stroke
     */
    public int getStroke() {
        return myStrokeWidth;
    }
    
    /**
     * Accessor method for getting current color of drawing tool.
     * @return Color
     */
    public Color getColor() {
        return myCurrentColor;
    }
    
    /** Inner Class for a mouse Listener.
     */
    class MouseEventHandler extends MouseAdapter {
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            if (!isMyThicknessZero()) {
                setDrawingHovering(true);
                setCurrentDrawing(
                    new DrawingStorage(myTool.start(theEvent.getX(),
                    theEvent.getY()), myCurrentColor, 
                                       myStrokeWidth));
                repaint();
            }
        }
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            if (!isMyThicknessZero()) {
                setCurrentDrawing(
                          new DrawingStorage(myTool.end(theEvent.getX(), 
                                 theEvent.getY()), myCurrentColor,
                                             getStroke()));
                addItemToStack(myCurrentDrawing);
                setDrawingHovering(false);
                //here it fires change.
                firePropertyChange(UNDO_PROPERTY_CHANGE, !isUndoneable(), isUndoneable());
                repaint();   
            }
        }
   
    }
    
}