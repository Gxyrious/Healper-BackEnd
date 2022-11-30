package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.model.PsychologyScaleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PsychologyScaleRepository extends
        JpaRepository<PsychologyScaleEntity, Integer>,
        JpaSpecificationExecutor<PsychologyScaleEntity> {

    @Query("select scale from PsychologyScaleEntity scale")
    List<PsychologyScaleEntity> findScales(Pageable pageable);

    PsychologyScaleEntity findPsychologyScaleEntityById(Integer scaleId);

}
