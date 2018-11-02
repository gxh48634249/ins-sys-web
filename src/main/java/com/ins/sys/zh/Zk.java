package com.ins.sys.zh;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Zk {
    private String id;
    private String jssGdp;
    private String jssRenkou;
    private String jssKezhipei;
    private String jssGouzhitudi;
    private String jssTouzie;
    private String jssShigongmianji;
    private String jssJungongmianji;
    private String jssXiaoshoumianji;
    private String jssPjxiaoshoujine;

    @Id
    @Basic
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "jss_gdp")
    public String getJssGdp() {
        return jssGdp;
    }

    public void setJssGdp(String jssGdp) {
        this.jssGdp = jssGdp;
    }

    @Basic
    @Column(name = "jss_renkou")
    public String getJssRenkou() {
        return jssRenkou;
    }

    public void setJssRenkou(String jssRenkou) {
        this.jssRenkou = jssRenkou;
    }

    @Basic
    @Column(name = "jss_kezhipei")
    public String getJssKezhipei() {
        return jssKezhipei;
    }

    public void setJssKezhipei(String jssKezhipei) {
        this.jssKezhipei = jssKezhipei;
    }

    @Basic
    @Column(name = "jss_gouzhitudi")
    public String getJssGouzhitudi() {
        return jssGouzhitudi;
    }

    public void setJssGouzhitudi(String jssGouzhitudi) {
        this.jssGouzhitudi = jssGouzhitudi;
    }

    @Basic
    @Column(name = "jss_touzie")
    public String getJssTouzie() {
        return jssTouzie;
    }

    public void setJssTouzie(String jssTouzie) {
        this.jssTouzie = jssTouzie;
    }

    @Basic
    @Column(name = "jss_shigongmianji")
    public String getJssShigongmianji() {
        return jssShigongmianji;
    }

    public void setJssShigongmianji(String jssShigongmianji) {
        this.jssShigongmianji = jssShigongmianji;
    }

    @Basic
    @Column(name = "jss_jungongmianji")
    public String getJssJungongmianji() {
        return jssJungongmianji;
    }

    public void setJssJungongmianji(String jssJungongmianji) {
        this.jssJungongmianji = jssJungongmianji;
    }

    @Basic
    @Column(name = "jss_xiaoshoumianji")
    public String getJssXiaoshoumianji() {
        return jssXiaoshoumianji;
    }

    public void setJssXiaoshoumianji(String jssXiaoshoumianji) {
        this.jssXiaoshoumianji = jssXiaoshoumianji;
    }

    @Basic
    @Column(name = "jss_pjxiaoshoujine")
    public String getJssPjxiaoshoujine() {
        return jssPjxiaoshoujine;
    }

    public void setJssPjxiaoshoujine(String jssPjxiaoshoujine) {
        this.jssPjxiaoshoujine = jssPjxiaoshoujine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zk zk = (Zk) o;
        return Objects.equals(id, zk.id) &&
                Objects.equals(jssGdp, zk.jssGdp) &&
                Objects.equals(jssRenkou, zk.jssRenkou) &&
                Objects.equals(jssKezhipei, zk.jssKezhipei) &&
                Objects.equals(jssGouzhitudi, zk.jssGouzhitudi) &&
                Objects.equals(jssTouzie, zk.jssTouzie) &&
                Objects.equals(jssShigongmianji, zk.jssShigongmianji) &&
                Objects.equals(jssJungongmianji, zk.jssJungongmianji) &&
                Objects.equals(jssXiaoshoumianji, zk.jssXiaoshoumianji) &&
                Objects.equals(jssPjxiaoshoujine, zk.jssPjxiaoshoujine);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, jssGdp, jssRenkou, jssKezhipei, jssGouzhitudi, jssTouzie, jssShigongmianji, jssJungongmianji, jssXiaoshoumianji, jssPjxiaoshoujine);
    }
}
