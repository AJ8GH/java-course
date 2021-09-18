package section12.collections.sets;

import java.util.HashSet;
import java.util.Set;

public final class HeavenlyBody {
    private final String name;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;

    public HeavenlyBody(String name, double orbitalPeriod) {
        this.name = name;
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Set<HeavenlyBody> getSatellites() {
        return new HashSet<>(satellites);
    }

    public boolean addMoon(HeavenlyBody moon) {
        return satellites.add(moon);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object.getClass() != this.getClass()) return false;
        return name.equals(((HeavenlyBody) object).getName());
    }

    @Override
    public int hashCode() {
        return 57 + name.hashCode();
    }
}
