import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isOpen = true;

        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
        int userChoice;
        int day;
        int month;
        int steps;

        while(isOpen) {
            showMenu();
            userChoice = scanner.nextInt();
            if (userChoice == 1) {
                printMonths();
                month = scanner.nextInt();
                month = inputCheckMonth(month,scanner);
                printDays();
                day = scanner.nextInt();
                day = inputCheckDays(day, scanner);
                System.out.println("Запись для " + month +"." + day);
                System.out.println("Сколько шагов вы хотите записать в этот день?");
                steps = scanner.nextInt();
                stepTracker.setStepsForDay(day-1,month-1,steps);
                System.out.println("Создана запись от " + month +"."+day + " - " + stepTracker.getStepsForDay(day-1, month-1));
            } else if (userChoice == 2) {
                printMonths();
                month = scanner.nextInt();
                month = inputCheckMonth(month,scanner);
                System.out.println("Статистика за "+month + "й мессяц");
                System.out.println("Количество пройденных шагов по дням:");
                stepTracker.printStaticsMonth(month-1);
                System.out.println("Максимальное пройденное количество шагов в месяце: "+ stepTracker.findMaxStepsMonth(month-1));
                System.out.println("Среднее количество шагов в месяце: " + stepTracker.findAverageMonth(month-1));
                System.out.println("Пройденная дистанция (в км): " + stepTracker.getDistance(month-1));
                System.out.println("Количество сожжённых килокалорий: " + stepTracker.getNumbersBurnedKiloCal(month-1));
                System.out.println("Лучшая серия за месяц: " + stepTracker.getBestSteak(month-1));
            } else if (userChoice == 3) {
                System.out.println("Изменение целевого количества шагов. Введённое значение не должно быть отрицательным.");
                int newGoal = scanner.nextInt();
                stepTracker.changeStepsGoal(inputCheckNegative(newGoal, scanner));
                System.out.println("Целевое значение теперь такое: " + stepTracker.stepsGoal);
            } else if (userChoice == 4 ) {
                isOpen = false;
            } else {
                System.out.println("Данная команда не поддерживается");
            }

        }
    }

    public static void showMenu(){
        System.out.println("1. Ввести количество шагов за определённый день");
        System.out.println("2. Напечатать статистику за определённый месяц");
        System.out.println("3. Изменить цель по количеству шагов");
        System.out.println("4. Выйти из приложения");
    }
    public static void printMonths() {
        System.out.println("Выберите месяц: 1.Январь | 2.Февраль | 3.Март | 4.Апрель | 5.Май | 6.Июнь" +
                " | 7.Июль | 8.Август | 9.Сентябрь | 10.Октябрь | 11.Ноябрь | 12.Декабрь");
    }
    public static void printDays() {
        System.out.println("Выберите день: В месяце 30 дней, введите число (1-30), чтобы выбрать конкртеный день!");
    }
    public static void printWarning() {
        System.out.println("Вы ввели некорректное значение! Повторите ввод.");
    }
    public static int inputCheckMonth(int userInput, Scanner scanner) {
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

    public static int inputCheckDays(int userInput, Scanner scanner) {
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

    public static int inputCheckNegative(int userInput, Scanner scanner) {
        boolean isInCorrect = true;
        while (isInCorrect) {
            if (userInput < 0) {
                printWarning();
                userInput = scanner.nextInt();
            } else {isInCorrect = false;}
        }
        return userInput;
    }
}
