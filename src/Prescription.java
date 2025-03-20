import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Prescription {
    private String scriptName;
    private Date issueDate;
    private int dosage;
    private String prescriber;

    public Prescription( String name, Date date, int dose, String presc ) {
        scriptName = name;
        issueDate = date;
        dosage = dose;
        prescriber = presc;
    }

    public String getScriptName() {
        return scriptName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public int getDosage() {
        return dosage;
    }

    public String getPrescriber() {
        return prescriber;
    }

    /*
    static Prescription makePrescription( String line ) {
        try {
            Prescription newPres = null;
            SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            String[] tokens = line.split(", ");

            PatientIdentity patID = new PatientIdentity( new Name( tokens[1], tokens[0] ), df.parse( tokens[2] ) );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    } */
}
