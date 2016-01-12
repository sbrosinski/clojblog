{
:title "Extending Spark's Accumulators"
:date "2015-04-12"
}

+++

Spark's accumulators can be used for much more than just adding up numbers. Spark only ships with int/double accumulators, but you can always create your own by implementing `AccumulatorParam`.
Let's say we're processing log files with Spark Streaming and we would like to create a running tally of the most used browsers. Look at this accumulator backed by a `HashMap<String, Long>`:

```language-java
public class MapAccumulator implements AccumulatorParam<Map<String, Long>>, Serializable {

    @Override
    public Map<String, Long> addAccumulator(Map<String, Long> t1, Map<String, Long> t2) {
        return mergeMap(t1, t2);
    }

    @Override
    public Map<String, Long> addInPlace(Map<String, Long> r1, Map<String, Long> r2) {
        return mergeMap(r1, r2);

    }

    @Override
    public Map<String, Long> zero(final Map<String, Long> initialValue) {
        return new HashMap<>();
    }

    private Map<String, Long> mergeMap( Map<String, Long> map1, Map<String, Long> map2) {
        Map<String, Long> result = new HashMap<>(map1);
        map2.forEach((k, v) -> result.merge(k, v, (a, b) -> a + b));
        return result;
    }

}
```

This creates an accumulator that can be used to keep track of a count of items identified by a String. Adding items to it will merge them with existing items and increase their total count.

Now you can register it with the Spark context like this:

```language-java
Accumulator<Map<String, Long>> browserCount = streamingContext.sparkContext().accumulator(
    new HashMap<String, Long>(),
    "browserCount",
    new MapCountAccumulator());
```

While processing your log lines you add each browser you found and its count:

```language-java
String browser = "Chrome" // for example
Map<String, Long> browserCount = new HashMap<>();
browserCount.put(browser, 1L);
```

If you do this for each record you end up with a map of counts per (browser) identifier:

```
{
    "chrome": 356,
    "firefox": 455,
    "ie9": 100
}
```

Now you could use this data to e.g. write it to a database in regular intervals and show it on some dashboard. Doing this in-stream and updating the database view very regularly will show you near real-time data.
This is just one way to make better use of Spark's accumulators.