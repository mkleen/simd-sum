# simd-sum

A benchmark for sum aggregations using simd with the java vector api.

## Run the benchmark:

```
$ sh run.sh
```

## Results

```
Benchmark                        (data_size)  Mode  Cnt      Score     Error  Units
DoubleBenchmark.double_scalar             64  avgt    5     12.644 ±   0.005  ns/op
DoubleBenchmark.double_scalar            512  avgt    5    248.231 ±   0.231  ns/op
DoubleBenchmark.double_scalar           4096  avgt    5   2191.549 ± 138.581  ns/op
DoubleBenchmark.double_scalar          32768  avgt    5  17992.861 ±   6.158  ns/op
DoubleBenchmark.double_vec                64  avgt    5      9.894 ±   0.109  ns/op
DoubleBenchmark.double_vec               512  avgt    5     84.576 ±   6.759  ns/op
DoubleBenchmark.double_vec              4096  avgt    5    712.020 ±  57.585  ns/op
DoubleBenchmark.double_vec             32768  avgt    5   5696.040 ± 468.014  ns/op
DoubleBenchmark.double_vec_acc            64  avgt    5      3.313 ±   0.028  ns/op
DoubleBenchmark.double_vec_acc           512  avgt    5     20.304 ±   0.016  ns/op
DoubleBenchmark.double_vec_acc          4096  avgt    5    258.935 ±  23.304  ns/op
DoubleBenchmark.double_vec_acc         32768  avgt    5   2193.248 ± 154.541  ns/op
DoubleBenchmark.double_vec_mask           64  avgt    5     11.194 ±   0.729  ns/op
DoubleBenchmark.double_vec_mask          512  avgt    5     84.791 ±   0.285  ns/op
DoubleBenchmark.double_vec_mask         4096  avgt    5    650.234 ±   1.101  ns/op
DoubleBenchmark.double_vec_mask        32768  avgt    5   5398.215 ±  10.093  ns/op
IntBenchmark.int_scalar                   64  avgt    5      8.696 ±   0.009  ns/op
IntBenchmark.int_scalar                  512  avgt    5     85.914 ±   0.244  ns/op
IntBenchmark.int_scalar                 4096  avgt    5    732.764 ±  23.949  ns/op
IntBenchmark.int_scalar                32768  avgt    5   6103.423 ± 135.991  ns/op
IntBenchmark.int_vec                      64  avgt    5      4.519 ±   0.311  ns/op
IntBenchmark.int_vec                     512  avgt    5     32.501 ±   0.055  ns/op
IntBenchmark.int_vec                    4096  avgt    5    240.203 ±   0.067  ns/op
IntBenchmark.int_vec                   32768  avgt    5   1855.932 ±   7.764  ns/op
IntBenchmark.int_vec_acc                  64  avgt    5      2.334 ±   0.012  ns/op
IntBenchmark.int_vec_acc                 512  avgt    5      7.534 ±   0.103  ns/op
IntBenchmark.int_vec_acc                4096  avgt    5     55.010 ±   0.087  ns/op
IntBenchmark.int_vec_acc               32768  avgt    5    773.864 ±   0.575  ns/op
IntBenchmark.int_vec_mask                 64  avgt    5      5.827 ±   0.009  ns/op
IntBenchmark.int_vec_mask                512  avgt    5     35.930 ±   0.670  ns/op
IntBenchmark.int_vec_mask               4096  avgt    5    265.783 ±   0.334  ns/op
IntBenchmark.int_vec_mask              32768  avgt    5   2135.005 ±   1.546  ns/op
LongBenchmark.long_scalar                 64  avgt    5      9.571 ±   0.499  ns/op
LongBenchmark.long_scalar                512  avgt    5     87.490 ±   5.163  ns/op
LongBenchmark.long_scalar               4096  avgt    5    733.370 ±   4.139  ns/op
LongBenchmark.long_scalar              32768  avgt    5   5935.086 ± 292.354  ns/op
LongBenchmark.long_vec                    64  avgt    5      5.109 ±   0.033  ns/op
LongBenchmark.long_vec                   512  avgt    5     38.625 ±   0.171  ns/op
LongBenchmark.long_vec                  4096  avgt    5    306.304 ±  18.859  ns/op
LongBenchmark.long_vec                 32768  avgt    5   2442.390 ±   2.621  ns/op
LongBenchmark.long_vec_acc                64  avgt    5      3.026 ±   0.034  ns/op
LongBenchmark.long_vec_acc               512  avgt    5     14.641 ±   0.062  ns/op
LongBenchmark.long_vec_acc              4096  avgt    5    112.065 ±   4.794  ns/op
LongBenchmark.long_vec_acc             32768  avgt    5   1495.975 ±  10.670  ns/op
LongBenchmark.long_vec_mask               64  avgt    5      7.312 ±   0.005  ns/op
LongBenchmark.long_vec_mask              512  avgt    5     58.617 ±   0.040  ns/op
LongBenchmark.long_vec_mask             4096  avgt    5    465.617 ±  10.007  ns/op
LongBenchmark.long_vec_mask            32768  avgt    5   3726.090 ±   3.199  ns/op
```

- AMD RYZEN 9 7950X
- OpenJDK version "23" 2024-09-17

## References:
https://www.dag.inf.usi.ch/wp-content/uploads/cc23-slides.pdf
https://www.jfokus.se/jfokus24-preso/Enter-the-Parallel-Universe-of-the-Vector-API.pdf
