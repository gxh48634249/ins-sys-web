package com.ins.sys.user.domain;

import com.ins.sys.organ.domain.OrganInfoEntity;
import com.ins.sys.permission.domain.PermissionInfoEntity;
import com.ins.sys.role.domain.RoleInfoEntity;
import com.ins.sys.tools.ListUtill;
import com.ins.sys.tools.StringTool;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "sys_user_info", schema = "sys_info", catalog = "")
public class SysUserInfoEntity implements UserDetails ,Serializable {

    @ApiModelProperty("用户主键")
    private String userId;

    @ApiModelProperty("用户登陆账户")
    private String userAccount;

    @ApiModelProperty("用户密码")
    private String userPwd;

    @ApiModelProperty("用户姓名")
    private String userName;

    @ApiModelProperty("手机号")
    private Long phone;

    @ApiModelProperty("邮箱")
    private String mail;

    @ApiModelProperty("微信")
    private String wechat;

    @ApiModelProperty("身份证号码")
    private String idCard;

    @ApiModelProperty("住址")
    private String address;

    @ApiModelProperty("QQ")
    private Integer qq;

    @ApiModelProperty("头像")
    private String portrait;

    @ApiModelProperty("生日")
    private Long birth;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("教育程度")
    private Integer education;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("用户状态1:正常/2:锁定/3：黑名单")
    private String userStatue;

    @ApiModelProperty("过期时间")
    private Long expiredTime;

    @Transient
    @ApiModelProperty("机构信息")
    private OrganInfoEntity organInfoEntity;

    @Transient
    public OrganInfoEntity getOrganInfoEntity() {
        return organInfoEntity;
    }

    public void setOrganInfoEntity(OrganInfoEntity organInfoEntity) {
        this.organInfoEntity = organInfoEntity;
    }

    @Transient
    private List<PermissionInfoEntity> per;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_account")
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Basic
    @Column(name = "user_pwd")
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "phone")
    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "wechat")
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Basic
    @Column(name = "id_card")
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "QQ")
    public Integer getQq() {
        return qq;
    }

    public void setQq(Integer qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "portrait")
    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    @Basic
    @Column(name = "birth")
    public Long getBirth() {
        return birth;
    }

    public void setBirth(Long birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "education")
    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    @Basic
    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "user_statue")
    public String getUserStatue() {
        return userStatue;
    }

    public void setUserStatue(String userStatue) {
        this.userStatue = userStatue;
    }

    @Basic
    @Column(name = "expired_time")
    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }

    @Transient
    public List<PermissionInfoEntity> getPer() {
        return per;
    }

    public void setPer(List<PermissionInfoEntity> per) {
        this.per = per;
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserInfoEntity that = (SysUserInfoEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(userAccount, that.userAccount) &&
                Objects.equals(userPwd, that.userPwd) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(mail, that.mail) &&
                Objects.equals(wechat, that.wechat) &&
                Objects.equals(idCard, that.idCard) &&
                Objects.equals(address, that.address) &&
                Objects.equals(qq, that.qq) &&
                Objects.equals(portrait, that.portrait) &&
                Objects.equals(birth, that.birth) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(education, that.education) &&
                Objects.equals(createTime, that.createTime);
    }

    @Transient
    @Override
    public int hashCode() {

        return Objects.hash(userId, userAccount, userPwd, userName, phone, mail, wechat, idCard, address, qq, portrait, birth, gender, education, createTime);
    }

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> lis = new ArrayList<>();
        if(!ListUtill.isnull(this.per)){
            for(PermissionInfoEntity temp:this.per){
                lis.add(new SimpleGrantedAuthority(temp.getPermissionCode()));
            }
        }
        return lis;
    }
    @Transient
    @Override
    public String getPassword() {
        return this.userPwd;
    }

    @Transient
    @Override
    public String getUsername() {
        return this.userAccount;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return null==this.expiredTime||this.expiredTime>new Date().getTime();
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return !this.userStatue.equals("2");
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return this.userStatue.equals("1");
    }

    public void initParams(){
        this.address = init(address);
        this.idCard = init(idCard);
        this.mail = init(mail);
        this.userName = init(userName);
    }

    public String init(String s){
        return StringTool.isnull(s)?" ":s;
    }
}
