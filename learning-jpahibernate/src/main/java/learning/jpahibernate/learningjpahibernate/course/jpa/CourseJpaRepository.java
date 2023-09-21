package learning.jpahibernate.learningjpahibernate.course.jpa;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import learning.jpahibernate.learningjpahibernate.course.Course;

@Repository
@Transactional
public class CourseJpaRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public void insert(Course course) {
		entityManager.merge(course);
	}
	
	public Course findById(long id) {
		return entityManager.find(Course.class,  id);
	}
	
	public void delete(long id) {
		Course course = findById(id);
		entityManager.remove(course);
	}
}
