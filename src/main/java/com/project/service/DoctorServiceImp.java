package com.project.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.DoctorDAO;
import com.project.dao.LoginDAO;
import com.project.enums.DoctorReviewStatus;
import com.project.model.DoctorVO;
import com.project.model.LoginVO;
import com.project.utils.BaseMethod;
import com.project.utils.FileUtils;

@Service
@Transactional
public class DoctorServiceImp implements DoctorService {

	@Value("${server.port}")
	private String serverPort;

	@Value("${email.subject}")
	private String emailSubject;

	@Value("${email.accept.body}")
	private String emailBody;

	@Value("${email.rejected.subject}")
	private String rejectedEmailSubject;

	@Value("${email.rejected.body}")
	private String rejectedEmailBody;

	@Value("${email.approval.path}")
	private String approvalEmailPath;
	
	@Value("${email.rejection.path}")
	private String rejectionEmailPath;

	private DoctorDAO doctorDAO;
	private BaseMethod baseMethod;
	private LoginDAO loginDAO;

	public DoctorServiceImp(DoctorDAO doctorDAO, BaseMethod baseMethod, LoginDAO loginDAO) {
		this.doctorDAO = doctorDAO;
		this.baseMethod = baseMethod;
		this.loginDAO = loginDAO;
	}

	public void save(DoctorVO DoctorVO) {
		this.doctorDAO.save(DoctorVO);
	}

	public List<DoctorVO> search() {
		List<DoctorVO> reqDoctorList = this.doctorDAO.search();
		return reqDoctorList;
	}

	public void doctorApproved(int id, DoctorVO doctorVO) {

		doctorVO.setId(id);
		doctorVO.setReviewStatus(com.project.enums.DoctorReviewStatus.ACCEPTED);
		String recepient = doctorVO.getEmail();

		String password = baseMethod.password();

		/*
		 * String replaced_message = emailBody.replace("[doctor name]",
		 * doctorVO.getDoctorName()) .replace("[serverPortnum]",
		 * serverPort).replace("[username]", doctorVO.getEmail())
		 * .replace("[password]", password);
		 */
		String emailTemplate;
		try {
			emailTemplate = FileUtils.readFileAsString(approvalEmailPath);
		} catch (IOException e) {
			e.printStackTrace();
			return; // or handle the error appropriately
		}

		String replaced_message = emailTemplate.replace("[doctor name]", doctorVO.getDoctorName())
				.replace("[serverPortnum]", serverPort).replace("[username]", doctorVO.getEmail())
				.replace("[password]", password);

		baseMethod.sendMail(recepient, emailSubject, replaced_message);

		// LoginVO
		LoginVO loginvo = new LoginVO();
		loginvo.setEnabled("1");
		loginvo.setPassword(password);
		loginvo.setRole("ROLE_DOCTOR");
		loginvo.setStatus(true);
		loginvo.setUsername(doctorVO.getEmail());

		this.loginDAO.save(loginvo);
		/*
		 * 1 password username ROLE_DOCTOR true
		 */

		// Save loginVO

		this.doctorDAO.save(doctorVO);

	}

	public void doctorRejected(int id, String reason, DoctorVO reqDoctvo) {
		reqDoctvo.setId(id);
		reqDoctvo.setStatus(false);
		reqDoctvo.setReason(reason);
		reqDoctvo.setReviewStatus(DoctorReviewStatus.REJECTED);

		String recepient = reqDoctvo.getEmail();
		
		String emailTemplate;
		try {
			emailTemplate = FileUtils.readFileAsString(rejectionEmailPath);
		} catch (IOException e) {
			e.printStackTrace();
			return; // or handle the error appropriately
		}

		String replaced_message = emailTemplate.replace("[doctor name]", reqDoctvo.getDoctorName())
				.replace("[reason]", reqDoctvo.getReason());
		
/*		String replaced_message = rejectedEmailBody.replace("[doctor name]", reqDoctvo.getDoctorName())
				.replace("[reason]", reqDoctvo.getReason());
*/
		baseMethod.sendMail(recepient, rejectedEmailSubject, replaced_message);

		this.doctorDAO.save(reqDoctvo);
	}

	public DoctorVO seachById(int id) {
		DoctorVO reqDocVo = this.doctorDAO.seachById(id);
		return reqDocVo;
	}

	public Response filterByReviewStatus(String review) {

		Response response = new Response();

		if (review.equals("ACCEPTED")) {
			response.setBody(this.doctorDAO.filterByReviewStatus(DoctorReviewStatus.ACCEPTED));
		} else if (review.equals("NOT_REVIEWED")) {
			response.setBody(this.doctorDAO.filterByReviewStatus(DoctorReviewStatus.NOT_REVIEWED));
		} else if (review.equals("REJECTED")) {
			response.setBody(this.doctorDAO.filterByReviewStatus(DoctorReviewStatus.REJECTED));
		} else if (review.equals("allDoctors")) {
			response.setBody(this.doctorDAO.filterByReviewStatus(null));
		}
		response.setStatus(true);
		response.setMessage("List found");

		return response;
	}

	public DoctorVO searchByUnPwd(String logUsername, String logPassword) {
		DoctorVO reqdocvo = this.doctorDAO.searchByUnPwd(logUsername, logPassword);
		return reqdocvo;
	}
}
