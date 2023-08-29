import java.io.Serializable;

public class Entry implements Serializable {
    private String key;
    private String value;

    // constructs a new entry with a given key and value
    public Entry(String key, String value){
        this.key = key;
        this.value = value;
    }

    // returns the entry key
    public String getKey() {
        return key;
    }

    // sets the entry key with a given key
    public void setKey(String key) {
        this.key = key;
    }

    // returns the entry value
    public String getValue() {
        return value;
    }

    // sets the entry value with the given value
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    // returns a string representation of this entry
    public String toString() {
        return key + " - " + value + "\n";
    }
}
