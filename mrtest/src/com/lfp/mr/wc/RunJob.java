package com.lfp.mr.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RunJob {

	public static void main(String[] args) {
		
		System.setProperty("HADOOP_USER_NAME", "root");

		Configuration config = new Configuration();
		config.set("fs.defaultFS", "hdfs://node1:8020");
		config.set("mapred.jar", "C:\\Users\\lfp\\Desktop\\wc.jar");
		try {
			FileSystem fs = FileSystem.get(config);
			Job job = Job.getInstance(config, "wc");
			job.setJarByClass(RunJob.class);
			job.setMapperClass(WcMapper.class);

			job.setReducerClass(WcReducer.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);

			job.setNumReduceTasks(1);

			FileInputFormat.addInputPath(job, new Path("/usr/input/wc"));

			Path outPath = new Path("/usr/output/wc");
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
