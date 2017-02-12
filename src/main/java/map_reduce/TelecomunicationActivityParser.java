package map_reduce;

public class TelecomunicationActivityParser {
    public static TelecomunicationActivity parseFromString(String line){
        double smsInActivity = 0;
        double smsOutActivity = 0;
        double callInActivity = 0;
        double callOutActivity = 0;
        double internetActivity = 0;

        String[] parts = line.split("\t");
        int length = parts.length;

        long squareId = Long.parseLong(parts[0]);

        long timeInterval = Long.parseLong(parts[1]);

        if (!parts[3].equals(""))
            smsInActivity = Double.parseDouble(parts[3]);
        if (length > 4 && !parts[4].equals(""))
            smsOutActivity = Double.parseDouble(parts[4]);
        if (length > 5 && !parts[5].equals(""))
            callInActivity = Double.parseDouble(parts[5]);
        if (length > 6 && !parts[6].equals(""))
            callOutActivity = Double.parseDouble(parts[6]);
        if (length > 7 && !parts[7].equals(""))
            internetActivity = Double.parseDouble(parts[7]);

        return new TelecomunicationActivity(squareId, timeInterval, smsInActivity, smsOutActivity, callInActivity, callOutActivity, internetActivity);
    }
}
