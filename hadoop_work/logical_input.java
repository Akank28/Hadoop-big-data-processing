package com.hadoop.counter;
import java.io.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapred.*;
//import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

//import org.apache.hadoop.mapreduce.lib.input
public class logical {
	public static class Map extends Mapper<LongWritable,Text,LongWritable,Text> {
		 // IntWritable one=new IntWritable(1);
		  //private Text word=new Text();
		

		 public void map(LongWritable key,Text value,Context context)throws IOException, InterruptedException
		{
		
			 context.write(key, value);
			   
	     }
			  
			    
				    
}
		  
		  
		  
	/*	 public static class Reduce extends Reducer<Text,IntWritable,Text,IntWritable>
		 { 
			 
			 public void reduce(Text key,Iterable<IntWritable> values, Context context)throws IOException, InterruptedException{
				for(IntWritable temp:values)
				{   
					
					context.write(key,temp);
				}
				
			 }
			
		 }*/
		 public static void main(String[] args)throws IOException, ClassNotFoundException, InterruptedException
		 {
			  Configuration conf=new Configuration();
			  Job job=new Job(conf,"logical");
			  
			  conf.set("mapred.max.split.size", "10000");
			  job.setJarByClass(logical.class);
			  job.setOutputKeyClass(Text.class);
			  job.setOutputValueClass(IntWritable.class);
			  job.setMapperClass(Map.class);
			//  job.setReducerClass(Reduce.class);
			 
			  job.setNumReduceTasks(0);
			  job.setInputFormatClass(TextInputFormat.class);
			  job.setOutputFormatClass(TextOutputFormat.class);
			  FileInputFormat.addInputPath(job,new Path(args[0]));
			  FileOutputFormat.setOutputPath(job,new Path(args[1]));
			  job.waitForCompletion(true);
			  
		 }
}
