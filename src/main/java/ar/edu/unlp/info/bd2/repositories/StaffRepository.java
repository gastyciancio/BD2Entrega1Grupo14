package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Staff;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StaffRepository  extends CrudRepository<Staff, Long> {

    List<Staff> findByFullnameContaining(String name);

    @Query("SELECT area FROM SupportStaff s GROUP BY s.area ORDER BY COUNT(*) ASC")
    List<String> getLessEmployeesSupportStaffArea(Pageable pageable);

}
