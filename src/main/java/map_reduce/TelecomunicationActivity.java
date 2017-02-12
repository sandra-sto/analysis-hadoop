package map_reduce;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

public class TelecomunicationActivity implements WritableComparable<TelecomunicationActivity>{
    LongWritable squareId;
    LongWritable time;

    DoubleWritable smsInActivity;
    DoubleWritable smsOutActivity;

    DoubleWritable callInActivity;
    DoubleWritable callOutActivity;

    DoubleWritable internetActivity;

    public TelecomunicationActivity() {
        squareId = new LongWritable();
        time = new LongWritable();

        smsInActivity = new DoubleWritable();
        smsOutActivity = new DoubleWritable();

        callInActivity = new DoubleWritable();
        callOutActivity = new DoubleWritable();

        internetActivity = new DoubleWritable();
    }

    public TelecomunicationActivity(LongWritable squareId, DoubleWritable smsInActivity, DoubleWritable smsOutActivity,
                                    DoubleWritable callInActivity, DoubleWritable callOutActivity, DoubleWritable internetActivity) {
        this.squareId = squareId;
        this.smsInActivity = smsInActivity;
        this.smsOutActivity = smsOutActivity;
        this.callInActivity = callInActivity;
        this.callOutActivity = callOutActivity;
        this.internetActivity = internetActivity;
    }

    public TelecomunicationActivity(LongWritable squareId, LongWritable time, DoubleWritable smsInActivity, DoubleWritable smsOutActivity,
                                    DoubleWritable callInActivity, DoubleWritable callOutActivity, DoubleWritable internetActivity) {
        this.squareId = squareId;
        this.time = time;

        this.smsInActivity = smsInActivity;
        this.smsOutActivity = smsOutActivity;

        this.callInActivity = callInActivity;
        this.callOutActivity = callOutActivity;

        this.internetActivity = internetActivity;
    }


    public TelecomunicationActivity(long squareId, long time, double smsInActivity, double smsOutActivity,
                                    double callInActivity, double callOutActivity, double internetActivity) {
        this.squareId = new LongWritable(squareId);
        this.time = new LongWritable(time);

        this.smsInActivity = new DoubleWritable(smsInActivity);
        this.smsOutActivity = new DoubleWritable(smsOutActivity);

        this.callInActivity = new DoubleWritable(callInActivity);
        this.callOutActivity = new DoubleWritable(callOutActivity);

        this.internetActivity = new DoubleWritable(internetActivity);
    }

    public LongWritable getSquareId() {
        return squareId;
    }

    public Date getDate() {
        return new Date(time.get());
    }

    public DateInformation getDateInformation() {
        return new DateInformation(time);
    }

    public LongWritable getTime() {
        return time;
    }

    public DoubleWritable getSmsInActivity() {
        return smsInActivity;
    }

    public DoubleWritable getSmsOutActivity() {
        return smsOutActivity;
    }

    public DoubleWritable getOverallSmsActivity() {
        return new DoubleWritable(smsInActivity.get() + smsOutActivity.get());
    }

    public DoubleWritable getOverallCallActivity() {
        return new DoubleWritable(callInActivity.get() + callOutActivity.get());
    }

    public DoubleWritable getOverallActivity() {
        return new DoubleWritable(callInActivity.get() + callOutActivity.get() + smsInActivity.get()
                + smsOutActivity.get() + internetActivity.get());
    }

    public DoubleWritable getCallInActivity() {
        return callInActivity;
    }

    public DoubleWritable getCallOutActivity() {
        return callOutActivity;
    }

    public DoubleWritable getInternetActivity() {
        return internetActivity;
    }

    public int compareTo(TelecomunicationActivity o) {
        return squareId.compareTo(o.getSquareId()) + time.compareTo(o.getTime()) + smsInActivity.compareTo(o.getSmsInActivity())
                + smsOutActivity.compareTo(o.getSmsOutActivity()) + callInActivity.compareTo(o.getCallInActivity())
                + callOutActivity.compareTo(o.getCallOutActivity()) + internetActivity.compareTo(o.getInternetActivity());
    }

    public void write(DataOutput out) throws IOException {
        squareId.write(out);
        time.write(out);

        smsInActivity.write(out);
        smsOutActivity.write(out);

        callInActivity.write(out);
        callOutActivity.write(out);
        internetActivity.write(out);
    }

    public void readFields(DataInput in) throws IOException {
        squareId.readFields(in);
        time.readFields(in);

        smsInActivity.readFields(in);
        smsOutActivity.readFields(in);

        callInActivity.readFields(in);
        callOutActivity.readFields(in);
        internetActivity.readFields(in);
    }
}
