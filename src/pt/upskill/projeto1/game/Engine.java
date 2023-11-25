package pt.upskill.projeto1.game;

import pt.upskill.projeto1.Creatures.Enemies;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.Creatures.Hero;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.status.StatusBar;

import java.util.ArrayList;
import java.util.List;

public class Engine {
    private List<ImageTile> tiles;
    private Hero hero;
    private Room room;
    private StatusBar statusBar;

    public Hero getHero() {
        return hero;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }


    public void init(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        Enemies.setEngine(this);


        ArrayList<String> rooms = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            rooms.add("rooms/room" + i + ".txt");
        }


        this.room = new Room(this.tiles, rooms.get(0));
        this.tiles = room.createRoom();

        this.hero = new Hero(new Position(7, 7),tiles, 20,1, room);
        tiles.add(hero);


        statusBar = new StatusBar();
        statusBar.updateStatusBar(hero.getHp());

        gui.setEngine(this);
        gui.newImages(tiles);
        gui.go();

        gui.setStatus("The Game has started");

        while (true){
            gui.update();
        }
    }
    public void notify(int keyPressed){
        hero.heroMovement(keyPressed);
        statusBar.catchItem(hero,tiles);
        hero.hitEnemy();
        room.moveEnemiesRoom();
    }

    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();

    }
}
