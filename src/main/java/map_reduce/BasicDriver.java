package map_reduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


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