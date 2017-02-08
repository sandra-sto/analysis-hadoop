package map_reduce.daily_activity;

import map_reduce.BasicDriver;
import map_reduce.TelecomunicationActivity;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DailyActivityByPlacesDriver extends BasicDriver {

    public void countDailyActivitiesForPlaces(String input,String output) throws Exception{
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "DailyActivity");
        job.setJarByClass(DailyActivityByPlacesDriver.class);

        job.setMapperClass(DailyActivityByPlacesMapper.class);
        job.setReducerClass(DailyActivityByPlacesReducer.class);

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