package bst;

import models.Patient;

public class PatientNode {
    Patient patient;
    PatientNode left;
    PatientNode right;

    public PatientNode(Patient patient) {
        this.patient = patient;
        this.left = null;
        this.right = null;
    }
}
