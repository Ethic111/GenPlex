package com.project.model;

import javax.persistence.CascadeType;
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

import com.project.enums.DoctorReviewStatus;

@Entity
@Table(name = "patient_tbl")
public class PatientVo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

//	@Column(name = "patientName")
//	private String patientName;

	@Column(name = "email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private StateVo statevo;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private CityVo cityvo;

	private boolean status;

	private boolean profileStatus;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getPatientName() {
//		return patientName;
//	}
//
//	public void setPatientName(String patientName) {
//		this.patientName = patientName;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isProfileStatus() {
		return profileStatus;
	}

	public void setProfileStatus(boolean profileStatus) {
		this.profileStatus = profileStatus;
	}
	
	

}
