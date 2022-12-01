package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.entity.ConsultHistoryEntity;
import cn.edu.tongji.healper.outdto.Archive;
import cn.edu.tongji.healper.po.ConsultOrder;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.el.ValueExpressionImpl;
import org.apache.el.util.Validation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Vector;

public interface ConsultHistoryRepository extends
        JpaRepository<ConsultHistoryEntity, Integer>,
        JpaSpecificationExecutor<ConsultHistoryRepository> {

    @Query("select new cn.edu.tongji.healper.po.ConsultOrder(che.startTime, che.endTime, che.consultantId, ce.realname, che.expense, che.status) from ConsultHistoryEntity che, ConsultantEntity ce where che.consultantId = ce.id and che.clientId = ?1")
    List<ConsultOrder> findConsultOrder(Integer clientId, Pageable pageable);

    @Query(value = "select new cn.edu.tongji.healper.outdto.Archive" +
            "(a.consultantId,a.endTime,a.expense,a.startTime,a.advice,a.summary,s.realname) " +
            "from ConsultHistoryEntity a inner join ConsultantEntity s " +
            "on a.consultantId=s.id where a.clientId=?1 order by a.endTime desc")
    List<Archive> getAllArchive(@Param("id") Integer id);

    @Query(value = "select new cn.edu.tongji.healper.outdto.Archive" +
            "(a.consultantId,a.endTime,a.expense,a.startTime,a.advice,a.summary,s.realname) " +
            "from ConsultHistoryEntity a inner join ConsultantEntity s " +
            "on a.consultantId=s.id where a.clientId=?1 order by a.endTime desc")
    List<Archive> getSomeArchive(@Param("id") Integer id, Pageable pageable);
}
