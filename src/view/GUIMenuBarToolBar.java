/*
 * TCSS 305 - Power Paint - GUI MenuBar ToolBar class.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import model.Ellipse;
import model.Line;
import model.Pencil;
import model.Rectangle;
import model.Tool;

/**
 *  Creates the menu bars and toolbars for the frame.
 *  Also handles the event for property change listener.
 * 
 * @author Munkhbayar Ganbold
 * @version November 2015
 */
public class GUIMenuBarToolBar {
    
    /** A group for toolbar toggle buttons. */
    private final ButtonGroup myToolBarButtonGroup;
    
    /** A group for tool menu radio buttons. */
    private final ButtonGroup myMenuButtonGroup;
    
    /** A group of toggle buttons for toolbar.  */
    private final List<JToggleButton> myToggleButtons;
    
    /** a current drawing panel will be drawn on.
     */
    private final DrawingArea myDrawingPanel;
    
    /**
     * Initializes all instance variables.
     * @param theDrawingPanel is Current drawing panel.
     */
    public GUIMenuBarToolBar(final DrawingArea theDrawingPanel) {
        
        myDrawingPanel = theDrawingPanel;
        myToolBarButtonGroup = new ButtonGroup();
        myMenuButtonGroup = new ButtonGroup();
        myToggleButtons = new ArrayList<JToggleButton>();  
    }
    /**
     *  Builds the tools menu with drawing tools which function same as tools bar items.
     * @return JMenu
     */
    public JMenu buildToolsMenu() {
        final JMenu menu = new JMenu("Tools");
        menu.setMnemonic(KeyEvent.VK_T);
        final Action pencilAction = new ToolAction(new Pencil(), findIcon("Pencil"));
        final JRadioButtonMenuItem pencilRadioButton = new JRadioButtonMenuItem(pencilAction);
        final JToggleButton pencilToolBarButton = new JToggleButton(pencilAction); 
        //initially selected tool is pencil
        pencilAction.putValue(Action.SELECTED_KEY, true);
        myDrawingPanel.setTool(new Pencil());
        //Line
        final Action lineAction = new ToolAction(new Line(), findIcon("Line"));
        final JRadioButtonMenuItem lineRadioButton = new JRadioButtonMenuItem(lineAction);
        final JToggleButton lineToolBarButton = new JToggleButton(lineAction);
        
        //Rectangle
        final Action recAction = new ToolAction(new Rectangle(), findIcon("Rectangle"));
        final JRadioButtonMenuItem recRadioButton = new JRadioButtonMenuItem(recAction);
        final JToggleButton recToolBarButton = new JToggleButton(recAction);
        //Ellipse
        final Action ellipseAction = new ToolAction(new Ellipse(), findIcon("Ellipse"));
        final JRadioButtonMenuItem ellipseRadioButton = 
                        new JRadioButtonMenuItem(ellipseAction);
        final JToggleButton ellipseToolBarButton = new JToggleButton(ellipseAction);
        
        myToggleButtons.add(pencilToolBarButton);
        myToggleButtons.add(lineToolBarButton);
        myToggleButtons.add(recToolBarButton);
        myToggleButtons.add(ellipseToolBarButton);
        menu.add(pencilRadioButton);
        menu.add(lineRadioButton);
        menu.add(recRadioButton);
        menu.add(ellipseRadioButton);
        
        myToolBarButtonGroup.add(pencilToolBarButton);
        myToolBarButtonGroup.add(lineToolBarButton);
        myToolBarButtonGroup.add(recToolBarButton);
        myToolBarButtonGroup.add(ellipseToolBarButton);
        myMenuButtonGroup.add(pencilRadioButton);   
        myMenuButtonGroup.add(ellipseRadioButton); 
        myMenuButtonGroup.add(recRadioButton); 
        myMenuButtonGroup.add(lineRadioButton);  
        return menu;
    }
    
    /**
     * Creates the tool bar with the drawing tools which functions same as tools menu items.
     * @return JToolBar
     */
    public JToolBar createToolbar() {
        final JToolBar toolBar = new JToolBar("tools");
        for (int i = 0; i < myToggleButtons.size(); i++) {
            toolBar.add(myToggleButtons.get(i));  
        }
        return toolBar;  
    }
    
    /**
     *  Finds the icon from the folder and return the image icon with corresponding item.
     * @param theToolName String state of tool
     * @return ImageIcon of tool
     */
    private ImageIcon findIcon(final String theToolName) {
        final String imgLocation = "images/"
                        + theToolName;
        return new ImageIcon(imgLocation + ".gif");
    }
    
    /**
     *  Determines the mnemomic value depeding on first letter of passed string.
     * @param theToolName String state of toolß
     * @return Integer MnemonicKey value
     */
    private int findMnemonicKey(final String theToolName) {
        int key = 0;
        final char firstLetter = theToolName.charAt(0);
        switch (firstLetter) {
            case 'P': key = KeyEvent.VK_P;
                break;
            case 'L': key = KeyEvent.VK_L;
                break;
            case 'R': key = KeyEvent.VK_R;
                break;
            case 'E': key = KeyEvent.VK_E;
                break;
            default: 
                break;

        } 
        return key;
    }
    /**
     * Inner class which contains the tool actions.
     * @author Munkhbayar Ganbold
     * @version Autumn 2015
     */
    class ToolAction extends AbstractAction {
        
        /**  A generated serial version UID for object Serialization. */
        private static final long serialVersionUID = 1L;
        
        /** A name of tool action.  */
        private final String myName;
        /** a current tool of drawing panel.    */
        private final Tool myCurrentTool;
        
        /**
         *  Constructs the tool action which controls the tool bar items and tool
         *   menu items at the same time.
         * @param theTool of current tool
         * @param theIcon of tool
         */
        public ToolAction(final Tool theTool, final ImageIcon theIcon) {
            super(theTool.getClass().getSimpleName(), theIcon);
            putValue(MNEMONIC_KEY, findMnemonicKey(theTool.getClass().getSimpleName()));
            myName = theTool.getClass().getSimpleName();
            myCurrentTool = theTool; 
        }
        
        @Override
        public void actionPerformed(final ActionEvent theEvent) { 
            putValue(Action.SELECTED_KEY, true);
            myDrawingPanel.setTool(myCurrentTool);
        }   
        /**
         * Accessor method for getting the name of tool action.
         * @return theName of tool action
         */
        public String getToolName() {
            return myName;
        }
    }
    
   

}
