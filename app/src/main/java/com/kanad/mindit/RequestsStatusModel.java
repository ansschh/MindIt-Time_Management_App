package com.kanad.mindit;

public class RequestsStatusModel {
    String title;
    String description;
    String timerequired;
    String activitytype;
    String deadline;
    String remindme;
    String typeoftask;
    String goal;
    String note;
    String task_id;
    String deadline1;

    public RequestsStatusModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimerequired() {
        return timerequired;
    }

    public void setTimerequired(String timerequired) {
        this.timerequired = timerequired;
    }

    public String getActivitytype() {
        return activitytype;
    }

    public void setActivitytype(String activitytype) {
        this.activitytype = activitytype;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getRemindme() {
        return remindme;
    }

    public void setRemindme(String remindme) {
        this.remindme = remindme;
    }

    public String getTypeoftask() {
        return typeoftask;
    }

    public void setTypeoftask(String typeoftask) {
        this.typeoftask = typeoftask;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getDeadline1() {
        return deadline1;
    }

    public void setDeadline1(String deadline1) {
        this.deadline1 = deadline1;
    }

    public RequestsStatusModel(String title, String description, String timerequired, String activitytype, String deadline, String remindme, String typeoftask, String goal, String note, String task_id, String deadline1) {
        this.title = title;
        this.description = description;
        this.timerequired = timerequired;
        this.activitytype = activitytype;
        this.deadline = deadline;
        this.remindme = remindme;
        this.typeoftask = typeoftask;
        this.goal = goal;
        this.note = note;
        this.task_id = task_id;
        this.deadline1 = deadline1;
    }
}
