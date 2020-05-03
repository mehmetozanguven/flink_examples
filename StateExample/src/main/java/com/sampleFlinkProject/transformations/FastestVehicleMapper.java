package com.sampleFlinkProject.transformations;

import com.sampleFlinkProject.util.VehicleInstantData;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.configuration.Configuration;

import java.io.IOException;

public class FastestVehicleMapper extends RichMapFunction<VehicleInstantData, VehicleInstantData>
{
    private transient ValueState<VehicleInstantData> fastestVehicleState;

    @Override
    public void open(Configuration parameters) throws Exception
    {
        ValueStateDescriptor<VehicleInstantData> valueStateDescriptor = new ValueStateDescriptor<VehicleInstantData>(
                "fastest-vehicle",
                TypeInformation.of(VehicleInstantData.class)
        );

        fastestVehicleState = getRuntimeContext().getState(valueStateDescriptor);
    }

    @Override
    public VehicleInstantData map(VehicleInstantData newVehicleData) throws Exception
    {
        VehicleInstantData previousVehicleData = this.fastestVehicleState.value();

        if (previousVehicleData == null) // means there is nothing in the state
        {
            this.fastestVehicleState.update(newVehicleData);
            return newVehicleData;
        }
        else
        {
            return updateStateAndReturnValue(newVehicleData, previousVehicleData);
        }
    }

    private VehicleInstantData updateStateAndReturnValue(VehicleInstantData newVehicleData, VehicleInstantData previousVehicleData) throws IOException
    {
        if (Integer.parseInt(previousVehicleData.getSpeed()) < Integer.parseInt(newVehicleData.getSpeed()))
        {
            this.fastestVehicleState.update(newVehicleData);
            return newVehicleData;
        }
        else
        {
            return previousVehicleData;
        }
    }
}
