package map_reduce;

import map_reduce.max_activity.MaxActivityDriver;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;


public abstract class BasicDriver {

    public void deletePreviousOutput(String output) {
        try {
            FileSystem fs = FileSystem.get(new Configuration());
            fs.delete(new Path(output), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}