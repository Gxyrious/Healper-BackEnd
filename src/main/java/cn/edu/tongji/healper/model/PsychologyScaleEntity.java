package cn.edu.tongji.healper.model;

import javax.persistence.*;

@Entity
@Table(name = "psychology_scale", schema = "healper", catalog = "")
public class PsychologyScaleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "ques_num", nullable = false)
    private int quesNum;
    @Basic
    @Column(name = "content_link", nullable = false, length = 128)
    private String contentLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuesNum() {
        return quesNum;
    }

    public void setQuesNum(int quesNum) {
        this.quesNum = quesNum;
    }

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PsychologyScaleEntity that = (PsychologyScaleEntity) o;

        if (id != that.id) return false;
        if (quesNum != that.quesNum) return false;
        if (contentLink != null ? !contentLink.equals(that.contentLink) : that.contentLink != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + quesNum;
        result = 31 * result + (contentLink != null ? contentLink.hashCode() : 0);
        return result;
    }
}
