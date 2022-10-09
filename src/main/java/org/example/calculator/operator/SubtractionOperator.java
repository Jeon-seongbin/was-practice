package org.example.calculator.operator;

import org.example.calculator.NewArithmeticOperator;

public class SubtractionOperator implements NewArithmeticOperator {
    @Override
    public boolean surpports(String operator) {
        return "-".equals(operator);
    }

    @Override
    public int calculator(int operand1, int operand2) {
        return operand1 - operand2;
    }
}
