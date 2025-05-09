import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrescriptionList {
    private ListRecord head;
    //private ListRecord tail;
    private ListRecord index;
    Contraindications conflicts;

    public PrescriptionList() {
        head = null;
        conflicts = new Contraindications();
        //tail = null;
    }

    public void initIteration() {
        index = head;
    }

    public Prescription next() {
        if( index != null ) {
            ListRecord current = index;
            index = index.next;
            return current.data;
        }
        return null;
    }

    public void add( Prescription pr ) {

        ListRecord newRecord = new ListRecord( pr );
        ListRecord before;
        if( head == null ) {
            head = newRecord;
        } else {
            if( comesBefore( newRecord.data, head.data ) ) {
                newRecord.next = head;
                head = newRecord;
            } else {
                before = head;
                while ( (before.next != null) && comesBefore(before.next.data, newRecord.data) ) {
                    before = before.next;
                }

                newRecord.next = before.next;
                before.next = newRecord;
            }
        }
    }

    public boolean importInteractions( String filename ) {
        boolean result = true;
        File file = new File( filename );
        Scanner scanner;

        try {
            scanner = new Scanner( file );
            while( scanner.hasNextLine() ) {
                Contraindications.Interaction interaction = conflicts.makeInteraction( scanner.nextLine() ) ;
                conflicts.storeInteraction( interaction );
            }
        } catch (FileNotFoundException e) {
            result = false;
        }
        return result;
    }

    public boolean hasInteraction( Prescription pr ) {
        initIteration();
        String str;

        while( index.data != null  ) {  // && index.data.getIssueDate() < 1 year old
            str = conflicts.hashString( pr.getScriptName(), index.data.getScriptName() );

            if( conflicts.findInteraction( str ) ) {
                return true;
            }
            next();
        }
        return false;
    }



//    public void reverseOrder() {
//        ListRecord reversedHead = head;
//        ListRecord beforeReverse = head;
//        ListRecord current = head;
//
//        if( comesBefore( current.data, reversedHead.data ) ) {
//            current.next = reversedHead;
//            reversedHead = current;
//        } else {
//            beforeReverse = reversedHead;
//            while( (current.next != null) ) {
//                if( comesBefore( current.next.data, beforeReverse.data )) {
//
//                }
//            }
//        }
//
//
//    }

    public int count() {
        int count = 0;
        ListRecord current = head;

        while( current != null ) {
            count++;
            current = current.next;
        }

        return count;
    }

    public boolean comesBeforeTest( Prescription p1, Prescription p2 ) {
        return comesBefore( p1, p2 );
    }


    //move into ListRecord, return T if record is null
    private static boolean comesBefore( Prescription pre1, Prescription pre2 ) {
        return ( pre1.getIssueDate().compareTo( pre2.getIssueDate() ) > 0);
    }

    private class ListRecord {
        public Prescription data;
        public ListRecord next;

        public ListRecord( Prescription pr ) {
            data = pr;
            next = null;
        }

        /*public comesBefore( Prescription pr ) {
            return ( data.getIssueDate().compareTo( pr.getIssueDate() ) > 0 );
         */

        //private ListRecord head;
    }



}
