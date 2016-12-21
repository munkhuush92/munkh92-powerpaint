/*
 * TCSS 305 - Power Paint Custom Class for Color Icon.
 */
package model;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JColorChooser;

/**
 *  A Custom Color class which implements Icon to use as Color item icon.
 *  
 * @author Munkhbayar Ganbold
 * @version November 2015
 */
public class ColorItemIcon {
    /** A default frame color of the icon.  */
    private static final Color DEFAULT_FRAME_COLOR = Color.BLACK;
    
    /** A current color of the icon.    */
    private Color myColor;
    /** A dynamic icon which is being created in this class.    */
    private final DynamicIcon myIcon;
    
    /** Action for color icon.  */
    private final ColorChooserAction myAction;
    
    /** 
     * Initializes instance variables of Color Item icon. Also
     * it creates its corresponding action.
     */
    public ColorItemIcon() {
        myColor = Color.BLACK;
        myIcon = new DynamicIcon();
        myAction = new ColorChooserAction("Color...");
    }
    
    /**
     *  Accessor method for getting the action of Color icon.
     * @return Color Chooser Action
     */           
    public ColorChooserAction getAction() {
        return myAction;
    }

    /**
     *  Accessor method for getting current color the Color icon.
     * @return Color
     */
    public Color getColor() {
        return myColor;
    }
    
    /**
     * Accessor method for the current icon.
     * @return Icon
     */
    public Icon getIcon() {
        return myIcon;
    }
    /**
     * Inner class which implements the dynamic icon for Color menu item.
     * @author Munkhbayar Ganbold
     * @version November 2015
     */
    class DynamicIcon implements Icon {
        /** constant width for color icon.  */
        private static final int ICON_WIDTH = 20;
        /** constant height for color icon.  */
        private static final int ICON_HEIGHT = 20;
        
        /**
         * Paints the icon with given x and y coordinate using graphics.
         * @param theComponent no usage
         * @param thGraphics used to paint the icon
         * @param theX coordinate
         * @param theY coordinate
         */
        @Override
        public void paintIcon(final Component theComponent, 
                              final Graphics theGraphics, final int theX, final int theY) {
            
            theGraphics.setColor(myColor);
            theGraphics.fillRect(theX, theY, getIconWidth(), getIconHeight());
            //drawing the black frame for the icon.
            theGraphics.setColor(DEFAULT_FRAME_COLOR);
            theGraphics.drawRect(theX, theY, getIconWidth(), getIconHeight());
        }
        /**
         * Accessor method for getting width of icon.
         */
        @Override
        public int getIconWidth() {
            return ICON_WIDTH;
        }

        /**
         * Accessor method for getting height of icon.
         */
        @Override
        public int getIconHeight() {
            return ICON_HEIGHT;
        }
    }
    
    /**
     * Inner class which implements the action of Color menu item icon.
     * it triggers JColorChooser for the user to choose the color.
     * @author Munkhbayar Ganbold
     * @version November 2015
     */
    class ColorChooserAction extends AbstractAction {
            /**  
             * A generated serial version UID for object Serialization. 
             * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
             */
        private static final long serialVersionUID  = -2193928786463255262L;
        
        /**
         * Initializes the Actions Fields.
         * @param theText the Text for this Action. 
         */
        ColorChooserAction(final String theText) {
            super(theText);
            putValue(MNEMONIC_KEY, KeyEvent.VK_C);
        }
        
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myColor = JColorChooser.showDialog(null, "A Color Chooser", null);
        }
    };
}
