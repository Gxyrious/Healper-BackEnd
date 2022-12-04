package cn.edu.tongji.healper.entity;

import javax.persistence.*;

@Entity
@Table(name = "consult_history", schema = "healper", catalog = "")
public class ConsultHistoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "client_id", nullable = false)
    private int clientId;
    @Basic
    @Column(name = "consultant_id", nullable = false)
    private int consultantId;
    @Basic
    @Column(name = "end_time", nullable = true)
    private Long endTime;
    @Basic
    @Column(name = "expense", nullable = false)
    private int expense;
    @Basic
    @Column(name = "start_time", nullable = true)
    private Long startTime;
    @Basic
    @Column(name = "status", nullable = false, length = 1)
    private String status;
    @Basic
    @Column(name = "advice", nullable = true, length = 1024)
    private String advice;
    @Basic
    @Column(name = "summary", nullable = true, length = 1024)
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

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
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

        if (id != that.id) return false;
        if (clientId != that.clientId) return false;
        if (consultantId != that.consultantId) return false;
        if (expense != that.expense) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (advice != null ? !advice.equals(that.advice) : that.advice != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + clientId;
        result = 31 * result + consultantId;
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + expense;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (advice != null ? advice.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        return result;
    }
}
