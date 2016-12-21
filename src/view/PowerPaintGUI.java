/*
 * TCSS 305 - Power Paint GUI class.
 */
package view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.ColorItemIcon;
/**
 *  Contains the content of GUI part of Power Paint with all the components.
 * 
 * @author Munkhbayar Ganbold
 * @version October 2015
 */
public class PowerPaintGUI implements PropertyChangeListener {

    /**  A generated serial version UID for object Serialization. */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -6181710142318138424L; 
    
    /** the location of UW logo icon.   */
    private static final String UW_ICON_LOCATION = "images/w.gif";
    
    /**
     * The minor tick spacing for the FPS slider.
     */
    private static final int MINOR_TICK_SPACING = 1;

    /**
     * The major tick spacing for the FPS slider.
     */
    private static final int MAJOR_TICK_SPACING = 5;
    
    /**
     * The maximum frames per second at which the simulation will run.
     */
    private static final int MAX_FRAMES_PER_SECOND = 20;

    /**
     * The initial frames per second at which the simulation will run.
     */
    private static final int INITIAL_FRAMES_PER_SECOND = 1;
    
    /** A main panel contains a drawing panel and toggle buttons.   */
    private final JPanel myMainPanel;
   
    /** the main frame we will be using for GUI. */
    private final JFrame myFrame;
    
    /** A drawing panel where all drawings will be drawn.   */
    private final DrawingArea myDrawingPanel;

    /** A custom color icon for Color menu item.    */
    private final ColorItemIcon myColorIcon;

    /** GUI custom class which initializes menu bar and toolbar.    */
    private GUIMenuBarToolBar myMenuBarToolbar;

    /** A undo menu item.   */
    private JMenuItem myUndoMenuItem;
    
    /** my redo JMenu item for menu bar.    */
    private JMenuItem myRedoMenuItem;
    
    /** my undo all Jmenu item for menu bar. */
    private JMenuItem myUndoAllMenuItem;
    
    /**
     * A constructor which initializes necessary instance variables.
     */
    public PowerPaintGUI() {
        super();
        myMainPanel = new JPanel();
        myFrame = new JFrame("PowerPaint");
        myColorIcon = new ColorItemIcon();
        myDrawingPanel = new DrawingArea();   
    }

    /**
     * Build the menu bar for this GUI. This method will need
     * to be called where access to a JFrame occurs. 
     *  It also create GUIMenuBarToolbar class to builds tool bars and menu bars.
     * @return the menu bar for this GUI
     */
    private JMenuBar createMenu() {
        final JMenuBar menuBar = new JMenuBar();
        //creates GUI menu bar and tool bar class to build them.
        myMenuBarToolbar = new GUIMenuBarToolBar(myDrawingPanel);
        menuBar.add(buildFileMenu());
        menuBar.add(myMenuBarToolbar.buildToolsMenu());
        menuBar.add(buildOptionsMenu());
        menuBar.add(buildHelpMenu());
        return menuBar;
    }
    
    /**
     *  Builds the help menu with menu item which triggers the info.
     * @return JMenuItem 
     */
    private JMenuItem buildHelpMenu() {
        final JMenu menuItem = new JMenu("Help");
        menuItem.setMnemonic(KeyEvent.VK_H);
        
        //menu items
        final JMenuItem aboutItem = new JMenuItem("About...");
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
                JOptionPane.showMessageDialog(myFrame, "TCSS 305 PowerPaint, Autumn 2015.", 
                                   "Power Paint", 0, new ImageIcon(UW_ICON_LOCATION));    
            } 
        });

        menuItem.add(aboutItem);
        return menuItem;
    }
    
    /**
     * Implements File menu items and returns the list that contains them.
     * @return Stack of JMenuItem
     */
    public Stack<JMenuItem> getFileMenuItems() {
        myUndoMenuItem = new JMenuItem("Undo");
        myUndoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                                                             InputEvent.CTRL_MASK));
        myUndoMenuItem.setEnabled(false);
        myUndoMenuItem.addActionListener(new ActionListener() {
     
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
                myDrawingPanel.undoChange();
                final boolean canUndo = myDrawingPanel.isUndoneable();
                myUndoMenuItem.setEnabled(canUndo);
                myUndoAllMenuItem.setEnabled(canUndo);
                myRedoMenuItem.setEnabled(true);
            }
        });
        
        myDrawingPanel.addPropertyChangeListener(this);
        //redo
        myRedoMenuItem = new JMenuItem("Redo");
        myRedoMenuItem.setMnemonic(KeyEvent.VK_U);
        myRedoMenuItem.setEnabled(false);
        myRedoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
                                                             InputEvent.CTRL_MASK));
        myRedoMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myDrawingPanel.redo();
                myRedoMenuItem.setEnabled(myDrawingPanel.isReDoable());
                final boolean canUndo = myDrawingPanel.isUndoneable();
                myUndoMenuItem.setEnabled(canUndo);
                myUndoAllMenuItem.setEnabled(canUndo);
            }
        });
        
        //undo all
        myUndoAllMenuItem = new JMenuItem("Undo All Changes");
        myUndoAllMenuItem.setMnemonic(KeyEvent.VK_U);
        myUndoAllMenuItem.setEnabled(myDrawingPanel.isUndoneable());
        myUndoAllMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myDrawingPanel.clearScreen();
                myUndoMenuItem.setEnabled(false);
                myRedoMenuItem.setEnabled(false);
                final boolean canUndo = myDrawingPanel.isUndoneable();
                myUndoAllMenuItem.setEnabled(canUndo); 
            }
        });
        
        final Stack<JMenuItem> menuStack = new Stack<JMenuItem>();
        menuStack.push(myUndoAllMenuItem);
        menuStack.push(myRedoMenuItem);
        menuStack.push(myUndoMenuItem);
        return menuStack;
         
    }
    
    /**
     *  Builds the file menu with its menu items.
     * @return JMenu
     */
    private JMenu buildFileMenu() {
        final JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_X);
        exitMenuItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
        final Stack<JMenuItem> list = getFileMenuItems();
        while (!list.isEmpty()) {
            menu.add(list.pop());
        }
        menu.addSeparator();
        menu.add(exitMenuItem);  
        return menu;
    }    
    /**
     *  Builds option menu with its menu items.
     * @return JMenu
     */
    private JMenu buildOptionsMenu() {
        final JMenu menu = new JMenu("Options");
        menu.setMnemonic(KeyEvent.VK_O);
        //menu items
        final JCheckBoxMenuItem grid = new JCheckBoxMenuItem("Grid");
        grid.setMnemonic(KeyEvent.VK_G);
        grid.addItemListener(new ItemListener() {
          
            @Override
            public void itemStateChanged(final ItemEvent theEvent) {
                if (theEvent.getStateChange() == 1) {
                    myDrawingPanel.setGridOn(true);
                } else {
                    myDrawingPanel.setGridOn(false);
                } 
                myDrawingPanel.repaint();
            }
            
        });
        final JMenu thickness = new JMenu("Thickness");
        
        final JSlider sliderForThickness = new JSlider(JSlider.HORIZONTAL,
                                     0, MAX_FRAMES_PER_SECOND, INITIAL_FRAMES_PER_SECOND);
        sliderForThickness.setMajorTickSpacing(MAJOR_TICK_SPACING);
        sliderForThickness.setMinorTickSpacing(MINOR_TICK_SPACING);
        sliderForThickness.setPaintLabels(true);
        sliderForThickness.setPaintTicks(true);
        
        thickness.add(sliderForThickness);
        thickness.setMnemonic(KeyEvent.VK_T);
        
        final JMenuItem color = new JMenuItem("Color...");
        //setting the action to pull out the color.
        color.setAction(myColorIcon.getAction());
        //update icon color;
        /**
         * Inner class which handles change of color on color icon.
         * @author munkhbayarganbileg
         */
        
        class Updater implements ChangeListener {
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                myDrawingPanel.setCurrentColor(myColorIcon.getColor());
                final int value = sliderForThickness.getValue();
                myDrawingPanel.setStrokeWidth(value);
            }
        }  
        final Updater updater = new Updater();
        color.addChangeListener(updater);
        sliderForThickness.addChangeListener(updater);
        color.setMnemonic(KeyEvent.VK_C);
        color.setIcon(myColorIcon.getIcon());
        menu.add(grid);
        menu.addSeparator();
        menu.add(thickness);
        menu.addSeparator();
        menu.add(color);
        return menu;
    }
    /**
     * Creates a JFrame to demonstrate this panel.
     * It is OK, even typical to include a main method 
     * in the same class file as a GUI for testing purposes. 
     * 
     * @param theArgs Command line arguments, ignored.
     */
    public static void main(final String[] theArgs) {
            /* Use an appropriate Look and Feel */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final PowerPaintGUI window = new PowerPaintGUI();
                window.createAndShowGUI();  
                }
            });
    }
   
    /**
     * Creates a frame and puts all the containers in the frame.
     * It runs the graphical application.  
     */
    private void createAndShowGUI() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //sets up drawing Panel 
        myMainPanel.setLayout(new BorderLayout());
        myFrame.setMinimumSize(myDrawingPanel.getMinimumSize());
        myDrawingPanel.setPreferredSize(myDrawingPanel.getPreferredSize());
        myDrawingPanel.setOpaque(true);
        //starts with stroke width size of 1.
        myDrawingPanel.setStrokeWidth(1);
        myMainPanel.add(myDrawingPanel, BorderLayout.CENTER);
        //add the tool bars to the main panel to the south side of the window
        myFrame.setJMenuBar(this.createMenu());
        myMainPanel.add(myMenuBarToolbar.createToolbar(), BorderLayout.SOUTH);
        // set icon for frame.
        final ImageIcon frameIcon = new ImageIcon(UW_ICON_LOCATION);
        myFrame.setIconImage(frameIcon.getImage());
        myFrame.setContentPane(myMainPanel);
        myFrame.pack();
        myFrame.setVisible(true);
        
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (theEvt.getPropertyName().equals(DrawingArea.UNDO_PROPERTY_CHANGE)) {
            myUndoMenuItem.setEnabled(true);
            myUndoAllMenuItem.setEnabled(true);
        }
        
    }
}
