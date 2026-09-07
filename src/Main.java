import bst.PatientBST;
import models.Patient;
import models.Treatment;
import models.Visit;
import queue.EmergencyQueue;
import stack.TreatmentStack;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PatientBST patientBST = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentStack treatmentStack = new TreatmentStack();

    public static void main(String[] args) {
        loadSampleData();
        runMenu();
    }

    private static void runMenu() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readIntInRange("Enter your choice: ", 1, 15);

            switch (choice) {
                case 1:
                    registerNewPatient();
                    break;
                case 2:
                    searchPatient();
                    break;
                case 3:
                    deletePatient();
                    break;
                case 4:
                    displayAllPatients();
                    break;
                case 5:
                    addEmergencyPatient();
                    break;
                case 6:
                    treatNextEmergencyPatient();
                    break;
                case 7:
                    displayEmergencyQueue();
                    break;
                case 8:
                    addTreatmentHistory();
                    break;
                case 9:
                    removeMostRecentTreatment();
                    break;
                case 10:
                    viewTreatmentHistory();
                    break;
                case 11:
                    addPatientVisit();
                    break;
                case 12:
                    searchPatientVisit();
                    break;
                case 13:
                    removePatientVisit();
                    break;
                case 14:
                    displayPatientVisitHistory();
                    break;
                case 15:
                    running = false;
                    System.out.println("Thank you for using the Hospital Emergency Management System.");
                    break;
                default:
                    System.out.println("Invalid menu option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("========== Hospital Emergency Management System ==========");
        System.out.println("1. Register New Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Delete Patient");
        System.out.println("4. Display All Patients");
        System.out.println("5. Add Emergency Patient");
        System.out.println("6. Treat Next Emergency Patient");
        System.out.println("7. Display Emergency Queue");
        System.out.println("8. Add Treatment History");
        System.out.println("9. Remove Most Recent Treatment");
        System.out.println("10. View Treatment History");
        System.out.println("11. Add Patient Visit");
        System.out.println("12. Search Patient Visit");
        System.out.println("13. Remove Patient Visit");
        System.out.println("14. Display Patient Visit History");
        System.out.println("15. Exit");
        System.out.println("==========================================================");
    }

    private static void registerNewPatient() {
        System.out.println("\n--- Register New Patient ---");
        int patientId = readPositiveInt("Patient ID: ");

        if (patientBST.search(patientId) != null) {
            System.out.println("A patient with this ID already exists.");
            return;
        }

        String patientName = readRequiredString("Patient Name: ");
        int age = readIntInRange("Age: ", 1, 120);
        String contactNumber = readContactNumber("Contact Number: ");
        String medicalCondition = readRequiredString("Medical Condition: ");

        Patient patient = new Patient(patientId, patientName, age, contactNumber, medicalCondition);
        patientBST.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        System.out.println("\n--- Search Patient ---");
        int patientId = readPositiveInt("Enter Patient ID: ");
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
        } else {
            System.out.println(patient);
        }
    }

    private static void deletePatient() {
        System.out.println("\n--- Delete Patient ---");
        int patientId = readPositiveInt("Enter Patient ID to delete: ");

        if (patientBST.delete(patientId)) {
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("Patient not found. Nothing was deleted.");
        }
    }

    private static void displayAllPatients() {
        System.out.println("\n--- All Patients Sorted by Patient ID ---");
        patientBST.displayInOrder();
    }

    private static void addEmergencyPatient() {
        System.out.println("\n--- Add Emergency Patient ---");
        Patient patient = findPatientByInput();

        if (patient == null) {
            return;
        }

        emergencyQueue.enqueue(patient);
        System.out.println("Patient added to the emergency waiting queue.");
    }

    private static void treatNextEmergencyPatient() {
        System.out.println("\n--- Treat Next Emergency Patient ---");
        Patient patient = emergencyQueue.dequeue();

        if (patient == null) {
            return;
        }

        System.out.println("Next patient for treatment:");
        System.out.println(patient);

        if (readYesNo("Record completed treatment now? (Y/N): ")) {
            Treatment treatment = readTreatmentForPatient(patient);
            treatmentStack.push(treatment);
            System.out.println("Treatment record added to history stack.");
        }
    }

    private static void displayEmergencyQueue() {
        System.out.println("\n--- Emergency Waiting Queue ---");
        emergencyQueue.displayQueue();
    }

    private static void addTreatmentHistory() {
        System.out.println("\n--- Add Treatment History ---");
        Patient patient = findPatientByInput();

        if (patient == null) {
            return;
        }

        Treatment treatment = readTreatmentForPatient(patient);
        treatmentStack.push(treatment);
        System.out.println("Treatment record added successfully.");
    }

    private static void removeMostRecentTreatment() {
        System.out.println("\n--- Remove Most Recent Treatment ---");
        Treatment removedTreatment = treatmentStack.pop();

        if (removedTreatment != null) {
            System.out.println("Removed treatment record:");
            System.out.println(removedTreatment);
        }
    }

    private static void viewTreatmentHistory() {
        System.out.println("\n--- Treatment History Stack ---");
        treatmentStack.displayHistory();
    }

    private static void addPatientVisit() {
        System.out.println("\n--- Add Patient Visit ---");
        Patient patient = findPatientByInput();

        if (patient == null) {
            return;
        }

        Visit visit = readVisit();
        if (patient.getVisitHistory().addVisit(visit)) {
            System.out.println("Visit record added successfully.");
        } else {
            System.out.println("A visit with this ID already exists for the patient.");
        }
    }

    private static void searchPatientVisit() {
        System.out.println("\n--- Search Patient Visit ---");
        Patient patient = findPatientByInput();

        if (patient == null) {
            return;
        }

        int visitId = readPositiveInt("Visit ID: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);

        if (visit == null) {
            System.out.println("Visit record not found.");
        } else {
            System.out.println(visit);
        }
    }

    private static void removePatientVisit() {
        System.out.println("\n--- Remove Patient Visit ---");
        Patient patient = findPatientByInput();

        if (patient == null) {
            return;
        }

        int visitId = readPositiveInt("Visit ID to remove: ");
        if (patient.getVisitHistory().removeVisit(visitId)) {
            System.out.println("Visit record removed successfully.");
        } else {
            System.out.println("Visit record not found.");
        }
    }

    private static void displayPatientVisitHistory() {
        System.out.println("\n--- Patient Visit History ---");
        Patient patient = findPatientByInput();

        if (patient == null) {
            return;
        }

        System.out.println(patient);
        patient.getVisitHistory().displayVisits();
    }

    private static Patient findPatientByInput() {
        int patientId = readPositiveInt("Enter Patient ID: ");
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found. Please register the patient first.");
        }

        return patient;
    }

    private static Treatment readTreatmentForPatient(Patient patient) {
        int treatmentId = readPositiveInt("Treatment ID: ");
        String doctorName = readRequiredString("Doctor Name: ");
        String treatmentDescription = readRequiredString("Treatment Description: ");
        String treatmentDate = readDate("Treatment Date (YYYY-MM-DD): ");

        return new Treatment(
                treatmentId,
                patient.getPatientId(),
                patient.getPatientName(),
                doctorName,
                treatmentDescription,
                treatmentDate
        );
    }

    private static Visit readVisit() {
        int visitId = readPositiveInt("Visit ID: ");
        String visitDate = readDate("Visit Date (YYYY-MM-DD): ");
        String doctorName = readRequiredString("Doctor Name: ");
        String diagnosis = readRequiredString("Diagnosis: ");
        String treatment = readRequiredString("Treatment: ");

        return new Visit(visitId, visitDate, doctorName, diagnosis, treatment);
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());

                if (value > 0) {
                    return value;
                }

                System.out.println("Please enter a positive number.");
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please enter digits only.");
            }
        }
    }

    private static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readPositiveInt(prompt);

            if (value >= min && value <= max) {
                return value;
            }

            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    private static String readRequiredString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty.");
        }
    }

    private static String readContactNumber(String prompt) {
        while (true) {
            String contactNumber = readRequiredString(prompt);

            if (contactNumber.matches("[0-9+\\- ]{7,20}") && contactNumber.matches(".*\\d.*")) {
                return contactNumber;
            }

            System.out.println("Enter a valid contact number using digits, spaces, +, or -.");
        }
    }

    private static String readDate(String prompt) {
        while (true) {
            String date = readRequiredString(prompt);

            try {
                LocalDate.parse(date);
                return date;
            } catch (DateTimeParseException exception) {
                System.out.println("Invalid date. Please use YYYY-MM-DD format, for example 2026-08-31.");
            }
        }
    }

    private static boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (value.equalsIgnoreCase("Y") || value.equalsIgnoreCase("Yes")) {
                return true;
            }

            if (value.equalsIgnoreCase("N") || value.equalsIgnoreCase("No")) {
                return false;
            }

            System.out.println("Please enter Y or N.");
        }
    }

    private static void loadSampleData() {
        Patient patient1 = new Patient(1003, "Amina Perera", 34, "0771234567", "Asthma attack");
        Patient patient2 = new Patient(1001, "Kamal Silva", 45, "0714567890", "Chest pain");
        Patient patient3 = new Patient(1005, "Nimal Fernando", 28, "0752223334", "Fractured arm");
        Patient patient4 = new Patient(1002, "Sarah Jayasuriya", 52, "0789876543", "High fever");
        Patient patient5 = new Patient(1004, "Ravi Kumar", 61, "0765558899", "Diabetes emergency");

        patientBST.insert(patient1);
        patientBST.insert(patient2);
        patientBST.insert(patient3);
        patientBST.insert(patient4);
        patientBST.insert(patient5);

        patient1.getVisitHistory().addVisit(new Visit(2001, "2026-08-01", "Dr. Silva", "Wheezing", "Nebulizer therapy"));
        patient1.getVisitHistory().addVisit(new Visit(2002, "2026-08-12", "Dr. Perera", "Breathing difficulty", "Inhaler adjustment"));
        patient2.getVisitHistory().addVisit(new Visit(2003, "2026-07-26", "Dr. Fernando", "Chest discomfort", "ECG and observation"));
        patient3.getVisitHistory().addVisit(new Visit(2004, "2026-08-05", "Dr. Kumar", "Arm injury", "X-ray and splint"));
        patient5.getVisitHistory().addVisit(new Visit(2005, "2026-08-18", "Dr. De Silva", "Low blood sugar", "Glucose support"));

        emergencyQueue.enqueue(patient2);
        emergencyQueue.enqueue(patient4);
        emergencyQueue.enqueue(patient1);

        treatmentStack.push(new Treatment(3001, patient3.getPatientId(), patient3.getPatientName(),
                "Dr. Kumar", "Applied cast for fractured arm", "2026-08-05"));
        treatmentStack.push(new Treatment(3002, patient5.getPatientId(), patient5.getPatientName(),
                "Dr. De Silva", "Stabilized blood sugar level", "2026-08-18"));
        treatmentStack.push(new Treatment(3003, patient1.getPatientId(), patient1.getPatientName(),
                "Dr. Perera", "Provided emergency nebulizer treatment", "2026-08-29"));
    }
}
