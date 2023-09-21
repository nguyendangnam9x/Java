package learning.jpahibernate.learningjpahibernate.course.springdatajpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.jpahibernate.learningjpahibernate.course.Course;

public interface CourseSpringDataJparepository extends JpaRepository<Course, Long> {
	List<Course> findByAuthor(String author);
	List<Course> findByName(String name);
}
