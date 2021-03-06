package com.sinosoft.stukpisys.entity;


import java.sql.Date;

public class Education {

  private long eduId;
  private String highestEducate;
  private String major;
  private String schoolName;
  private String location;
  private long is211;
  private java.sql.Date graduationTime;

  public Education(){}

  public Education(long eduId, String highestEducate, String major, String schoolName, String location, long is211, Date graduationTime) {
    this.eduId = eduId;
    this.highestEducate = highestEducate;
    this.major = major;
    this.schoolName = schoolName;
    this.location = location;
    this.is211 = is211;
    this.graduationTime = graduationTime;
  }

  public long getEduId() {
    return eduId;
  }

  public void setEduId(long eduId) {
    this.eduId = eduId;
  }


  public String getHighestEducate() {
    return highestEducate;
  }

  public void setHighestEducate(String highestEducate) {
    this.highestEducate = highestEducate;
  }


  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major;
  }


  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }


  public long getIs211() {
    return is211;
  }

  public void setIs211(long is211) {
    this.is211 = is211;
  }


  public java.sql.Date getGraduationTime() {
    return graduationTime;
  }

  public void setGraduationTime(java.sql.Date graduationTime) {
    this.graduationTime = graduationTime;
  }
  public Education(String highestEducate, String major, String schoolName, String location, long is211, Date graduationTime) {
    this.highestEducate = highestEducate;
    this.major = major;
    this.schoolName = schoolName;
    this.location = location;
    this.is211 = is211;
    this.graduationTime = graduationTime;
}
}
