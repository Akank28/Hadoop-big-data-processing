# Hadoop-big-data-processing
## Programs
### Wordcount
Basic hadoop java program ,
Input: txt or csv file of words uploaded on hdfs.,
Output : count of each word in the file 

### Combiner
Combiner reduces the work of the reducer. It basically groups by the key for the particular input split file.,
Input: txt or csv file of words uploaded on hdfs.,
Output : count of each word in the file

### Partitioner 
Partitioner partitions the file into n partitions.,
Input : File with inputs seperated by space or space or comma etc uploaded on hdfs,
Output : N partitions 

### Counters
Counters can be used for counting any thing related to the input file,output can either be printed on terminal using
System.out.println or the output can be written to hdfs

### nLine input
Using this the no. of lines to be inputed at a time can be set.

### Distributed cache
This is used to map side join or reduce side join. One file can be saved on the distributed cache and the other file can be input from hdfs.

### Inverted Indexing
Like normal indexing, in inverted indexing the count of the word and the filename which it belongs to is displayed in the output.,
Input : file directory where the input files are stored

## Steps to run
1. Make jar of your input file 
2. type "start-all.sh" or "start-dfs.sh " to start hadoop on the terminal
3. To put your input file on hdfs type
hadoop fs -put <input file path>/<input file name> <hdfs file path>
4. to run the program type
hadoop jar <name of jar file> <classname> <input file path in hdfs> <output file path in hdfs>
