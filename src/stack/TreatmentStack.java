package stack;

import models.Treatment;

public class TreatmentStack {
    private StackNode top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Treatment treatment) {
        if (treatment == null) {
            System.out.println("Cannot add an empty treatment record.");
            return;
        }

        StackNode newNode = new StackNode(treatment);

        // A stack pushes and pops from the same end, giving LIFO behavior.
        newNode.next = top;
        top = newNode;
    }

    public Treatment pop() {
        if (isEmpty()) {
            System.out.println("Treatment history stack is empty.");
            return null;
        }

        Treatment removedTreatment = top.treatment;
        top = top.next;
        return removedTreatment;
    }

    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("No treatment history available.");
            return;
        }

        StackNode current = top;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.treatment);
            current = current.next;
            position++;
        }
    }
}
