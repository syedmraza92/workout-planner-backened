package com.codedifferently.workoutplanner;
import com.codedifferently.workoutplanner.model.Workout;
import com.codedifferently.workoutplanner.repository.WorkoutRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class WorkoutRepositoryTest {

	@Autowired
	private WorkoutRepository workoutRepository;

	@Test
	public void testSaveWorkout() {
		Workout workout = new Workout();
		workout.setFirstName("John");
		workout.setLastName("Doe");
		workout.setAge(25);
		workout.setWeight(70.5);
		workout.setHeight(175.0);
		workout.setDesiredWeight(65.0);
		workout.setWorkoutRecommendation("Sample recommendation");

		Workout savedWorkout = workoutRepository.save(workout);

		assertNotNull(savedWorkout.getId());
		assertEquals("John", savedWorkout.getFirstName());
		assertEquals("Doe", savedWorkout.getLastName());
		assertEquals(25, savedWorkout.getAge());
		assertEquals(70.5, savedWorkout.getWeight());
		assertEquals(175.0, savedWorkout.getHeight());
		assertEquals(65.0, savedWorkout.getDesiredWeight());
		assertEquals("Sample recommendation", savedWorkout.getWorkoutRecommendation());
	}
}
