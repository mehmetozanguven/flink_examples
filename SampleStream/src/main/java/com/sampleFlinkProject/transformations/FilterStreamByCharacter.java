package com.sampleFlinkProject.transformations;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilterStreamByCharacter implements FilterFunction<Tuple2<String, Integer>>
{
    private Logger logger = LoggerFactory.getLogger(FilterStreamByCharacter.class);

    private String specificCharacter;

    public FilterStreamByCharacter(String specificCharacter)
    {
        this.specificCharacter = specificCharacter;
    }

    @Override
    public boolean filter(Tuple2<String, Integer> value) throws Exception
    {
        String wordInTuple = value.getField(0);

        return wordInTuple.contains(specificCharacter);
    }
}
