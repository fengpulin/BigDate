package com.lfp.mr.friend;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FriendReducer extends Reducer<Text, Text, Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		Set<String> valueSet = new HashSet<String>();
		for (Text value : values) {
			valueSet.add(value.toString());
		}
		for (String valueX : valueSet) {
			for (String valueY : valueSet) {
				if (valueX!=valueY) {
					context.write(new Text(valueX), new Text(valueY));
				}
			}
		}

	}
}
