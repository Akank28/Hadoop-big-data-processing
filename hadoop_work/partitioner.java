package com.hadoop.parti;

import java.io.*;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.w3c.*;
import org.apache.hadoop.mapreduce.Partitioner;


public class partitioner{
	 
  public static class Map extends Mapper<LongWritable,Text,Text,IntWritable> {
 // IntWritable one=new IntWritable(1);
  //private Text word=new Text();
 

 public void map(LongWritable key,Text value,Context context)throws IOException, InterruptedException
{
	String[] line=value.toString().split(" ");
	Text regno=new Text(line[1]);
	int i= new Integer(Integer.parseInt(line[4]));
	IntWritable fatmark= new IntWritable(i);
	
	context.write(regno, fatmark);
}
  }
  
  public static class dpart extends Partitioner<Text,IntWritable>
  {
	  public int getPartition(Text key,IntWritable value,int nr)
	  {
		 if(value.get()>90)
			  return 0;
		 if(value.get()>80)
		 	  return 1;
		  else
			  return 2;
		  }
} 
 public static class Reduce extends Reducer<Text,IntWritable,Text,IntWritable>
 { 
	 
	 public void reduce(Text key,Iterable<IntWritable> values, Context context)throws IOException, InterruptedException{
		for(IntWritable temp:values)
		{   
			
			context.write(key,temp);
		}
		
	 }
	
 }
 public static void main(String[] args)throws IOException, ClassNotFoundException, InterruptedException
 {
	  Configuration conf=new Configuration();
	  Job job=new Job(conf,"partitioner");
	  
	  
	  job.setJarByClass(partitioner.class);
	  job.setOutputKeyClass(Text.class);
	  job.setOutputValueClass(IntWritable.class);
	  job.setMapperClass(Map.class);
	  job.setReducerClass(Reduce.class);
	  job.setPartitionerClass(dpart.class);
	  job.setNumReduceTasks(3);
	  job.setInputFormatClass(TextInputFormat.class);
	  job.setOutputFormatClass(TextOutputFormat.class);
	  FileInputFormat.addInputPath(job,new Path(args[0]));
	  FileOutputFormat.setOutputPath(job,new Path(args[1]));
	  job.waitForCompletion(true);
	  
 }
  
}
