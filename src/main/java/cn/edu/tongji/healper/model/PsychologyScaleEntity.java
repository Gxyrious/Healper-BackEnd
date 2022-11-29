package cn.edu.tongji.healper.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "psychology_scale", schema = "healper", catalog = "")
public class PsychologyScaleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "ques_num")
    private int quesNum;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "name")
    private String name;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PsychologyScaleEntity that = (PsychologyScaleEntity) o;
        return id == that.id && quesNum == that.quesNum && Objects.equals(content, that.content) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quesNum, content, name);
    }
}
