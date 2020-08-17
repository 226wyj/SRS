import java.util.HashMap;

public class Section {
    //------------
    // Attributes.
    //------------

    private int sectionNo;
    private char dayOfWeek;
    private String timeOfDay;
    private String room;
    private int seatingCapacity;
    private Course representedCourse;
    private ScheduleOfClasses offeredIn;
    private Professor instructor;
    private HashMap<String, Student> enrolledStudents;
    private HashMap<Student, TranscriptEntry> assignedGrades;

    //--------------
    // Constructors.
    //--------------

    public Section(int sNo, char day, String time, Course course,
                   String room, int capacity) {
        setSectionNo(sNo);
        setDayOfWeek(day);
        setTimeOfDay(time);
        setRepresentedCourse(course);
        setRoom(room);
        setSeatingCapacity(capacity);
        setInstructor(null);

        enrolledStudents = new HashMap<String, Student>();
        assignedGrades = new HashMap<Student, TranscriptEntry>();
    }

    //------------------
    // Accessor methods.
    //------------------

    public void setSectionNo(int s) {
        sectionNo = s;
    }

    public int getSectionNo() {
        return sectionNo;
    }

    public void setDayOfWeek(char d) {
        dayOfWeek = d;
    }

    public char getDayOfWeek() {
        return dayOfWeek;
    }

    public void setTimeOfDay(String t) {
        timeOfDay = t;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setRoom(String r) {
        room = r;
    }

    public String getRoom() {
        return room;
    }

    public void setSeatingCapacity(int capacity) {
        seatingCapacity = capacity;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setRepresentedCourse(Course c) {
        representedCourse = c;
    }

    public Course getRepresentedCourse() {
        return representedCourse;
    }

    public void setOfferedIn(ScheduleOfClasses soc) {
        offeredIn = soc;
    }

    public ScheduleOfClasses getOfferedIn() {
        return offeredIn;
    }

    public void setInstructor(Professor p) {
        instructor = p;
    }

    public Professor getInstructor() {
        return instructor;
    }

    //-----------------------------
    // Miscellaneous other methods.
    //-----------------------------

    // MATH101 - 1 - M - 8:00 AM
    public String toString() {
        return getRepresentedCourse().getCourseNo() + " - " +
                getSectionNo() + " - " +
                getDayOfWeek() + " - " +
                getTimeOfDay();
    }

    // Concatenation of the course no. and section no.
    public String getFullSectionNo() {
        return getRepresentedCourse().getCourseNo() + " - " + getSectionNo();
    }

    private boolean confirmSeatAvailability() {
        if (enrolledStudents.size() < getSeatingCapacity())
            return true;
        return false;
    }

    public EnrollmentStatus enroll(Student s) {
        // First, make sure that the student is not already
        // enrolled for this section, and that he/she has
        // never taken and passed the course before.

        Transcript transcript = s.getTranscript();

        if (s.isCurrentlyEnrolledInSimilar(this) ||
            transcript.verifyCompletion(this.getRepresentedCourse())) {
            return EnrollmentStatus.prevEnroll;
        }

        // If there are any prerequisites for this course,
        // check to ensure that the student has completed them.

        Course c = this.getRepresentedCourse();
        if (c.hasPrerequisites()) {
            for (Course pre : c.getPrerequisites()) {
                // See if the student's Transcript reflects
                // successful completion of the prerequisite.

                if (!transcript.verifyCompletion(pre)) {
                    return EnrollmentStatus.prereq;
                }
            }
        }

        // If the total enrollment is already at the capacity
        // of the section, we reject this enrollment request.

        if (!this.confirmSeatAvailability()) {
            return EnrollmentStatus.secFull;
        }

        // Enroll the student.

        enrolledStudents.put(s.getSsn(), s);
        s.addSection(this);

        return EnrollmentStatus.success;
    }

    public boolean drop(Student s) {
        // We may only drop a student if he/she is enrolled.

        if (!s.isEnrolledIn(this))
            return false;
        else {
            // Find the student in the HashMap, and remove it.

            enrolledStudents.remove(s.getSsn());

            // Bidirectional.

            s.dropSection(this);
            return true;
        }
    }

    public int getTotalEnrollment() {
        return enrolledStudents.size();
    }

    public void display() {
        System.out.println("Section Information:");
        System.out.println("\tSemester:  " + getOfferedIn().getSemester());
        System.out.println("\tCourse No.:  " +
                getRepresentedCourse().getCourseNo());
        System.out.println("\tSection No:  " + getSectionNo());
        System.out.println("\tOffered:  " + getDayOfWeek() +
                " at " + getTimeOfDay());
        System.out.println("\tIn Room:  " + getRoom());
        if (getInstructor() != null)
            System.out.println("\tProfessor:  " +
                    getInstructor().getName());
        displayStudentRoster();
    }

    public void displayStudentRoster() {
        System.out.print("\tTotal of " + getTotalEnrollment() +
                "students enrolled");
        if (getTotalEnrollment() == 0)
            System.out.println(".");
        else
            System.out.println(", as follows");
        for (Student s : enrolledStudents.values()) {
            System.out.println("\t\t" + s.getName());
        }
    }

    public String getGrade(Student s) {
        String grade = null;
        TranscriptEntry te = assignedGrades.get(s);
        if (te != null) {
            grade = te.getGrade();
        }
        return grade;
    }

    public boolean postGrade(Student s, String grade) {
        if (!TranscriptEntry.validateGrade(grade))
            return false;

        if (assignedGrades.get(s) != null)
            return false;

        TranscriptEntry te = new TranscriptEntry(s, grade, this);
        assignedGrades.put(s, te);
        return true;
    }

    public boolean isSectionOf(Course c) {
        if (c == representedCourse)
            return true;
        return false;
    }
}
