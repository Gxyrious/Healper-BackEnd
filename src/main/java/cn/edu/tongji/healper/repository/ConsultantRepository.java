package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.model.ConsultantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultantRepository extends JpaRepository<ConsultantEntity, Integer> {

    ConsultantEntity findConsultantEntityByUserphone(String userPhone);
}
