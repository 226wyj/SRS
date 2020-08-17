public enum EnrollmentStatus {
    success("Enrollment successful!"),
    secFull("Enrollment failed: section was full."),
    prereq("Enrollment failed; prerequisites not satisfied."),
    prevEnroll("Enrollment failed; previously enrolled.");

    private final String value;

    EnrollmentStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
