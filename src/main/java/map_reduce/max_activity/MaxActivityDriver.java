package map_reduce.max_activity;

import map_reduce.BasicDriver;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MaxActivityDriver extends BasicDriver {

    public void countMaxActivity(String input, String output, int numberOfMax) throws  Exception{
        Configuration conf = new Configuration();
        conf.setInt("Number of maximums", numberOfMax);

        Job job = Job.getInstance(conf, "max_activity");
        job.setJarByClass(MaxActivityDriver.class);

        job.setMapperClass(MaxActivityMapper.class);
        job.setReducerClass(MaxActivityReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(DoubleWritable.class);

        deletePreviousOutput(output);

        FileInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        job.waitForCompletion(true);
    }
}