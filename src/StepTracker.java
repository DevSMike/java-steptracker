public class StepTracker {
    int stepsGoal = 10_000;

    MonthData[] monthToData;
    Converter converter = new Converter();
    public StepTracker(){
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    public void setStepsForDay(int day, int month, int steps) {
        monthToData[month].daysToData[day] = steps;
    }
    public int getStepsForDay(int day, int month) {
        return monthToData[month].daysToData[day];
    }
    public void printStaticsMonth(int month) {
        for (int i=0; i<monthToData[month].daysToData.length; i++) {
            if (i ==monthToData[month].daysToData.length-1) {
                System.out.print((i+1)+" день: "+monthToData[month].daysToData[i]);
            } else {
                System.out.print((i+1)+" день: "+monthToData[month].daysToData[i]+", ");
            }
        }
        System.out.println();
    }
    public int findMaxStepsMonth(int month) {
       int maxSteps = 0;
       for (int i=0; i <monthToData[month].daysToData.length; i++) {
           if (maxSteps < monthToData[month].daysToData[i]) {
               maxSteps = monthToData[month].daysToData[i];
           }
       }
       return maxSteps;
    }
    public int findAverageMonth(int month) {
        int sumSteps = 0;
        for (int i=0; i<monthToData[month].daysToData.length; i++) {
            sumSteps += monthToData[month].daysToData[i];
        }
        return sumSteps/monthToData[month].daysToData.length;
    }
    public double getDistance(int month) {
       return converter.convertStepsToKm(findMaxStepsMonth(month));
    }
    public double getNumbersBurnedKiloCal(int month) {
        return converter.convertStepsToKiloCalories(findMaxStepsMonth(month));
    }
    public void changeStepsGoal (int newGoal) {
        stepsGoal = newGoal;
    }
    public int getBestSteak(int month) {
        int count =0;
        int maxCount = 0;
        for (int i=0; i<monthToData[month].daysToData.length; i++) {
            if (monthToData[month].daysToData[i] >= stepsGoal) {
                count++;
                if (maxCount < count) {
                    maxCount = count;
                }

            } else { count=0; }
        }
        return maxCount;
    }
}
class MonthData {
    int[] daysToData;

    public MonthData(){
      daysToData = new int[30];
    }
}

class Converter {
  double stepLength = 0.00075;
  int sizeKiloCalorie = 1_000;
  int sizeCalorie = 50;
  double convertStepsToKm(int sumSteps) {
      return sumSteps * stepLength;
  }
  double convertStepsToCalories(int sumSteps) {
      return sumSteps * sizeCalorie;
  }
  double convertStepsToKiloCalories(int sumSteps) {
      return convertStepsToCalories(sumSteps) / sizeKiloCalorie;
  }

}