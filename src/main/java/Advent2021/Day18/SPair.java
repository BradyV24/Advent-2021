package Advent2021.Day18;

public class SPair implements SNumber {
    public SPair parent;
    public SNumber left;
    public SNumber right;
    public boolean isRight = false;
    public int depth;

    public SPair(String number) {
        depth = 0;
        int d = 0;
        boolean recur = false;
        for (Character c : number.toCharArray()) {
            if (c == '[') d++;
            if (d > 1) {
                recur = true;
                break;
            }
        }
        d=0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '[') d++;
            else if (number.charAt(i) == ']') d--;
            else if (number.charAt(i) == ',' && d == 1) {
                if (recur) {
                    if (i == 2) {
                        left = new SLit(Integer.parseInt(number.substring(1, i)));
                        left.setParent(this);
                        left.setDepth(depth + 1);
                    } else {
                        left = new SPair(number.substring(1, i), this);
                    }
                    if (i == number.length() - 3) {
                        right = new SLit(Integer.parseInt(number.substring(i + 1, number.length() - 1)));
                        right.setParent(this);
                        right.setDepth(depth + 1);
                        right.setRight();
                    } else {
                        right = new SPair(number.substring(i + 1, number.length() - 1), this);
                        right.setRight();
                    }
                    break;
                } else {
                    String[] split = number.substring(1, number.length() - 1).split(",");
                    left = new SLit(Integer.parseInt(split[0]));
                    right = new SLit(Integer.parseInt(split[1]));
                    left.setParent(this);
                    left.setDepth(depth + 1);
                    right.setParent(this);
                    right.setDepth(depth + 1);
                    right.setRight();
                    break;
                }
            }
        }

    }
    public SPair(String number, SPair parent) {
        this.parent = parent;
        this.depth = parent.depth + 1;
        int d = 0;
        boolean recur = false;
        for (Character c : number.toCharArray()) {
            if (c == '[') d++;
            if (d > 1) {
                recur = true;
                break;
            }
        }
        d=0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '[') d++;
            else if (number.charAt(i) == ']') d--;
            else if (number.charAt(i) == ',' && d == 1) {
                if (recur) {
                    if (i == 2) {
                        left = new SLit(Integer.parseInt(number.substring(1, i)));
                        left.setParent(this);
                        left.setDepth(depth + 1);
                    } else {
                        left = new SPair(number.substring(1, i), this);
                    }
                    if (i == number.length() - 3) {
                        right = new SLit(Integer.parseInt(number.substring(i + 1, number.length() - 1)));
                        right.setParent(this);
                        right.setDepth(depth + 1);
                        right.setRight();
                    } else {
                        right = new SPair(number.substring(i + 1, number.length() - 1), this);
                        right.setRight();
                    }
                    break;
                } else {
                    String[] split = number.substring(1, number.length() - 1).split(",");
                    left = new SLit(Integer.parseInt(split[0]));
                    right = new SLit(Integer.parseInt(split[1]));
                    left.setParent(this);
                    left.setDepth(depth + 1);
                    right.setParent(this);
                    right.setDepth(depth + 1);
                    right.setRight();
                    break;
                }
            }
        }
    }
    public SPair(SLit left, SLit right, SPair parent) {
        this.depth = parent.depth + 1;
        this.parent = parent;
        left.parent = this;
        left.depth = this.depth + 1;
        this.left = left;
        right.parent = this;
        right.depth = this.depth + 1;
        this.right = right;
    }
    public SPair(SPair left, SPair right) {
        this.depth = 0;
        this.left = left;
        right.isRight = true;
        this.right = right;
        left.increaseDepth();
        right.increaseDepth();
    }
    public void explode() {
        if (isRight) {
            parent.left.add((SLit) left, true);
            explodeUp(parent, (SLit) right, true);
        } else {
            parent.right.add((SLit) right, false);
            explodeUp(parent, (SLit) left, false);
        }
    }
    public static void explodeUp(SPair target, SLit num, boolean right) {
        if (target.parent == null) {
            return;
        }
        if (right) {
            if (!target.isRight) {
                target.parent.right.add(num, false);
            } else {
                explodeUp(target.parent, num, true);
            }
        } else {
            if (target.isRight) {
                target.parent.left.add(num, true);
            } else {
                explodeUp(target.parent, num, false);
            }
        }
    }
    public void setRight() {
        isRight = true;
    }
    public void setParent(SPair parent) {
        this.parent = parent;
    }
    public void setDepth(int d) {
        depth = d;
    }
    public void increaseDepth() {
        depth++;
        right.increaseDepth();
        left.increaseDepth();
    }

    public boolean explodeCheck() {
        if (left instanceof SPair) {
            if (((SPair) left).left instanceof SLit && ((SPair) left).right instanceof SLit && ((SPair) left).depth >= 4) {
                ((SPair) left).explode();
                left = new SLit(0);
                left.setParent(this);
                left.setDepth(depth+1);
                return true;
            }
            if (left.explodeCheck()) return true;
        }
        if (right instanceof SPair) {
            if (((SPair) right).left instanceof SLit && ((SPair) right).right instanceof SLit && ((SPair) right).depth >= 4) {
                ((SPair) right).explode();
                right = new SLit(0);
                right.setParent(this);
                right.setDepth(depth+1);
                return true;
            }
            return right.explodeCheck();
        }
        return false;
    }

    public boolean splitCheck() {
        if (left instanceof SPair) {
            if (left.splitCheck()) return true;
        }
        if (left instanceof SLit && ((SLit) left).val > 9) {
            left = left.split();
            left.setDepth(depth+1);
            ((SPair) left).left.setDepth(depth+2);
            ((SPair) left).left.setParent(((SPair) left));
            ((SPair) left).right.setDepth(depth+2);
            ((SPair) left).right.setParent(((SPair) left));
            ((SPair) left).right.setRight();
            return true;
        }
        if (right instanceof SPair) {
            return right.splitCheck();
        }
        if (right instanceof SLit && ((SLit) right).val > 9) {
            right = right.split();
            right.setDepth(depth+1);
            right.setRight();
            ((SPair) right).left.setDepth(depth+2);
            ((SPair) right).left.setParent(((SPair) right));
            ((SPair) right).right.setDepth(depth+2);
            ((SPair) right).right.setParent(((SPair) right));
            ((SPair) right).right.setRight();
            return true;
        }
        return false;
    }


    public void add(SLit num, boolean right) {
        if (right) {
            this.right.add(num, true);
        } else this.left.add(num, false);
    }
    //do not use
    public SPair split() {return new SPair("");}

    public long mag() {
        return 3*left.mag() + 2*right.mag();
    }
}
