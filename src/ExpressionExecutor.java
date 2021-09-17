import java.util.List;

public class ExpressionExecutor {
    public static double execute(String expression) {
        List<Lexeme> lexemes = new Analyze().lexAnalyze(expression);
        return new SyntaxAnalyze().expr(new LexemeBuffer(lexemes));
    }
}
