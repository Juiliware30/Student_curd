
package com.practise.controlller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practise.entity.Student;

@RestController
public class student_controller 
{
	    @Autowired
	SessionFactory factory;
		
	
	@PostMapping("savestudent")
	public String savestudent(@RequestBody Student student)
	{
		Session session=factory.openSession();
		
		Transaction tx=session.beginTransaction();
		
				session.save(student);
				
		tx.commit();
		
		return "record saved";
	}
	
	
	
	@PutMapping("updatestudent")
	public String updatestudent(@RequestBody Student student)
	{
		Session session=factory.openSession();
		
		Transaction tx=session.beginTransaction();
		
				session.update(student);
				
		tx.commit();
		
		return "record updated";
	}
	
	
	
	
	@DeleteMapping("deletestudent/{username}")
	public String deletestudent(@PathVariable String username)
	{
	
		Session session=factory.openSession();
	
		Student userfromdb=session.load(Student.class, username);
		
		Transaction tx=session.beginTransaction();
		
				session.delete(userfromdb);
				
		tx.commit();
		
		return "record deleted";
	}
	
	
		
	
	
	@GetMapping("getstudent/{username}")
	public Student getstudent(@PathVariable String username)
	{
		Session session=factory.openSession();
		
		return session.get(Student.class, username);
	}

	
	@GetMapping("getAllstudent")
	public List<Student> getAllstudent()
	{
		Session session=factory.openSession();
		
		return session.createCriteria(Student.class).list();
		
	}
	
}

