import java.util.ArrayList;
import java.util.Collection;

public class Student extends Person {
    //------------
    // Attributes.
    //------------

    private String major;
    private String degree;
    private Transcript transcript;
    private ArrayList<Section> attends;

    //----------------
    // Constructor(s).
    //----------------

    public Student(String name, String ssn, String major, String degree) {
        super(name, ssn);

        this.major = major;
        this.degree = degree;

        this.setTranscript(new Transcript(this));

        attends = new ArrayList<Section>();
    }

    public Student(String ssn) {
        this("TBD", ssn, "TBD", "TBD");
    }

    //------------------
    // Accessor methods.
    //------------------

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setTranscript(Transcript t) {
        this.transcript = t;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    //-----------------------------
    // Miscellaneous other methods.
    //-----------------------------


    public void display() {
        // First, display the generic Person info.

        super.display();

        // Then, display student-specific info.

        System.out.println("Student-Specific Information:");
        System.out.println("\tMajor: " + this.getMajor());
        System.out.println("\tDegree: " + this.getDegree());
        this.displayCourseSchedule();
        this.printTranscript();

        System.out.println();
    }

    public String toString() {
        return this.getName() + "(" + this.getSsn() + ") [" + this.getDegree() +
                " - " + this.getMajor() + "]";
    }

    public void displayCourseSchedule() {

    }

    public void printTranscript() {
        this.getTranscript().display();
    }

    public void addSection(Section s) {
        attends.add(s);
    }

    public void dropSection(Section s) {
        attends.remove(s);
    }

    public boolean isEnrolledIn(Section s) {
        if (attends.contains(s))
            return true;
        return false;
    }

    public boolean isCurrentlyEnrolledInSimilar(Section s1) {
        boolean foundMatch = false;

        Course c1 = s1.getRepresentedCourse();

        for (Section s2 : attends) {
            Course c2 = s2.getRepresentedCourse();
            if (c1 == c2) {
                if (s2.getGrade(this) == null) {
                    foundMatch = true;
                    break;
                }
            }
        }
        return foundMatch;
    }

    public Collection<Section> getEnrolledSections() {
        return attends;
    }
}
