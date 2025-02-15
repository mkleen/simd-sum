package simd.sum;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Fork(
        warmups = 1,
        value = 1,
        jvmArgs = {
                "-XX:-TieredCompilation",
                "-XX:-UseSuperWord",
                "-Xms16g",
                "--add-modules=jdk.incubator.vector"
        }
)
public class DoubleBenchmark {

    @Param({"64", "512", "4096", "32768"})
    int data_size;

    double[] data_double;

    @Setup(Level.Trial)
    public void setup() {

        data_double = new double[data_size];

        Random random = new Random(1);
        for (int i = 0; i < data_size; i++) {
            data_double[i] = random.nextDouble();
        }
    }

    @Benchmark
    public void double_scalar(Blackhole blackhole) {
        double result = DoubleSum.sum_scalar(data_double);
        blackhole.consume(result);
    }

    @Benchmark
    public void double_vec(Blackhole blackhole) {
        double result = DoubleSum.sum_vec(data_double);
        blackhole.consume(result);
    }

    @Benchmark
    public void double_vec_mask(Blackhole blackhole) {
        double result = DoubleSum.sum_vec_mask(data_double);
        blackhole.consume(result);
    }

    @Benchmark
    public void double_vec_acc(Blackhole blackhole) {
        double result = DoubleSum.sum_vec_acc(data_double);
        blackhole.consume(result);
    }
}
