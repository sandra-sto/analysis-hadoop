package map_reduce.comparing_activities;

import map_reduce.BasicDriver;
import map_reduce.TelecomunicationActivity;

import map_reduce.max_activity.MaxActivityDriver;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.ToolRunner;


public class ComparingActivitiesDriver extends BasicDriver {

    public void compareActivitiesForPlaces(String input, String output) throws Exception{
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "ComparingActivities");
        job.setJarByClass(ComparingActivitiesDriver.class);

        job.setMapperClass(ComparingActivitiesMapper.class);
        job.setReducerClass(ComparingActivitiesReducer.class);
        job.setCombinerClass(ComparingActivitiesCombiner.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(TelecomunicationActivity.class);

        deletePreviousOutput(output);

        FileInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        job.waitForCompletion(true);
    }

}