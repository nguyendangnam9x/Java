package learning.jpahibernate.learningjpahibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import learning.jpahibernate.learningjpahibernate.course.Course;
import learning.jpahibernate.learningjpahibernate.course.jpa.CourseJpaRepository;
import learning.jpahibernate.learningjpahibernate.course.springdatajpa.CourseSpringDataJparepository;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {
	@Autowired
//	private CourseJdbcRepository repository;
//	private CourseJpaRepository repository;
	private CourseSpringDataJparepository repository;
	@Override
	public void run(String... args) throws Exception {
//		repository.insert(new Course(1, "Dang Nam", "Dang Nam auto"));
//		repository.insert(new Course(2, "Dang Nam1", "Dang Nam auto1"));
//		repository.insert(new Course(3, "Dang Nam2", "Dang Nam auto2"));
//		
//		repository.delete(2);
//		
//		System.out.println(repository.findById(3));
		
		repository.save(new Course(1, "Dang Nam", "Dang Nam auto"));
		repository.save(new Course(2, "Dang Nam1", "Dang Nam auto1"));
		repository.save(new Course(3, "Dang Nam2", "Dang Nam auto2"));
		
		repository.deleteById((long) 1);
		System.out.println("All: " + repository.findAll());
		System.out.println("Count: " + repository.count());
		System.out.println("FindByAuthor: " + repository.findByAuthor("Dang Nam auto1"));
		System.out.println("FindByName: " + repository.findByName("Dang Nam2"));
		System.out.println(repository.findById(3l));
	}
	
}
