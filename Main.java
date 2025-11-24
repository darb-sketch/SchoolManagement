import java.util.*;

//         Jack

// Main SchoolManagement class
class SchoolManagement {
    String schoolName, address, contactNumber, mediumOfStudy;
    Auditorium auditorium;
    Playground playground;
    NoticeBoard noticeBoard;
    List<Employee> employees = new ArrayList<>();
    List<Classroom> classrooms = new ArrayList<>();
    List<Lab> labs = new ArrayList<>();
    List<Bus> buses = new ArrayList<>();
    List<Student> students = new ArrayList<>();
    List<Department> departments = new ArrayList<>();

    public SchoolManagement(String schoolName, String address, String contactNumber, String mediumOfStudy) {
        this.schoolName = schoolName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.mediumOfStudy = mediumOfStudy;
    }

    public void initialize(Auditorium a, Playground p, NoticeBoard n, List<Employee> e, List<Classroom> c, List<Lab> l) {
        auditorium = a;
        playground = p;
        noticeBoard = n;
        employees = e;
        classrooms = c;
        labs = l;
    }

    public void schoolDetails() {
        System.out.println("\n--- School Details ---");
        System.out.println("School Name: " + schoolName);
        System.out.println("Address: " + address);
        System.out.println("Contact: " + contactNumber);
        System.out.println("Medium: " + mediumOfStudy);
        System.out.println("Total Employees: " + employees.size());
        System.out.println("Total Students: " + students.size());
        System.out.println("Total Classrooms: " + classrooms.size());
        System.out.println("Total Labs: " + labs.size());
    }

    public boolean isOpen() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        boolean open = (day != Calendar.FRIDAY && day != Calendar.SATURDAY);
        System.out.println("School is " + (open ? "Open" : "Closed"));
        return open;
    }

    // main interactive UI
    public void runSchool() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nUser Interface:");
            System.out.println("A- Bus\nB- Student\nC- Employee\nD- Class\nE- NoticeBoard\nF- Auditorium\nG- ShowSchoolDetails\nH- Exit");
            String choice = sc.nextLine().toUpperCase();
            switch (choice) {
                case "A":
                    busMenu(sc);
                    break;
                case "B":
                    studentMenu(sc);
                    break;
                case "C":
                    employeeMenu(sc);
                    break;
                case "D":
                    classMenu(sc);
                    break;
                case "E":
                    noticeBoardMenu(sc);
                    break;
                case "F":
                    auditoriumMenu(sc);
                    break;
                case "G":
                    schoolDetails();
                    break;
                case "H":
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ---- Menus ----
    private void busMenu(Scanner sc) {
        while (true) {
            System.out.println("\nBus Menu:\n1- Add Bus\n2- Show Bus Details\n3- Show Seats\n4- Go back");
            int opt = sc.nextInt(); sc.nextLine();
            if (opt == 1) {
                System.out.print("Bus ID: "); String bid = sc.nextLine();
                System.out.print("Driver ID: "); String did = sc.nextLine();
                System.out.print("Bus Number: "); String bno = sc.nextLine();
                System.out.print("Capacity: "); int cap = sc.nextInt(); sc.nextLine();
                Bus b = new Bus(bid, did, bno, cap);
                buses.add(b);
                System.out.println("Bus added.");
            } else if (opt == 2) {
                for (Bus b : buses) b.busDetails();
            } else if (opt == 3) {
                for (Bus b : buses) b.showSeats();
            } else break;
        }
    }

    private void studentMenu(Scanner sc) {
        while (true) {
            System.out.println("\nStudent Menu:\n1- Add Student\n2- Show Student Details\n3- Pay Fee\n4- Go back");
            int opt = sc.nextInt(); sc.nextLine();
            if (opt == 1) {
                System.out.print("Student ID: "); String sid = sc.nextLine();
                System.out.print("Name: "); String name = sc.nextLine();
                System.out.print("Class ID: "); String cid = sc.nextLine();
                System.out.print("Section: "); String sec = sc.nextLine();
                System.out.print("Bus ID: "); String bus = sc.nextLine();
                System.out.print("Level (P=Primary, H=Higher): ");
                String lvl = sc.nextLine().toUpperCase();
                Student s = lvl.equals("P")
                        ? new PrimaryStudent(sid, name, cid, sec, bus)
                        : new HigherSecondaryStudent(sid, name, cid, sec, bus);
                students.add(s);
                System.out.println("Student added successfully.");
            } else if (opt == 2) {
                for (Student s : students) s.studentDetails();
            } else if (opt == 3) {
                for (Student s : students) s.payFees();
            } else break;
        }
    }

    private void employeeMenu(Scanner sc) {
        while (true) {
            System.out.println("\nEmployee Menu:\n1- Teacher\n2- Support Staff\n3- Go back");
            int opt = sc.nextInt(); sc.nextLine();
            if (opt == 1) {
                for (Employee e : employees)
                    if (e instanceof Teacher) e.employeeDetails();
            } else if (opt == 2) {
                for (Employee e : employees)
                    if (e instanceof SupportStaff) e.employeeDetails();
            } else break;
        }
    }

    private void classMenu(Scanner sc) {
        while (true) {
            System.out.println("\nClass Menu:\n1- Add Students\n2- Assign Teacher\n3- Show Details\n4- Go back");
            int opt = sc.nextInt(); sc.nextLine();
            if (opt == 3) {
                for (Classroom c : classrooms) c.classDetails();
            } else if (opt == 4) break;
            else System.out.println("Feature not implemented yet (demo mode).");
        }
    }

    private void noticeBoardMenu(Scanner sc) {
        while (true) {
            System.out.println("\nNoticeBoard Menu:\n1- Display\n2- Add Content\n3- Go back");
            int opt = sc.nextInt(); sc.nextLine();
            if (opt == 1) noticeBoard.display();
            else if (opt == 2) {
                System.out.print("Enter new notice: ");
                String content = sc.nextLine();
                noticeBoard.addContent(content);
            } else break;
        }
    }

    private void auditoriumMenu(Scanner sc) {
        while (true) {
            System.out.println("\nAuditorium Menu:\n1- Book Auditorium\n2- Show Event Details\n3- Show Seats\n4- Go back");
            int opt = sc.nextInt(); sc.nextLine();
            if (opt == 1) auditorium.bookAuditorium(sc);
            else if (opt == 2) auditorium.eventDetails();
            else if (opt == 3) auditorium.displaySeats();
            else break;
        }
    }
}

//-----------------------------------//
// Supporting Classes                //
//-----------------------------------//
//Samuel 
class Auditorium {
    int totalSeats, seatsOccupied;
    String eventName, eventDate, eventTime;

    public Auditorium(int totalSeats) {
        this.totalSeats = totalSeats;
        this.seatsOccupied = 0;
    }

    public void bookAuditorium(Scanner sc) {
        System.out.print("Event Name: ");
        eventName = sc.nextLine();
        System.out.print("Event Date: ");
        eventDate = sc.nextLine();
        System.out.print("Event Time: ");
        eventTime = sc.nextLine();
        System.out.print("Number of participants: ");
        seatsOccupied = sc.nextInt(); sc.nextLine();
        System.out.println("Auditorium booked for " + eventName);
    }

    public void eventDetails() {
        if (eventName == null) System.out.println("No event booked yet.");
        else System.out.println("Event: " + eventName + " on " + eventDate + " at " + eventTime);
    }

    public void displaySeats() {
        System.out.println("Total Seats: " + totalSeats + " | Occupied: " + seatsOccupied +
                           " | Available: " + (totalSeats - seatsOccupied));
    }
}
//Abdurrahman 
class Playground {
    String area;
    boolean occupied;

    public Playground(String area) {
        this.area = area;
        this.occupied = false;
    }

    public void isOccupied() {
        System.out.println("Playground is " + (occupied ? "occupied" : "free") + " in area: " + area);
    }
}
//Abdurrahman 
class NoticeBoard {
    List<String> newsList = new ArrayList<>();
    String inchargeName;

    public NoticeBoard(String inchargeName) {
        this.inchargeName = inchargeName;
    }

    public void display() {
        System.out.println("\n--- Notice Board (" + inchargeName + ") ---");
        if (newsList.isEmpty()) System.out.println("No news available.");
        else for (String n : newsList) System.out.println("- " + n);
    }

    public void addContent(String content) {
        newsList.add(content);
        System.out.println("Notice added successfully.");
    }
}
//aiman
// Abstract Equipment class
abstract class Equipment {
    String equipmentId;
    double cost;

    public Equipment(String equipmentId, double cost) {
        this.equipmentId = equipmentId;
        this.cost = cost;
    }

    abstract void equipmentDetails();
    abstract void purchaseEquipment();
    abstract void repair();
}
//aiman
class LabEquipment extends Equipment {
    String equipmentName;
    int equipmentCount;

    public LabEquipment(String id, double cost, String name, int count) {
        super(id, cost);
        this.equipmentName = name;
        this.equipmentCount = count;
    }

    @Override
    void equipmentDetails() {
        System.out.println("Lab Equipment: " + equipmentName + " | Count: " + equipmentCount + " | Cost: " + cost);
    }

    @Override
    void purchaseEquipment() {
        System.out.println("Purchased new lab equipment: " + equipmentName);
    }

    @Override
    void repair() {
        System.out.println("Repairing lab equipment: " + equipmentName);
    }
}
//aiman
class ClassEquipment extends Equipment {
    int benchCount, fanCount, lightCount;

    public ClassEquipment(String id, double cost, int benchCount, int fanCount, int lightCount) {
        super(id, cost);
        this.benchCount = benchCount;
        this.fanCount = fanCount;
        this.lightCount = lightCount;
    }

    @Override
    void equipmentDetails() {
        System.out.println("Class Equipment -> Benches: " + benchCount + ", Fans: " + fanCount + ", Lights: " + lightCount);
    }

    @Override
    void purchaseEquipment() {
        System.out.println("Purchased classroom equipment.");
    }

    @Override
    void repair() {
        System.out.println("Repairing classroom equipment.");
    }
}
//aiman
class Department {
    String departmentId, departmentName, inchargeName;
    List<Employee> members = new ArrayList<>();

    public Department(String departmentId, String departmentName, String inchargeName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.inchargeName = inchargeName;
    }

    public void addMember(Employee emp) { members.add(emp); }

    public void departmentDetails() {
        System.out.println("Department: " + departmentName + " | Incharge: " + inchargeName);
        for (Employee e : members) System.out.println(" - " + e.name);
    }
}

//Abdurrahman 
// Abstract Employee
abstract class Employee {
    String employeeId, name, departmentId;
    double salary;

    public Employee(String employeeId, String name, double salary, String departmentId) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    abstract void employeeDetails();
    abstract void checkIn();
    abstract void receiveSalary();
}
//Abdurrahman 
class Teacher extends Employee {
    public Teacher(String employeeId, String name, double salary, String departmentId) {
        super(employeeId, name, salary, departmentId);
    }

    @Override
    void employeeDetails() {
        System.out.println("Teacher: " + name + " | Dept: " + departmentId + " | Salary: " + salary);
    }

    @Override
    void checkIn() { System.out.println("Teacher " + name + " checked in."); }

    @Override
    void receiveSalary() { System.out.println("Teacher " + name + " received salary " + salary); }
}
//Abdurrahman 
class SupportStaff extends Employee {
    public SupportStaff(String employeeId, String name, double salary, String departmentId) {
        super(employeeId, name, salary, departmentId);
    }

    @Override
    void employeeDetails() {
        System.out.println("Support Staff: " + name + " | Dept: " + departmentId + " | Salary: " + salary);
    }

    @Override
    void checkIn() { System.out.println("Support Staff " + name + " checked in."); }

    @Override
    void receiveSalary() { System.out.println("Support Staff " + name + " received salary " + salary); }
}
//Abdurrahman 
// Abstract Student
abstract class Student {
    String studentId, name, classId, section, busId;

    public Student(String studentId, String name, String classId, String section, String busId) {
        this.studentId = studentId;
        this.name = name;
        this.classId = classId;
        this.section = section;
        this.busId = busId;
    }

    abstract void studentDetails();
    abstract void payFees();
}
//Abdurrahman 
class PrimaryStudent extends Student {
    public PrimaryStudent(String studentId, String name, String classId, String section, String busId) {
        super(studentId, name, classId, section, busId);
    }

    @Override
    void studentDetails() {
        System.out.println("Primary Student: " + name + " | Class: " + classId + " | Section: " + section);
    }

    @Override
    void payFees() { System.out.println(name + " paid primary student fee."); }
}
//Abdurrahman 
class HigherSecondaryStudent extends Student {
    public HigherSecondaryStudent(String studentId, String name, String classId, String section, String busId) {
        super(studentId, name, classId, section, busId);
    }

    @Override
    void studentDetails() {
        System.out.println("Higher Secondary Student: " + name + " | Class: " + classId + " | Section: " + section);
    }

    @Override
    void payFees() { System.out.println(name + " paid higher secondary student fee."); }
}
//Abdurrahman 
class Bus {
    String busId, driverId, busNumber;
    List<String> areaList = new ArrayList<>();
    int capacity;

    public Bus(String busId, String driverId, String busNumber, int capacity) {
        this.busId = busId;
        this.driverId = driverId;
        this.busNumber = busNumber;
        this.capacity = capacity;
    }

    public void busDetails() {
        System.out.println("Bus ID: " + busId + " | Bus No: " + busNumber + " | Capacity: " + capacity);
    }

    public void showSeats() {
        System.out.println("Bus " + busNumber + " has " + capacity + " seats.");
    }
}
//aiman
class Classroom {
    String className;
    int studentCount;
    ClassEquipment equipment;

    public Classroom(String className, int studentCount, ClassEquipment equipment) {
        this.className = className;
        this.studentCount = studentCount;
        this.equipment = equipment;
    }

    public void classDetails() {
        System.out.println("Class: " + className + " | Students: " + studentCount);
        equipment.equipmentDetails();
    }
}
//aiman
class Lab {
    String labName;
    List<LabEquipment> labEquipments = new ArrayList<>();
    String inchargeId;
    boolean occupied;

    public Lab(String labName) {
        this.labName = labName;
        this.occupied = false;
    }

    public void labDetails() {
        System.out.println("Lab: " + labName + " | Incharge: " + inchargeId);
        for (LabEquipment l : labEquipments) l.equipmentDetails();
    }

    public void isOccupied() {
        System.out.println("Lab " + labName + " is " + (occupied ? "occupied" : "free"));
    }

    public void payFine(Student s) {
        System.out.println("Student " + s.name + " paid fine for damaged equipment.");
    }
}

//-----------------------------------//
//              MAIN                 //
//-----------------------------------//
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("School Name: ");
        String name = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Contact Number: ");
        String contact = sc.nextLine();
        System.out.print("Medium of Study: ");
        String medium = sc.nextLine();

        SchoolManagement sm = new SchoolManagement(name, address, contact, medium);

        System.out.print("Auditorium - Number of Seats: ");
        int seats = sc.nextInt(); sc.nextLine();
        Auditorium auditorium = new Auditorium(seats);

        System.out.print("Playground - Area/Size: ");
        String area = sc.nextLine();
        Playground playground = new Playground(area);

        System.out.print("Notice Board Incharge Name: ");
        String incharge = sc.nextLine();
        NoticeBoard noticeBoard = new NoticeBoard(incharge);

        // Minimal initialization for demo
        sm.initialize(auditorium, playground, noticeBoard, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        sm.isOpen();
        sm.runSchool();
        sc.close();
    }
}
