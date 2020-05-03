package com.sampleFlinkProject.transformations;

import com.sampleFlinkProject.util.VehicleInstantData;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapToObjectTransformation extends RichMapFunction<String, VehicleInstantData>
{
    @Override
    public VehicleInstantData map(String value) throws Exception
    {
        String[] splittedByComma = value.split(",");
        return VehicleInstantData.createFromSplittedArray(splittedByComma);
    }
}
