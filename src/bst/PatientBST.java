package bst;

import models.Patient;

public class PatientBST {
    private PatientNode root;

    public boolean isEmpty() {
        return root == null;
    }

    public boolean insert(Patient patient) {
        if (patient == null) {
            return false;
        }

        if (root == null) {
            root = new PatientNode(patient);
            return true;
        }

        return insertRecursive(root, patient);
    }

    // BST insertion compares patient IDs and places smaller IDs left, larger IDs right.
    private boolean insertRecursive(PatientNode current, Patient patient) {
        int patientId = patient.getPatientId();
        int currentId = current.patient.getPatientId();

        if (patientId == currentId) {
            return false;
        }

        if (patientId < currentId) {
            if (current.left == null) {
                current.left = new PatientNode(patient);
                return true;
            }
            return insertRecursive(current.left, patient);
        }

        if (current.right == null) {
            current.right = new PatientNode(patient);
            return true;
        }
        return insertRecursive(current.right, patient);
    }

    public Patient search(int patientId) {
        PatientNode result = searchRecursive(root, patientId);
        return result == null ? null : result.patient;
    }

    private PatientNode searchRecursive(PatientNode current, int patientId) {
        if (current == null || current.patient.getPatientId() == patientId) {
            return current;
        }

        if (patientId < current.patient.getPatientId()) {
            return searchRecursive(current.left, patientId);
        }

        return searchRecursive(current.right, patientId);
    }

    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false;
        }

        root = deleteRecursive(root, patientId);
        return true;
    }

    private PatientNode deleteRecursive(PatientNode current, int patientId) {
        if (current == null) {
            return null;
        }

        if (patientId < current.patient.getPatientId()) {
            current.left = deleteRecursive(current.left, patientId);
            return current;
        }

        if (patientId > current.patient.getPatientId()) {
            current.right = deleteRecursive(current.right, patientId);
            return current;
        }

        if (current.left == null && current.right == null) {
            return null;
        }

        if (current.left == null) {
            return current.right;
        }

        if (current.right == null) {
            return current.left;
        }

        PatientNode successor = findMinimum(current.right);
        current.patient = successor.patient;
        current.right = deleteRecursive(current.right, successor.patient.getPatientId());
        return current;
    }

    private PatientNode findMinimum(PatientNode current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public void displayInOrder() {
        if (isEmpty()) {
            System.out.println("No patient records available.");
            return;
        }

        // In-order traversal prints BST keys in ascending order.
        displayInOrderRecursive(root);
    }

    private void displayInOrderRecursive(PatientNode current) {
        if (current == null) {
            return;
        }

        displayInOrderRecursive(current.left);
        System.out.println(current.patient);
        displayInOrderRecursive(current.right);
    }
}
