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
    private Long endTime;
    @Basic
    @Column(name = "expense")
    private double expense;
    @Basic
    @Column(name = "start_time")
    private Long startTime;
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

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
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
        return id == that.id && clientId == that.clientId && consultantId == that.consultantId && Double.compare(that.expense, expense) == 0 && Objects.equals(endTime, that.endTime) && Objects.equals(startTime, that.startTime) && Objects.equals(status, that.status) && Objects.equals(advice, that.advice) && Objects.equals(summary, that.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, consultantId, endTime, expense, startTime, status, advice, summary);
    }
}
