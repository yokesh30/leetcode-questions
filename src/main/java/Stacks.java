import java.util.Iterator;
import java.util.Stack;

public class Stacks extends Stack<Integer> {
    public Stack<Integer> minStack;

    public Stacks() {
        minStack = new Stack<>();
    }

    public void push(int d) {
        super.push(d);
        if (d < top()) {
            minStack.push(d);
            return;
        }
    }

    public Integer pop() {
        Integer poppedElement = super.pop();
        if (poppedElement == top()) {
            minStack.pop();
        }
        return poppedElement;
    }

    public int top() {
        if (minStack.isEmpty()) return Integer.MAX_VALUE;
        return minStack.peek();
    }

    public Integer peek() {
        return super.peek();
    }

    public Integer min() {
        return minStack.peek();
    }

    public Stack<Integer> sort(Stacks stack) {
        //3, 5, 1, 4, 2, 8
        Stack<Integer> tempStack = new Stack<>();
        tempStack.push(stack.pop()); //8
        while (!stack.isEmpty()) {
            Integer temp = stack.pop();
            while (!tempStack.isEmpty() && temp < tempStack.peek()) {
                stack.push(tempStack.pop());
            }
            tempStack.push(temp);
        }
        return tempStack;
    }

    public void print(Stack<Integer> stack) {
        Iterator<Integer> iterator = stack.iterator();
        iterator.forEachRemaining(c -> {
            System.out.println(c);
        });
    }

    public static void main(String args[]) {
        Stacks stacks = new Stacks();
//        stacks.push(10);
//        System.out.println("Top element is :" + stacks.peek());
//        System.out.println("Min element is :" + stacks.min());
//        stacks.push(20);
//        System.out.println("Top element is :" + stacks.peek());
//        System.out.println("Min element is :" + stacks.min());
//        stacks.push(8);
//        System.out.println("Top element is :" + stacks.peek());
//        System.out.println("Min element is :" + stacks.min());
//        stacks.push(30);
//        System.out.println("Top element is :" + stacks.peek());
//        System.out.println("Min element is :" + stacks.min());
//        stacks.push(5);
//        System.out.println("Top element is :" + stacks.peek());
//        System.out.println("Min element is :" + stacks.min());
//        stacks.pop();
//        System.out.println("Top element is :" + stacks.peek());
//        System.out.println("Min element is :" + stacks.min());

        stacks.push(3);
        stacks.push(5);
        stacks.push(1);
        stacks.push(4);
        stacks.push(2);
        stacks.push(8);
        Stack<Integer> sort = stacks.sort(stacks);
        stacks.print(sort);
    }
}


