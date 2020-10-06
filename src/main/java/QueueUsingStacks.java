import java.util.Stack;

public class QueueUsingStacks {
    public Stack<Integer> oldStack;
    public Stack<Integer> newStack;

    public QueueUsingStacks() {
        oldStack = new Stack<>();
        newStack = new Stack<>();
    }

    public void push(Integer d) {
        shiftOldToNewStacks();
        newStack.push(d);
    }

    public Integer pop() {
        Integer poppedElement = null;
        shiftNewToOldStacks();
        if (!oldStack.isEmpty()) {
            poppedElement = oldStack.pop();
        }
        return poppedElement;
    }

    public void shiftNewToOldStacks() {
        while (!newStack.isEmpty()) {
            oldStack.push(newStack.pop());
        }
    }

    public void shiftOldToNewStacks() {
        while (!oldStack.isEmpty()) {
            newStack.push(oldStack.pop());
        }
    }

    public Integer peek() {
        shiftNewToOldStacks();
        return oldStack.peek();
    }

    public static void main(String args[]) {
        QueueUsingStacks queueUsingStacks = new QueueUsingStacks();
        queueUsingStacks.push(1);
        queueUsingStacks.push(2);
        queueUsingStacks.push(3);
        queueUsingStacks.push(4);

        System.out.println("Peek element is: " + queueUsingStacks.peek());
        System.out.println("Popped element is: " + queueUsingStacks.pop());
        queueUsingStacks.push(10);
        queueUsingStacks.push(15);
        System.out.println("Peek element is: " + queueUsingStacks.peek());
        System.out.println("Popped element is: " + queueUsingStacks.pop());
        System.out.println("Popped element is: " + queueUsingStacks.pop());
        System.out.println("Popped element is: " + queueUsingStacks.pop());
        System.out.println("Popped element is: " + queueUsingStacks.pop());
        System.out.println("Popped element is: " + queueUsingStacks.pop());
    }

}
