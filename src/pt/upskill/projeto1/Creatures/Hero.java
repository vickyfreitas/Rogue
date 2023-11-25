package pt.upskill.projeto1.Creatures;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

import java.awt.event.KeyEvent;
import java.util.List;

public class Hero extends Mobs {

    private Room room;
    private Position nextPosition;
    private ImageMatrixGUI gui;

    public Hero(Position position, List<ImageTile> tiles, int hp, int damage, Room room) {
        super(position, tiles, hp, damage);
        this.room = room;
        this.gui = ImageMatrixGUI.getInstance();

    }
    @Override
    public String getName() {
        return "Hero";
    }

    public void heroMovement(int keyPressed) {
        switch (keyPressed) {
            case KeyEvent.VK_DOWN:
                nextPosition = getPosition().plus(Direction.DOWN.asVector());
                gui.setStatus("Hero is moving down!");
                break;
            case KeyEvent.VK_UP:
                nextPosition = getPosition().plus(Direction.UP.asVector());
                gui.setStatus("Hero is moving up!");
                break;
            case KeyEvent.VK_LEFT:
                nextPosition = getPosition().plus(Direction.LEFT.asVector());
                gui.setStatus("Hero is moving left!");
                break;
            case KeyEvent.VK_RIGHT:
                nextPosition = getPosition().plus(Direction.RIGHT.asVector());
                gui.setStatus("Hero is moving right!");
                break;
            default:
                gui.setStatus("Invalid key pressed");
                return;
        }

        if (nextPosition != null && collision(nextPosition)) {
            hitEnemy();
            setPosition(nextPosition);
        }
    }

    public void hitEnemy() {
        for (ImageTile tile : room.getTiles()) {
            if (tile instanceof Enemies && tile.getPosition().equals(nextPosition)) {
                Enemies enemy = (Enemies) tile;
                enemy.setHp(enemy.getHp() - getDamage());
                gui.setStatus(enemy.getName() + ": " + enemy.getHp() + " HP");

                if (enemy.getHp() <= 0) {
                    room.getTiles().remove(enemy);
                    gui.removeImage(enemy);
                    gui.setStatus(enemy.getName() + " is dead");
                }
                break;
            }
        }
    }
}
