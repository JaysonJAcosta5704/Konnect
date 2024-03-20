package onetomany.adminUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface adminActivityReportRepository  extends JpaRepository<adminUser, Long> {


    adminUser findById(int id);
}
