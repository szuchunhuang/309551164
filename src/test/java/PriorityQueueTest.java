import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {
    static Stream<Arguments> stringProvider() {
        return Stream.of(
                Arguments.of(new int[]{5, 4, 2, 3}, new int[]{-2, 2, 4, 5}),
                Arguments.of(new int[]{7, 4, 1, 3}, new int[]{-3, 1, 4, 7}),
                Arguments.of(new int[]{8, 7, 9, 0, 6}, new int[]{-1, 6, 7, 8, 9}),
                Arguments.of(new int[]{-1, 10, 9, 2, -8}, new int[]{-7, -1, 2, 9, 10}),
                Arguments.of(new int[]{-11, 7, 0, -3, 10}, new int[]{-10, -3, 0, 7, 10})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("stringProvider")
    public void PriorityQueue_Test(int[] random_array, int[] expected_array) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int[] result=new int[random_array.length];

        //add elements in random array to the priority queue
        for (Integer s: random_array) {
            queue.add(s);
        }

        //get the result array from the priority queue
        for (int i = 0; i < result.length; i++ ) {
            if (!queue.isEmpty())
                result[i] = queue.poll();
        }

        assertArrayEquals(expected_array, result);
    }

    @Test
    public void ExceptionExpected_AddNullElement() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            queue.add(null);
        });
    }

    @Test
    public void ExceptionExpected_PollEmptyQueue() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            int a = queue.poll();
        });
    }

    @Test
    public void ExceptionExpected_IncompatibleElement() {
        class Student{
            final int Id;
            final String Name;
            Student() {
                Id = 123;
                Name = "Taylor";
            }
        }
        Student student = new Student();

        Assertions.assertThrows(ClassCastException.class, () -> {
            PriorityQueue<Student> queue = new PriorityQueue<>();
            queue.add(student);
        });
    }
}
