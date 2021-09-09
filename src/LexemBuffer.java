import java.util.List;

public class LexemBuffer {
//буфер лексем

    private int pos;

    public List<Lexeme> lexemes;

    public LexemBuffer(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    public Lexeme next() {
        return lexemes.get(pos++);
    }

    public void back(){
        pos--;
    }

    public int getPos() {
        return pos;
    }
}
