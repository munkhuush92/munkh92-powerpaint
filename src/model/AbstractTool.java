/*
 * TCSS 305 - Power Paint Abstract Tool class.
 */
package model;
/**
 *  A parent class (Abstract) that get inherited all tools.
 * 
 * @author Munkhbayar Ganbold
 * @version November 2015
 */
public abstract class AbstractTool implements Tool {
    /** Name of the tool.   */
    private final String myName;
    
    /**
     * Constructs tool with given name.
     */
    public AbstractTool() {
        myName = this.getClass().getSimpleName();
    }

    /**
     * Accessor for getting the name of tool.
     * @return String name of tool.
     */
    protected String getName() {
        return myName;
    }
}
