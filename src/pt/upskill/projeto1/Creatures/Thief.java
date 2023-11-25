package pt.upskill.projeto1.Creatures;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.List;

public class Thief extends Enemies {
    public Thief(Position position, List<ImageTile> tiles, int hp, int damge, Room room) {
        super(position, tiles, hp, damge, room);
    }

    @Override
    public String getName() {
        return "Thief";
    }
}