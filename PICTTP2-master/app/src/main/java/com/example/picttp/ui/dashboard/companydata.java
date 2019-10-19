package com.example.picttp.ui.dashboard;

import android.widget.EditText;

public class companydata {
    private String Comp_name,user_ctc,TYPE,ROLE,URL,desc,mock_q,requirement;

    public companydata() {
    }

    public String getComp_name() {
        return Comp_name;
    }

    public void setComp_name(String comp_name) {
        Comp_name = comp_name;
    }

    public String getUser_ctc() {
        return user_ctc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setUser_ctc(String user_ctc) {
        this.user_ctc = user_ctc;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getMock_q() {
        return mock_q;
    }

    public void setMock_q(String mock_q) {
        this.mock_q = mock_q;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public companydata(String comp_name, String user_ctc, String TYPE, String ROLE, String URL, String desc, String mock_q, String requirement) {
        Comp_name = comp_name;
        this.user_ctc = user_ctc;
        this.TYPE = TYPE;
        this.ROLE = ROLE;
        this.URL = URL;
        this.desc = desc;
        this.mock_q = mock_q;
        this.requirement = requirement;
    }
}
