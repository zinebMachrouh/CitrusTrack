package com.spring.CitrusTrack.entities.enums;

import java.time.LocalDate;
import java.time.Period;

public enum TreeStatus {
    YOUNG(2.5),
    MATURE(12),
    OLD(20),
    NON_PRODUCTIVE(0);

    private final double productivityPerSeason;

    TreeStatus(double productivityPerSeason) {
        this.productivityPerSeason = productivityPerSeason;
    }

    public double getAnnualProductivity() {
        int seasonsPerYear = 4;
        return this.productivityPerSeason * seasonsPerYear;
    }

    public static TreeStatus determineTreeStatus(LocalDate plantDate) {
        int age = calculateAge(plantDate);

        if (age < 3) {
            return YOUNG;
        } else if (age <= 10) {
            return MATURE;
        } else if (age <= 20) {
            return OLD;
        } else {
            return NON_PRODUCTIVE;
        }
    }

    public static int calculateAge(LocalDate plantDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(plantDate, currentDate);
        return period.getYears();
    }
}
