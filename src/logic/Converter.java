package logic;

public class Converter {

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
