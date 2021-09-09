import java.util.ArrayList;
import java.util.List;


public class LexAnalyze {
//анализ лексем

    public List<Lexeme> lexAnalyze(String expText) {
        List<Lexeme> lexemes = new ArrayList<>();
        expText= expText.replaceAll("\s+", "");
        int pos = 0;

        while (pos < expText.length()) {
            char c = expText.charAt(pos);
            switch (c) {
                case '(':
                    lexemes.add(new Lexeme(LexemType.LEFT_BRACKET, c));
                    pos++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemType.RIGHT_BRACKET, c));
                    pos++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(LexemType.OP_PLUS, c));
                    pos++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemType.OP_MINUS, c));
                    pos++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemType.OP_MUL, c));
                    pos++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemType.OP_DIV, c));
                    pos++;
                    continue;
                default:
                    if (c <= '9' && c >= '0') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= expText.length()) {
                                break;
                            }
                            c = expText.charAt(pos);
                        } while ((c <= '9' && c >= '0') || c == '.');
                        lexemes.add(new Lexeme(LexemType.NUMBER, sb.toString()));
                } else {
                        throw new RuntimeException("Неизвестный символ: " + c);
                    }
            }
        }
        lexemes.add(new Lexeme(LexemType.EOF, ""));
        return lexemes;
    }
}
