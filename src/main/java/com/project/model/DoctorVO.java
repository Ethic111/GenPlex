package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.web.multipart.MultipartFile;

import com.project.enums.DoctorReviewStatus;

@Entity
@Table(name = "doctor_register_tbl")
public class DoctorVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id")
	private int id;

	@Column(name = "email")
	private String email;

	@Column(name = "doctor_name")
	private String doctorName;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private StateVo statevo;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private CityVo cityvo;

	@ManyToOne
	@JoinColumn(name = "degree_id")
	private DegreeVo degreevo;

	@Column(name = "rejection_reason")
	private String reason;

	@Enumerated(EnumType.STRING)
	private DoctorReviewStatus reviewStatus;

	private boolean status;

	// For form 2:

	@Column(name = "age")
	private int age;

	//

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public StateVo getStatevo() {
		return statevo;
	}

	public void setStatevo(StateVo statevo) {
		this.statevo = statevo;
	}

	public CityVo getCityvo() {
		return cityvo;
	}

	public void setCityvo(CityVo cityvo) {
		this.cityvo = cityvo;
	}

	public DegreeVo getDegreevo() {
		return degreevo;
	}

	public void setDegreevo(DegreeVo degreevo) {
		this.degreevo = degreevo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public DoctorReviewStatus getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(DoctorReviewStatus reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	///////////////////////////

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
