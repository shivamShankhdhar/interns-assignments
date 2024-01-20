package ignitersHubAssignment;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class ArithmeticExpressionSolver {

    public static void main(String[] args) throws IOException {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String result = solveExpression(line);
                writer.write(line + " " + result + "\n");
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(outputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solveExpression(String expression) {
        Stack<Double> numberStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);

            if (Character.isDigit(character)) {
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.') {
                    number.append(expression.charAt(i++));
                }
                i--; // Adjust for the extra increment in the loop
                numberStack.push(Double.parseDouble(number.toString()));
            } else if (isOperator(character)) {
                while (!operatorStack.isEmpty() && hasPrecedence(character, operatorStack.peek())) {
                    double secondOperand = numberStack.pop();
                    double firstOperand = numberStack.pop();
                    char operator = operatorStack.pop();
                    numberStack.push(performOperation(firstOperand, secondOperand, operator));
                }
                operatorStack.push(character);
            }
        }

        while (!operatorStack.isEmpty()) {
            double secondOperand = numberStack.pop();
            double firstOperand = numberStack.pop();
            char operator = operatorStack.pop();
            numberStack.push(performOperation(firstOperand, secondOperand, operator));
        }

        return String.valueOf(numberStack.pop());
    }

    private static boolean isOperator(char character) {
        return character == '+' || character == '-' || character == '*' || character == '/' || character == '^';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '^') {
            return false;
        } else if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        } else {
            return true;
        }
    }

    private static double performOperation(double firstOperand, double secondOperand, char operator) {
        switch (operator) {
            case '+':
                return firstOperand + secondOperand;
            case '-':
                return firstOperand - secondOperand;
            case '*':
                return firstOperand * secondOperand;
            case '/':
                return firstOperand / secondOperand;
            case '^':
                return Math.pow(firstOperand, secondOperand);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    
}
