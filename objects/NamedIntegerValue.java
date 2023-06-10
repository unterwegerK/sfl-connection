package de.ku.sfl.connection.objects;

public class NamedIntegerValue {
    private final String name;
    private int value;
    private final Object lockObject = new Object();

    public NamedIntegerValue(String name, int initialValue){
        this.name = name;
        this.value = initialValue;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        synchronized(lockObject) {
            return value;
        }
    }

    public void setValue(int newValue){
        synchronized (lockObject) {
            this.value = newValue;
        }
    }

}
