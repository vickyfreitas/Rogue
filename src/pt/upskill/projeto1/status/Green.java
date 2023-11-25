package pt.upskill.projeto1.status;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Green extends StatusBar implements ImageTile {

    private Position position;

    public Green(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Green";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
