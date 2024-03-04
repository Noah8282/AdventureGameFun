import java.util.*;

public class Adventure {

    private int numberOfRows;
    private Random rn = new Random();
    private HashMap<String, Room> rooms;
    private Room currentRoom;

    public Adventure() {
        rooms = new HashMap<>();
        createRooms();
        connectRooms();
        System.out.println("Current room: "+(intToCoords(currentRoom.getX(), currentRoom.getY())));
    }

    private void createRooms() {
        double numRoomSqrt;
        do {
            numRoomSqrt = Math.sqrt(rn.nextInt(100,101));
            numberOfRows = (int) numRoomSqrt;
            System.out.println("Ran");
        }while(!(numRoomSqrt == numberOfRows));


        for (int y = 1; y <= numberOfRows; y++) {
            for (int x = 1; x <= numberOfRows; x++) {
                rooms.put(intToCoords(x,y),new Room(x,y,"Room "+intToCoords(x,y), "Nothing special"));

                System.out.println(intToCoords(x,y));


            }
        }

    }

    private void connectRooms() {
        boolean isValid = false;
        do {
            currentRoom = rooms.get(intToCoords(rn.nextInt(1, numberOfRows+1), rn.nextInt(1,numberOfRows+1)));
            for (int y = 1; y <= numberOfRows; y++) {
                for (int x = 1; x <= numberOfRows; x++) {
                    String coords = intToCoords(x, y);

                    Room editableRoom = rooms.get(coords);
                    while (editableRoom.getConnectedRooms().isEmpty()) {

                        if (rn.nextInt(2) == 1 && (x - 1) >= 1) {
                            //System.out.println("Trying to add " + (x - 1) + "." + (y) + " to: " + coords);
                            editableRoom.setConnectedRooms(rooms.get(intToCoords(x - 1, y)));
                        }
                        if (rn.nextInt(2) == 1 && (x + 1) <= numberOfRows) {
                            //System.out.println("Trying to add " + (x + 1) + "." + (y) + " to: " + coords);
                            editableRoom.setConnectedRooms(rooms.get(intToCoords(x + 1, y)));
                        }
                        if (rn.nextInt(2) == 1 && (y - 1) >= 1) {
                            //System.out.println("Trying to add " + (x) + "." + (y - 1) + " to: " + coords);
                            editableRoom.setConnectedRooms(rooms.get(intToCoords(x, y - 1)));
                        }
                        if (rn.nextInt(2) == 1 && (y + 1) <= numberOfRows) {
                            //System.out.println("Trying to add " + (x) + "." + (y + 1) + " to: " + coords);
                            editableRoom.setConnectedRooms(rooms.get(intToCoords(x, y + 1)));
                        }
                    }
                    rooms.put(coords, editableRoom);

                }
            }

            if(!allConnectionsValid()) {
                System.out.println("Not valid");
                rooms.clear();
                createRooms();
            } else {
                isValid = true;
            }


        } while(!isValid);
    }
    //AllConnectionsValid does a Breadth-First Search (BFS) to ensure all the rooms are accessible.
    private boolean allConnectionsValid() {
        //Queue list will contain each visited Room. When a room in the queue's neighbours has been checked, it will be removed.
        Queue<Room> queue = new LinkedList<>();
        //Contains all the rooms the algorithm has visited.
        Set<Room> visited = new HashSet<>();

        //The starting room will be the players starting location, and therefor the first Room to be added to both lists.
        queue.add(currentRoom);
        visited.add(currentRoom);

        //While loop that checks if the queue is empty. Will stop when there are no more visited rooms to check.
        while(!queue.isEmpty()) {
            //Makes a poll from the queue. The poll returns the next room in queue, and removes it.
            Room testRoom = queue.poll();

            //For each loop. Loops through each neighbouring room in the past visited room.
            for (Room connectedRoom : testRoom.getConnectedRooms()) {
                //Checks if the neighbouring room has already been visited.
                if(!visited.contains(connectedRoom)) {
                    //Adds the neighbours to both the visited list and queue list, so the "neighbours" neighbours also can be checked.
                    visited.add(connectedRoom);
                    queue.add(connectedRoom);
                }
            }
        }

        //The check is done and all accessible rooms from the players start has been checked. If the length of visited rooms is equal to
        //the length of all the rooms, then all rooms has been visited, and therefor all rooms are accessible.
        boolean isAccessible = visited.size() == rooms.size();
        System.out.println(isAccessible);
        return isAccessible;

    }

    private String intToCoords(int x, int y) {
        return x+"."+y;
    }
    private int[] coordsToInt(String coords) {
         String[] coordString = coords.split("\\.");
        return new int[]{Integer.parseInt(coordString[0]), Integer.parseInt(coordString[1])};
    }

}
