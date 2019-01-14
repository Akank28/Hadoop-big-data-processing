import java.io.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.w3c.*;


public class wordcount{
	 
  public static class Map extends Mapper<LongWritable,Text,Text,IntWritable> {
  IntWritable one=new IntWritable(1);
  private Text word=new Text();
 

 public void map(LongWritable key,Text value,Context context)throws IOException, InterruptedException
{
	String[] line=value.toString().split(" ");
	for(String lines:line)
	{
		context.write(new Text(lines),new IntWritable(1));
		
	}
}
  }
 public static class Reduce extends Reducer<Text,IntWritable,Text,IntWritable>
 {
	 public void reduce(Text key,Iterable<IntWritable> values, Context context)throws IOException, InterruptedException{
		 int sum=0;
		 for(IntWritable val:values){
			 sum+=val.get();
		 }
		 context.write(key,new IntWritable(sum));
	 }
 }
 public static void main(String[] args)throws IOException, ClassNotFoundException, InterruptedException
 {
	  Configuration conf=new Configuration();
	  Job job=new Job(conf,"wordcount");
	  job.setJarByClass(wordcount.class);
	  job.setOutputKeyClass(Text.class);
	  job.setOutputValueClass(IntWritable.class);
	  job.setMapperClass(Map.class);
	  job.setReducerClass(Reduce.class);
	  job.setInputFormatClass(TextInputFormat.class);
	  job.setOutputFormatClass(TextOutputFormat.class);
	  FileInputFormat.addInputPath(job,new Path(args[0]));
	  FileOutputFormat.setOutputPath(job,new Path(args[1]));
	  job.waitForCompletion(true);
	  
 }
  
}