package tree;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class for representing simple arithmetic expressions.
 * @author Ryan Smith
 * @version Feb 10, 2015
 */
public class Expression {
    Tree<String> expressionTree;
    
    /**
     * Constructs a Tree<String> representing the given arithmetic expression,
     * then verifies that the newly created Tree is valid as an expression.
     * If the Tree is invalid, throws an IllegalArgumentException.<br>
     * Here are the validity rules:<ul>
     * <li>The value of each node must be one of "+", "-", "*", "/",
     *     or a String representing an unsigned integer.</li>
     * <li>If a node has value "+" or "*", it must have two or more children.</li>
     * <li>If a node has value "-" or "/", it must have exactly two children.</li>
     * <li>If a node contains a numeric string, it must be a leaf.</li></ul>
     * Note that the input parameter uses prefix notation, for example:
     * "+ (5 10 -( *(15 20) 25) 30)"
     * @param expression The String representation of the expression to be constructed.
     */
    public Expression(String expression) {
        expressionTree = Tree.parse(expression);
        if (!valid(expressionTree)) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }

    }

    /**
     * Tests whether the given Tree represents a valid Expression.
     * @param tree The input tree.
     * @return <code>true</code> iff the Tree is a valid Expression.
     */
    private boolean valid(Tree<String> tree) {
    	String currentVal = tree.getValue();
    	boolean isNumber = isNumber(tree);
        if (!isNumber && !currentVal.equals("+") && !currentVal.equals("*") && !currentVal.equals("-") && !currentVal.equals("/"))
        	return false;
        else if (isNumber && tree.getNumberOfChildren() > 0)
        	return false;
        else if (currentVal.equals("*") && tree.getNumberOfChildren() < 2){
        	return false;
        }
        else if (currentVal.equals("+") && tree.getNumberOfChildren() < 2)
        	return false;
        else if (currentVal.equals("-") && tree.getNumberOfChildren() != 2)
        	return false;
        else if (currentVal.equals("/") && tree.getNumberOfChildren() != 2)
        	return false;
        else {
        	if (tree.getNumberOfChildren() > 0){
        		for (int i = 0; i < tree.getNumberOfChildren(); i++){
        			if (valid(tree.getChild(i)) == false)
        				return false;
        		}
        	}
        }
        return true;
    }
    
    /**
     * Evaluates this Expression.
     * @return The value of this Expression.
     */
    public int evaluate() {
        return evaluate(expressionTree);
    }
    
    /**
     * Evaluates the given Tree, which must represent a valid Expression.
     * @return The value of this Expression.
     */
    private int evaluate(Tree<String> tree) {
        int total = 0;
    	boolean isNumber = isNumber(tree);
        if (isNumber)
        	return Integer.decode(tree.getValue());
        else{
        	if (tree.getValue().equals("+")){
        		for (int i = 0; i < tree.getNumberOfChildren(); i++){
        			total += evaluate(tree.getChild(i));
       			}
       		}
        	else if (tree.getValue().equals("-"))
        		total = evaluate(tree.getChild(0)) - evaluate(tree.getChild(1));
       		else if (tree.getValue().equals("*")){
        		for (int i = 0; i < tree.getNumberOfChildren(); i++){
        			if (i == 0)
        				total = 1;
       				total = total * evaluate(tree.getChild(i));
        		}
        	}
        	else if (tree.getValue().equals("/"))
       			total = evaluate(tree.getChild(0)) / evaluate(tree.getChild(1));
        }
        return total;   
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return toString(expressionTree);
    }
    
    private static String toString(Tree<String> tree) {
    	StringBuilder sb = new StringBuilder();
    	//if node is a leaf return its value
    	boolean isNumber = isNumber(tree);
        if (isNumber)
        	return tree.getValue();
        if (tree.getValue().equals("+")){
        	for (int i = 0; i < tree.getNumberOfChildren(); i++){
        		if (i == 0)
        			sb.append("(" + toString(tree.getChild(i)));
        		else
        			sb.append(" + " + toString(tree.getChild(i)));
        	}
        	sb.append(")");
        }
        if (tree.getValue().equals("-")){
        	for (int i = 0; i < tree.getNumberOfChildren(); i++){
        		if (i == 0)
        			sb.append("(" + toString(tree.getChild(i)));
        		else
        			sb.append(" - " + toString(tree.getChild(i)));
        	}
        	sb.append(")");
        }
       	if (tree.getValue().equals("*")){
       		for (int i = 0; i < tree.getNumberOfChildren(); i++){
        		if (i == 0)
        			sb.append("(" + toString(tree.getChild(i)));
        		else
        			sb.append(" * " + toString(tree.getChild(i)));
        	}
        	sb.append(")");
       	}
     	if (tree.getValue().equals("/")){
     		for (int i = 0; i < tree.getNumberOfChildren(); i++){
        		if (i == 0)
        			sb.append("(" + toString(tree.getChild(i)));
        		else
        			sb.append(" / " + toString(tree.getChild(i)));
        	}
        	sb.append(")");
     	}
     	return sb.toString();
    }
    
    private static boolean isNumber(Tree<String> tree){
    	boolean isNumber = true;
    	String currentVal = tree.getValue();
        char chars[] = currentVal.toCharArray();
        for (char c : chars){
        	if (!Character.isDigit(c))
        		isNumber = false;
        }
        return isNumber;
    }
}
