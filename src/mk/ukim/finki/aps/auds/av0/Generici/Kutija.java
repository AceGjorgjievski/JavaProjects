package mk.ukim.finki.aps.auds.av0.Generici;

class Kutija<T> {
    private T obj;

    public Kutija() {
    }

    Kutija(T obj){
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "Kutija sodizhi objekt " + obj;
    }
}
