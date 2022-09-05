package mk.ukim.finki.np.auds.av5.FuncInter;

public class LineCounter {
    private int lines;
    private int words;
    private int chars;

    public LineCounter(int lines, int words, int chars) {
        this.lines = lines;
        this.words = words;
        this.chars = chars;
    }

    public LineCounter(String input) {
        ++lines;
        words += input.split("\\s++").length;
        chars += input.length();
    }

    public LineCounter sum(LineCounter other){
        return new LineCounter(this.lines + other.lines,
                this.words + other.words,
                this.chars + other.chars);
    }

    @Override
    public String toString() {
        return "LineCounter{" +
                "lines=" + lines +
                ", words=" + words +
                ", chars=" + chars +
                '}';
    }
}
