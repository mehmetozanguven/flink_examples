package com.sampleFlinkProject.steams;

import com.sampleFlinkProject.transformations.FilterStreamByCharacter;
import com.sampleFlinkProject.transformations.SplitSentenceTransformation;
import com.sampleFlinkProject.transformations.TempWindowFunction;
import com.sampleFlinkProject.util.WordCountData;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StreamCreator
{
    private Logger logger = LoggerFactory.getLogger(StreamCreator.class);

    public DataStream<String> createDataSourceStreamForWordCount(StreamExecutionEnvironment env)
    {
        DataStream<String> source = env.fromElements(WordCountData.WORDS).name("WordCountDataSource");
        return source;
    }


    public DataStream<Tuple2<String, Integer>> splitSentenceWordByWord(DataStream<String> wordDataSourceStream)
    {
        DataStream<Tuple2<String, Integer>> wordByWordStream = wordDataSourceStream.flatMap(new SplitSentenceTransformation()).name("WordByWordStream");
        return wordByWordStream;
    }

    public DataStream<Tuple2<String, Integer>> filterWordThatContainsSpecificCharacter(DataStream<Tuple2<String, Integer>> wordByWordStream, String specificCharacter)
    {
        DataStream<Tuple2<String, Integer>> filteredStream = wordByWordStream.filter(new FilterStreamByCharacter(specificCharacter)).name("Filter Stream By Specific Character");
        return filteredStream;
    }

    public SingleOutputStreamOperator<Tuple2<String, Integer>> sumTheWordContainsSpecificCharacter( DataStream<Tuple2<String, Integer>> filteredStream)
    {
        SingleOutputStreamOperator<Tuple2<String, Integer>> sumWordStream = filteredStream.keyBy(1).sum(1).name("Count keyed stream");
        return sumWordStream;
    }
}
