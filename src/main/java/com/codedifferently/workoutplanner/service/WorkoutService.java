package com.codedifferently.workoutplanner.service;

import com.codedifferently.workoutplanner.model.Workout;

import java.util.List;

public interface WorkoutService {

    List<Workout> getAllWorkouts();

    Workout getWorkoutById(Integer id);

    Workout createWorkout(Workout workout);

    Workout updateWorkout(Integer id, Workout workout);

    boolean deleteWorkout(Integer id);
}
