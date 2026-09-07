package queue;

import models.Patient;

public class EmergencyQueue {
    private QueueNode front;
    private QueueNode rear;

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Patient patient) {
        if (patient == null) {
            System.out.println("Cannot add an empty patient to the emergency queue.");
            return;
        }

        QueueNode newNode = new QueueNode(patient);

        // A queue inserts at the rear and removes from the front to maintain FIFO order.
        if (rear == null) {
            front = newNode;
            rear = newNode;
            return;
        }

        rear.next = newNode;
        rear = newNode;
    }

    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient is waiting.");
            return null;
        }

        Patient nextPatient = front.patient;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return nextPatient;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty.");
            return;
        }

        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }
}
