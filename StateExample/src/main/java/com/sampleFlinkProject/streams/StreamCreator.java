package com.sampleFlinkProject.streams;

import com.sampleFlinkProject.transformations.MapToObjectTransformation;
import com.sampleFlinkProject.util.VehicleInstantData;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

// '2019-01-07 22:12:17,
// 0xE6100000010C37A5BC5642CF3C4027A25F5B3F854440,
// 28.809606,
// 41.040996,
// Tibbi Atik Araç,
// 77,
// 2019,
// 1,
// 7'

// Expect field types:
// class java.lang.String,
// class java.lang.String,
// class java.lang.Float,
// class java.lang.Float,
// class java.lang.String,
// class java.lang.Double,
// class java.lang.Double,
// class java.lang.Double,
// class java.lang.Double
public class StreamCreator
{
    public DataStream<String> getDataSourceStream(StreamExecutionEnvironment env, String filePath)
    {
        return env.readTextFile(filePath);
    }

    public DataStream<VehicleInstantData> mapDataSourceStreamToObject(DataStream<String> dataSourceStream)
    {
        return dataSourceStream.map(new MapToObjectTransformation());
    }


}
