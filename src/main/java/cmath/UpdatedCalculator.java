/*
 * File: UpdatedCalculator.java
 * Description: Updated Dijkstra's calculator,
 * including arithmetic operations such as sin, cos, pow
 * with double values defined as a string
 * or a sequence of character symbols.
 * Authors:
 *   - Ilya Tsivilskiy
 * Redacted:
 *   - Danil Zabinskiy
 * Copyright: (c) 2024 Ilya Tsivilskiy
 * License: This file is licensed under the MIT License.
 */

package cmath;
import linkedlist.staque.Stack;

/**
 * The calculator that may evaluate arithmetic expressions
 */
public class UpdatedCalculator {
    /**
     * An arithmetic operator
     */
    public enum Operator {
        ADD('+'),
        SUB('-'),
        MUL('*'),
        DIV('/'),
        OPEN('('),
        CLOSE(')'),
        SIN('s'),
        COS('c'),
        TAN('t'),
        POW('^');

        /** by setting up trigonometry expressions like sin, cos...
         *  for chars I "killed 2 birds with one stone":
         *  using the Operator structure, without the need to redefine trigonometry Values
         *  as String (in the case of Symbol - Character), without the need for unnecessary
         *  transitions
         */

        private final char symbol;

        Operator(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }

        @Override
        public String toString() {
            return String.valueOf(this.symbol);
        }

        /**
         * Try to find the operator that is corresponding to given character
         * @param symbol A character to cast to the Operator
         * @return An operator corresponding to given symbol
         */
        public static Operator fromSymbol(char symbol) {
            // loop over possible values (ADD, SUBTRACT, ...)
            for (Operator operator : Operator.values()) {
                if (operator.getSymbol() == symbol) {
                    return operator;
                }
            }
            return null;
        }

        /**
         * Checks whether given symbol belongs to available Operator values
         * @param symbol A character to cast to the Operator
         * @return True, if the symbol is an Operator; False otherwise
         */
        public static boolean isOperator(char symbol) {
            return Operator.fromSymbol(symbol) != null;
        }

    } // end public enum Operator
    private static final char DOT = '.';

    /**
     * Evaluates an arithmetic expression
     * @param expression A string representation of the expression
     * @return last Calculated value
     */
    public static double eval(String expression, boolean debug) {
        // define the stacks of operators and values
        Stack<Operator> operators = new Stack<>();
        Stack<Double> values = new Stack<>();

        // Trim expression and replace all whitespaces
        expression = expression.trim().replaceAll("\\s+", "");

        // While staying in expression's range
        int currentIndex = 0;
        /*
        Using "While" loop to avoid useless
        incrementing and manage them as needed
         */
        while (currentIndex < expression.length()) {
            char currentChar = expression.charAt(currentIndex);
            // Handle the digit
            if (Character.isDigit(currentChar)) {
                int localPowCounter = 0;
                int fractionalCounter = 0; // To control infinity deviding
                double num = 0.0;
                boolean isFractional = false;
                // Similar loops doesn't upcent our complexity to O(n^2)
                // cause after that loop our currentIndex move to last
                // while's loop position
                while (currentIndex < expression.length() &&
                      (Character.isDigit(currentChar) || currentChar == DOT)) {
                    if (currentChar == DOT) {
                        isFractional = true; // Switch our state to upload num after the DOT
                        localPowCounter++;
                        currentIndex++;
                        // Predicting infinity deviding
                        fractionalCounter++;
                        if (fractionalCounter > 10) {
                            break;
                        }
                        currentChar = expression.charAt(currentIndex);
                        continue; // Skip the dot and continue parsing the fractional part
                    }
                    if (isFractional) {
                        // Add a fractional digit's part
                        num += Character.getNumericValue(currentChar) * Math.pow(10, -1 * (localPowCounter - 1));
                    } else {
                        num = num * 10 + Character.getNumericValue(currentChar);
                    }
                    localPowCounter++;
                    currentIndex++;

                    if (currentIndex < expression.length()) {
                        // Move our current character
                        currentChar = expression.charAt(currentIndex);
                    }
                }
                // Upload the num value into Stack<Double> values
                values.add(num);
            } else if (Operator.fromSymbol(currentChar) == Operator.SIN ||
                       Operator.fromSymbol(currentChar) == Operator.COS ||
                       Operator.fromSymbol(currentChar) == Operator.TAN) {
                // Remember our old value of CurrentIndex before incrementing
                Operator currentOp = Operator.fromSymbol(expression.charAt(currentIndex));
                // Assuming 's' is for sin(...), 'c' for cos(...)..
                currentIndex += 3;
                // Init some helpful params
                double result = 0;
                // If the next symbol is OPEN bracket : we should solve
                // expression into it: cos(60 + 23) -> cos(83) -> cos83
                if (Operator.fromSymbol(expression.charAt(currentIndex)) == Operator.OPEN) {
                    // Next char is OpenBracket => loop started
                    // with next char should notice 1 OPEN bracket
                    int bracketsOnTheWay = 1;
                    for (int j = currentIndex + 1; j < expression.length(); j++) {
                        // Meeting CLOSE bracket == checking if it is Parentheses
                        if (Operator.fromSymbol(expression.charAt(j)) == Operator.CLOSE) {
                            bracketsOnTheWay--;
                            /*
                            If bracketsOnTheWay == 0 : we bumped on a
                            matched close bracket for our open bracket
                            1   2  1 2  1   0 - we bumped on a pair
                            (...(..).(..)...)
                            */
                            if (bracketsOnTheWay == 0) {
                                // We bumped with a match CLOSE bracket

                                // HighLight the subString to count it by
                                // UpdatedCalculator.eval("subResult", true)
                                String subResult = expression.substring(currentIndex + 1, j);
                                result = eval(subResult, true);
                                // Move current index to don't increase our complexity
                                currentIndex = j + 1;
                                // Break the loop after finding new expression's value
                                break;
                            }
                        }
                    }
                } else {
                    // It means we have a basic expression
                    // above the trigonometry, lets count it

                    // Update the currentChar value
                    currentChar = expression.charAt(currentIndex);

                    boolean isFractional = false;
                    int localPowCounter = 0;
                    int fractionalCounter = 0; // To control infinity deviding
                    while (Character.isDigit(currentChar)) {
                        if (currentChar == DOT) {
                            isFractional = true;
                            currentIndex++;
                            localPowCounter++;

                            fractionalCounter++;

                            if (fractionalCounter > 10) {
                                break;
                            }
                            // Update currentChar after Incrementing
                            currentChar = expression.charAt(currentIndex);
                            continue; // Skip the dot and continue parsing the fractional part
                        }
                        if (isFractional) {
                            result += Character.getNumericValue(currentChar) * Math.pow(10, -1 * (localPowCounter - 1));
                        } else {
                            result = result * 10 + Character.getNumericValue(currentChar);
                        }
                        currentIndex++;
                        localPowCounter++;
                        if (currentIndex < expression.length()) {
                            currentChar = expression.charAt(currentIndex);
                        }
                    }
                    // Skip the Digit's tail FX:
                    // for 45.0 we'd skip 5,.,0 chars because
                    // we have added them. On the other side we have
                    // a localPowCounter == length of out digit =>
                    currentIndex += localPowCounter;
                }
                // Apply the trigonometric function
                switch (currentOp) {
                    case SIN -> values.add(Math.sin(result));
                    case COS -> values.add(Math.cos(result));
                    case TAN -> values.add(Math.tan(result));
                }
            } else if(Operator.fromSymbol(currentChar) == Operator.POW) {
                // If next Symbol is Open bracket -> Pow func has value in brackets
                // we split our expression in UpdatedCalculator.java, eval, line: 96;
                // that is why our next symbol should be a OPEN BRACKET OR A DIGIT
                if (Operator.fromSymbol(expression.charAt(currentIndex + 1)) == Operator.OPEN) {
                    int openedBracketIndex = currentIndex + 1;
                    int lastClosedBracket = 0;
                    // Param to control matched Brackets
                    int bracketsOnTheWay = 1; // Current symbol is OPEN Bracket
                    double result = 0D;
                    boolean state = true;
                    for (int j = openedBracketIndex + 1; j < expression.length() && state; j++) {
                        Operator currentOperator = Operator.fromSymbol(expression.charAt(j));
                        if (currentOperator == Operator.OPEN) {
                            bracketsOnTheWay++;
                        } else if (currentOperator == Operator.CLOSE) {
                            bracketsOnTheWay--;
                            /*
                            If bracketsOnTheWay == 0 : we bumped on a
                            matched close bracket for our open bracket
                            1   2  1 2  1   0 - we bumped on a pair
                            (...(..).(..)...)
                            */
                            if (bracketsOnTheWay == 0) {
                                lastClosedBracket = j;
                                String subResult = expression.substring(openedBracketIndex + 1, lastClosedBracket);
                                result = eval(subResult, true);
                                values.add(Math.pow(values.extract(), result));
                                state = false;
                                // Move current Index
                                currentIndex = j + 1;
                                break;
                            }
                        }
                    }
                } else {
                    currentIndex++;
                    char character = expression.charAt(currentIndex);

                    boolean isFractional = false; // To control fractional part of Digit
                    int fractionalCounter = 0; // To control infinity deviding
                    double num = 0D;
                    int localPowCounter = 0;
                    while (Character.isDigit(expression.charAt(currentIndex))) {
                        if (character == DOT) {
                            isFractional = true;
                            currentIndex++;
                            localPowCounter++;

                            fractionalCounter++;
                            if (fractionalCounter > 10) {
                                break;
                            }
                            character = expression.charAt(currentIndex);
                            continue; // Skip the dot and continue parsing the fractional part
                        }
                        if (isFractional) {
                            num += Character.getNumericValue(character) * Math.pow(10, -1 * (localPowCounter - 1));
                        } else {
                            num = num * 10 + Character.getNumericValue(character);
                        }
                        currentIndex++;
                        localPowCounter++;
                        if (currentIndex < expression.length()) {
                            character = expression.charAt(currentIndex);
                        } else {
                            break;
                        }
                    }
                    values.add(Math.pow(values.extract(), num));
                    currentIndex += 2;
                }
            } else {
                /*
                If currentNumber is Operator
                Make new Operator
                 */
                Operator operatorCurrent = Operator.fromSymbol(currentChar);
                if (operators.isEmpty()) {
                    // Push curr Operator into operators Stack
                    operators.add(operatorCurrent);
                    currentIndex++;
                } else if (operatorCurrent == Operator.ADD ||
                        operatorCurrent == Operator.SUB) {
                    // If current Operator is '+' or '-'
                    Operator topCharacter = operators.get();
                    if (topCharacter == Operator.OPEN) {
                        /*
                        If Current Operator is Open Bracket '('
                        than push it to handle after Close Bracket ')'
                         */
                        operators.add(operatorCurrent);
                        currentIndex++;
                    } else {
                        /*
                        Handle the expression into pair of Brackets
                        "(expression)" -> calculate (stack1, stack2)
                         */
                        calculate(values, operators);
                    }
                } else if (operatorCurrent == Operator.MUL ||
                           operatorCurrent == Operator.DIV) {
                    // If operator is '*' or '/' ->
                    Operator topCharacter = operators.get();

                    if (topCharacter == Operator.OPEN) {
                        // If last operator is '(' -> push it and count
                        operators.add(operatorCurrent);
                        currentIndex++;
                    } else if (topCharacter == Operator.MUL ||
                               topCharacter == Operator.DIV) {
                        // Same logic according to higher priority
                        calculate(values, operators);
                    } else if (topCharacter == Operator.ADD ||
                               topCharacter == Operator.SUB) {
                        // Add current Operator and handle it later
                        operators.add(operatorCurrent);
                        currentIndex++;
                    }
                } else if (operatorCurrent == Operator.OPEN) {
                    // Current operator is '(' -> add Open Bracket
                    operators.add(operatorCurrent);
                    currentIndex++;
                } else if (operatorCurrent == Operator.CLOSE) {
                    // If Current operator is ')' -> count till meet '('
                    while (operators.get() != Operator.OPEN) {
                        calculate(values, operators);
                    }
                    // Extract the '('
                    operators.extract();
                    currentIndex++;
                }
            }

        }
        // Count half-solved expression without precedence brackets
        while (!operators.isEmpty()) {
            calculate(values, operators);
        }
        // Return last Calculated value
        return values.get();
    }

    /**
     * Evaluates an arithmetic expression above two digits
     * @param values    A Stack<Double> of values of counted expression,
     * @param operators A Stack<Operator> of last added operators
     * Upload a value Stack by adding a result
     */
    private static void calculate(Stack<Double> values, Stack<Operator> operators) {
        double num1 = values.extract();
        double num2 = values.extract();

        switch (operators.extract()) {
            // Push the result back onto the values stack
            case ADD -> values.add(num2 + num1);
            case SUB -> values.add(num2 - num1);
            case MUL -> values.add(num2 * num1);
            case DIV -> {
                if (num2 == 0) {
                    throw new ArithmeticException("Cannot divide by 0");
                }
                values.add(num2 / num1);
            }
        }
    }
}
