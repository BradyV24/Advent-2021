package Day18;

public interface SNumber {
    void add(SLit num, boolean b);
    void setRight();
    void setParent(SPair parent);
    void setDepth(int d);
    void increaseDepth();
    boolean explodeCheck();
    boolean splitCheck();
    SPair split();
    long mag();
}
