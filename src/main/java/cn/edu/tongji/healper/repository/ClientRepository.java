package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    ClientEntity findClientEntityByUserphone(String userPhone);

}
