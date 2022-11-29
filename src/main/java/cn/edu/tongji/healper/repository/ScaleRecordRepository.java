package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.model.ScaleRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScaleRecordRepository extends JpaRepository<ScaleRecordEntity, Integer> {
    List<ScaleRecordEntity> findScaleRecordEntitiesByClientId(Integer client_id);

    void deleteById(Integer id);
}
