package com.project.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "report_tbl")
public class ReportVo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@NotNull(message = "Report type is required")
	@ManyToOne
	@JoinColumn(name = "reportType_id")
	private ReportTypeVo reporttypevo;
	
	@NotEmpty(message = "Summary is required")
	@Column(name = "summary")
	private String summary;
	
	@NotNull(message = "Date is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date")
	private Date date;

	
	@ManyToOne
	@JoinColumn(name = "patient_doctor_id")
	private PatientDoctorMappingVO patientdoctorvo;
	
	@NotNull(message = "State is required")
	@ManyToOne
	@JoinColumn(name = "state_id")
	private StateVo statevo;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "city_id")
	private CityVo cityvo;
	
//	@NotEmpty(message = "Report path is required")
	@Column(name = "report_path")
	private String reportPath;
	
	private boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ReportTypeVo getReporttypevo() {
		return reporttypevo;
	}

	public void setReporttypevo(ReportTypeVo reporttypevo) {
		this.reporttypevo = reporttypevo;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
//
//	public PatientVo getPatientvo() {
//		return patientvo;
//	}
//
//	public void setPatientvo(PatientVo patientvo) {
//		this.patientvo = patientvo;
//	}

	public StateVo getStatevo() {
		return statevo;
	}

	public PatientDoctorMappingVO getPatientdoctorvo() {
		return patientdoctorvo;
	}

	public void setPatientdoctorvo(PatientDoctorMappingVO patientdoctorvo) {
		this.patientdoctorvo = patientdoctorvo;
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

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
//
//	public DoctorVO getDoctorvo() {
//		return doctorvo;
//	}
//
//	public void setDoctorvo(DoctorVO doctorvo) {
//		this.doctorvo = doctorvo;
//	}

	
}
