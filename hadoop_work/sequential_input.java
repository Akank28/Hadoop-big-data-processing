package com.hadoop.counter;
import java.io.*;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Mapper.Context;
//import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
//import org.apache.hadoop.mapreduce.lib.input

public class sequence {
	public static class Map extends Mapper<LongWritable,Text,LongWritable,Text> {
		 // IntWritable one=new IntWritable(1);
		  //private Text word=new Text();
		// public enum STU_COUNTER{more,low};
		 
       
		 public void map(LongWritable key,Text value,Context context)throws IOException, InterruptedException
		{
		/*	String[] line=value.toString().split(",");
			Text regno=new Text(line[1]);
			int i= new Integer(Integer.parseInt(line[3]));
			// String a=key.toString();
			//if(a.equalsIgnoreCase(name))
			IntWritable fatmark= new IntWritable(i);*/
			context.write(key, value);
			
		}
		
		  }
		  
	/*	  public static class dpart extends Partitioner<Text,IntWritable>
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
			
		 }*/
		 public static void main(String[] args)throws IOException, ClassNotFoundException, InterruptedException
		 {
			  Configuration conf=new Configuration();
			//  conf.setInt(NLineInputFormat.LINES_PER_MAP, 3);
			 
			//  conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator",",");
			  Job job=new Job(conf,"sequence");
			  
			  
			  job.setJarByClass(sequence.class);
			  job.setOutputKeyClass(Text.class);
			  job.setOutputValueClass(Text.class);
			  job.setMapperClass(Map.class);
			  job.setInputFormatClass(TextInputFormat.class);
			  
			  job.setOutputFormatClass(SequenceFileOutputFormat.class);
			  job.setNumReduceTasks(0);
			  FileInputFormat.addInputPath(job,new Path(args[0]));
			  FileOutputFormat.setOutputPath(job,new Path(args[1]));
			  job.waitForCompletion(true);
			 
			  
			  
		 }

}
