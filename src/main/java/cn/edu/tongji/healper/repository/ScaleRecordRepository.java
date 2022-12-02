package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.entity.ScaleRecordEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScaleRecordRepository extends JpaRepository<ScaleRecordEntity, Integer> {
    List<ScaleRecordEntity> findScaleRecordEntitiesByClientId(Integer clientId, Pageable pageable);
}
