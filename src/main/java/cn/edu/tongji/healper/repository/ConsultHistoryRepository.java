package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.entity.ConsultHistoryEntity;
import cn.edu.tongji.healper.po.ConsultOrder;
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
}
