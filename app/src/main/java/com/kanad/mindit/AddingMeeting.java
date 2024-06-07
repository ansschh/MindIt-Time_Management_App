package com.kanad.mindit;

public class AddingMeeting {
    String title;
    String description;
    String timerequired;
    String activitytype;
    String deadline;
    String remindme;
    String meetinglink;
    String task_id;
    String deadline1;

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

    public String getMeetinglink() {
        return meetinglink;
    }

    public void setMeetinglink(String meetinglink) {
        this.meetinglink = meetinglink;
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

    public String getRelatedActivity() {
        return relatedActivity;
    }

    public void setRelatedActivity(String relatedActivity) {
        this.relatedActivity = relatedActivity;
    }

    String relatedActivity;

    public AddingMeeting(String title, String description, String timerequired, String activitytype, String deadline, String remindme, String meetinglink, String task_id, String deadline1, String relatedActivity) {
        this.title = title;
        this.description = description;
        this.timerequired = timerequired;
        this.activitytype = activitytype;
        this.deadline = deadline;
        this.remindme = remindme;
        this.meetinglink = meetinglink;
        this.task_id = task_id;
        this.deadline1 = deadline1;
        this.relatedActivity = relatedActivity;
    }

    public AddingMeeting() {
    }
}
