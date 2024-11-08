package lk.ijse.demo.dao;

import lk.ijse.demo.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldDAO extends JpaRepository<FieldEntity,String> {
}
