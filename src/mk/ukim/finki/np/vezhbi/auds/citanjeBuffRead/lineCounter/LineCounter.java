package mk.ukim.finki.np.vezhbi.auds.citanjeBuffRead.lineCounter;

public class LineCounter {
    private int lines;
    private int words;
    private int chars;

    public LineCounter(int lines, int words, int chars) {
        this.lines = lines;
        this.words = words;
        this.chars = chars;
    }

    public LineCounter(String line) {
        ++lines;
        words = line.split("\\s++").length;
        chars = line.trim().length();
    }

    public LineCounter sum(LineCounter other) {
        return new LineCounter(
                this.lines + other.lines,
                this.words + other.words,
                this.chars + other.chars);
    }

    @Override
    public String toString() {
        return String.format("Lines: %d, Words: %d, Chars: %d", lines, words, chars);
    }
}
