package com.ins.sys.zh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 招拍挂数据源
 */
@Entity
@Table(name = "zp", schema = "sys_info", catalog = "")
public class ZP {

    private String id;

    private String yongtu;

    private String zongmianji;

    private String jianzhumianji;

    private String churangnianxian;

    private String dikuaibianhao;

    private String sizhi;

    private String qishishijian  ;

    private String jiezhishijian  ;

    private String qishijia   ;

    private String chengjiaojia ;

    private String tudidanjia     ;

    private String yijialv ;

    private String gonggaoriqi    ;

    private String shiming;

    private String jingwei;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "yongtu")
    public String getYongtu() {
        return yongtu;
    }

    public void setYongtu(String yongtu) {
        this.yongtu = yongtu;
    }

    @Column(name = "zongmianji")
    public String getZongmianji() {
        return zongmianji;
    }

    public void setZongmianji(String zongmianji) {
        this.zongmianji = zongmianji;
    }

    @Column(name = "jianzhumianji")
    public String getJianzhumianji() {
        return jianzhumianji;
    }

    public void setJianzhumianji(String jianzhumianji) {
        this.jianzhumianji = jianzhumianji;
    }

    @Column(name = "churangnianxian")
    public String getChurangnianxian() {
        return churangnianxian;
    }

    public void setChurangnianxian(String churangnianxian) {
        this.churangnianxian = churangnianxian;
    }

    @Column(name = "dikuaibianhao")
    public String getDikuaibianhao() {
        return dikuaibianhao;
    }

    public void setDikuaibianhao(String dikuaibianhao) {
        this.dikuaibianhao = dikuaibianhao;
    }

    @Column(name = "sizhi")
    public String getSizhi() {
        return sizhi;
    }

    public void setSizhi(String sizhi) {
        this.sizhi = sizhi;
    }

    @Column(name = "qishishijian")
    public String getQishishijian() {
        return qishishijian;
    }

    public void setQishishijian(String qishishijian) {
        this.qishishijian = qishishijian;
    }

    @Column(name = "jiezhishijian")
    public String getJiezhishijian() {
        return jiezhishijian;
    }

    public void setJiezhishijian(String jiezhishijian) {
        this.jiezhishijian = jiezhishijian;
    }

    @Column(name = "qishijia")
    public String getQishijia() {
        return qishijia;
    }

    public void setQishijia(String qishijia) {
        this.qishijia = qishijia;
    }

    @Column(name = "chengjiaojia")
    public String getChengjiaojia() {
        return chengjiaojia;
    }

    public void setChengjiaojia(String chengjiaojia) {
        this.chengjiaojia = chengjiaojia;
    }

    @Column(name = "tudidanjia")
    public String getTudidanjia() {
        return tudidanjia;
    }

    public void setTudidanjia(String tudidanjia) {
        this.tudidanjia = tudidanjia;
    }

    @Column(name = "yijialv")
    public String getYijialv() {
        return yijialv;
    }

    public void setYijialv(String yijialv) {
        this.yijialv = yijialv;
    }

    @Column(name = "gonggaoriqi")
    public String getGonggaoriqi() {
        return gonggaoriqi;
    }

    public void setGonggaoriqi(String gonggaoriqi) {
        this.gonggaoriqi = gonggaoriqi;
    }

    @Column(name = "jingwei")
    public String getJingwei() {
        return jingwei;
    }

    public void setJingwei(String jingwei) {
        this.jingwei = jingwei;
    }

    @Column(name = "shiming")
    public String getShiming() {
        return shiming;
    }

    public void setShiming(String shiming) {
        this.shiming = shiming;
    }
}
