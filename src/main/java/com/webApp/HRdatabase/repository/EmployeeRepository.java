package com.webApp.HRdatabase.repository;

import com.webApp.HRdatabase.data.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Employee> getEmployees() {
        String query = "FROM Employee";
        Session session = sessionFactory.openSession();
        List<Employee> list = session.createQuery(query).list();
        session.close();
        return list;
    }

    public Optional<Employee> execute(Long id) {
        String query = "FROM Employee E WHERE E.id =" + id;
        Employee employee = execute(query);
        return Optional.of(employee);
    }

    public Employee addOrUpdateEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(employee);
        transaction.commit();
        session.close();
        return employee;
    }

    public Optional<Employee> findEmployeeByPhoneNumber(String phoneNumber) {
        String query = "FROM Employee E WHERE E.phoneNumber =" + phoneNumber;
        Employee employee = execute(query);
        return Optional.of(employee);
    }

    public Optional<Employee> findEmployeeByEmail(String email) {
        String query = "FROM Employee E WHERE E.email =" + email;
        Employee employee = execute(query);
        return Optional.of(employee);
    }

    private Employee execute(String query) {
        Session session = sessionFactory.openSession();
        Employee employee = (Employee) session.createQuery(query).getSingleResult();
        session.close();
        return employee;
    }
}
