package simd.sum;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

import static simd.sum.LongSum.checkSize;

public class DoubleSum {
    static final VectorSpecies<Double> SPECIES_PREFERRED = DoubleVector.SPECIES_PREFERRED;


    public static double sum_scalar(double[] data) {
        double sum = 0;
        for (double number : data) {
            sum += number;
        }
        return sum;
    }

    public static double sum_vec(double[] data) {
        checkSize(SPECIES_PREFERRED, data.length);
        double sum = 0;
        for (int i = 0; i < data.length; i += SPECIES_PREFERRED.length()) {
            DoubleVector v = DoubleVector.fromArray(SPECIES_PREFERRED, data, i);
            sum += v.reduceLanes(VectorOperators.ADD);
        }
        return sum;
    }

    public static double sum_vec_mask(double[] data) {
        checkSize(SPECIES_PREFERRED, data.length);
        double sum = 0;
        for (int i = 0; i < data.length; i += SPECIES_PREFERRED.length()) {
            VectorMask<Double> mask = SPECIES_PREFERRED.indexInRange(i, data.length);
            DoubleVector v = DoubleVector.fromArray(SPECIES_PREFERRED, data, i, mask);
            sum += v.reduceLanes(VectorOperators.ADD, mask);
        }
        return sum;
    }

    public static double sum_vec_acc(double[] data) {
        checkSize(SPECIES_PREFERRED, data.length);
        DoubleVector acc = DoubleVector.zero(SPECIES_PREFERRED);
        for (int i = 0; i < data.length; i += SPECIES_PREFERRED.length()) {
            DoubleVector dv = DoubleVector.fromArray(SPECIES_PREFERRED, data, i);
            acc = acc.add(dv);
        }
        return acc.reduceLanes(VectorOperators.ADD);
    }
}
