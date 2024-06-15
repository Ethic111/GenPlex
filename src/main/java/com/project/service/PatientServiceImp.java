package com.project.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.LoginDAO;
import com.project.dao.PatientDao;
import com.project.dao.PatientDoctorMappingDao;
import com.project.model.CityVo;
import com.project.model.DoctorVO;
import com.project.model.LoginVO;
import com.project.model.PatientDoctorMappingVO;
import com.project.model.PatientVo;
import com.project.model.ReportTypeVo;
import com.project.model.StateVo;
import com.project.utils.BaseMethod;
import com.project.utils.FileUtils;

@Service
@Transactional
public class PatientServiceImp implements PatientService {

	@Value("${server.port}")
	private String serverPort;

	@Value("${email.patientRegistered.subject}")
	private String patientRegisteredEmailSubject;

	@Value("${email.patientRegistered.path}")
	private String patientRegisteredEmailPath;

	private PatientDao patientdao;
	private LoginDAO loginDAO;
	private BaseMethod baseMethod;
	private PatientDoctorMappingDao patientDoctorDao;
	private DoctorService doctorService;
	private LoginService loginService;

	public PatientServiceImp(PatientDao patientdao, LoginDAO loginDAO, BaseMethod baseMethod,
			PatientDoctorMappingDao patientDoctorDao, DoctorService doctorService, LoginService loginService) {
		this.patientdao = patientdao;
		this.loginDAO = loginDAO;
		this.baseMethod = baseMethod;
		this.patientDoctorDao = patientDoctorDao;
		this.doctorService = doctorService;
		this.loginService = loginService;
	}

	public void save(PatientVo patientVo) {

		LoginVO loginvo = this.loginService.searchByUserName(patientVo.getEmail());
		PatientVo existingpatient = searchByEmail(patientVo.getEmail());

		String password = "";

		patientVo.setStatus(true);
		patientVo.setProfileStatus(false);

		// email

		String recepient = patientVo.getEmail();

		String emailTemplate;
		try {
			emailTemplate = FileUtils.readFileAsString(patientRegisteredEmailPath);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		// System.out.println(emailTemplate);
		String replaced_message = "";
		if (loginvo == null) {
			password = baseMethod.password();
			replaced_message = emailTemplate.replace("[patient name]", patientVo.getEmail())
					.replace("[serverPortnum]", serverPort).replace("[username]", patientVo.getEmail())
					.replace("[password]", password);

		} else {
			replaced_message = emailTemplate.replace("[patient name]", patientVo.getEmail())
					.replace("[serverPortnum]", serverPort).replace("[username]", patientVo.getEmail())
					.replace("[password]", loginvo.getPassword());
		}

		baseMethod.sendMail(recepient, patientRegisteredEmailSubject, replaced_message);

		if (loginvo == null) {

			// LoginVO
			loginvo = new LoginVO();
			loginvo.setEnabled("1");
			loginvo.setPassword(password);
			loginvo.setRole("ROLE_PATIENT");
			loginvo.setStatus(true);
			loginvo.setUsername(patientVo.getEmail());

			this.loginDAO.save(loginvo);

		}
		
		if (existingpatient == null) {
			this.patientdao.save(patientVo);
		}

		DoctorVO doctorvo = this.doctorService.searchByUn(BaseMethod.getUsername());
		PatientDoctorMappingVO pdvo = new PatientDoctorMappingVO();
		pdvo.setDoctorvo(doctorvo);
		pdvo.setPatientvo(patientVo);
		pdvo.setStatus(true);
		System.out.println("patient Doctor Dao - 1");
		this.patientDoctorDao.save(pdvo);

	}

	public List<PatientVo> search() {
		List<PatientVo> searchList = this.patientdao.search();
		return searchList;

	}
	
	public Response seachByFilterValue(int value) {
		Response response = new Response();
		response.setBody(this.patientdao.seachByFilterValue(value));
//		List<PatientVo> searchList = this.patientdao.seachByFilterValue(value);
//		return searchList;
		return response;
		
	}


	public PatientVo searchById(int id) {
		PatientVo patientvo = this.patientdao.searchById(id);
		return patientvo;

	}

	public PatientVo searchByEmail(String pemail) {
		PatientVo patientvo = this.patientdao.searchByEmail(pemail);
		return patientvo;

	}

}
