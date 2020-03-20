package com.sampleFlinkProject.transformations;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class SplitSentenceTransformation extends RichFlatMapFunction<String, Tuple2<String, Integer>> {

    @Override
    public void flatMap(String input, Collector<Tuple2<String, Integer>> collector) throws Exception
    {
        String[] splittedSentence = input.toLowerCase().split("\\W+");
        for (String eachWord : splittedSentence)
        {
            collector.collect(new Tuple2<String, Integer>(eachWord, 2));
        }
    }
}
