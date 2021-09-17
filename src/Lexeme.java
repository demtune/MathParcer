class Lexeme {
    Analyze.LexemType type;
    String value;

    public Lexeme(Analyze.LexemType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Lexeme(Analyze.LexemType type, Character value) {
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

