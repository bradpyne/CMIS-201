public class Name {
    private String firstName = "";
    private String lastName = "";

    Name( String name1, String name2) {
        firstName = name1;
        lastName = name2;
    }

    public String fullName() {
        return ( lastName + ", " + firstName );
    }

    public boolean match( Name other ) {
        return ( firstName.toLowerCase().equals( other.firstName.toLowerCase() )
                    && lastName.toLowerCase().equals( other.lastName.toLowerCase() ) );
    }

    public boolean isLessThan( Name other ) {
        return ( fullName().compareTo( other.fullName() ) < 0 );
    }

}
