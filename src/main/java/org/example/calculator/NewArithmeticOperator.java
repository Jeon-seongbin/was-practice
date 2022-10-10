package org.example.calculator;

public interface NewArithmeticOperator {
    boolean supports(String operator);
    int calculator(int operand1, int operand2);
}
