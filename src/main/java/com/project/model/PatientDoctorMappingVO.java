package com.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patient_doctor_mapping_tbl")
public class PatientDoctorMappingVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private DoctorVO doctorvo;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private PatientVo patientvo;
	
	private boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DoctorVO getDoctorvo() {
		return doctorvo;
	}

	public void setDoctorvo(DoctorVO doctorvo) {
		this.doctorvo = doctorvo;
	}

	public PatientVo getPatientvo() {
		return patientvo;
	}

	public void setPatientvo(PatientVo patientvo) {
		this.patientvo = patientvo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
