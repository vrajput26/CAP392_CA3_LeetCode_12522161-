
import java.util.*;

interface QueueOperations {
    void push(int x);
    int pop();
    int peek();
    boolean empty();
}

class MyQueue implements QueueOperations {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public void push(int x) {
        s1.push(x);
    }

    public int pop() {
        moveElements();
        return s2.pop();
    }

    public int peek() {
        moveElements();
        return s2.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    private void moveElements() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
    }
}
