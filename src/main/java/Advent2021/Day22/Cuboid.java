package Advent2021.Day22;

import java.util.ArrayList;
import java.util.List;

public class Cuboid {
    int x1, x2, y1, y2, z1, z2;
    long area;

    //c1 < c2
    public Cuboid(int x1, int x2, int y1, int y2, int z1, int z2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z1 = z1;
        this.z2 = z2;
        area = Math.abs((long)(x2 - x1 + 1) * (y2 - y1 + 1) * (z2 - z1 + 1));
    }

    public List<Cuboid> cut(Cuboid other) {
        boolean inx1 = (other.x1 >= x1 && other.x1 <= x2);
        boolean inx2 = (other.x2 >= x1 && other.x2 <= x2);
        boolean iny1 = (other.y1 >= y1 && other.y1 <= y2);
        boolean iny2 = (other.y2 >= y1 && other.y2 <= y2);
        boolean inz1 = (other.z1 >= z1 && other.z1 <= z2);
        boolean inz2 = (other.z2 >= z1 && other.z2 <= z2);
        List<Cuboid> subcubes = new ArrayList<>();
        if (inx1 && inx2 && iny1 && iny2 && inz1 && inz2) {
            //all 8 corners inside; 6 subcubes.
            subcubes.add(new Cuboid(other.x1, other.x2, other.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(other.x1, other.x2, other.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }

        else if (inx1 && inx2 && iny1 && iny2 && inz1) {
            //4 corners in; sticking out +z, 5 subcubes.
            subcubes.add(new Cuboid(other.x1, other.x2, other.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (inx1 && inx2 && iny1 && iny2 && inz2) {
            //4 corners in; sticking out -z, 5 subcubes.
            subcubes.add(new Cuboid(other.x1, other.x2, other.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (inx1 && inx2 && iny1 && inz1 && inz2) {
            //4 corners in; sticking out +y, 5 subcubes.
            subcubes.add(new Cuboid(other.x1, other.x2, other.y1, this.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(other.x1, other.x2, other.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, this.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, this.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (inx1 && inx2 && iny2 && inz1 && inz2) {
            //4 corners in; sticking out -y, 5 subcubes.
            subcubes.add(new Cuboid(other.x1, other.x2, this.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(other.x1, other.x2, this.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (inx1 && iny1 && iny2 && inz1 && inz2) {
            //4 corners in; sticking out +x, 5 subcubes.
            subcubes.add(new Cuboid(other.x1, this.x2, other.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(other.x1, this.x2, other.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (inx2 && iny1 && iny2 && inz1 && inz2) {
            //4 corners in; sticking out -x, 5 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x2, other.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, other.x2, other.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }

        else if (inx1 && inx2 && iny1 && inz1) {
            //2 corners in; sticking out +y & +z, 4 subcubes.
            subcubes.add(new Cuboid(other.x1, other.x2, other.y1, this.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, this.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, this.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (inx1 && inx2 && iny1 && inz2) {
            //2 corners in; sticking out +y & -z, 4 subcubes.
            subcubes.add(new Cuboid(other.x1, other.x2, other.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, this.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, this.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (inx1 && inx2 && iny2 && inz1) {
            //2 corners in; sticking out -y & +z, 4 subcubes.
            subcubes.add(new Cuboid(other.x1, other.x2, this.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (inx1 && inx2 && iny2 && inz2) {
            //2 corners in; sticking out -y & -z, 4 subcubes.
            subcubes.add(new Cuboid(other.x1, other.x2, this.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }

        else if (iny1 && iny2 && inx1 && inz1) {
            //2 corners in; sticking out +x & +z, 4 subcubes.
            subcubes.add(new Cuboid(other.x1, this.x2, other.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (iny1 && iny2 && inx1 && inz2) { //THIS ONE
            //2 corners in; sticking out +x & -z, 4 subcubes.
            subcubes.add(new Cuboid(other.x1, this.x2, other.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (iny1 && iny2 && inx2 && inz1) {
            //2 corners in; sticking out -x & +z, 4 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x2, other.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (iny1 && iny2 && inx2 && inz2) {
            //2 corners in; sticking out -x & -z, 4 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x2, other.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }

        else if (inz1 && inz2 && inx1 && iny1) {
            //2 corners in; sticking out +x & +y, 4 subcubes.
            subcubes.add(new Cuboid(other.x1, this.x2, other.y1, this.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(other.x1, this.x2, other.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, this.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (inz1 && inz2 && inx1 && iny2) {
            //2 corners in; sticking out +x & -y, 4 subcubes.
            subcubes.add(new Cuboid(other.x1, this.x2, this.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(other.x1, this.x2, this.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (inz1 && inz2 && inx2 && iny1) {
            //2 corners in; sticking out -x & +y, 4 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x2, other.y1, this.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, other.x2, other.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, this.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (inz1 && inz2 && inx2 && iny2) {
            //2 corners in; sticking out -x & -y, 4 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x2, this.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, other.x2, this.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }

        else if (inx1 && iny1 && inz1) {
            //1 corner in; sticking out +x, +y, +z, 3 subcubes.
            subcubes.add(new Cuboid(other.x1, this.x2, other.y1, this.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, this.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (inx1 && iny1 && inz2) {
            //1 corner in; sticking out +x, +y, -z, 3 subcubes.
            subcubes.add(new Cuboid(other.x1, this.x2, other.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, this.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (inx1 && iny2 && inz1) {
            //1 corner in; sticking out +x, -y, +z, 3 subcubes.
            subcubes.add(new Cuboid(other.x1, this.x2, this.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (inx1 && iny2 && inz2) {
            //1 corner in; sticking out +x, -y, -z, 3 subcubes.
            subcubes.add(new Cuboid(other.x1, this.x2, this.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (inx2 && iny1 && inz1) {
            //1 corner in; sticking out -x, +y, +z, 3 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x2, other.y1, this.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, this.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (inx2 && iny1 && inz2) {
            //1 corner in; sticking out -x, +y, -z, 3 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x2, other.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, this.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (inx2 && iny2 && inz1) {
            //1 corner in; sticking out -x, -y, +z, 3 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x2, this.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (inx2 && iny2 && inz2) {
            //1 corner in; sticking out -x, -y, -z, 3 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x2, this.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }

        boolean outx = (other.x1 <= x1 && other.x2 >= x2);
        boolean outy = (other.y1 <= y1 && other.y2 >= y2);
        boolean outz = (other.z1 <= z1 && other.z2 >= z2);

        if (outx && iny1 && iny2 && inz1 && inz2) {
            //through x faces; 4 subcubes
            subcubes.add(new Cuboid(this.x1, this.x2, other.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, this.x2, other.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (outy && inx1 && inx2 && inz1 && inz2) {
            //through y faces; 4 subcubes
            subcubes.add(new Cuboid(other.x1, other.x2, this.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(other.x1, other.x2, this.y1, this.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, this.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, this.y2, this.z1, this.z2)); //right
            return subcubes;
        }
        else if (outz && iny1 && iny2 && inx1 && inx2) {
            //through z faces; 4 subcubes
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }

        else if (outx && outy && inz1 && inz2) {
            // Split xy plane; 2 subcubes.
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, this.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, this.y2, other.z2+1, this.z2)); //top
            return subcubes;
        }
        else if (outx && outz && iny1 && iny2) {
            // Split xz plane; 2 subcubes.
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (outz && outy && inx1 && inx2) {
            // Split yz plane; 2 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, this.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, this.y2, this.z1, this.z2)); //right
            return subcubes;
        }

        else if (outx && inz1 && iny1 && iny2) {
            // Slash across +z face in x direction; 3 subcubes;
            subcubes.add(new Cuboid(this.x1, this.x2, other.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //right
            return subcubes;
        }
        else if (outx && inz2 && iny1 && iny2) {
            // Slash across -z face in x direction; 3 subcubes;
            subcubes.add(new Cuboid(this.x1, this.x2, other.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //right
            return subcubes;
        }
        else if (outy && inz1 && inx1 && inx2) {
            // Slash across +z face in y direction; 3 subcubes;
            subcubes.add(new Cuboid(other.x1, other.x2, this.y1, this.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, this.y2, this.z1, this.z2)); //back
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (outy && inz2 && inx1 && inx2) {
            // Slash across -z face in y direction; 3 subcubes;
            subcubes.add(new Cuboid(other.x1, other.x2, this.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, this.y2, this.z1, this.z2)); //back
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }

        else if (outx && iny1 && inz1 && inz2) {
            // Slash across +y face in x direction; 3 subcubes;
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, other.z1, other.z2)); //back
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, this.y2, this.z1, other.z1-1)); //bottom
            return subcubes;
        }
        else if (outx && iny2 && inz1 && inz2) {
            // Slash across -y face in x direction; 3 subcubes;
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, other.z1, other.z2)); //front
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, this.y2, this.z1, other.z1-1)); //bottom
            return subcubes;
        }
        else if (outz && iny1 && inx1 && inx2) {
            // Slash across +y face in z direction; 3 subcubes;
            subcubes.add(new Cuboid(other.x1, other.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, this.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, this.y2, this.z1, this.z2)); //right
            return subcubes;
        }
        else if (outz && iny2 && inx1 && inx2) {
            // Slash across -y face in z direction; 3 subcubes;
            subcubes.add(new Cuboid(other.x1, other.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, this.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, this.y2, this.z1, this.z2)); //right
            return subcubes;
        }

        else if (outy && inx1 && inz1 && inz2) {
            // Slash across +x face in y direction; 3 subcubes;
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, this.y2, other.z1, other.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, this.y2, this.z1, other.z1-1)); //bottom
            return subcubes;
        }
        else if (outy && inx2 && inz1 && inz2) {
            // Slash across -x face in y direction; 3 subcubes;
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, this.y2, other.z1, other.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, this.y2, this.z1, other.z1-1)); //bottom
            return subcubes;
        }
        else if (outz && inx1 && iny1 && iny2) {
            // Slash across +x face in z direction; 3 subcubes;
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (outz && inx2 && iny1 && iny2) {
            // Slash across -x face in z direction; 3 subcubes;
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, other.y2, this.z1, this.z2));
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }

        else if (outx && iny1 && inz1) {
            //cut off edge +y & +z, 2 subcubes.
            subcubes.add(new Cuboid(this.x1, this.x2, other.y1, this.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (outx && iny1 && inz2) {
            //cut off edge +y & -z, 2 subcubes.
            subcubes.add(new Cuboid(this.x1, this.x2, other.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (outx && iny2 && inz1) {
            //cut off edge -y & +z, 2 subcubes.
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (outx && iny2 && inz2) {
            //cut off edge -y & -z, 2 subcubes.
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }

        else if (outy && inx1 && inz1) {
            //cut off edge +x & +z, 2 subcubes.
            subcubes.add(new Cuboid(other.x1, this.x2, this.y1, this.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, this.y2, this.z1, this.z2)); //left
            return subcubes;
        }
        else if (outy && inx1 && inz2) {
            //cut off edge +x & -z, 2 subcubes.
            subcubes.add(new Cuboid(other.x1, this.x2, this.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, this.y2, this.z1, this.z2)); //left
            return subcubes;
        }
        else if (outy && inx2 && inz1) {
            //cut off edge -x & +z, 2 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x2, this.y1, this.y2, this.z1, other.z1-1)); //bottom
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, this.y2, this.z1, this.z2)); //right
            return subcubes;
        }
        else if (outy && inx2 && inz2) {
            //cut off edge -x & -z, 2 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x2, this.y1, this.y2, other.z2+1, this.z2)); //top
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, this.y2, this.z1, this.z2)); //right
            return subcubes;
        }

        else if (outz && inx1 && iny1) {
            //cut off edge +x & +y, 2 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x1-1, other.y1, this.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (outz && inx1 && iny2) {
            //cut off edge +x & -y, 2 subcubes.
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, other.y2, this.z1, this.z2)); //left
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }
        else if (outz && inx2 && iny1) {
            //cut off edge -x & +y, 2 subcubes.
            subcubes.add(new Cuboid(other.x2+1, this.x2, other.y1, this.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2)); //back
            return subcubes;
        }
        else if (outz && inx2 && iny2) {
            //cut off edge -x & -y, 2 subcubes.
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, other.y2, this.z1, this.z2)); //right
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2)); //front
            return subcubes;
        }

        else if (outx && outy && inz1) {
            // cuts off z+, 1 subcube;
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, this.y2, this.z1, other.z1-1));
            return subcubes;
        }
        else if (outx && outy && inz2) {
            // cuts off z-, 1 subcube;
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, this.y2, other.z2+1, this.z2));
            return subcubes;
        }
        else if (outx && outz && iny1) {
            // cuts off y+, 1 subcube;
            subcubes.add(new Cuboid(this.x1, this.x2, this.y1, other.y1-1, this.z1, this.z2));
            return subcubes;
        }
        else if (outx && outz && iny2) {
            // cuts off y-, 1 subcube;
            subcubes.add(new Cuboid(this.x1, this.x2, other.y2+1, this.y2, this.z1, this.z2));
            return subcubes;
        }
        else if (outz && outy && inx1) {
            // cuts off x+, 1 subcube;
            subcubes.add(new Cuboid(this.x1, other.x1-1, this.y1, this.y2, this.z1, this.z2));
            return subcubes;
        }
        else if (outz && outy && inx2) {
            // cuts off x-, 1 subcube;
            subcubes.add(new Cuboid(other.x2+1, this.x2, this.y1, this.y2, this.z1, this.z2));
            return subcubes;
        }

        else if (outx && outy && outz) {
            // other surrounds this, no subcubes.
            return new ArrayList<>();
        }

        subcubes.add(this);
        return subcubes;
    }
}
