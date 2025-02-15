package simd.sum;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class SumTest {

    static final int DATA_SIZE = 100;
    static final int[] INT_DATA = new int[DATA_SIZE];

    static {
        Random random = new Random(1);
        for (int i = 0; i < DATA_SIZE; i++) {
            INT_DATA[i] = random.nextInt();
        }
    }

    @Test
    public void test() {
       assertEquals(IntSum.sum_scalar(INT_DATA), IntSum.sum_vec(INT_DATA));
       assertEquals(IntSum.sum_scalar(INT_DATA), IntSum.sum_vec(INT_DATA));
       assertEquals(IntSum.sum_scalar(INT_DATA), IntSum.sum_vec_mask(INT_DATA));
       assertEquals(IntSum.sum_scalar(INT_DATA), IntSum.sum_vec_acc(INT_DATA));
    }

}

