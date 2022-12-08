package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.entity.PsychologyScaleEntity;
import cn.edu.tongji.healper.outdto.ScaleInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PsyScaleRepository extends
        JpaRepository<PsychologyScaleEntity, Integer>,
        JpaSpecificationExecutor<PsychologyScaleEntity> {

    @Query("select new cn.edu.tongji.healper.outdto.ScaleInfo(scale.id, scale.quesNum, scale.name, scale.image, scale.summary) from PsychologyScaleEntity scale")
    List<ScaleInfo> findScales(Pageable pageable);

    PsychologyScaleEntity findPsychologyScaleEntityById(Integer scaleId);

}
