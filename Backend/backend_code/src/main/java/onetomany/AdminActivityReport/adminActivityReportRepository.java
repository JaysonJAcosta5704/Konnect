package onetomany.AdminActivityReport;

import onetomany.adminUser.adminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface adminActivityReportRepository  extends JpaRepository<adminUser, Long> {


    adminUser findById(int id);
}
