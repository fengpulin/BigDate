package com.lfp.mr.friend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RunJob {

	public static void main(String[] args) {

		System.setProperty("HADOOP_USER_NAME", "root");

		Configuration config = new Configuration();
		config.set("fs.defaultFS", "hdfs://node1:8020");
		config.set("mapred.jar", "C:\\Users\\lfp\\Desktop\\friend.jar");
		try {
			FileSystem fs = FileSystem.get(config);
			Job job = Job.getInstance(config, "friend");
			job.setJarByClass(RunJob.class);
			job.setMapperClass(FriendMapper.class);

			job.setReducerClass(FriendReducer.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);

			job.setNumReduceTasks(1);

			FileInputFormat.addInputPath(job, new Path("/usr/input/friend"));

			Path outPath = new Path("/usr/output/friend");
			if (fs.exists(outPath)) {
				fs.delete(outPath, true);
			}
			FileOutputFormat.setOutputPath(job, outPath);

			boolean f = job.waitForCompletion(true);
			if (f) {
				System.out.println("job成功执行！");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
