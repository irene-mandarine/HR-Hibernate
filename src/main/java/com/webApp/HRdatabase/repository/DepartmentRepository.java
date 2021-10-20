package com.webApp.HRdatabase.repository;

import com.webApp.HRdatabase.data.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Department> getDepartments() {
        String query = "FROM Department";
        Session session = sessionFactory.openSession();
        List<Department> list = session.createQuery(query).list();
        session.close();
        return list;
    }

    public Optional<Department> getDepartment(Long id) {
        String query = "FROM Department D WHERE D.departmentId =" + id;
        Session session = sessionFactory.openSession();
        Department department = (Department) session.createQuery(query).getSingleResult();
        session.close();
        return Optional.of(department);
    }

    public Department addOrUpdateDepartment(Department department) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(department);
        transaction.commit();
        session.close();
        return department;
    }
}
