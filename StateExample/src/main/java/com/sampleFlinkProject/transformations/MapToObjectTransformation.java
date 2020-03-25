package com.sampleFlinkProject.transformations;

import com.sampleFlinkProject.util.VehicleInstantData;
import org.apache.flink.api.common.functions.MapFunction;

public class MapToObjectTransformation implements MapFunction<String, VehicleInstantData>
{

    @Override
    public VehicleInstantData map(String value) throws Exception
    {
        String[] splittedByComma = value.split(",");
        return VehicleInstantData.createFromSplittedArray(splittedByComma);
    }
}
