package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer>, CrudRepository<ClientEntity, Integer> {

    ClientEntity findClientEntityByUserphone(String userPhone);

}
