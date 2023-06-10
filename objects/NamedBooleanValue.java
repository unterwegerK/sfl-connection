package de.ku.sfl.connection.objects;

/**
 * This class represents a switch that a client can present to the server and other clients.
 */
public class NamedBooleanValue {

    private String name;
    private boolean state;

    /**
     * This constructor sets the state to false.
     */
    public NamedBooleanValue(String name){
        this(name, false);
    }

    public NamedBooleanValue(String name, boolean initialState){
        this.name = name;
        this.state = initialState;
    }

    public String getName(){
        return name;
    }

    public boolean getState(){
        return state;
    }

    public void setState(boolean newState){
        state = newState;
    }
}
