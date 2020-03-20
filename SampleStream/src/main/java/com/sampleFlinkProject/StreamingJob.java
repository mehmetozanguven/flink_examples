/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sampleFlinkProject;

import com.sampleFlinkProject.steams.StreamCreator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class StreamingJob
{

	public static void main(String[] args) throws Exception
	{
		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		StreamCreator streamCreator = new StreamCreator();

		DataStream<String> wordCountDataSource = streamCreator.createDataSourceStreamForWordCount(env);

		DataStream<Tuple2<String, Integer>> readWordByWordStream = streamCreator.splitSentenceWordByWord(wordCountDataSource);
		DataStream<Tuple2<String, Integer>> filterStreamContainsSpecificCharacter = streamCreator.filterWordThatContainsSpecificCharacter(readWordByWordStream, "a");

		SingleOutputStreamOperator<Tuple2<String, Integer>> summedStream = streamCreator.sumTheWordContainsSpecificCharacter(filterStreamContainsSpecificCharacter);
		summedStream.print();

		env.execute("Flink word count example");
	}
}
