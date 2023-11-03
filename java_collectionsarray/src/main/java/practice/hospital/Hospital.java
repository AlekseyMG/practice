package practice.hospital;

import java.text.DecimalFormat;

public class Hospital {

    public static float[] generatePatientsTemperatures(int patientsCount) {
        float[] patientsTemperatures = new float[patientsCount];
        DecimalFormat decimalformat = new DecimalFormat("#.#");
        double temperatures;
        String stringTemperatures;
        for (int i = 0; i < patientsTemperatures.length; i++) {
            temperatures = Math.random() * 8 + 32;
            stringTemperatures = decimalformat.format(temperatures);
            patientsTemperatures[i] =  Float.valueOf(stringTemperatures.replaceAll(",","."));
        }
        return patientsTemperatures;
    }

    public static String getReport(float[] temperatureData) {
        /*
        TODO: Напишите код, который выводит среднюю температуру по больнице,количество здоровых пациентов,
            а также температуры всех пациентов.
            Округлите среднюю температуру с помощью Math.round до 2 знаков после запятой,
            а температуры каждого пациента до 1 знака после запятой
        */
        DecimalFormat decimalformat = new DecimalFormat("#.##");
        float temperatures = 0;
        StringBuilder stringTemperatures = new StringBuilder();
        int healthyPatients = 0;
        for (int i = 0; i < temperatureData.length; i++) {
            if (i < temperatureData.length - 1) {
                stringTemperatures.append(temperatureData[i]).append(" ");
            } else {
                stringTemperatures.append(temperatureData[i]);
            }
            temperatures += temperatureData[i];
            if ((int)(temperatureData[i] * 10) >= 362 && (int)(temperatureData[i] * 10) <= 369) {
                healthyPatients++;
            }
        }
        temperatures = temperatures / temperatureData.length;
        String report =
            "Температуры пациентов: " + stringTemperatures +
            "\nСредняя температура: " + decimalformat.format(temperatures) +
            "\nКоличество здоровых: " + healthyPatients;
        return report;
    }
}
