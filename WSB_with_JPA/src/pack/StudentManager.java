package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


@ManagedBean//register the class student as JSF resource
@SessionScoped // creates an instance of Student for each user request
public class StudentManager {
	private List<Student> students;
	
	public StudentManager() {
		students = new ArrayList<Student>();
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	// Calls the getStudents method of StudentDbUtil
	public void loadStudents() throws Exception {
		students = StudentDbUtil.getStudents();
	}
	
	// Uses the addStudent method of StudentDbUtil
	public String addStudent(Student stu) throws Exception {
		StudentDbUtil.addStudent(stu);
		return "List-students";
	}
	
	// uses the fetchStudent method of StudentDbUtil
	public String loadStudent(int id) throws Exception {
		Student theStudent = StudentDbUtil.fetchStudent(id);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
		Map<String, Object> requestMap = externalContext.getRequestMap(); 
		requestMap.put("student", theStudent); 
		return "Edit-student";
	}
	
	// uses the updateStudent method of StudentDbUtil
	public String updateStudent(Student stu) {
		StudentDbUtil.updateStudent(stu);
		return "List-students";
	}
	
	// uses the deleteStudent method of StudentDbUtil
	public String deleteStudent(int id) {
		StudentDbUtil.deleteStudent(id);
		return "List-students";
	}
}