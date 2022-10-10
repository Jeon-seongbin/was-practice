package org.example.calculator.operator;


import org.example.calculator.NewArithmeticOperator;

public class DivisionOperator implements NewArithmeticOperator {
    @Override
    public boolean supports(String operator) {
        return "/".equals(operator);
    }

    @Override
    public int calculator(int operand1, int operand2) {
        if(operand2 == 0){

            System.out.println("operand2: "+ operand2);
            throw new IllegalArgumentException("");
        }
        return operand1 / operand2;
    }
}
