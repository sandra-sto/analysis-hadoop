package map_reduce.daily_activity;

import map_reduce.BasicDriver;
import map_reduce.TelecomunicationActivity;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.ToolRunner;


public class TotalDailyActivityDriver extends BasicDriver {

    public void countTotalDailyActivity(String input, String output) throws Exception{
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "DailyActivity");
        job.setJarByClass(TotalDailyActivityDriver.class);

        job.setMapperClass(TotalDailyActivityMapper.class);
        job.setReducerClass(TotalDailyActivityReducer.class);
        job.setCombinerClass(TotalDailyActivityCombiner.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(TelecomunicationActivity.class);

        deletePreviousOutput(output);

        FileInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        job.waitForCompletion(true);
    }

}