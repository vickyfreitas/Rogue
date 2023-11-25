package pt.upskill.projeto1.Creatures;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.*;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Mobs implements ImageTile {

    private Position position;
    private List<ImageTile> tiles;
    private int hp;
    private int damage;

    public Mobs(Position position, List<ImageTile> tiles, int hp, int damage) {
        this.position = position;
        this.tiles = tiles;
        this.hp = hp;
        this.damage = damage;
    }

    @Override
    public abstract String getName();

    @Override
    public Position getPosition() {
        return position;
    }


    public void setPosition(Position position) {
        this.position = position;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


    public boolean collision(Position pos) {
        List<Class<?>> nonCollisionTiles = new ArrayList<>();
        nonCollisionTiles.add(Floor.class);
        nonCollisionTiles.add(GoodMeat.class);
        nonCollisionTiles.add(Key.class);
        nonCollisionTiles.add(Sword.class);
        nonCollisionTiles.add(DoorWay.class);
        nonCollisionTiles.add(DoorOpen.class);

        for (ImageTile tile : tiles) {
            if (tile.getPosition().equals(pos) && !nonCollisionTiles.contains(tile.getClass())) {
                return false;
            }
        }
        return true;
    }

}
