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
public class LongBenchmark {

    @Param({"64", "512", "4096", "32768"})
    int data_size;

    long[] data_long;

    @Setup(Level.Trial)
    public void setup() {

        data_long = new long[data_size];

        Random random = new Random(1);
        for (int i = 0; i < data_size; i++) {
            data_long[i] = random.nextLong();
        }
    }

    @Benchmark
    public void long_scalar(Blackhole blackhole) {
        long result = LongSum.sum_scalar(data_long);
        blackhole.consume(result);
    }

    @Benchmark
    public void long_vec(Blackhole blackhole) {
        long result = LongSum.sum_vec(data_long);
        blackhole.consume(result);
    }

    @Benchmark
    public void long_vec_mask(Blackhole blackhole) {
        long result = LongSum.sum_vec_mask(data_long);
        blackhole.consume(result);
    }

    @Benchmark
    public void long_vec_acc(Blackhole blackhole) {
        long result = LongSum.sum_vec_acc(data_long);
        blackhole.consume(result);
    }
}
