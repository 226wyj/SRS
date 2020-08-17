public class TranscriptEntry {
    //-------------
    // Attributes.
    //-------------

    private String grade;
    private Student student;
    private Section section;
    private Transcript transcript;

    //----------------
    // Constructor(s).
    //----------------

    public TranscriptEntry(Student s, String grade, Section se) {
        this.setStudent(s);
        this.setGrade(grade);
        this.setSection(se);

        Transcript t = s.getTranscript();
        this.setTranscript(t);
        t.addTranscriptEntry(this);
    }

    //------------------
    // Accessor methods.
    //------------------

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setStudent(Student s) {
        student = s;
    }

    public Student getStudent() {
        return student;
    }

    public void setSection(Section se) {
        section = se;
    }

    public Section getSection() {
        return section;
    }

    public void setTranscript(Transcript t) {
        transcript = t;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    //-----------------------------
    // Miscellaneous other methods.
    //-----------------------------

    // 验证特定的String是否是有效成绩
    public static boolean validateGrade(String grade) {
        boolean outcome = false;

        if (grade.equals("F") || grade.equals("I"))
            outcome = true;
        if (grade.startsWith("A") || grade.startsWith("B") ||
            grade.startsWith("C") || grade.startsWith("D")) {
            if (grade.length() == 1)
                outcome = true;
            else if (grade.length() == 2) {
                if (grade.endsWith("+") || grade.endsWith("-"))
                    outcome = true;
            }
        }
        return outcome;
    }

    // 确定特定的String是否是及格成绩，需要与validateGrade结合使用
    public static boolean passingGrade(String grade) {
        boolean outcome = false;

        if (validateGrade(grade)) {
            if (grade.startsWith("A") ||
                grade.startsWith("B") ||
                grade.startsWith("C") ||
                grade.startsWith("D")) {
                outcome = true;
            }
        }
        return outcome;
    }
}
