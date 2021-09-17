import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class UnitTests {

    public static void test() throws Exception {

        List<Method> methods = new ArrayList<>();
        for (Method method : UnitTests.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                methods.add(method);
            }
        }

        for (Method method : methods) {
            try {
                method.invoke(null);
                System.out.println("Метод " + method.getName() + " успешно");
            } catch (InvocationTargetException e) {
                System.out.println("Ошибка в методе: " + method.getName() + " текст ошибки: " + e.getCause().getMessage());
            }
        }
    }

    @Test
    public static void testPlus() {
        double result = execExp("22 + 4");
        double expected = 22.0 + 4.0;
        if (result != expected) {
            throw new TestException(expected, result);
        }
    }

    @Test
    public static void testMinus() {
        final double result = execExp("22 - 4");
        final double expected = 22.0 - 4.0;
        if (result != expected) {
            throw new TestException(expected, result);
        }
    }

    @Test
    public static void testMul() {
        final double result = execExp("22 * 4");
        final double expected = 22.0 * 4.0;
        if (result != expected) {
            throw new TestException(expected, result);
        }
    }

    @Test
    public static void testDiv() {
        double result = execExp("22 / 4");
        double expected = 22.0 / 4.0;
        if (result != expected) {
            throw new TestException(expected, result);
        }
    }

    @Test
    public static void testOpMul() {
        double result = execExp("22 + 22 * 4");
        double expected = 22.0 + 22.0 * 4.0;
        if (result != expected) {
            throw new TestException(expected, result);
        }
    }

    @Test
    public static void testOpDiv() {
        double result = execExp("22 + 22 / 4");
        double expected = 22.0 + 22.0 / 4.0;
        if (result != expected) {
            throw new TestException(expected, result);
        }
    }

    @Test
    public static void testUnary() {
        double result = execExp("-22 + -4");
        double expected = (-22.0) + (-4.0);
        if (result != expected) {
            throw new TestException(expected, result);
        }
    }

    @Test
    public static void testUnaryMul() {
        double result = execExp("-22 * -4");
        double expected = (-22.0) * (-4.0);
        if (result != expected) {
            throw new TestException(expected, result);
        }
    }

    @Test
    public static void testUnaryDiv() {
        double result = execExp("-22 / -4");
        double expected = (-22.0) / (-4.0);
        if (result != expected) {
            throw new TestException(expected, result);
        }
    }

    @Test
    public static void testStrong() {
        double result = execExp("22 + 3 - 2 * (2 * 5 + 2) * 4.2");
        double expected = 22.0 + 3.0 - 2.0 * (2.0 * 5.0 + 2.0) * 4.2;
        if (result != expected) {
            throw new TestException(expected, result);
        }
    }

    @Test
    public static void testStrongUnary() {
        double result = execExp("-22 + 3 - 2 * (2 * 5 + 2) * 4.2");
        double expected = -22.0 + 3.0 - 2.0 * (2.0 * 5.0 + 2.0) * 4.2;
        if (result != expected) {
            throw new TestException(expected, result);
        }
    }

    private static double execExp(String exp) {
        return ExpressionExecutor.execute(exp);
    }

    private static class TestException extends RuntimeException {
        public TestException(Object expected, Object result) {
            super("Ожидаемый результат " + expected + " фактический результат " + result);
        }
    }
}



