import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackTest {

    @Test
    void pushPop() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push( 3 );
        stack.push( 2 );
        stack.push( 1 );

        assertEquals( 1, stack.pop() );
        assertEquals( 2, stack.pop() );
        assertEquals( 3, stack.pop() );
    }

}
