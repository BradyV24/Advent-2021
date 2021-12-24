package Advent2021.Day19;

import java.util.Objects;

public class Beacon {
    int x;
    int y;
    int z;

    public Beacon(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Beacon)) return false;
        Beacon beac = (Beacon) other;
        return (x == beac.x && y == beac.y && z == beac.z );
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
