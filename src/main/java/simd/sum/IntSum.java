package simd.sum;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

public class IntSum {

    public static long sum_scalar(int[] data) {
        int sum = 0;
        for (int number : data) {
            sum += number;
        }
        return sum;
    }

    public static int sum_vec(int[] data) {
        int sum = 0;
        VectorSpecies<Integer> speciesPreferred = IntVector.SPECIES_PREFERRED;
        for (int i = 0; i < data.length; i += speciesPreferred.length()) {
            IntVector v = IntVector.fromArray(speciesPreferred, data, i);
            sum += v.reduceLanes(VectorOperators.ADD);
        }
        return sum;
    }

    public static int sum_vec_mask(int[] data) {
        int sum = 0;
        VectorSpecies<Integer> speciesPreferred = IntVector.SPECIES_PREFERRED;
        for (int i = 0; i < data.length; i += speciesPreferred.length()) {
            VectorMask<Integer> mask = speciesPreferred.indexInRange(i, data.length);
            IntVector v = IntVector.fromArray(speciesPreferred, data, i, mask);
            sum += v.reduceLanes(VectorOperators.ADD, mask);
        }
        return sum;
    }

    public static int sum_vec_acc(int[] data) {
        VectorSpecies<Integer> speciesPreferred = IntVector.SPECIES_PREFERRED;
        IntVector acc = IntVector.zero(speciesPreferred);
        for (int i = 0; i < data.length; i += speciesPreferred.length()) {
            IntVector dv = IntVector.fromArray(speciesPreferred, data, i);
            acc = acc.add(dv);
        }
        return acc.reduceLanes(VectorOperators.ADD);
    }
}

