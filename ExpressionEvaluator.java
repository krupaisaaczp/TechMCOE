import java.util.Stack;

public class ExpressionEvaluator {
    public static void main(String[] args) {
        String[] expressions = {
            "2+3*4",
            "10-2*3+4",
            "5*(2+3)",
            "20/4+2"
        };
        
        for (String expr : expressions) {
            System.out.println(expr + " = " + evaluateExpression(expr));
        }
    }
    
    public static double evaluateExpression(String expression) {
        char[] tokens = expression.toCharArray();
        
        // Stack for numbers
        Stack<Double> values = new Stack<>();
        
        // Stack for operators
        Stack<Character> ops = new Stack<>();
        
        for (int i = 0; i < tokens.length; i++) {
            // Skip whitespace
            if (tokens[i] == ' ')
                continue;
            
            // If current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuilder sb = new StringBuilder();
                // Extract the complete number
                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
                    sb.append(tokens[i++]);
                }
                i--; // Adjust index after the loop
                values.push(Double.parseDouble(sb.toString()));
            }
            
            // If current token is an opening brace, push it to ops stack
            else if (tokens[i] == '(') {
                ops.push(tokens[i]);
            }
            
            // If closing brace, solve the entire brace
            else if (tokens[i] == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOperation(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop(); // Remove the '(' from stack
            }
            
            // If current token is an operator
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                // While top of ops has same or greater precedence to current
                // token, apply operator on top of ops to top two elements in
                // values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                    values.push(applyOperation(ops.pop(), values.pop(), values.pop()));
                }
                
                // Push current token to ops
                ops.push(tokens[i]);
            }
        }
        
        // Apply remaining ops to remaining values
        while (!ops.empty()) {
            values.push(applyOperation(ops.pop(), values.pop(), values.pop()));
        }
        
        // Top of values contains result
        return values.pop();
    }
    
    // Returns true if op2 has higher or same precedence as op1
    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        return true;
    }
    
    // Apply an operation 'op' on operands a and b
    public static double applyOperation(char op, double b, double a) {
        switch (op) {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        case '/':
            if (b == 0)
                throw new ArithmeticException("Division by zero");
            return a / b;
        }
        return 0;
    }
}
