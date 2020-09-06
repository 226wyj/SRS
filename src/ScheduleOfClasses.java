import java.util.HashMap;

public class ScheduleOfClasses {
    //-------------
    // Attributes.
    //-------------

    private String semester;
    private HashMap<String, Section> sectionsOffered;

    //----------------
    // Constructor(s).
    //----------------

    public ScheduleOfClasses(String semester) {
        this.semester = semester;
        sectionsOffered = new HashMap<String, Section>();
    }

    //------------------
    // Accessor methods.
    //------------------

    public void setSemester(String s) {
        semester = s;
    }

    public String getSemester() {
        return semester;
    }

    public HashMap<String, Section> getSectionsOffered() {
        return sectionsOffered;
    }

    //-----------------------------
    // Miscellaneous other methods.
    //-----------------------------

    public void display() {
        System.out.println("Schedule of Classes for " + getSemester());
        System.out.println();

        // Iterate through all the values in the HashMap.

        for (Section s : sectionsOffered.values()) {
            s.display();
            System.out.println();
        }
    }

    public void addSection(Section s) {
        // We formulate a key by concatenating the course no.
        // and section no., separated by a hyphen.
        String key = s.getRepresentedCourse().getCourseNo() +
                " - " + s.getSectionNo();
        sectionsOffered.put(key, s);

        // 双向关系
        s.setOfferedIn(this);
    }

    // The full section number is a concatenation of the
    // course no. and section no., separated by a hyphen;
    // e.g., "ART101 - 1".
    public Section findSection(String fullSectionNo) {
        return sectionsOffered.get(fullSectionNo);
    }

    public boolean isEmpty() {
        if (sectionsOffered.size() == 0)
            return true;
        return false;
    }
}
