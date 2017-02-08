package map_reduce.standard_deviation;

import map_reduce.BasicDriver;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.ToolRunner;


public class AverageActivitiesDriver extends BasicDriver {

    public void countAverageActivities(String input, String output) throws  Exception{
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Average");
        job.setJarByClass(AverageActivitiesDriver.class);

        job.setMapperClass(AverageActivitiesMapper.class);
        job.setReducerClass(AverageActivitiesReducer.class);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DoubleWritable.class);

        deletePreviousOutput(output);

        FileInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        job.waitForCompletion(true);
    }

}