package main;

import map_reduce.comparing_activities.ComparingActivitiesDriver;
import map_reduce.daily_activity.DailyActivityByPlacesDriver;
import map_reduce.daily_activity.TotalDailyActivityDriver;
import map_reduce.max_activity.MaxActivityDriver;
import map_reduce.standard_deviation.AverageActivitiesDriver;
import map_reduce.standard_deviation.StandardDeviationDriver;

public class MainClass {

    public static void main(String[] args) throws  Exception{

        String input = "/home/nissatech/Downloads/sms-call-internet-mi-2013-11-01.txt";
        String baseOutput = "/home/nissatech/Documents/";

//        String maxActivityOutput =  baseOutput + "outputMaxActivity";
//        MaxActivityDriver maxActivityDriver = new MaxActivityDriver();
//        maxActivityDriver.countMaxActivity(input, maxActivityOutput, 10);
//
//        String comparingActivitiesOutput = baseOutput + "outputComparingActivities";
//        ComparingActivitiesDriver comparingActivitiesDriver = new ComparingActivitiesDriver();
//        comparingActivitiesDriver.compareActivitiesForPlaces(input, comparingActivitiesOutput);

//        String totalDailyActivityOutput = baseOutput + "outputDailyActivity";
//        TotalDailyActivityDriver totalDailyActivityDriver = new TotalDailyActivityDriver();
//        totalDailyActivityDriver.countTotalDailyActivity(input, totalDailyActivityOutput);

//        String dailyActivityByPlacesOutput = baseOutput + "outputDailyActivityByPlaces";
//        DailyActivityByPlacesDriver dailyActivityByPlacesDriver = new DailyActivityByPlacesDriver();
//        dailyActivityByPlacesDriver.countDailyActivitiesForPlaces(input, dailyActivityByPlacesOutput);
//
        String averageActivitiesOutput = baseOutput + "outputAverage";
//        AverageActivitiesDriver averageActivitiesDriver = new AverageActivitiesDriver();
//        averageActivitiesDriver.countAverageActivities(input, averageActivitiesOutput);
//
        String standardDeviationOutput = baseOutput + "outputStandardDeviation";
        StandardDeviationDriver standardDeviationDriver = new StandardDeviationDriver();
        standardDeviationDriver.countStandardDeviationForActivities(input, standardDeviationOutput,
                                                        averageActivitiesOutput + "/part-r-00000");
    }
}
