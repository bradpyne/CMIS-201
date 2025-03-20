import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

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


    public static Prescription makePrescription( String line ) {
        try {
            Prescription newPr = null;
            SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            String[] tokens = line.split(",");

            newPr = new Prescription( tokens[3], df.parse( tokens[4] ), parseInt( tokens[5] ), tokens[6] );

            return newPr;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
