import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    //-22 + 3 - 2 * (2 * 5 + 2) * 4.2

    public static void main(String[] args) throws Exception {

        if (Boolean.parseBoolean(System.getenv("CALC_TEST"))) {
            UnitTests.test();
        } else {
            System.out.println("Введите выражение: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String expressionText = reader.readLine();

            double result = ExpressionExecutor.execute(expressionText);
            System.out.println("Результат: " + result);
        }
    }
}



