package pt.upskill.projeto1.Creatures;

import pt.upskill.projeto1.game.Engine;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.rogue.utils.DiagonalDirection;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.util.List;
import java.util.Random;

public abstract class Enemies extends Mobs {

    private Hero hero;
    private static Engine engine;
    private ImageMatrixGUI gui;
    private Room room;
    private boolean gameOver;


    public Enemies(Position position, List<ImageTile> tiles, int hp, int damage, Room room) {
        super(position, tiles, hp, damage);
        this.room = room;
        this.gui = ImageMatrixGUI.getInstance();
        this.gameOver = false;
    }

    public static void setEngine(Engine engine) {
        Enemies.engine = engine;
    }


    public Vector2D randomDirection(boolean isThief) {

        if (isThief) {
            DiagonalDirection[] diagonals = DiagonalDirection.values();
            DiagonalDirection randomDirection = diagonals[new Random().nextInt(diagonals.length)];
            return randomDirection.asVector();
        } else {
            Direction[] directions = Direction.values();
            Direction randomDirection = directions[new Random().nextInt(directions.length)];
            return randomDirection.asVector();
        }
    }

    public void mobMovement(boolean isThief) {

        Position heroPosition = engine.getHero().getPosition();
        Position enemyPosition = getPosition();

        double heroEnemyDistance = enemyPosition.euclideanDistance(heroPosition);

        if (heroEnemyDistance <= 3) {
            Vector2D direction = null;

            if (heroPosition.getX() > enemyPosition.getX()) {
                direction = Direction.RIGHT.asVector(); //
            } else if (heroPosition.getX() < enemyPosition.getX()) {
                direction = Direction.LEFT.asVector();
            } else if (heroPosition.getY() > enemyPosition.getY()) {
                direction = Direction.DOWN.asVector();
            } else if (heroPosition.getY() < enemyPosition.getY()) {
                direction = Direction.UP.asVector();
            }

            if (direction != null) {
                Position nextPosition = enemyPosition.plus(direction);

                if (collision(nextPosition)) {
                    setPosition(nextPosition);
                    attackHero();
                }
            }
        }

        Vector2D randomDirection = randomDirection(isThief);
        Position nextPosition = enemyPosition.plus(randomDirection);

        if (collision(nextPosition)) {
            setPosition(nextPosition);
            attackHero();
        }
    }

    public void attackHero() {
        this.hero = engine.getHero();
        int heroHp = engine.getHero().getHp();
        double heroEnemyDistance = getPosition().euclideanDistance(hero.getPosition());

        if (heroEnemyDistance == 1) {
            heroHp -= this.getDamage();
            hero.setHp(heroHp);

            gui.setStatus("HERO: " + Math.max(heroHp, 0) + " Hp");

            if (heroHp <= 0) {
                engine.getStatusBar().updateStatusBar(heroHp);
                handleGameOver();
            } else {
                engine.getStatusBar().updateStatusBar(heroHp);
            }
        }
    }
    private void handleGameOver() {
        gameOver = true;
        gui.showMessage("You Lost", "Press OK to quit the game");
        gui.dispose();
    }
}
