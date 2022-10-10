package org.example.calculator.operator;


import org.example.calculator.NewArithmeticOperator;

public class MultiplyOperator implements NewArithmeticOperator {
    @Override
    public boolean supports(String operator) {
        return "*".equals(operator);
    }

    @Override
    public int calculator(int operand1, int operand2) {
        return operand1 * operand2;
    }
}
