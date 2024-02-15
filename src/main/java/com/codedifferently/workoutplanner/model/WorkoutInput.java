package com.codedifferently.workoutplanner.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class WorkoutInput {

        // Fields representing the data to be transferred
        private String firstName;
        private String lastName;
        private Integer age;
        private double weight;
        private double height;
        private double desiredWeight;
    }

