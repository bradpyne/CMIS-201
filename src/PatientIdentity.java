import java.text.SimpleDateFormat;
import java.util.Date;
public class PatientIdentity {
    private Name name;
    private Date dateOfBirth;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    PatientIdentity( Name patName, Date dOB ) {
        name = patName;
        dateOfBirth = dOB;
    }

    public boolean match( PatientIdentity other ) {
        return ( name.match( other.getName() ) && dateOfBirth.equals( other.getDOB() ) );
    }

    public String toString() {
        return ( "Patient full name: " + name.fullName() + "."
                + "\nPatient date of birth: " + df.format( dateOfBirth ) ) ;
    }

    public Name getName() {
        return name;
    }

    public Date getDOB() {
        return dateOfBirth;
    }

    public boolean isLessThan ( PatientIdentity identity ) {
        if( name.isLessThan( identity.getName() ) ) {
            return true;
        } else if( identity.getName().match( name ) ) {
            if (dateOfBirth.compareTo( identity.getDOB() ) < 0) {
                return true;
            } else {
                return false;
            }
        } else
            return false;
    }

}
