public class SyntaxAnalyze {
    // рекурентные правила, определяемые друг через друга

//    expr : plusminus* EOF ;
//
//    plusminus: multdiv ( ( '+' | '-' ) multdiv )* ;
//
//    multdiv : factor ( ( '*' | '/' ) factor )* ;
//
//    factor : unary | NUMBER | '(' expr ')' ;
//
//    unary : '-' factor;


//синтаксический анализ по массиву лексем

    public double expr(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == Analyze.LexemType.EOF) {
            return 0;
        } else {
            lexemes.back();
            return plusminus(lexemes);
        }
    }

    private double plusminus(LexemeBuffer lexemes) {
        double value = multdiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_PLUS -> value = value + multdiv(lexemes);
                case OP_MINUS -> value = value - multdiv(lexemes);
                default -> {
                    lexemes.back();
                    return value;
                }
            }
        }
    }

    private double multdiv(LexemeBuffer lexemes) {
        double value = factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_MUL -> value = value * factor(lexemes);
                case OP_DIV -> value = value / factor(lexemes);
                default -> {
                    lexemes.back();
                    return value;
                }
            }
        }
    }

    private double factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();             //читаем лексему и проверяем тип
        switch (lexeme.type) {
            case OP_MINUS:
                double valueUnary = factor(lexemes);    //унарный минус
                return -valueUnary;
            case NUMBER:
                return Double.parseDouble(lexeme.value);
            case LEFT_BRACKET:
                double value = expr(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != Analyze.LexemType.RIGHT_BRACKET) {
                    throw new RuntimeException("Неверный символ: " + lexeme.value
                            + " в позиции: " + lexemes.getPos());
                }
                return value;
            default:
                throw new RuntimeException("Неверный символ: " + lexeme.value
                        + " в позиции: " + lexemes.getPos());
        }
    }
}
