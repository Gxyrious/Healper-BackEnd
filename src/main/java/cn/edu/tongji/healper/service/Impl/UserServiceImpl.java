package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.entity.ClientEntity;
import cn.edu.tongji.healper.entity.ConsultHistoryEntity;
import cn.edu.tongji.healper.entity.ConsultantEntity;
import cn.edu.tongji.healper.entity.User;
import cn.edu.tongji.healper.outdto.ConsultantInfoWithClient;
import cn.edu.tongji.healper.outdto.UserType;
import cn.edu.tongji.healper.po.ClientInfo;
import cn.edu.tongji.healper.po.ConsultantInfo;
import cn.edu.tongji.healper.repository.ClientRepository;
import cn.edu.tongji.healper.repository.ConsultHistoryRepository;
import cn.edu.tongji.healper.repository.ConsultantRepository;
import cn.edu.tongji.healper.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static cn.edu.tongji.healper.util.MD5Utils.stringToMD5;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private ClientRepository clientRepository;

    @Resource
    private ConsultantRepository consultantRepository;

    @Resource
    private ConsultHistoryRepository historyRepository;

    @Override
    public ClientEntity findClientEntityByUserPhone(String userPhone) {
        return clientRepository.findClientEntityByUserphone(userPhone);
    }

    @Override
    public ConsultantEntity findConsultantEntityByUserPhone(String userPhone) {
        return consultantRepository.findConsultantEntityByUserphone(userPhone);
    }

    @Override
    public User findUserByPhone(String phone) {
        // 先找Client
        ClientEntity client = findClientEntityByUserPhone(phone);
        if (client == null) {
            // 再找Consultant
            return findConsultantEntityByUserPhone(phone);
        } else {
            return client;
        }
    }

    @Override
    public List<ConsultantInfo> findConsultantsByLabel(String label, Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page - 1, size, Sort.Direction.ASC, "id");
        return consultantRepository.findConsultantsByLabel(label, pageRequest);
    }

    @Override
    public List<ConsultantInfoWithClient> findConsultantsWithClient(
            Integer clientId, String label, Integer page, Integer size
    ) {
        Pageable pageRequest = PageRequest.of(page - 1, size, Sort.Direction.ASC, "id");
        List<ConsultantInfo> consultants = consultantRepository.findConsultantsByLabel(label, pageRequest);
        List<ConsultantInfoWithClient> consultantsWithClient = new ArrayList<>();
        for (ConsultantInfo consultant : consultants) {
            ConsultantInfoWithClient newConsultantInfo = new ConsultantInfoWithClient();
            newConsultantInfo.setInfo(consultant);
            ConsultHistoryEntity entity = historyRepository
                    .findFirstByClientIdAndConsultantId(clientId, consultant.getId());
            if (entity != null) {
                newConsultantInfo.setIsAppointed(Boolean.TRUE);
            } else {
                newConsultantInfo.setIsAppointed(Boolean.FALSE);
            }
            consultantsWithClient.add(newConsultantInfo);
        }
        return consultantsWithClient;
    }

    @Override
    public ClientEntity addClientInfo(String nickname, String password, String userPhone, String sex) {
        ClientEntity newClient = new ClientEntity();
        newClient.setNickname(nickname);
        newClient.setPassword(stringToMD5(password));//密码进行md5加密
        newClient.setSex(sex);
        newClient.setUserphone(userPhone);
        return clientRepository.saveAndFlush(newClient);
    }

    @Override
    public void updateClientInfo(ClientInfo client) {
        ClientEntity updatedClient = clientRepository.findById(client.getId()).get();
        updatedClient.setBasicInfo(client.getNickname(), client.getSex(), client.getAge(), client.getProfile());
        clientRepository.save(updatedClient);
    }

    @Override
    public void updateConsultantInfo(ConsultantInfo consultant) {
        ConsultantEntity updatedConsultant = consultantRepository.findById(consultant.getId()).get();
        updatedConsultant.setBasicInfo(
                consultant.getRealname(), consultant.getSex(), consultant.getAge(),
                consultant.getProfile(), consultant.getExpense(), consultant.getLabel()
        );
        consultantRepository.save(updatedConsultant);
    }

    @Override
    public void updateConsultantQrCode(Integer id, String url) {
        ConsultantEntity updatedConsultant = consultantRepository.findById(id).get();
        updatedConsultant.setQrCodeLink(url);
        consultantRepository.save(updatedConsultant);
    }

    @Override
    public Boolean checkPasswdWithId(Integer id, UserType userType, String password) {
        String realPassword;
        if (userType == UserType.client) {
            realPassword = clientRepository.findPasswordById(id);
        } else if (userType == UserType.consultant) {
            realPassword = consultantRepository.findPasswordById(id);
        } else {
            throw new RuntimeException("UserType error!");
        }
        return realPassword != null && realPassword.equals(stringToMD5(password));
    }

    @Override
    public void updateUserPasswd(Integer id, UserType userType, String password) {
        if (userType == UserType.client) {
            ClientEntity updatedClient = clientRepository.findById(id).get();
            updatedClient.setPassword(stringToMD5(password));
            clientRepository.save(updatedClient);
        } else if (userType == UserType.consultant) {
            ConsultantEntity updatedConsultant = consultantRepository.findById(id).get();
            updatedConsultant.setPassword(stringToMD5(password));
            consultantRepository.save(updatedConsultant);
        } else {
            throw new RuntimeException("UserType error!");
        }
    }

    @Override
    public ClientInfo findClientInfoById(Integer id) {
        return clientRepository.findClientInfoById(id);
    }

    @Override
    public ConsultantInfo findConsultantInfoById(Integer id) {
        return consultantRepository.findConsultantInfoById(id);
    }

}
