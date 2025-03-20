import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class PrescriptionList {
    private ListRecord head;
    //private ListRecord tail;
    private ListRecord index;

    public PrescriptionList() {
        head = null;
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
        ListRecord before = null;
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

        private ListRecord head;
    }



}
