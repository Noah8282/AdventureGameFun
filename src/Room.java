import java.util.ArrayList;

public class Room {
    /////// ATTRIBUTES ///////

    //coordinates
    private int x;
    private int y;

    //Descriptive Room Attributes
    private String name;
    private String description;

    //Items in room
    ArrayList<Item> items;

    //Enemies in room
    ArrayList<Enemy> enemies;


    public Room(int x, int y, String name, String description) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
