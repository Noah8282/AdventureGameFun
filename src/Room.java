import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Room {
    /////// ATTRIBUTES ///////

    //coordinates
    private int x;
    private int y;

    //Descriptive Room Attributes
    private String name;
    private String description;

    //Connected Rooms
    Set<Room> connectedRooms;

    //Items in room
    ArrayList<Item> items;

    //Enemies in room
    ArrayList<Enemy> enemies;


    public Room(int x, int y, String name, String description) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.description = description;
        connectedRooms = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Room> getConnectedRooms() {
        return connectedRooms;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setConnectedRooms(Room room) {
        if(!(connectedRooms.contains(room))) {
            if((room.getX()+1 == x || room.getX()-1 == x || room.getX() == x) && (room.getY()+1 == y || room.getY()-1 == y || room.getY() == y)) {
                connectedRooms.add(room);
                room.setConnectedRooms(this);
                //System.out.println("Room added");
            } else {
                //System.out.println("Not a Neighbour");
            }
        }
    }
}
