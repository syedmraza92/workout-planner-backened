package com.codedifferently.workoutplanner.service;

import com.codedifferently.workoutplanner.model.WorkoutInput;
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

    @Override
    public String getWorkoutRecommendation(WorkoutInput workoutInput) {
        try {
            // Create a basic workout plan based on user information
            String workoutPlan = createBasicWorkoutPlan(workoutInput);

            // Save the workout recommendation to the database
            saveWorkoutRecommendationToDatabase(workoutInput, workoutPlan);

            return "Recommended workout plan:\n" + workoutPlan;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating workout plan";
        }
    }

    private void saveWorkoutRecommendationToDatabase(WorkoutInput workoutInput, String workoutPlan) {
        // Check if a recommendation already exists for the user
        Workout existingWorkout = workoutRepo.findByFirstNameAndLastName(
                workoutInput.getFirstName(), workoutInput.getLastName());

        if (existingWorkout != null) {
            // Update the existing recommendation instead of creating a new one
            existingWorkout.setWorkoutRecommendation(workoutPlan);
            workoutRepo.save(existingWorkout);
        } else {
            Workout workout = new Workout();
            workout.setFirstName(workoutInput.getFirstName());
            workout.setLastName(workoutInput.getLastName());
            workout.setAge(workoutInput.getAge());
            workout.setWeight(workoutInput.getWeight());
            workout.setHeight(workoutInput.getHeight());
            workout.setDesiredWeight(workoutInput.getDesiredWeight());
            workout.setWorkoutRecommendation(workoutPlan);

            // Save the workout entity to the database
            workoutRepo.save(workout);
        }
    }

    private String createBasicWorkoutPlan(WorkoutInput workoutInput) {

        int age = workoutInput.getAge();
        double weight = workoutInput.getWeight();
        double height = workoutInput.getHeight();
        double desiredWeight = workoutInput.getDesiredWeight();


        StringBuilder workoutPlan = new StringBuilder();
        workoutPlan.append("Here's your personalized workout plan:\n");

        if (age < 30) {
            workoutPlan.append("- Start with cardio exercises like running or cycling.\n");
            workoutPlan.append("- Include strength training exercises for overall fitness.\n");
        } else {
            workoutPlan.append("- Focus on a mix of cardio and strength training exercises.\n");
            workoutPlan.append("- Consider incorporating flexibility exercises like yoga.\n");
        }

        if (weight > desiredWeight) {
            workoutPlan.append("- Include a calorie deficit diet to help with weight loss.\n");
        } else if (weight < desiredWeight) {
            workoutPlan.append("- Ensure a balanced diet with sufficient protein for muscle growth.\n");
        } else {
            workoutPlan.append("- Maintain a balanced diet to support overall health.\n");
        }

        return workoutPlan.toString();
    }

}


