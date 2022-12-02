package cn.edu.tongji.healper.po;

import lombok.Data;

@Data
public class ClientInfo {
    Integer id;
    String nickname;
    String sex;
    String userphone;
    Integer exConsultantId;
    Integer age;
    String profile;

    public ClientInfo(Integer id, String nickname, String sex,
                      String userphone, Integer exConsultantId,
                      Integer age, String profile) {
        this.id = id;
        this.nickname = nickname;
        this.sex = sex;
        this.userphone = userphone;
        this.exConsultantId = exConsultantId;
        this.age = age;
        this.profile = profile;
    }
}
