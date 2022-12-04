package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.entity.ScaleRecordEntity;
import cn.edu.tongji.healper.po.ScaleRecordInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScaleRecordRepository extends JpaRepository<ScaleRecordEntity, Integer> {

    @Query(value = "select new cn.edu.tongji.healper.po.ScaleRecordInfo(s.id ,s.endTime," +
            "s.isHidden, s.scaleId, s.record, p.name) " +
            "from ScaleRecordEntity s inner join PsychologyScaleEntity p " +
            "on s.scaleId=p.id where s.clientId=?1 order by s.endTime desc")
    List<ScaleRecordInfo> findScaleRecordInfoByClientId(Integer clientId, Pageable pageable);
    Integer countScaleRecordEntitiesByClientId(Integer clientId);
}
