package pt.upskill.projeto1.Creatures;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.List;

public class Skeleton extends Enemies {

    public Skeleton(Position position, List<ImageTile> tiles, int hp, int damage, Room room) {
        super(position, tiles, hp, damage, room);
    }


    @Override
    public String getName() {
        return "Skeleton";
    }
}
