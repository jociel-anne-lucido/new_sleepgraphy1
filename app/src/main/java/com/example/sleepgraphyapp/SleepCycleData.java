package com.example.sleepgraphyapp;

public class SleepCycleData {

    public long lastRecorded, newRecorded, totalDur;
    public String moodQual, sleepTime, wakeTime;
    private String pushId;

    public SleepCycleData() {

    }

    public SleepCycleData(long lastRecorded, long newRecorded, long totalDur, String moodQual, String sleepTime, String wakeTime) {
        this.lastRecorded = lastRecorded;
        this.newRecorded = newRecorded;
        this.totalDur = totalDur;
        this.moodQual = moodQual;
        this.sleepTime = sleepTime;
        this.wakeTime = wakeTime;
    }

    public long getLastRecorded() {
        return lastRecorded;
    }

    public void setLastRecorded(long lastRecorded) {
        this.lastRecorded = lastRecorded;
    }

    public long getNewRecorded() {
        return newRecorded;
    }

    public void setNewRecorded(long newRecorded) {
        this.newRecorded = newRecorded;
    }

    public long getTotalDur() {
        return totalDur;
    }

    public void setTotalDur(long totalDur) {
        this.totalDur = totalDur;
    }

    public String getMoodQual() {
        return moodQual;
    }

    public void setMoodQual(String moodQual) {
        this.moodQual = moodQual;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(String wakeTime) {
        this.wakeTime = wakeTime;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
