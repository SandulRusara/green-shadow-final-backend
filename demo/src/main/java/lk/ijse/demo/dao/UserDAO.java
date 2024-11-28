package lk.ijse.demo.dao;

import lk.ijse.demo.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDAO extends JpaRepository<UserEntity,String> {
    @Query(value = "SELECT * FROM user WHERE user_id = (SELECT user_id FROM user ORDER BY CAST(SUBSTRING(user_id, 6) AS UNSIGNED) DESC LIMIT 1);", nativeQuery = true)
    UserEntity findLastRowNative();
    Optional<UserEntity> findByEmail(String email);
}