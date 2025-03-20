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
                while ( comesBefore(before.next.data, newRecord.data) && (before.next != null) ) {
                    before = before.next;
                }

                newRecord.next = before.next;
                before.next = newRecord;
            }
        }
    }

    public boolean comesBeforeTest( Prescription p1, Prescription p2 ) {
        return comesBefore( p1, p2 );
    }

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

        private ListRecord head;
    }



}
