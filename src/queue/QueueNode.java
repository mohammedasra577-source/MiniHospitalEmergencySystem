package queue;

import models.Patient;

public class QueueNode {
    Patient patient;
    QueueNode next;

    public QueueNode(Patient patient) {
        this.patient = patient;
        this.next = null;
    }
}
