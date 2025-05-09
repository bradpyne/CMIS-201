public class Contraindications {
    Interaction[] contraindications;
    final int TABLE_SIZE = 96797;

    public Contraindications() {
        contraindications = new Interaction[ TABLE_SIZE ];
    }

    public Interaction makeInteraction( String line ) {
        Interaction newInteraction = null;
        try {
            String[] tokens = line.split(",");

            if( tokens.length >= 2 )  {
                newInteraction = new Interaction(tokens[0], tokens[1]);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newInteraction;
    }

    public String hashString( Interaction item ) {
        String str = "";
        if( item != null ) {
            if( item.med1.compareTo(item.med2) < 0 ) {
                str = item.med1 + "#" + item.med2;
            } else {
                str = item.med2 + "#" + item.med1;
            }
        }
        str = str.toLowerCase();
        return str;
    }

    public String hashString( String m1, String m2 ) {
        String str;
        if( m1.compareTo( m2 ) <0 ) {
            str = m1 + "#" + m2;
        } else {
            str = m2 + "#" + m1;
        }
        str = str.toLowerCase();
        return str;
    }

    public int hashCode(String str) {
        str = str.toLowerCase();

        char[] chars = { str.charAt(0), str.charAt( str.length() / 3 ),
                str.charAt( ((str.length() / 3) * 2) ), str.charAt( str.length() - 1 ) };

        int hashed = ( chars[0] & 0xFF ) |
                        ( ( chars[1] & 0xFF ) << 8 ) |
                        ( ( chars[2] & 0xFF ) << 16 ) |
                        ( ( chars[3] & 0x7F ) << 24 );

        return hashed;
    }

    public void storeInteraction( Interaction item ) {
        String str = hashString( item );
        int count = 0;
        int index = hashCode( str ) % TABLE_SIZE;

        if( hashString( contraindications[index] ).equals(hashString( item ) ) ) {
            return;
        }
        while( contraindications[index] != null && count < contraindications.length ) {
            index++;
            if(index == contraindications.length ) {
                index = 0;
            }
            count++;
        }

        contraindications[index] = item;
    }

    public boolean findInteraction( String str ) {
        //boolean hasInteraction = false;
        int index = hashCode( str ) % TABLE_SIZE;
        int count = 0;


        while( contraindications[index] != null && count < contraindications.length ) {
            if( hashString( contraindications[index] ).equals( str ) ) {
                return true;
            } else {
                count++;
                index++;
                if( index >= contraindications.length ) {
                    index = 0;
                }
            }
        }
        return false;
    }

    public class Interaction {
        String med1;
        String med2;

        public Interaction( String m1, String m2) {
            med1 = m1;
            med2 = m2;
        }
    }
}

