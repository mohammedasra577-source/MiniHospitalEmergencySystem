package linkedlist;

import models.Visit;

public class VisitNode {
    Visit visit;
    VisitNode next;

    public VisitNode(Visit visit) {
        this.visit = visit;
        this.next = null;
    }
}
