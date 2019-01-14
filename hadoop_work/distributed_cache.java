package com.hadoop.counter;
import java.io.*;
import java.util.ArrayList;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.w3c.*;



public class discache {
	public static class Map extends Mapper<LongWritable,Text,Text,Text> {
		 // IntWritable one=new IntWritable(1);
		  //private Text word=new Text();
		 Path[] cfile=new Path[0];
		    ArrayList<Text> empl=new ArrayList<Text>();
		    
		    public void setup(Context context)
		    {
		    	Configuration conf=context.getConfiguration();
		    	try
		    	{
		    	    	cfile = DistributedCache.getLocalCacheFiles(conf);
		    	BufferedReader reader=new BufferedReader(new FileReader(cfile[0].toString()));
		    	String line;
		    	while ((line=reader.readLine())!=null)
		    	{
		    		Text tt=new Text(line);
		    	empl.add(tt);	
		    	}
		    	
		    	    }
		    	
		    	catch(IOException e)
		    	{
		    		e.printStackTrace();
		    		    	}
		    }
		    

		 public void map(LongWritable key,Text value,Context context)throws IOException, InterruptedException
		{
		
			 
			    String line2 = value.toString();
			    String[] elements=line2.split(",");
			        
			    for(Text e:empl)
			    {
			       String[] line1 = e.toString().split(",");
			        	
			        	
			       if(elements[0].equals(line1[0]))
			        {
			        		
			        	context.write(new Text(elements[0]),new Text(elements[1]+","+line1[1]));
			        }
			        	
			                
			    }  
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
			  Job job=new Job(conf,"discache");
			  
			  
			  job.setJarByClass(discache.class);
			  job.setOutputKeyClass(Text.class);
			  job.setOutputValueClass(IntWritable.class);
			  job.setMapperClass(Map.class);
			//  job.setReducerClass(Reduce.class);
			  DistributedCache.addCacheFile(new Path(args[0]).toUri(),job.getConfiguration());
			  FileInputFormat.addInputPath(job, new Path(args[1]));
			  FileOutputFormat.setOutputPath(job, new Path(args[2]));
			  job.setNumReduceTasks(0);
			  job.setInputFormatClass(TextInputFormat.class);
			  job.setOutputFormatClass(TextOutputFormat.class);
			  
			  job.waitForCompletion(true);
			  
		 }
}
