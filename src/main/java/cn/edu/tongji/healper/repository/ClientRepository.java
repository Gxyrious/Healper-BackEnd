package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.entity.ClientEntity;
import cn.edu.tongji.healper.po.ClientInfo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    ClientEntity findClientEntityByUserphone(String userPhone);

    @Query(value = "select new cn.edu.tongji.healper.po.ClientInfo" +
            "(client.id,client.nickname,client.sex,client.userphone,client.exConsultantId,client.age,client.profile)" +
            " from ClientEntity client where client.id=?1")
    ClientInfo findClientInfoById(@Param("id") Integer id);

    @Query(value = "select client.password from ClientEntity client where client.id = ?1")
    String findPasswordById(@Param("id") Integer id);

}
