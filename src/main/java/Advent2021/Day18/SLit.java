package Advent2021.Day18;

public class SLit implements SNumber {
    public SPair parent;
    public int val;
    public int depth;
    public boolean isRight;

    public SLit(int val) {
        this.val = val;
    }

    public SPair split() {
        return new SPair(new SLit(val/2), new SLit(val/2 + val%2), parent);
    }

    public void add(SLit num, boolean b) {
        val += num.val;
    }

    public void setRight() {
        isRight =true;
    }

    public void setDepth(int d) {
        depth = d;
    }

    public void setParent(SPair parent) {
        this.parent = parent;
    }

    public void increaseDepth() {
        depth++;
    }

    public boolean explodeCheck() {return true;}
    public boolean splitCheck() {return true;}

    public long mag() {
        return val;
    }
}
