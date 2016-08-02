package com.lfp.mr.friend;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FriendMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {
		//分割字符串
		String[] names = value.toString().split("\t");
		for (String name:names) {
			context.write(new Text(names[0]), new Text(name));
			context.write(new Text(name), new Text(names[0]));
		}
		
	}

}