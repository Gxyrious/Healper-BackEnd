package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import reactor.util.annotation.Nullable;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    @Nullable
    ClientEntity findClientEntityByUserphone(String userPhone);
}
