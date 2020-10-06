public class StackClass {
    Stack top;
    Stack min;

    static class Stack {
        int data;
        Stack next;

        public Stack(int d) {
            data = d;
            next = null;
        }
    }

    public void push(int d) {
        Stack item = new Stack(d);
        if (top == null) {
            System.out.println("Stack is empty");
            top = item;
            min = item;
            return;
        }

        item.next = top;
        top = item;
        if (item.data < min.data)
            min = item;
    }

    public Stack pop() {
        if (top == null) return null;
        Stack item = top;
        top = top.next;
        return item;
    }

    public Stack peek() {
        if (top == null) return null;
        return top;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public static void main(String args[]) {
        StackClass stackClass = new StackClass();
        stackClass.push(10);
        System.out.println("Top is: " + stackClass.top.data);
        System.out.println("Min is: " + stackClass.min.data);
        stackClass.push(30);
        System.out.println("Top is: " + stackClass.top.data);
        System.out.println("Min is: " + stackClass.min.data);
        stackClass.push(20);
        System.out.println("Top is: " + stackClass.top.data);
        System.out.println("Min is: " + stackClass.min.data);
        stackClass.push(5);
        System.out.println("Top is: " + stackClass.top.data);
        System.out.println("Min is: " + stackClass.min.data);
        stackClass.push(40);
        System.out.println("Top is: " + stackClass.top.data);
        System.out.println("Min is: " + stackClass.min.data);
//        stackClass.pop();
        stackClass.push(2);
        System.out.println("Top is: " + stackClass.top.data);
        System.out.println("Min is: " + stackClass.min.data);
    }
}
