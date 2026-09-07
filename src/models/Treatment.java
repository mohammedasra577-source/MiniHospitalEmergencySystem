package models;

public class Treatment {
    private int treatmentId;
    private int patientId;
    private String patientName;
    private String doctorName;
    private String treatmentDescription;
    private String treatmentDate;

    public Treatment(int treatmentId, int patientId, String patientName, String doctorName,
                     String treatmentDescription, String treatmentDate) {
        this.treatmentId = treatmentId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.treatmentDescription = treatmentDescription;
        this.treatmentDate = treatmentDate;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public String getTreatmentDate() {
        return treatmentDate;
    }

    @Override
    public String toString() {
        return "Treatment ID: " + treatmentId
                + " | Patient ID: " + patientId
                + " | Patient: " + patientName
                + " | Doctor: " + doctorName
                + " | Description: " + treatmentDescription
                + " | Date: " + treatmentDate;
    }
}
