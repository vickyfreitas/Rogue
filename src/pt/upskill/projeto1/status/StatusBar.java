package pt.upskill.projeto1.status;

import pt.upskill.projeto1.Creatures.Hero;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.GoodMeat;
import pt.upskill.projeto1.objects.Key;
import pt.upskill.projeto1.objects.Sword;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.List;

public class StatusBar {

    private static int StatusBarSize = 10;

    private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private boolean hasSword;
    private boolean hasKey;

    public void updateStatusBar(int heroHp) {
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        for (int i = 0; i < StatusBarSize; i++) {
            if (hasSword == true && hasKey == false) {

                gui.addStatusImage(new Black(new Position(i, 0)));
                gui.addStatusImage(new Fire(new Position(0, 0)));
                gui.addStatusImage(new Fire(new Position(1, 0)));
                gui.addStatusImage(new Fire(new Position(2, 0)));
                gui.addStatusImage(new Sword(new Position(7, 0)));


            } else if (hasSword == true && hasKey == true) {

                gui.addStatusImage(new Black(new Position(i, 0)));
                gui.addStatusImage(new Fire(new Position(0, 0)));
                gui.addStatusImage(new Fire(new Position(1, 0)));
                gui.addStatusImage(new Fire(new Position(2, 0)));
                gui.addStatusImage(new Sword(new Position(7, 0)));
                gui.addStatusImage(new Key(new Position(8, 0)));

            } else if (hasSword == false && hasKey == true) {

                gui.addStatusImage(new Black(new Position(i, 0)));
                gui.addStatusImage(new Fire(new Position(0, 0)));
                gui.addStatusImage(new Fire(new Position(1, 0)));
                gui.addStatusImage(new Fire(new Position(2, 0)));
                gui.addStatusImage(new Key(new Position(8, 0)));

            } else {

                gui.addStatusImage(new Black(new Position(i, 0)));
                gui.addStatusImage(new Fire(new Position(0, 0)));
                gui.addStatusImage(new Fire(new Position(1, 0)));
                gui.addStatusImage(new Fire(new Position(2, 0)));

            }
        }
        if (heroHp > 15) {
            for (int i = 3; i < 7; i++) {
                gui.addStatusImage(new Green(new Position(i, 0)));
            }
        } else if (heroHp > 10) {
            for (int i = 3; i < 6; i++) {
                gui.addStatusImage(new Green(new Position(i, 0)));
            }
            for (int i = 6; i < 7; i++) {
                gui.addStatusImage(new Red(new Position(i, 0)));
            }
        } else if (heroHp >5) {
            for (int i = 3; i < 5; i++) {
                gui.addStatusImage(new Green(new Position(i, 0)));
            }
            for (int i = 5; i < 7; i++) {
                gui.addStatusImage(new Red(new Position(i, 0)));
            }
        } else if (heroHp > 0) {
            gui.addStatusImage(new Green(new Position(3, 0)));
            for (int i = 4; i < 7; i++) {
                gui.addStatusImage(new Red(new Position(i, 0)));
            }
        } else {
            for (int i = 3; i < 7; i++) {
                gui.addStatusImage(new Red(new Position(i, 0)));
            }
        }

    }

    public void catchItem(Hero hero, List<ImageTile> tiles) {
        int heroDamage = hero.getDamage();
        int heroHp = hero.getHp();
        for (ImageTile item : tiles) {
            if (item instanceof Sword && hero.getPosition().equals(item.getPosition())) {
                gui.addStatusImage(new Sword(new Position(7, 0)));
                gui.removeImage(item);
                hasSword = true;
                heroDamage = 10;
                hero.setDamage(heroDamage);
                gui.setStatus("HERO has now a Sword and " + heroDamage + " damage points");
                break;
            } else if (item instanceof Key && hero.getPosition().equals(item.getPosition())) {
                gui.addStatusImage(new Key(new Position(8, 0)));
                gui.removeImage(item);
                hasKey = true;
                gui.setStatus("HERO has now a Key");
                break;

            } else if (item instanceof GoodMeat && hero.getPosition().equals(item.getPosition())) {
                gui.removeImage(item);
                heroHp = heroHp + 10;
                hero.setHp(heroHp);
                updateStatusBar(heroHp);
                gui.setStatus("HERO HP is now " + heroHp + " points");
                tiles.remove(item);
                break;
            }
        }
    }
}
