package simd.sum;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.LongVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

public class LongSum {

    public static long sum_scalar(long[] data) {
        long sum = 0;
        for (long number : data) {
            sum += number;
        }
        return sum;
    }

    public static long sum_vec(long[] data) {
        long sum = 0;
        VectorSpecies<Long> speciesPreferred = LongVector.SPECIES_PREFERRED;
        for (int i = 0; i < data.length; i += speciesPreferred.length()) {
            LongVector v = LongVector.fromArray(speciesPreferred, data, i);
            sum += v.reduceLanes(VectorOperators.ADD);
        }
        return sum;
    }

    public static long sum_vec_mask(long[] data) {
        long sum = 0;
        VectorSpecies<Long> speciesPreferred = LongVector.SPECIES_PREFERRED;
        for (int i = 0; i < data.length; i += speciesPreferred.length()) {
            VectorMask<Long> mask = speciesPreferred.indexInRange(i, data.length);
            LongVector v = LongVector.fromArray(speciesPreferred, data, i, mask);
            sum += v.reduceLanes(VectorOperators.ADD, mask);
        }
        return sum;
    }

    public static long sum_vec_acc(long[] data) {
        VectorSpecies<Long> speciesPreferred = LongVector.SPECIES_PREFERRED;
        LongVector acc = LongVector.zero(speciesPreferred);
        for (int i = 0; i < data.length; i += speciesPreferred.length()) {
            LongVector dv = LongVector.fromArray(speciesPreferred, data, i);
            acc = acc.add(dv);
        }
        return acc.reduceLanes(VectorOperators.ADD);
    }
    
}
