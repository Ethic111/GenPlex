package com.project.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.LoginDAO;
import com.project.dao.PatientDao;
import com.project.model.CityVo;
import com.project.model.LoginVO;
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

	public PatientServiceImp(PatientDao patientdao, LoginDAO loginDAO, BaseMethod baseMethod) {
		this.patientdao = patientdao;
		this.loginDAO = loginDAO;
		this.baseMethod = baseMethod;
	}

	public void save(PatientVo patientVo) {

		patientVo.setStatus(true);
		patientVo.setProfileStatus(false);

		String recepient = patientVo.getEmail();
		String password = baseMethod.password();

		String emailTemplate;
		try {
			emailTemplate = FileUtils.readFileAsString(patientRegisteredEmailPath);
		} catch (IOException e) {
			e.printStackTrace();
			return; // or handle the error appropriately
		}

		String replaced_message = emailTemplate.replace("[patient name]", patientVo.getPatientName())
				.replace("[serverPortnum]", serverPort).replace("[username]", patientVo.getEmail())
				.replace("[password]", password);

		baseMethod.sendMail(recepient, patientRegisteredEmailSubject, replaced_message);

		// LoginVO
		LoginVO loginvo = new LoginVO();
		loginvo.setEnabled("1");
		loginvo.setPassword(password);
		loginvo.setRole("ROLE_PATIENT");
		loginvo.setStatus(true);
		loginvo.setUsername(patientVo.getEmail());

		this.loginDAO.save(loginvo);
		this.patientdao.save(patientVo);

	}

	public List<PatientVo> search() {
		List<PatientVo> searchList = this.patientdao.search();
		return searchList;

	}

	public PatientVo searchById(int id) {
		PatientVo patientvo = this.patientdao.searchById(id);
		return patientvo;

	}

}
