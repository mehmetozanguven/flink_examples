
package com.sampleFlinkProject;

import com.sampleFlinkProject.streams.StreamCreator;
import com.sampleFlinkProject.transformations.FastestVehicleMapper;
import com.sampleFlinkProject.util.VehicleInstantData;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.runtime.state.memory.MemoryStateBackend;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class StreamingJob {

	public static void main(String[] args) throws Exception {
		String txtFilePath = "/home/mehmetozanguven/Desktop/flink_examples/datas/ibb_datas/01.2019_1-10.txt";
		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		StreamCreator streamCreator = new StreamCreator();

		DataStream<String> dataSource = streamCreator.getDataSourceStream(env, txtFilePath);

		DataStream<VehicleInstantData> vehicleInstantDataDataStream = streamCreator.mapDataSourceStreamToObject(dataSource);

		KeyedStream<VehicleInstantData, Tuple> keyByVehicleType = streamCreator.keyByVehicleType(vehicleInstantDataDataStream);

		SingleOutputStreamOperator<VehicleInstantData> fastestVehicles = streamCreator.findFastestVehicleForEachType(keyByVehicleType);

		fastestVehicles.print().name("PrintResult");


		// execute program
		env.execute("Flink Streaming Java API Skeleton");
	}
}
