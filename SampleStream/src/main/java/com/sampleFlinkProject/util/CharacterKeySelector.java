package com.sampleFlinkProject.util;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterKeySelector implements KeySelector<Tuple2<String, Integer>, String>
{
    private Logger logger = LoggerFactory.getLogger(CharacterKeySelector.class);

    private String specificCharacter;

    public CharacterKeySelector(String specificCharacter)
    {
        this.specificCharacter = specificCharacter;
    }

    @Override
    public String getKey(Tuple2<String, Integer> value) throws Exception
    {
        String wordInTuple = value.getField(0);
        if (wordInTuple.toLowerCase().contains(specificCharacter.toLowerCase()) || wordInTuple.contains(specificCharacter.toUpperCase()))
        {
            logger.info("value matched the if {}", value);
            return specificCharacter;
        }
        return "key value another";
    }
}
