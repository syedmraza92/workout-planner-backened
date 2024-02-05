package com.codedifferently.workoutplanner.service;

import com.codedifferently.workoutplanner.model.Workout;
import com.codedifferently.workoutplanner.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepo;

    @Override
    public List<Workout> getAllWorkouts() {
        return workoutRepo.findAll();
    }

    @Override
    public Workout getWorkoutById(Integer id) {
        return workoutRepo.findById(id).orElse(null);
    }

    @Override
    public Workout createWorkout(Workout workout) {
        return workoutRepo.save(workout);
    }

    @Override
    public Workout updateWorkout(Integer id, Workout workout) {
        Workout oldWorkout = workoutRepo.findById(id).orElse(null);

        if (oldWorkout != null) {
            // Update the properties of oldWorkout with the new values from workout
            oldWorkout.setFirstName(workout.getFirstName());
            oldWorkout.setLastName(workout.getLastName());
            oldWorkout.setAge(workout.getAge());
            oldWorkout.setWeight(workout.getWeight());
            oldWorkout.setHeight(workout.getHeight());
            oldWorkout.setDesiredWeight(workout.getDesiredWeight());

            return workoutRepo.save(oldWorkout);
        } else {
            return null; // Handle the case where the workout with the given id is not found
        }
    }

    @Override
    public boolean deleteWorkout(Integer id) {
        Workout workout = workoutRepo.findById(id).orElse(null);

        if (workout != null) {
            workoutRepo.delete(workout);
            return true; // Deletion successful
        } else {
            return false; // Workout with the given id not found
        }
    }
}
