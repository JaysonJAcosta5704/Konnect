package onetomany.Reports;

import org.springframework.data.jpa.repository.JpaRepository;
public interface ReportsRepository extends JpaRepository<Reports, Long>{
    Reports findById(int id);


}
