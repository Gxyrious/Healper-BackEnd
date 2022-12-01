package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.entity.ConsultHistoryEntity;
import cn.edu.tongji.healper.po.ConsultOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsultHistoryRepository extends
        JpaRepository<ConsultHistoryEntity, Integer>,
        JpaSpecificationExecutor<ConsultHistoryRepository> {

    @Query("select new cn.edu.tongji.healper.po.ConsultOrder(che.startTime, che.endTime, che.consultantId, ce.realname, che.expense, che.status) from ConsultHistoryEntity che, ConsultantEntity ce where che.consultantId = ce.id and che.clientId = ?1")
    List<ConsultOrder> findConsultOrder(Integer clientId, Pageable pageable);
}
