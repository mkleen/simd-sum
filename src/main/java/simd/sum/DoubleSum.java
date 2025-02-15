package simd.sum;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

public class DoubleSum {

    public static double sum_scalar(double[] data) {
        double sum = 0;
        for (double number : data) {
            sum += number;
        }
        return sum;
    }

    public static double sum_vec(double[] data) {
        double sum = 0;
        VectorSpecies<Double> speciesPreferred = DoubleVector.SPECIES_PREFERRED;
        for (int i = 0; i < data.length; i += speciesPreferred.length()) {
            DoubleVector v = DoubleVector.fromArray(speciesPreferred, data, i);
            sum += v.reduceLanes(VectorOperators.ADD);
        }
        return sum;
    }

    public static double sum_vec_mask(double[] data) {
        double sum = 0;
        VectorSpecies<Double> speciesPreferred = DoubleVector.SPECIES_PREFERRED;
        for (int i = 0; i < data.length; i += speciesPreferred.length()) {
            VectorMask<Double> mask = speciesPreferred.indexInRange(i, data.length);
            DoubleVector v = DoubleVector.fromArray(speciesPreferred, data, i, mask);
            sum += v.reduceLanes(VectorOperators.ADD, mask);
        }
        return sum;
    }

    public static double sum_vec_acc(double[] data) {
        VectorSpecies<Double> speciesPreferred = DoubleVector.SPECIES_PREFERRED;
        DoubleVector acc = DoubleVector.zero(speciesPreferred);
        for (int i = 0; i < data.length; i += speciesPreferred.length()) {
            DoubleVector dv = DoubleVector.fromArray(speciesPreferred, data, i);
            acc = acc.add(dv);
        }
        return acc.reduceLanes(VectorOperators.ADD);
    }
}
