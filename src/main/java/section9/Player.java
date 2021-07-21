package section9;

import java.util.ArrayList;
import java.util.List;

public class Player implements ISaveable {
    private String name;
    private int hitPoints;
    private int strength;
    private String weapon;

    public Player(String name, int hitPoints, int strength) {
        this.name = name;
        this.weapon = "Sword";
        this.hitPoints = hitPoints;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }


    @Override
    public List<String> write() {
        List<String> list = new ArrayList<>();
        list.add(name);
        list.add(String.valueOf(hitPoints));
        list.add(String.valueOf(strength));
        list.add(weapon);
        return list;
    }

    @Override
    public void read(List<String> list) {
        if (list != null && list.size() > 0) {
            setName(list.get(0));
            setHitPoints(Integer.parseInt(list.get(1)));
            setStrength(Integer.parseInt(list.get(2)));
            setWeapon(list.get(3));
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                ", weapon='" + weapon + '\'' +
                '}';
    }
}
