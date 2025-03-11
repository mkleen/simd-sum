package simd.sum;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

import static simd.sum.LongSum.checkSize;

public class IntSum {

    static final VectorSpecies<Integer> SPECIES_PREFERRED = IntVector.SPECIES_PREFERRED;

    public static long sum_scalar(int[] data) {
        int sum = 0;
        for (int number : data) {
            sum += number;
        }
        return sum;
    }

    public static int sum_vec(int[] data) {
        checkSize(SPECIES_PREFERRED, data.length);
        int sum = 0;
        for (int i = 0; i < data.length; i += SPECIES_PREFERRED.length()) {
            IntVector v = IntVector.fromArray(SPECIES_PREFERRED, data, i);
            sum += v.reduceLanes(VectorOperators.ADD);
        }
        return sum;
    }

    public static int sum_vec_mask(int[] data) {
        checkSize(SPECIES_PREFERRED, data.length);
        int sum = 0;
        for (int i = 0; i < data.length; i += SPECIES_PREFERRED.length()) {
            VectorMask<Integer> mask = SPECIES_PREFERRED.indexInRange(i, data.length);
            IntVector v = IntVector.fromArray(SPECIES_PREFERRED, data, i, mask);
            sum += v.reduceLanes(VectorOperators.ADD, mask);
        }
        return sum;
    }

    public static int sum_vec_acc(int[] data) {
        checkSize(SPECIES_PREFERRED, data.length);
        IntVector acc = IntVector.zero(SPECIES_PREFERRED);
        for (int i = 0; i < data.length; i += SPECIES_PREFERRED.length()) {
            IntVector dv = IntVector.fromArray(SPECIES_PREFERRED, data, i);
            acc = acc.add(dv);
        }
        return acc.reduceLanes(VectorOperators.ADD);
    }
}

