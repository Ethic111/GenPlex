package com.project.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dao.PatientDoctorMappingDao;
import com.project.dao.ReportDao;
import com.project.model.DoctorVO;
import com.project.model.PatientDoctorMappingVO;
import com.project.model.PatientVo;
import com.project.model.ReportVo;
import com.project.utils.BaseMethod;

@Service
@Transactional
public class ReportServiceImp implements ReportService {

	@Autowired
	private BaseMethod baseMethods;

	@Autowired
	private ReportDao reportDao;

	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientDoctorMappingDao patientDoctorDao;

	@Autowired
	private PatientDoctorMappingService patientdoctorMappingService;

	public void save(ReportVo reportVo, MultipartFile reportFile, HttpServletRequest request, String email) {

		LocalDateTime timeStamp = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
		String formatDateTime = timeStamp.format(format);

		String reportFileName = formatDateTime + reportVo.getReporttypevo().getReportTypeName() + "-"
				+ reportFile.getOriginalFilename();

		String folderPath = request.getSession().getServletContext().getRealPath("/") + "documents\\patientReports\\"
				+ email;

		File theDir = new File(folderPath);
		if (!theDir.exists()) {
			theDir.mkdirs();
		}
		try {
			byte barr[] = reportFile.getBytes();

			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(folderPath + "\\" + reportFileName));
			bufferedOutputStream.write(barr);
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		reportVo.setStatus(true);
		reportVo.setReportPath("\\documents\\patientReports\\" + email + "\\" + reportFileName);

		savePatientIfNotExist(email, reportVo);

		String doctorUsername = BaseMethod.getUsername();
		DoctorVO doctorvo = this.doctorService.searchByUn(doctorUsername);

		PatientVo pvo = this.patientService.searchByEmail(email);

		PatientDoctorMappingVO pdvo = this.patientdoctorMappingService.searchByEmail(email, doctorvo);

		if (pdvo == null) {
			pdvo = new PatientDoctorMappingVO();
			pdvo.setDoctorvo(doctorvo);
			pdvo.setPatientvo(pvo);
			pdvo.setStatus(true);
			System.out.println("patient Doctor Dao - 2");
			this.patientDoctorDao.save(pdvo);
			System.out.println("inside report saving ");
		}

		reportVo.setPatientdoctorvo(pdvo);

		System.out.println("Report saving");
		this.reportDao.save(reportVo);

	}

	private void savePatientIfNotExist(String email, ReportVo reportVo) {
		PatientVo existingpatient = this.patientService.searchByEmail(email);
		if (existingpatient == null) {
			PatientVo patientVo = new PatientVo();
			patientVo.setEmail(email);
			patientVo.setStatevo(reportVo.getStatevo());
			patientVo.setCityvo(reportVo.getCityvo());

			this.patientService.save(patientVo);

			// DoctorV
			// PatientDoctorMappingVO pdVo =
			// this.patientDoctorMappingService.searchByEmail(patientEmail,pdvo.);
		}

	}

	public List<ReportVo> search() {
		List<ReportVo> searchList = this.reportDao.search();
		return searchList;
	}

	public List<ReportVo> searchByDoctor(String doctorun) {
		List<ReportVo> searchList = this.reportDao.searchByDoctor(doctorun);
		return searchList;
	}

	public List<ReportVo> searchByPatient(String patientUn) {
		List<ReportVo> searchList = this.reportDao.searchByPatient(patientUn);
		return searchList;
	}

	public Response searchFilter(int cityState, int patientDoctor, int reportType) {
		Response response = new Response();

		if (cityState == 0 && patientDoctor == 0 && reportType == 0) {

			response.setBody(this.reportDao.search());
			
		} else if (cityState != 0 && patientDoctor == 0 && reportType == 0) {

			response.setBody(this.reportDao.searchFilterCityState(cityState));

		} else if (cityState == 0 && patientDoctor != 0 && reportType == 0) {

			response.setBody(this.reportDao.searchFilterPatientDoctor(patientDoctor));

		} else if (cityState == 0 && patientDoctor == 0 && reportType != 0) {

			response.setBody(this.reportDao.searchFilterReportType(reportType));

		} else if (cityState != 0 && patientDoctor != 0 && reportType == 0) {

			response.setBody(this.reportDao.searchFilterCSPD(cityState, patientDoctor));

		} else if (cityState != 0 && patientDoctor == 0 && reportType != 0) {

			response.setBody(this.reportDao.searchFilterCSRT(cityState, reportType));

		} else if (cityState == 0 && patientDoctor != 0 && reportType != 0) {

			response.setBody(this.reportDao.searchFilterPDRT(patientDoctor, reportType));

		} else if (cityState != 0 && patientDoctor != 0 && reportType != 0) {

			response.setBody(this.reportDao.searchFilterAll(cityState, patientDoctor, reportType));

		}

		return response;
	}
	
	public Response searchDoctorReportFilter(int cityState, int patientDoctor, int reportType) {
		Response response = new Response();
		
		DoctorVO doctorvo = this.doctorService.searchByUn(BaseMethod.getUsername());
		int doctorId = doctorvo.getId();
		
		if (cityState == 0 && patientDoctor == 0 && reportType == 0) {

			response.setBody(searchByDoctor(BaseMethod.getUsername()));
			
		} else if (cityState != 0 && patientDoctor == 0 && reportType == 0) {
			
			response.setBody(this.reportDao.searchDoctorFilterCityState(cityState,doctorId));
			
		} else if (cityState == 0 && patientDoctor != 0 && reportType == 0) {
			
			response.setBody(this.reportDao.searchDoctorFilterPatientDoctor(patientDoctor,doctorId));
			
		} else if (cityState == 0 && patientDoctor == 0 && reportType != 0) {
			
			response.setBody(this.reportDao.searchDoctorFilterReportType(reportType,doctorId));
			
		} else if (cityState != 0 && patientDoctor != 0 && reportType == 0) {
			
			response.setBody(this.reportDao.searchDoctorFilterCSPD(cityState, patientDoctor,doctorId));
			
		} else if (cityState != 0 && patientDoctor == 0 && reportType != 0) {
			
			response.setBody(this.reportDao.searchDoctorFilterCSRT(cityState, reportType,doctorId));
			
		} else if (cityState == 0 && patientDoctor != 0 && reportType != 0) {
			
			response.setBody(this.reportDao.searchDoctorFilterPDRT(patientDoctor, reportType,doctorId));
			
		} else if (cityState != 0 && patientDoctor != 0 && reportType != 0) {
			
			response.setBody(this.reportDao.searchDoctorFilterAll(cityState, patientDoctor, reportType,doctorId));
			
		}
		
		return response;
	}
	
	
	public Response searchPatientReportFilter(int patientDoctor, int reportType) {
		Response response = new Response();
		
		PatientVo patientvo = this.patientService.searchByEmail(BaseMethod.getUsername());
		int patientId = patientvo.getId();
		
		if (patientDoctor == 0 && reportType == 0) {
			
			response.setBody(searchByPatient(BaseMethod.getUsername()));
			
		}  else if (patientDoctor != 0 && reportType == 0) {
			
			response.setBody(this.reportDao.searchPatientFilterReports(patientDoctor,patientId));
			
		} else if (patientDoctor == 0 && reportType != 0) {
			
			response.setBody(this.reportDao.searchPatientFilterReportType(reportType,patientId));
			
		}  else if (patientDoctor != 0 && reportType != 0) {
			
			response.setBody(this.reportDao.searchPatientFilterPDRT(patientDoctor, reportType,patientId));
			
		}
		return response;
	}

}
