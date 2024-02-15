package com.codedifferently.workoutplanner.repository;

import com.codedifferently.workoutplanner.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
    Workout findByFirstNameAndLastName(String firstName, String lastName);
}
