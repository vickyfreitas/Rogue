package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.Creatures.*;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Room class represents a room in the game.
 * It implements the ImageTile interface. A Room is made up of multiple ImageTiles.
 * This class contains methods for creating a room based on a file, moving enemies in a room, and adding tiles to a room.
 */

public class Room implements ImageTile {

    private List<ImageTile> tiles; // List of tiles that make up the room.
    private String fileName;// Name of the file that describes the room layout.

    /**
     * Creates a new Room object.
     * @param tiles A list of ImageTiles that make up the room.
     * @param fileName The name of the file that describes the room layout.
     */

    public Room(List<ImageTile> tiles, String fileName) {
        this.tiles = tiles;
        this.fileName = fileName;
    }

    /**
     * @return A list of ImageTiles that make up the room.
     */

    public List<ImageTile> getTiles() {
        return tiles;
    }

    /**
     * @return The name of the file that describes the room layout.
     */

    public String getFileName() {
        return fileName;
    }
    /**
     * @return The name of the file that describes the room layout.
     */

    @Override
    public String getName() {
        return fileName;
    }
    /**
     * @return The Position of the Room. This method is part of the ImageTile interface.
     * In the context of the Room class, it returns null because a Room does not have a specific position in the game.
     */
    @Override
    public Position getPosition() {
        return null;
    }

    /**
     * Reads the layout of the room from a file and creates the room.
     * @return A list of ImageTiles that represent the created room.
     */


    public List<ImageTile> createRoom() {
        tiles = new ArrayList<>();

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                tiles.add(new Floor(new Position(i, j)));
            }
        }

        int j = 0;
        try {
            Scanner currentRoom = new Scanner(new File(getFileName()));

            while (currentRoom.hasNextLine()) {
                String row = currentRoom.nextLine();
                if(row.startsWith("#")) {
                    continue;
                }
                for (int i = 0; i < row.length(); i++) {
                    char objectRoom = row.charAt(i);
                    addTileBasedOnChar(objectRoom, new Position(i, j));
                }
                j++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist.");
        }
        return tiles;
    }

    /**
     * Adds an ImageTile to the room based on a character and a Position.
     * The character determines the type of ImageTile to be added (e.g., wall, door, enemy).
     * @param room A character that represents the type of ImageTile to be added.
     * @param position The position of the ImageTile in the room.
     */

    private void addTileBasedOnChar(char room, Position position) {
        switch (room) {
            case 'W':
                tiles.add(new Wall(position));
                break;
            case '0':
                tiles.add(new DoorOpen(position));
                break;
            case '1':
                tiles.add(new DoorClosed(position));
                break;
            case '2':
                tiles.add(new DoorWay(position));
                break;
            case 'k':
                tiles.add(new Key(position));
                break;
            case 'm':
                tiles.add(new GoodMeat(position));
                break;
            case 'h':
                tiles.add(new Hammer(position));
                break;
            case 's':
                tiles.add(new Sword(position));
                break;
            case 'S':
                tiles.add(new Skeleton(position, tiles, 30, 2, this));
                break;
            case 'G':
                tiles.add(new BadGuy(position, tiles, 25, 3, this));
                break;
            case 'T':
                tiles.add(new Thief(position, tiles, 35, 3, this));
                break;
            case 'B':
                tiles.add(new Bat(position, tiles, 15, 1, this));
                break;
            default:
                break;
        }
    }
    /**
     * Moves all enemies in the room.
     * The direction of the movement is determined by the Enemies.mobMovement method.
     */
    public void moveEnemiesRoom() {
        for (ImageTile tile : tiles) {
            if (tile instanceof Enemies) {
                boolean isThief = tile instanceof Thief;
                ((Enemies) tile).mobMovement(isThief);
            }
        }
    }
}
