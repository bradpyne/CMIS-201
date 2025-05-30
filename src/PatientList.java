import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PatientList {
    private Tree patientTree;
    private int indexOfIteration = -1;

    private Patient[] patientArray = null;
    //private final int MAX_PATIENTS = 1000;
    //private int nextOpenIndex = 0;

    public PatientList() {
        //patientArray = new Patient[ MAX_PATIENTS ];
        patientTree = new Tree();
    }

    public boolean add( Patient pat ) {
        patientTree.add( pat );
        return true;
    }

    public Patient find( PatientIdentity patID ) {
        return patientTree.find( patID );
    }

    public void initIteration() {
        patientTree.initIteration();
        indexOfIteration = 0;
    }

    public Patient next() {
        Patient nextPat = patientTree.next();
        if(patientTree.hasNext()) {
            indexOfIteration = -1;
        }
        return nextPat;
    }

    public Patient[] mergeSort( Patient[] inputArray ) {
        if( inputArray.length == 2 ) {
            if (inputArray[0].getPatientIdentity().isLessThan(inputArray[1].getPatientIdentity())) {
                return inputArray;
            } else {
                return new Patient[]{inputArray[1], inputArray[0]};
            }
        } else {
            if( inputArray.length == 1) {
                return inputArray;
            } else {
                int mid = inputArray.length / 2;
                Patient[] lower = new Patient[ inputArray.length / 2 ];
                Patient[] upper = new Patient[ inputArray.length - lower.length ];
                for( int i = 0; i < inputArray.length; i++ ) {
                    if( i < lower.length )
                        lower[ i ] = inputArray[ i ];
                    else
                        upper[ i - mid ] = inputArray[ i ];
                }
                Patient[] sortedLower = mergeSort( lower );
                Patient[] sortedUpper = mergeSort( upper );
                return merge( sortedLower, sortedUpper );
            }
        }
    }

    public Patient[] merge( Patient[] array1, Patient[] array2 ) {
        Patient[] merged = new Patient[ array1.length + array2.length ];
        int index1 = 0, targetIndex = 0, index2 = 0;
        while( index1 < array1.length && index2 < array2.length ) {
            if( array1[index1].getPatientIdentity().isLessThan( array2[index2].getPatientIdentity() )) {
                merged[ targetIndex++ ] = array1[ index1++ ];
            } else {
                merged[ targetIndex++ ] = array2[ index2++ ];
            }
        }
        while( index1 < array1.length ) {
            merged[ targetIndex++ ] = array1[ index1++ ];
        }
        while( index2 < array2.length ) {
            merged[ targetIndex++ ] = array2[ index2++ ];
        }

        return merged;
    }

    public boolean saveToFile ( String filename ) {
        initIteration();
        boolean result =  true;
        File file = new File ( filename );
        FileWriter writer;

        try {
            writer  = new FileWriter( file );
            while( indexOfIteration >= 0 ) {
                writer.write( next().toCSV() + "\n" );
            }
            writer.close();
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    public boolean importFromFile( String filename ) {
        boolean result = true;
        File file = new File( filename );
        Scanner scanner;

        try {
            scanner = new Scanner( file );
            while( scanner.hasNextLine() ) {
                Patient newPat = Patient.makePatient( scanner.nextLine() );
                add( newPat );
            }
        } catch (FileNotFoundException e) {
            result = false;
        }
        return result;
    }

    public boolean importAndMergeSort( String filename ) {
        boolean result = true;
        File file = new File( filename );
        Scanner scanner;
        Patient[] pats = new Patient[1000];
        int index = 0;

        try {
            scanner = new Scanner( file );
            while( scanner.hasNextLine()  && index < 1000 ) {
                Patient newPat = Patient.makePatient( scanner.nextLine() );
                pats[index] = newPat;
                index++;
            }

            patientArray = mergeSort( pats );

        } catch (FileNotFoundException e) {
            result = false;
        }

        return result;
    }

    public boolean importPrescriptions( String filename ) {
        boolean result = true;
        File file = new File( filename );
        Scanner scanner;
        Prescription pr;
        Patient pat;

        try {
            scanner = new Scanner( file );

            while( scanner.hasNextLine() ) {
                String line = scanner.nextLine();

                pr = Prescription.makePrescription( line );
                pat = Patient.makePatient( line );

                recordInteraction( pat, pr );

                find( pat.getPatientIdentity() ).getPrescriptionList().add( pr );
            }
        } catch (FileNotFoundException e) {
            result = false;
        }
        return result;
    }

    public void recordInteraction( Patient pat, Prescription pr ) {
        FileWriter fileWrite = null;
        File file = new File ( "contraindications.csv" );

        try {
            Prescription interaction = ( find( pat.getPatientIdentity() ).getPrescriptionList() ).checkInteraction( pr );
            if (interaction != null) {
                fileWrite = new FileWriter(file, true);
                String line = pat.toCSV() + "," + pr.getScriptName() + "," + interaction.getScriptName() + "\n";
                fileWrite.write( line );
                fileWrite.close();
            }
        } catch( IOException e ) {
            throw new RuntimeException();
        }

    }


    //METHODS USING OLD PATIENT ARRAY vs PATIENT TREE
    /*
    public boolean add( Patient pat ) {
        return arrayAddOrdered( pat );
    }

    public Patient find( PatientIdentity patID ) {
        return binarySearch( patID );
    }

    public void initIteration () {
        indexOfIteration = 0;
    }

    public Patient next() {
        int currentIndex = indexOfIteration;
        if( indexOfIteration == -1 ) {
            return null;
        } else {
            indexOfIteration++;

            if( patientArray[indexOfIteration ] == null ) {
                indexOfIteration = -1;
            }

            return patientArray[ currentIndex ];
        }
    }

    private boolean arrayAddOrdered( Patient pat ) {
        int currentIndex = nextOpenIndex - 1;

        if( nextOpenIndex >= patientArray.length ) {
            return false;
        }

        while( currentIndex >= 0 && pat.getPatientIdentity().isLessThan( patientArray[ currentIndex ].getPatientIdentity() ) ) {
            patientArray [ currentIndex + 1 ] = patientArray [ currentIndex ];
            currentIndex--;
        }
        patientArray[ currentIndex + 1 ] = pat;
        nextOpenIndex++;
        return true;
    }

    private Patient binarySearch( PatientIdentity patID ) {
        int upperIndex = nextOpenIndex  - 1;
        int lowerIndex = 0;
        int midIndex = ( upperIndex + lowerIndex ) / 2;

        while ( upperIndex >= lowerIndex ) {
            midIndex = (upperIndex + lowerIndex) / 2;

            if ( patientArray[midIndex].getPatientIdentity().match( patID )) {
                return patientArray[midIndex];
            } else if( patientArray[midIndex].getPatientIdentity().isLessThan( patID ) ) {
                lowerIndex = midIndex + 1;
            } else {
                upperIndex = midIndex - 1;
            }
        }
        return null;
    }
    */

    /*
    public void diag() {
        for( int i = 0; i < nextOpenIndex; i++) {
            System.out.println("@ Index: " + i + " -- " + patientArray[ i ].getPatientIdentity().getName().fullName() );
        }
    } */


}
