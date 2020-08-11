public abstract class Person {
    //------------
    // Attributes.
    //------------

    private String name;
    private String ssn;

    //----------------
    // Constructor(s).
    //----------------

    public Person(String name, String ssn) {
        this.setName(name);
        this.setSsn(ssn);
    }

    public Person() {
        this.setName("?");
        this.setSsn("???-??-????");
    }

    //------------------
    // Accessor methods.
    //------------------

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSsn(String s) {
        this.ssn = s;
    }

    public String getSsn() {
        return ssn;
    }

    //-----------------------------
    // Miscellaneous other methods.
    //-----------------------------

    public abstract String toString();

    public void display() {
        System.out.println("Person Information:");
        System.out.println("\tName: " + this.getName());
        System.out.println("\tSoc.Security No.: " + this.getSsn());
    }
}
