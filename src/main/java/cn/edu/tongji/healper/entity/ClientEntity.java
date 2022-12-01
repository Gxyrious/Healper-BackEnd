package cn.edu.tongji.healper.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "client", schema = "healper", catalog = "")
public class ClientEntity implements User{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nickname")
    private String nickname;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "sex")
    private String sex;
    @Basic
    @Column(name = "userphone")
    private String userphone;
    @Basic
    @Column(name = "ex_consultant_id")
    private Integer exConsultantId;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public Integer getExConsultantId() {
        return exConsultantId;
    }

    public void setExConsultantId(Integer exConsultantId) {
        this.exConsultantId = exConsultantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return id == that.id && Objects.equals(nickname, that.nickname) && Objects.equals(password, that.password) && Objects.equals(sex, that.sex) && Objects.equals(userphone, that.userphone) && Objects.equals(exConsultantId, that.exConsultantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, password, sex, userphone, exConsultantId);
    }
}