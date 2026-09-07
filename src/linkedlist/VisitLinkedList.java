package linkedlist;

import models.Visit;

public class VisitLinkedList {
    private VisitNode head;

    public boolean isEmpty() {
        return head == null;
    }

    public boolean addVisit(Visit visit) {
        if (visit == null || searchVisit(visit.getVisitId()) != null) {
            return false;
        }

        VisitNode newNode = new VisitNode(visit);

        // A singly linked list stores each visit in a node that points to the next node.
        if (head == null) {
            head = newNode;
            return true;
        }

        VisitNode current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        return true;
    }

    public boolean removeVisit(int visitId) {
        if (head == null) {
            return false;
        }

        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            return true;
        }

        VisitNode current = head;
        while (current.next != null && current.next.visit.getVisitId() != visitId) {
            current = current.next;
        }

        if (current.next == null) {
            return false;
        }

        current.next = current.next.next;
        return true;
    }

    public Visit searchVisit(int visitId) {
        VisitNode current = head;

        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                return current.visit;
            }
            current = current.next;
        }

        return null;
    }

    public void displayVisits() {
        if (isEmpty()) {
            System.out.println("No visit history available for this patient.");
            return;
        }

        VisitNode current = head;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.visit);
            current = current.next;
            position++;
        }
    }
}
