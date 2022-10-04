import java.util.Scanner;

public class StepTracker {
    int stepsGoal = 10_000;
    int month = 0;
    int day = 0;
    int steps = 0;
    MonthData[] monthToData;
    Converter converter = new Converter();
    public StepTracker(){
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }
    public void printMonths() {
        System.out.println("Выберите месяц: 1.Январь | 2.Февраль | 3.Март | 4.Апрель | 5.Май | 6.Июнь" +
                " | 7.Июль | 8.Август | 9.Сентябрь | 10.Октябрь | 11.Ноябрь | 12.Декабрь");
    }
    public void printDays() {
        System.out.println("Выберите день: В месяце 30 дней, введите число (1-30), чтобы выбрать конкртеный день!");
    }
    public int chooseTheMenuItem(Scanner scanner) {
        return scanner.nextInt();
    }
    public void printMonthStatistic(Scanner scanner){
        printMonths();
        month = scanner.nextInt();
        month = inputCheckMonth(month,scanner);
        System.out.println("Статистика за "+month + "й мессяц");
        System.out.println("Количество пройденных шагов по дням:");
        printStaticsMonth(month-1);
        System.out.println("Максимальное пройденное количество шагов в месяце: "+ findMaxStepsMonth(month-1));
        System.out.println("Среднее количество шагов в месяце: " + findAverageMonth(month-1));
        System.out.println("Пройденная дистанция (в км): " + getDistance(month-1));
        System.out.println("Количество сожжённых килокалорий: " + getNumbersBurnedKiloCal(month-1));
        System.out.println("Лучшая серия за месяц: " + getBestSteak(month-1));

    }
    public void changeGoal(Scanner scanner){
        System.out.println("Изменение целевого количества шагов. Введённое значение не должно быть отрицательным.");
        int newGoal = scanner.nextInt();
        changeStepsGoal(inputCheckNegative(newGoal, scanner));
        System.out.println("Целевое значение теперь такое: " + stepsGoal);
    }
    public void addCountStepsPerDay(Scanner scanner){
        printMonths();
        month = scanner.nextInt();
        month = inputCheckMonth(month,scanner);
        printDays();
        day = scanner.nextInt();
        day = inputCheckDays(day, scanner);
        System.out.println("Запись для " + month +"." + day);
        System.out.println("Сколько шагов вы хотите записать в этот день? Введите неотрицательное число.");
        steps = scanner.nextInt();
        steps = inputCheckNegative(steps,scanner);
        setStepsForDay(day-1,month-1,steps);
        System.out.println("Создана запись от " + month +"."+day + " - " + getStepsForDay(day-1, month-1));

    }
    public  int inputCheckMonth(int userInput, Scanner scanner) {
        boolean isInCorrect = true;
        while (isInCorrect) {
            if (userInput < 1 || userInput > 12) {
                printWarning();
                printMonths();
                userInput = scanner.nextInt();
            } else {isInCorrect = false;}
        }
        return userInput;
    }

    public  int inputCheckDays(int userInput, Scanner scanner) {
        boolean isInCorrect = true;
        while (isInCorrect) {
            if (userInput < 1 || userInput > 30) {
                printWarning();
                printDays();
                userInput = scanner.nextInt();
            } else {isInCorrect = false;}
        }
        return userInput;
    }

    public  int inputCheckNegative(int userInput, Scanner scanner) {
        boolean isInCorrect = true;
        while (isInCorrect) {
            if (userInput < 0) {
                printWarning();
                userInput = scanner.nextInt();
            } else {isInCorrect = false;}
        }
        return userInput;
    }
    public static void printWarning() {
        System.out.println("Вы ввели некорректное значение! Повторите ввод.");
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