import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isOpen = true;
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
        int userChoice;

        while(isOpen) {
            showMenu();
            userChoice = stepTracker.chooseTheMenuItem(scanner);
            if (userChoice == 1) {
                stepTracker.addCountStepsPerDay(scanner);
            } else if (userChoice == 2) {
                stepTracker.printMonthStatistic(scanner);
            } else if (userChoice == 3) {
                stepTracker.changeGoal(scanner);
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
}
