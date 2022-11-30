package cn.edu.tongji.healper.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "consult_history", schema = "healper", catalog = "")
public class ConsultHistoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "client_id")
    private int clientId;
    @Basic
    @Column(name = "consultant_id")
    private int consultantId;
    @Basic
    @Column(name = "end_time")
    private Integer endTime;
    @Basic
    @Column(name = "expense")
    private int expense;
    @Basic
    @Column(name = "start_time")
    private Integer startTime;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "advice")
    private String advice;
    @Basic
    @Column(name = "summary")
    private String summary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultHistoryEntity that = (ConsultHistoryEntity) o;
        return id == that.id && clientId == that.clientId && consultantId == that.consultantId && expense == that.expense && Objects.equals(endTime, that.endTime) && Objects.equals(startTime, that.startTime) && Objects.equals(status, that.status) && Objects.equals(advice, that.advice) && Objects.equals(summary, that.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, consultantId, endTime, expense, startTime, status, advice, summary);
    }
}
