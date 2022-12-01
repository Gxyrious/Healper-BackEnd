package cn.edu.tongji.healper.po;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class ConsultantInfo {
    Integer id;
    String qrCodeLink;
    String realname;
    String sex;
    String userphone;
    Integer age;
    Short expense;
    String label;
    String profile;

    public ConsultantInfo(Integer id, String qrCodeLink, String realname,
                          String sex, String userphone, Integer age,
                          Short expense, String label, String profile) {
        this.id = id;
        this.qrCodeLink = qrCodeLink;
        this.realname = realname;
        this.sex = sex;
        this.userphone = userphone;
        this.age = age;
        this.expense = expense;
        this.label = label;
        this.profile = profile;
    }
}
