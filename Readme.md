This project contains an extended calculator capable of evaluating arithmetic expressions given as strings. It supports addition, subtraction, multiplication, division, exponentiation, and trigonometric functions such as sin, cos, and tan. The calculator works with both integer and fractional numbers.

The expression is processed character by character. The calculator uses two stacks: one for operators and one for numeric values. It handles numbers, operators, parentheses, and trigonometric functions. When encountering parentheses or expressions for functions and exponentiation, it evaluates them recursively. After the full expression is parsed, the remaining operations are processed according to their priority.

The project also includes a simple Stack data structure and a small JavaFX application that displays “Hello METANIT.COM!” in a window. Unit tests are provided to check basic arithmetic operations, trigonometric functions, exponentiation, complex expressions, and error handling.

This project demonstrates how the calculator works, how the stack structure is used, and includes a minimal interface and a set of tests.