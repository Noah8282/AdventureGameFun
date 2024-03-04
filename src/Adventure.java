import java.util.HashMap;
import java.util.Random;

public class Adventure {

    private int numberOfRows;
    final private Random rn = new Random();
    private HashMap<String, Room> rooms;
    double numRoomSqrt;
    private Room currentRoom;

    public Adventure() {
        rooms = new HashMap<>();

        do {
            numRoomSqrt = Math.sqrt(rn.nextInt(9,21));

        }while(!(numRoomSqrt % 2 == 0));
        numberOfRows = (int)numRoomSqrt;


        for (int y = 1; y <= numberOfRows; y++) {
            for (int x = 1; x <= numberOfRows; x++) {
                rooms.put(x+"."+y,new Room(x,y,"Room "+x+"."+y, "Nothing special"));
            }
        }

        currentRoom = rooms.get(rn.nextInt(1,numberOfRows+1)+"."+rn.nextInt(1,numberOfRows+1));

        System.out.println(currentRoom.getName() + " " + currentRoom.getDescription());

    }



    public void move(String direction) {
        if(direction.equals("north")) {
            //return "hello";
        } else if(direction.equals("west")) {
            //return "hello";
        }
    }

}
