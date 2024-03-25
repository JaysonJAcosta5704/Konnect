package onetomany.userLogIn;


import org.springframework.data.jpa.repository.JpaRepository;
public interface userLoginRepository  extends JpaRepository<userLogin, Long>{


    userLogin findById(int id);
    void deleteById(int id);
    userLogin findByEmail(String email);
}
