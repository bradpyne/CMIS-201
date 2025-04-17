public class Stack<T> {
//    private Object[] stackArray;
//    private int nStackPtr = 0;

    private StackItem stack;

    public Stack() {
        stack = null;
    }

    public T pop() {
        T obj = null;
        if( stack != null ) {
            obj = stack.data;
            if( stack.before != null ) {
                stack = stack.before;
            } else {
                stack = null;
            }
        }

        return obj;
    }

    public void push( T element ) {
        if( stack == null ) {
            stack = new StackItem( element );
        } else  {
            StackItem newItem = new StackItem( element );
            newItem.before = stack;
            stack = newItem;
        }
    }

//    public Stack() {
//        stack = new Object[1024];
//    }
//
//    @SuppressWarnings("unchecked")
//    public T pop() {
//        T obj = null;
//        if( nStackPtr != 0 ) {
//            obj = (T) stack[ nStackPtr-- ];
//
//        }
//
//        return obj;
//    }
//
//    public void push( T element ) {
//        if( nStackPtr < stack.length ) {
//            stack[ nStackPtr ] = element;
//        }
//    }

    private class StackItem {
        public StackItem before;
        public T data;

        public StackItem( T element ) {
          data = element;
          before = null;
        }

    }

}
