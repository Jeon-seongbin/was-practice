package org.example.calculator;


import org.example.calculator.operator.AdditionOperator;
import org.example.calculator.operator.DivisionOperator;
import org.example.calculator.operator.MultiplyOperator;
import org.example.calculator.operator.SubtractionOperator;

import java.util.List;

public class Calculator {

//    public static int calculator(int operand1, String operand, int operand2) {
//        if (operand.equals("+")) {
//            return ArithmeticOperator.ADDITION.arithmeticCalculate(operand1, operand2);
//        } else if (operand.equals("-")) {
//            return  ArithmeticOperator.SUBTRACTION.arithmeticCalculate(operand1, operand2);
//        } else if (operand.equals("*")) {
//            return  ArithmeticOperator.ADDITION.arithmeticCalculate(operand1, operand2);
//        } else if (operand.equals("/")) {
//            return ArithmeticOperator.ADDITION.arithmeticCalculate(operand1, operand2);
//        }
//
//        return 0;
//    }

    private static final List<NewArithmeticOperator> arithmeticOperatorList =
            List.of(
                    new AdditionOperator(),
                    new SubtractionOperator(),
                    new MultiplyOperator(),
                    new DivisionOperator()
            );

    public static int calculator(int operand1, String operator, int operand2) {

        return arithmeticOperatorList.stream()
                .filter(arithmeticOperator -> arithmeticOperator.supports(operator))
                .map(arithmeticOperator -> arithmeticOperator.calculator(operand1, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙 연산이 아닙니다"));
    }
}
