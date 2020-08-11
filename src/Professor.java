import java.util.ArrayList;

public class Professor extends Person {
    //------------
    // Attributes.
    //------------

    private String title;
    private String department;
    private ArrayList<Section> teaches;

    //----------------
    // Constructor(s).
    //----------------

    public Professor(String name, String ssn, String title, String dept) {
        super(name, ssn);
        this.title = title;
        this.department = dept;
        teaches = new ArrayList<Section>();
    }

    //-------------------
    // Accessor methods.
    //-------------------

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDepartment(String dept) {
        this.department = dept;
    }

    public String getDepartment() {
        return department;
    }

    //-----------------------------
    // Miscellaneous other methods.
    //-----------------------------

    public void display() {
        // First, display the generic Person info.

        super.display();

        // Then, display Professor-specific info.

        System.out.println("Professor-Specific Information:");
        System.out.println("\tTitle: " + this.getTitle());
        System.out.println("\tTeaches for Dept: " + this.getDepartment());

        displayTeachingAssignments();

        System.out.println();
    }

    public String toString() {
        return getName() + "(" + getTitle() + ", " +
                getDepartment() + ")";
    }

    public void displayTeachingAssignments() {
        System.out.println("Teaching Assignments for" + getName() + ":");
        if (teaches.size() == 0)
            System.out.println("\t(none)");
        else {
            for (Section s : teaches) {
                System.out.println("\tCourse No.: " +
                        s.getRepresentedCourse().getCourseNo());
                System.out.println("\tSection No.: " +
                        s.getSectionNo());
                System.out.println("\tCourse Name: " +
                        s.getRepresentedCourse().getCourseName());
                System.out.println("\tDay and Time: " +
                        s.getDayOfWeek() + " - " + s.getTimeOfDay());
                System.out.println("\t------");
            }
        }
    }

    public void agreeToTeach(Section s) {
        teaches.add(s);
    }
}
