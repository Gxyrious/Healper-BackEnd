package cn.edu.tongji.healper.entity;

import javax.persistence.*;

@Entity
@Table(name = "consultant", schema = "healper", catalog = "")
public class ConsultantEntity implements User{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "password", nullable = false, length = 32)
    private String password;
    @Basic
    @Column(name = "qr_code_link", nullable = true, length = 128)
    private String qrCodeLink;
    @Basic
    @Column(name = "realname", nullable = false, length = 16)
    private String realname;
    @Basic
    @Column(name = "sex", nullable = true, length = 1)
    private String sex;
    @Basic
    @Column(name = "userphone", nullable = false, length = 11)
    private String userphone;
    @Basic
    @Column(name = "age", nullable = true)
    private Integer age;
    @Basic
    @Column(name = "expense", nullable = true)
    private Short expense;
    @Basic
    @Column(name = "label", nullable = true, length = 64)
    private String label;
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

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQrCodeLink() {
        return qrCodeLink;
    }

    public void setQrCodeLink(String qrCodeLink) {
        this.qrCodeLink = qrCodeLink;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Short getExpense() {
        return expense;
    }

    public void setExpense(Short expense) {
        this.expense = expense;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsultantEntity that = (ConsultantEntity) o;

        if (id != that.id) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (qrCodeLink != null ? !qrCodeLink.equals(that.qrCodeLink) : that.qrCodeLink != null) return false;
        if (realname != null ? !realname.equals(that.realname) : that.realname != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (userphone != null ? !userphone.equals(that.userphone) : that.userphone != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (expense != null ? !expense.equals(that.expense) : that.expense != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (profile != null ? !profile.equals(that.profile) : that.profile != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (qrCodeLink != null ? qrCodeLink.hashCode() : 0);
        result = 31 * result + (realname != null ? realname.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (userphone != null ? userphone.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (expense != null ? expense.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        return result;
    }

    public void setBasicInfo(String realname, String sex, Integer age, String profile, Short expense) {
        if (realname != null) {
            this.setRealname(realname);
        }
        if (sex != null) {
            this.setSex(sex);
        }
        if (age != null) {
            this.setAge(age);
        }
        if (profile != null) {
            this.setProfile(profile);
        }
        if (expense != null) {
            this.setExpense(expense);
        }
    }
}
