package map_reduce.standard_deviation;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by nissatech on 2/8/17.
 */
public class ParameterValue implements WritableComparable<ParameterValue>{
    Text parameterName;
    DoubleWritable value;

    public ParameterValue() {
        parameterName = new Text();
        value = new DoubleWritable();
    }

    public ParameterValue(Text parameterName, DoubleWritable value) {
        this.parameterName = parameterName;
        this.value = value;
    }

    public ParameterValue(String parameterName, double value) {
        this.parameterName = new Text(parameterName);
        this.value = new DoubleWritable(value);
    }
    public Text getParameterName() {
        return parameterName;
    }

    public DoubleWritable getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ParameterValue) {
            ParameterValue tp = (ParameterValue) o;
            return parameterName.equals(tp.getParameterName());
        }
        return false;
    }

    public int compareTo(ParameterValue o) {
        return parameterName.compareTo(o.getParameterName());
    }

    public void write(DataOutput out) throws IOException {
        parameterName.write(out);
        value.write(out);
    }

    public void readFields(DataInput in) throws IOException {
        parameterName.readFields(in);
        value.readFields(in);
    }
}
