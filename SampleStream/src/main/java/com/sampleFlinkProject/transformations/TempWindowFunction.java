package com.sampleFlinkProject.transformations;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.RichWindowFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TempWindowFunction extends RichWindowFunction<Tuple2<String, Integer>, String, Tuple, TimeWindow> {
    private Logger logger = LoggerFactory.getLogger(TempWindowFunction.class);
    private int count = 0;
    @Override
    public void apply(Tuple tuple, TimeWindow window, Iterable<Tuple2<String, Integer>> input, Collector<String> out) throws Exception
    {
        logger.info("Key is:' {} ' and collected element for that key and count: {}", (Object) tuple.getField(0), count);
        StringBuilder builder = new StringBuilder();
        for (Tuple2 each : input)
        {
            String key = (String) each.getField(0);
            Integer value = (Integer) each.getField(1);
            String tupleStr = "[ " + key + " , " + value + "]";
            builder.append(tupleStr);
            count ++;
        }
        logger.info("All tuples {}", builder.toString());
        logger.info("Exit method");
        logger.info("----");
    }
}
