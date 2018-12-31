package com.project.pojo;

import java.util.Date;

public class Projects {
    private Integer id;

    private String pNumber;

    private String pName;

    private String pGroup;

    private String pGroupleader;

    private String pTutor;

    private String pFirsttutor;

    private Date pDeclaredate;

    private Date pUpdatedate;

    private String pInsitute;

    private String pProfiles;

    private String pRemarks;

    private String pQq;

    public Projects(Integer id, String pNumber, String pName, String pGroup, String pGroupleader, String pTutor, String pFirsttutor, Date pDeclaredate, Date pUpdatedate, String pInsitute, String pProfiles, String pRemarks, String pQq) {
        this.id = id;
        this.pNumber = pNumber;
        this.pName = pName;
        this.pGroup = pGroup;
        this.pGroupleader = pGroupleader;
        this.pTutor = pTutor;
        this.pFirsttutor = pFirsttutor;
        this.pDeclaredate = pDeclaredate;
        this.pUpdatedate = pUpdatedate;
        this.pInsitute = pInsitute;
        this.pProfiles = pProfiles;
        this.pRemarks = pRemarks;
        this.pQq = pQq;
    }

    public Projects() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber == null ? null : pNumber.trim();
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public String getpGroup() {
        return pGroup;
    }

    public void setpGroup(String pGroup) {
        this.pGroup = pGroup == null ? null : pGroup.trim();
    }

    public String getpGroupleader() {
        return pGroupleader;
    }

    public void setpGroupleader(String pGroupleader) {
        this.pGroupleader = pGroupleader == null ? null : pGroupleader.trim();
    }

    public String getpTutor() {
        return pTutor;
    }

    public void setpTutor(String pTutor) {
        this.pTutor = pTutor == null ? null : pTutor.trim();
    }

    public String getpFirsttutor() {
        return pFirsttutor;
    }

    public void setpFirsttutor(String pFirsttutor) {
        this.pFirsttutor = pFirsttutor == null ? null : pFirsttutor.trim();
    }

    public Date getpDeclaredate() {
        return pDeclaredate;
    }

    public void setpDeclaredate(Date pDeclaredate) {
        this.pDeclaredate = pDeclaredate;
    }

    public Date getpUpdatedate() {
        return pUpdatedate;
    }

    public void setpUpdatedate(Date pUpdatedate) {
        this.pUpdatedate = pUpdatedate;
    }

    public String getpInsitute() {
        return pInsitute;
    }

    public void setpInsitute(String pInsitute) {
        this.pInsitute = pInsitute == null ? null : pInsitute.trim();
    }

    public String getpProfiles() {
        return pProfiles;
    }

    public void setpProfiles(String pProfiles) {
        this.pProfiles = pProfiles == null ? null : pProfiles.trim();
    }

    public String getpRemarks() {
        return pRemarks;
    }

    public void setpRemarks(String pRemarks) {
        this.pRemarks = pRemarks == null ? null : pRemarks.trim();
    }

    public String getpQq() {
        return pQq;
    }

    public void setpQq(String pQq) {
        this.pQq = pQq == null ? null : pQq.trim();
    }
}