package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.entity.ConsultantEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ConsultantRepository extends
        JpaRepository<ConsultantEntity, Integer>,
        JpaSpecificationExecutor<ConsultantEntity> {

    ConsultantEntity findConsultantEntityByUserphone(String userPhone);

    List<ConsultantEntity> findConsultantEntitiesByLabel(String label, Pageable pageable);
}
