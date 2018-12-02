package com.project.millatinventory.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.millatinventory.controller.LoginController;

@Entity
@Table(name = "USERS")
public class Users extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "LOGIN_ID")
	private String loginId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loginId == null) ? 0 : loginId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (loginId == null) {
			if (other.loginId != null)
				return false;
		} else if (!loginId.equals(other.loginId))
			return false;
		return true;
	}

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORDS")
	private String password;

	@Transient
	private String newPassword;
	@Column(name = "USER_ROLE")
	private String role;

/*	@Column(name = "PYM04LV", length = 1)
	private String userLevel;

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
*/
	
/*	@Column(name = "EMAIL")
	private String email;

	@Column(name = "DOB")
	private Date dateOfBirth;
*/
	@Column(name = "GENDER")
	private String gender;

	@Column(name = "COMMENTS")
	private String comments;

	@Column(name = "STATUS")
	private String status;

/*	@Transient
	private String imageFile;

	@Column(name = "File_Name")
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Lob
	@Column(name = "IMAGE", columnDefinition = "blob")
	private byte[] image;

	@Lob
	@Column(name = "files", columnDefinition = "blob")
	private byte[] file;

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
*/
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "siteId")
	private Sites site;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
/*
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
*/
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
/*
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
*/
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/*
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
*/
	public String getNewPassword() {
		return newPassword;
	}

	public Sites getSite() {
		return site;
	}

	public void setSite(Sites site) {
		this.site = site;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/*// Handle multiple session
	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
	
		logger.info("valueBound" + this);
		HttpSession oldSession = logins.get(this);
		if (oldSession != null) {
			alreadyLoggedIn = true;
		} else {
			logins.put(this, arg0.getSession());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {

		if (arg0.getValue() instanceof Users) {
			logger.info("valueUnbound" + this);
			logins.remove(this);
		}
	}*/
	@Transient
	private boolean alreadyLoggedIn = false;

	public boolean isAlreadyLoggedIn() {
		return alreadyLoggedIn;
	}
	public void setAlreadyLoggedIn(boolean alreadyLoggedIn) {
		this.alreadyLoggedIn = alreadyLoggedIn;
	}
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private static final Logger logger = LoggerFactory.getLogger(Users.class);

	private static Map<Users, HttpSession> logins = new HashMap<Users, HttpSession>();
	
	//END
	
	
	@Column(name="text",columnDefinition= "nvarchar (500)")
	private String text;

	@Override
	public String toString() {
		return "Users [id=" + id + ", loginId=" + loginId + ", userName=" + userName + ", password=" + password
				+ ", newPassword=" + newPassword + ", role=" + role + ", gender=" + gender + ", comments=" + comments
				+ ", status=" + status + ", site=" + site + ", alreadyLoggedIn=" + alreadyLoggedIn + ", text=" + text
				+ "]";
	}
	
	
	
}