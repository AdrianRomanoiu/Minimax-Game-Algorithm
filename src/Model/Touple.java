package Model;

public class Touple<K, V> {
    private K x;
    private V y;

    public Touple(K x, V y){
        this.x = x;
        this.y = y;
    }

    public K getX() {
        return x;
    }

    public void setX(K x) {
        this.x = x;
    }

    public V getY() {
        return y;
    }

    public void setY(V y) {
        this.y = y;
    }

}
