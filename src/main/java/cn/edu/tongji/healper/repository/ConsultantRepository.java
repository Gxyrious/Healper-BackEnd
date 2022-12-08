package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.entity.ConsultantEntity;
import cn.edu.tongji.healper.outdto.ConsultantInfo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsultantRepository extends
        JpaRepository<ConsultantEntity, Integer>,
        JpaSpecificationExecutor<ConsultantEntity> {

    ConsultantEntity findConsultantEntityByUserphone(String userPhone);

    @Query(value = "select new cn.edu.tongji.healper.outdto.ConsultantInfo(consultant.id, " +
                "consultant.qrCodeLink, consultant.realname, consultant.sex, consultant.userphone, " +
                "consultant.age, consultant.expense, consultant.label, consultant.profile) " +
            "from ConsultantEntity consultant where consultant.label like %?1%")
    List<ConsultantInfo> findConsultantsByLabel(String label, Pageable pageable);

    @Query(value = "select new cn.edu.tongji.healper.outdto.ConsultantInfo" +
                "(consultant.id, consultant.qrCodeLink, consultant.realname, consultant.sex," +
                "consultant.userphone, consultant.age, consultant.expense, consultant.label, consultant.profile)" +
            " from ConsultantEntity consultant where consultant.id=?1")
    ConsultantInfo findConsultantInfoById(@Param("id") Integer id);

    @Query(value = "select consultant.password from ConsultantEntity consultant where consultant.id = ?1")
    String findPasswordById(@Param("id") Integer id);

}
