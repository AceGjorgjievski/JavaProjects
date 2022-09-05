package mk.ukim.finki.np.vezhbi.za_juni.box;

import java.util.stream.IntStream;

class Circle implements Drawable<Circle>{

    @Override
    public Circle draw() {
        return this;
    }
}

public class BoxTest {

    public static void main(String[] args) {
        Box<Circle> box = new Box<Circle>();
        IntStream.range(0,100)
                .forEach(i -> new Circle());

        IntStream.range(0, 103)
                .forEach(i -> System.out.println(box.drawElement()));

    }
}
