package pack;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;




@ApplicationScoped
@ManagedBean(eager=true)

public class StudentDbUtil {
	
	
	
	private static final String PERSISTENCE_UNIT_NAME = "JSFJPA";
	private static EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	private static EntityTransaction transaction = em.getTransaction();
	
	@SuppressWarnings("unchecked")
	public static List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		
			em.getEntityManagerFactory().getCache().evictAll();
			students = em.createQuery("SELECT s FROM Student s").getResultList();
			
			
			
			return students;
	}
		
		
	
	public static void addStudent(Student student){
		
			em.getTransaction().begin();
			Student newstudent = student;
			newstudent.setId(student.getId());
			newstudent.setFirstname(student.getFirstname());
			newstudent.setLastname(student.getLastname());
			newstudent.setEmail(student.getEmail());
			em.persist(newstudent);
			em.getTransaction().commit();
			
			 
	}
	
	public static Student fetchStudent(int id){
		Student student = new Student();
		student = em.find(Student.class, id);
		return student;

	}
	
	public static void updateStudent(Student student) {
		
		Student editedstudent = em.find(Student.class, student.getId());
		em.getTransaction().begin();
		editedstudent.setFirstname(student.getFirstname());
		editedstudent.setLastname(student.getLastname());
		editedstudent.setEmail(student.getEmail());
		em.getTransaction().commit();
	}
	
	public static void deleteStudent(int id){
		
		em.getTransaction().begin();
		Student student = new Student();
		student = fetchStudent(id);
		em.remove(em.merge(student));
		em.getTransaction().commit();

	}
	
	
	
}