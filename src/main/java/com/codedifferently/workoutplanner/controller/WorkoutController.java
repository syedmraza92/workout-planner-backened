package com.codedifferently.workoutplanner.controller;

import com.codedifferently.workoutplanner.model.Workout;
import com.codedifferently.workoutplanner.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping("/")
    public ResponseEntity<?> getAllWorkouts() {
        return new ResponseEntity<>(workoutService.getAllWorkouts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkoutById(@PathVariable Integer id) {
        Workout workout = workoutService.getWorkoutById(id);
        if (workout != null) {
            return new ResponseEntity<>(workout, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createWorkout")
    public ResponseEntity<?> createWorkout(@RequestBody Workout workout) {
        return new ResponseEntity<>(workoutService.createWorkout(workout), HttpStatus.CREATED);
    }

    @PostMapping("/updateWorkout/{id}")
    public ResponseEntity<?> updateWorkout(@PathVariable Integer id, @RequestBody Workout workout) {
        Workout updatedWorkout = workoutService.updateWorkout(id, workout);
        if (updatedWorkout != null) {
            return new ResponseEntity<>(updatedWorkout, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/deleteWorkout/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Integer id) {
        if (workoutService.deleteWorkout(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

