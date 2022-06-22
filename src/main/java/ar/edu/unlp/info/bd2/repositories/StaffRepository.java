package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Nurse;
import ar.edu.unlp.info.bd2.model.Staff;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StaffRepository  extends CrudRepository<Staff, Long> {

    @Query("FROM Staff e WHERE e.fullname LIKE ?1")
    List<Staff> getStaffWithName(String name);

    @Query("SELECT area FROM SupportStaff s GROUP BY s.area ORDER BY COUNT(*) ASC")
    String getLessEmployeesSupportStaffArea();

}
