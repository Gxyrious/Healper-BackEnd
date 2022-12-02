package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.entity.ConsultHistoryEntity;
import cn.edu.tongji.healper.outdto.Archive;
import cn.edu.tongji.healper.po.ConsultOrder;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ConsultHistoryRepository extends
        JpaRepository<ConsultHistoryEntity, Integer>,
        JpaSpecificationExecutor<ConsultHistoryEntity> {

    @Query("select new " +
            "cn.edu.tongji.healper.po.ConsultOrder(" +
            "che.id, che.startTime, che.endTime, " +
            "che.consultantId, ce.realname, che.expense, che.status" +
            ") from ConsultHistoryEntity che, ConsultantEntity ce " +
            "where che.consultantId = ce.id and che.clientId = ?1"
    )
    List<ConsultOrder> findConsultOrder(Integer clientId, Pageable pageable);

    @Query("select ce.qrCodeLink " +
            "from ConsultHistoryEntity che, ConsultantEntity ce " +
            "where che.consultantId = ce.id and che.id = ?1"
    )
    String findQrCodeByHistoryId(Integer historyId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value =
            "update consult_history as ch set ch.status = ?2 where ch.id = ?1"
    )
    Integer updateHistoryStatusById(Integer historyId, String status);


    @Query(value = "select count(a.id) from ConsultHistoryEntity a where a.clientId=?1")
    Integer getAllArchive(@Param("id") Integer id);

    @Query(value = "select new cn.edu.tongji.healper.outdto.Archive" +
            "(a.consultantId,a.endTime,a.expense,a.startTime,a.advice,a.summary,s.realname) " +
            "from ConsultHistoryEntity a inner join ConsultantEntity s " +
            "on a.consultantId=s.id where a.clientId=?1 and a.status='f' order by a.endTime desc")
    List<Archive> getSomeArchive(@Param("id") Integer id, Pageable pageable);

    ConsultHistoryEntity getConsultHistoryEntityById(Integer historyId);
}
