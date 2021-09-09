public class SyntaxAnalyze {
// рекурентные правила, определяемые друг через друга

//    expr : plusminus* EOF ;
//
//    plusminus: multdiv ( ( '+' | '-' ) multdiv )* ;
//
//    multdiv : factor ( ( '*' | '/' ) factor )* ;
//
//    factor : NUMBER | '(' expr ')' ;

//синтаксический анализ по массиву лексем


    public double expr(LexemBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemType.EOF) {
            return 0;
        } else {
            lexemes.back();
            return plusminus(lexemes);
        }
    }

    public double plusminus(LexemBuffer lexemes) {
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

    public double multdiv(LexemBuffer lexemes) {
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

    public double factor(LexemBuffer lexemes) {
        Lexeme lexeme = lexemes.next();             //читаем лексему и проверяем тип
        switch (lexeme.type) {
            case NUMBER:
                return Double.parseDouble(lexeme.value);
            case LEFT_BRACKET:
                double value = expr(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != LexemType.RIGHT_BRACKET) {
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
