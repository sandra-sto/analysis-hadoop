package map_reduce.standard_deviation;

import map_reduce.BasicDriver;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class StandardDeviationDriver extends BasicDriver {

    public void countStandardDeviationForActivities(String input, String output, String averagesOutput) throws Exception{
        Configuration conf = new Configuration();
        conf.set("Averages Output", averagesOutput);

        Job job = Job.getInstance(conf, "StandardDeviation");
        job.setJarByClass(StandardDeviationDriver.class);

        job.setMapperClass(StandardDeviationMapper.class);
        job.setReducerClass(StandardDeviationReducer.class);

        job.setOutputKeyClass(ParameterValue.class);
        job.setOutputValueClass(DoubleWritable.class);

        job.setMapOutputKeyClass(ParameterValue.class);
        job.setMapOutputValueClass(DoubleWritable.class);

        deletePreviousOutput(output);

        FileInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        job.waitForCompletion(true);
    }
}