package cn.edu.tongji.healper.entity;

import javax.persistence.*;

@Entity
@Table(name = "client", schema = "healper", catalog = "")
public class ClientEntity implements User{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nickname", nullable = false, length = 64)
    private String nickname;
    @Basic
    @Column(name = "password", nullable = false, length = 32)
    private String password;
    @Basic
    @Column(name = "sex", nullable = true, length = 1)
    private String sex;
    @Basic
    @Column(name = "userphone", nullable = false, length = 11)
    private String userphone;
    @Basic
    @Column(name = "ex_consultant_id", nullable = true)
    private Integer exConsultantId;
    @Basic
    @Column(name = "age", nullable = true)
    private Integer age;
    @Basic
    @Column(name = "profile", nullable = true, length = 128)
    private String profile;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setBasicInfo(String nickname, String sex, Integer age) {
        if (nickname != null) {
            this.setNickname(nickname);
        }
        if (sex != null) {
            this.setSex(sex);
        }
        if (age != null) {
            this.setAge(age);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity client = (ClientEntity) o;

        if (id != client.id) return false;
        if (nickname != null ? !nickname.equals(client.nickname) : client.nickname != null) return false;
        if (password != null ? !password.equals(client.password) : client.password != null) return false;
        if (sex != null ? !sex.equals(client.sex) : client.sex != null) return false;
        if (userphone != null ? !userphone.equals(client.userphone) : client.userphone != null) return false;
        if (exConsultantId != null ? !exConsultantId.equals(client.exConsultantId) : client.exConsultantId != null)
            return false;
        if (age != null ? !age.equals(client.age) : client.age != null) return false;
        if (profile != null ? !profile.equals(client.profile) : client.profile != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (userphone != null ? userphone.hashCode() : 0);
        result = 31 * result + (exConsultantId != null ? exConsultantId.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        return result;
    }
}
