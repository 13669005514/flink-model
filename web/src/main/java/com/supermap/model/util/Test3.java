package com.supermap.model.util;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;


import java.util.Arrays;

public class Test3 {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment(new Configuration());
        DataStreamSource<String> socket = env.socketTextStream("localhost", 6666, "\n");

        SingleOutputStreamOperator<String> flatMap = socket.flatMap((String value, Collector<String> out) -> {
            Arrays.stream(value.split(" ")).forEach(word -> {
                out.collect(word);
            });
        }).returns(Types.STRING);

        flatMap.print();
        env.execute();
    }


}
