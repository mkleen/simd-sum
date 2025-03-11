package simd.sum;

import jdk.incubator.vector.LongVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

public class LongSum {

    static final VectorSpecies<Long> SPECIES_PREFERRED = LongVector.SPECIES_PREFERRED;

    public static long sum_scalar(long[] data) {
        long sum = 0;
        for (long number : data) {
            sum += number;
        }
        return sum;
    }

    static void checkSize(VectorSpecies<?> vectorSpecies, int size) {
        if(size % vectorSpecies.length() > 0) {
            throw new IllegalArgumentException("Data must be divisible by vector size");
        }
    }

    public static long sum_vec(long[] data) {
        checkSize(SPECIES_PREFERRED, data.length);
        long sum = 0;
        for (int i = 0; i < data.length; i += SPECIES_PREFERRED.length()) {
            LongVector v = LongVector.fromArray(SPECIES_PREFERRED, data, i);
            sum += v.reduceLanes(VectorOperators.ADD);
        }
        return sum;
    }

    public static long sum_vec_mask(long[] data) {
        checkSize(SPECIES_PREFERRED, data.length);
        long sum = 0;
        for (int i = 0; i < data.length; i += SPECIES_PREFERRED.length()) {
            VectorMask<Long> mask = SPECIES_PREFERRED.indexInRange(i, data.length);
            LongVector v = LongVector.fromArray(SPECIES_PREFERRED, data, i, mask);
            sum += v.reduceLanes(VectorOperators.ADD, mask);
        }
        return sum;
    }

    public static long sum_vec_acc(long[] data) {
        checkSize(SPECIES_PREFERRED, data.length);
        LongVector acc = LongVector.zero(SPECIES_PREFERRED);
        for (int i = 0; i < data.length; i += SPECIES_PREFERRED.length()) {
            LongVector dv = LongVector.fromArray(SPECIES_PREFERRED, data, i);
            acc = acc.add(dv);
        }
        return acc.reduceLanes(VectorOperators.ADD);
    }
    
}
