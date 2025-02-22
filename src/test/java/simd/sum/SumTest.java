package simd.sum;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class SumTest {

    static final int DATA_SIZE = 100;
    static final int[] INT_DATA = new int[DATA_SIZE];
    static final long[] LONG_DATA = new long[DATA_SIZE];
    static final double[] DOUBLE_DATA = new double[DATA_SIZE];

    static {
        Random random = new Random(1);
        for (int i = 0; i < DATA_SIZE; i++) {
            INT_DATA[i] = random.nextInt();
            LONG_DATA[i] = random.nextLong();
            DOUBLE_DATA[i] = random.nextDouble();
        }
    }

    @Test
    public void test_int() {
       assertEquals(IntSum.sum_scalar(INT_DATA), IntSum.sum_vec(INT_DATA));
       assertEquals(IntSum.sum_scalar(INT_DATA), IntSum.sum_vec_mask(INT_DATA));
       assertEquals(IntSum.sum_scalar(INT_DATA), IntSum.sum_vec_acc(INT_DATA));
    }

    @Test
    public void test_long() {
        assertEquals(LongSum.sum_scalar(LONG_DATA), LongSum.sum_vec(LONG_DATA));
        assertEquals(LongSum.sum_scalar(LONG_DATA), LongSum.sum_vec_mask(LONG_DATA));
        assertEquals(LongSum.sum_scalar(LONG_DATA), LongSum.sum_vec_acc(LONG_DATA));
    }

    @Test
    public void test_double() {
        assertEquals(DoubleSum.sum_scalar(DOUBLE_DATA), DoubleSum.sum_vec(DOUBLE_DATA), 0.01);
        assertEquals(DoubleSum.sum_scalar(DOUBLE_DATA), DoubleSum.sum_vec_mask(DOUBLE_DATA), 0.01);
        assertEquals(DoubleSum.sum_scalar(DOUBLE_DATA), DoubleSum.sum_vec_acc(DOUBLE_DATA), 0.01);
    }

}

