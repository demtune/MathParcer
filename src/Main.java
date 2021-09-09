import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

//22 + 3 - 2 * (2 * 5 + 2) * 4.2

    public static void main(String[] args) throws IOException {
        System.out.println("Введите выражение: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String expressionText = reader.readLine();

        List<Lexeme> lexemes = new LexAnalyze().lexAnalyze(expressionText);
        double result = new SyntaxAnalyze().expr(new LexemBuffer(lexemes));
        System.out.println("Результат: " + result);
    }
}


