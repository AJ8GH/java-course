package section12.collections.setchallenge;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class HeavenlyBody {
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;
    private final Key key;

    public HeavenlyBody(String name, double orbitalPeriod, BodyTypes bodyType) {
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
        this.key = new Key(name, bodyType);
    }

    public static final class Key {
        private String name;
        private BodyTypes bodyType;

        public String getName() {
            return name;
        }

        public BodyTypes getBodyType() {
            return bodyType;
        }

        private Key(String name, BodyTypes bodyType) {
            this.name = name;
            this.bodyType = bodyType;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object != null && object.getClass() == this.getClass()) {
                Key that = (Key) object;
                return this.getName().equals(that.getName()) &&
                        this.getBodyType().equals(that.getBodyType());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this, name, bodyType);
        }

        @Override
        public String toString() {
            return name + ": " + bodyType;
        }
    }

    public enum BodyTypes {
        PLANET, MOON, DWARF_PLANET
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public boolean addSatellite(HeavenlyBody satellite) {
        return this.satellites.add(satellite);
    }

    public Set<HeavenlyBody> getSatellites() {
        return new HashSet<>(this.satellites);
    }

    public Key getKey() {
        return key;
    }

    @Override
    public final boolean equals(Object that) {
        if(this == that) return true;
        if ((that != null) && (that.getClass() == this.getClass())) {
            return this.key.equals(((HeavenlyBody) that).getKey());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return key.hashCode();
    }

    public static Key makeKey(String name, BodyTypes bodyType) {
        return new Key(name, bodyType);
    }

    @Override
    public String toString() {
        return key.name + ": " + key.bodyType + ", " + orbitalPeriod;
    }
}
