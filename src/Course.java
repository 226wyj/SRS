import java.util.ArrayList;
import java.util.Collection;

public class Course {
    //------------
    // Attributes.
    //------------

    private String courseNo;
    private String courseName;
    private double credits;
    private ArrayList<Section> offeredAsSection;
    private ArrayList<Course> prerequistites;

    //----------------
    // Constructor(s).
    //----------------

    public Course(String cNo, String cName, double credits) {
        this.courseNo = cNo;
        this.courseName = cName;
        this.credits = credits;

        offeredAsSection = new ArrayList<Section>();
        prerequistites = new ArrayList<Course>();
    }

    //------------------
    // Accessor methods.
    //------------------

    public void setCourseNo(String cNo) {
        courseNo = cNo;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseName(String cName) {
        courseName = cName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCredits(double c) {
        credits = c;
    }

    public double getCredits() {
        return credits;
    }

    //-----------------------------
    // Miscellaneous other methods.
    //-----------------------------

    public void display() {
        System.out.println("Course Information:");
        System.out.println("\tCourse No.:  " + getCourseNo());
        System.out.println("\tCourse Name:  " + getCourseName());
        System.out.println("\tCredits:  " + getCredits());
        System.out.println("\tPrerequisite Courses:");

        for (Course c : prerequisites) {
            System.out.println("\t\t" + c.toString());
        }

        System.out.print("\tOffered As Section(s):  ");
        for (Section s : offeredAsSection) {
            System.out.print(s.getSectionNo() + " ");
        }

        System.out.println();
    }

    public String toString() {
        return getCourseNo() + ": " + getCourseName();
    }

    public void addPrerequisite(Course c) {
        prerequistites.add(c);
    }

    public boolean hasPrerequisites() {
        if (prerequistites.size() > 0)
            return true;
        return false;
    }

    public Collection<Course> getPrerequisites() {
        return prerequistites;
    }

    public Section scheduleSection(char day, String time, String room,
                                   int capacity) {
        Section s = new Section(offeredAsSection.size() + 1,
                day, time, room, capacity);
        this.addSection(s);

        return s;
    }
}
