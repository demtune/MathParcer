public class Lexeme {
    LexemType type;
    String value;

    public Lexeme(LexemType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Lexeme(LexemType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
