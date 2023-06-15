import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Doctor {
    private String name;

    public Doctor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Patient {
    private String name;

    public Patient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Appointment {
    private String date;
    private String time;
    private Doctor doctor;
    private Patient patient;

    public Appointment(String date, String time, Doctor doctor, Patient patient) {
        this.date = date;
        this.time = time;
        this.doctor = doctor;
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }
}

class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String enteredUsername, String enteredPassword) {
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }
}

public class MedicalAppointmentSystem {
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();
    private static Map<String, String> admins = new HashMap<>();

    public static void main(String[] args) {

        doctors.add(new Doctor("Doctor 1"));
        doctors.add(new Doctor("Doctor 2"));
        patients.add(new Patient("Paciente 1"));
        patients.add(new Patient("Paciente 2"));
        admins.put("admin", "admin123");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de citas médicas");
        System.out.print("Ingrese su identificador de administrador: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        if (authenticateAdmin(username, password)) {
            System.out.println("Inicio de sesión exitoso");
            boolean exit = false;
            while (!exit) {
                System.out.println("\nSeleccione una opción:");
                System.out.println("1. Dar de alta un doctor");
                System.out.println("2. Dar de alta un paciente");
                System.out.println("3. Crear una cita");
                System.out.println("4. Salir");

                int option = scanner.nextInt();
                scanner.nextLine(); // Limpiar el búfer

                switch (option) {
                    case 1:
                        System.out.print("Ingrese el nombre del doctor: ");
                        String doctorName = scanner.nextLine();
                        Doctor doctor = new Doctor(doctorName);
                        doctors.add(doctor);
                        System.out.println("Doctor agregado exitosamente.");
                        break;
                    case 2:
                        System.out.print("Ingrese el nombre del paciente: ");
                        String patientName = scanner.nextLine();
                        Patient patient = new Patient(patientName);
                        patients.add(patient);
                        System.out.println("Paciente agregado exitosamente.");
                        break;
                    case 3:
                        System.out.print("Ingrese la fecha de la cita (DD/MM/AAAA): ");
                        String date = scanner.nextLine();
                        System.out.print("Ingrese la hora de la cita (HH:MM): ");
                        String time = scanner.nextLine();

                        // Mostrar lista de doctores disponibles
                        System.out.println("Doctores disponibles:");
                        for (int i = 0; i < doctors.size(); i++) {
                            System.out.println((i + 1) + ". " + doctors.get(i).getName());
                        }

                        System.out.print("Seleccione el número de doctor: ");
                        int doctorIndex = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el búfer

                        // Mostrar lista de pacientes disponibles
                        System.out.println("Pacientes disponibles:");
                        for (int i = 0; i < patients.size(); i++) {
                            System.out.println((i + 1) + ". " + patients.get(i).getName());
                        }

                        System.out.print("Seleccione el número de paciente: ");
                        int patientIndex = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el búfer

                        // Crear la cita y agregarla a la lista de citas
                        Doctor selectedDoctor = doctors.get(doctorIndex - 1);
                        Patient selectedPatient = patients.get(patientIndex - 1);
                        Appointment appointment = new Appointment(date, time, selectedDoctor, selectedPatient);
                        appointments.add(appointment);
                        System.out.println("Cita creada exitosamente.");
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Gracias por usar el sistema de citas médicas. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                }
            }
        } else {
            System.out.println("Identificador de administrador o contraseña incorrectos. Acceso denegado.");
        }
    }

    private static boolean authenticateAdmin(String username, String password) {
        return admins.containsKey(username) && admins.get(username).equals(password);
    }
}



