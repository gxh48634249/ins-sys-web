package com.ins.sys.zh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 安居客数据源
 */
@Entity
@Table(name = "zh", schema = "sys_info", catalog = "")
public class ZH {

    private String id;

    private String lpName;

    private String address;

    private String huxing;

    private String jianzhumianj;

    private String forsale;

    private String danjia;

    private String wuyestyle;

    private String kaifashang;

    private String zuidishoufu;

    private String kaijianmianji;

    private String shangyemianji;

    private String songjianmianji;

    private String zuixinkaipan;

    private String jiangfangshijian;

    private String zhaoshangyetai;

    private String linjinshangquan;

    private String qianyueshanghu;

    private String zhoubianrenqun;

    private String shouloudizhi;

    private String yushouxukezheng;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "lp_name")
    public String getLpName() {
        return lpName;
    }

    public void setLpName(String lpName) {
        this.lpName = lpName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHuxing() {
        return huxing;
    }

    public void setHuxing(String huxing) {
        this.huxing = huxing;
    }

    public String getJianzhumianj() {
        return jianzhumianj;
    }

    public void setJianzhumianj(String jianzhumianj) {
        this.jianzhumianj = jianzhumianj;
    }

    public String getForsale() {
        return forsale;
    }

    public void setForsale(String forsale) {
        this.forsale = forsale;
    }

    public String getDanjia() {
        return danjia;
    }

    public void setDanjia(String danjia) {
        this.danjia = danjia;
    }

    public String getWuyestyle() {
        return wuyestyle;
    }

    public void setWuyestyle(String wuyestyle) {
        this.wuyestyle = wuyestyle;
    }

    public String getKaifashang() {
        return kaifashang;
    }

    public void setKaifashang(String kaifashang) {
        this.kaifashang = kaifashang;
    }

    public String getZuidishoufu() {
        return zuidishoufu;
    }

    public void setZuidishoufu(String zuidishoufu) {
        this.zuidishoufu = zuidishoufu;
    }

    public String getKaijianmianji() {
        return kaijianmianji;
    }

    public void setKaijianmianji(String kaijianmianji) {
        this.kaijianmianji = kaijianmianji;
    }

    public String getShangyemianji() {
        return shangyemianji;
    }

    public void setShangyemianji(String shangyemianji) {
        this.shangyemianji = shangyemianji;
    }

    public String getSongjianmianji() {
        return songjianmianji;
    }

    public void setSongjianmianji(String songjianmianji) {
        this.songjianmianji = songjianmianji;
    }

    public String getZuixinkaipan() {
        return zuixinkaipan;
    }

    public void setZuixinkaipan(String zuixinkaipan) {
        this.zuixinkaipan = zuixinkaipan;
    }

    public String getJiangfangshijian() {
        return jiangfangshijian;
    }

    public void setJiangfangshijian(String jiangfangshijian) {
        this.jiangfangshijian = jiangfangshijian;
    }

    public String getZhaoshangyetai() {
        return zhaoshangyetai;
    }

    public void setZhaoshangyetai(String zhaoshangyetai) {
        this.zhaoshangyetai = zhaoshangyetai;
    }

    public String getLinjinshangquan() {
        return linjinshangquan;
    }

    public void setLinjinshangquan(String linjinshangquan) {
        this.linjinshangquan = linjinshangquan;
    }

    public String getQianyueshanghu() {
        return qianyueshanghu;
    }

    public void setQianyueshanghu(String qianyueshanghu) {
        this.qianyueshanghu = qianyueshanghu;
    }

    public String getZhoubianrenqun() {
        return zhoubianrenqun;
    }

    public void setZhoubianrenqun(String zhoubianrenqun) {
        this.zhoubianrenqun = zhoubianrenqun;
    }

    public String getShouloudizhi() {
        return shouloudizhi;
    }

    public void setShouloudizhi(String shouloudizhi) {
        this.shouloudizhi = shouloudizhi;
    }

    public String getYushouxukezheng() {
        return yushouxukezheng;
    }

    public void setYushouxukezheng(String yushouxukezheng) {
        this.yushouxukezheng = yushouxukezheng;
    }
}
