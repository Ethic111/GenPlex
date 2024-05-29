package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.RequestedDoctorDao;
import com.project.model.DoctorReviewStatus;
import com.project.model.RequestedDoctorVo;
import com.project.utils.BaseMethod;

@Service
@Transactional
public class RequestedDoctorServiceImp implements RequestedDoctorService {
	
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

	private RequestedDoctorDao requestedDoctorDao;
	private BaseMethod baseMethod;

	public RequestedDoctorServiceImp(RequestedDoctorDao requestedDoctorDao, BaseMethod baseMethod) {
		this.requestedDoctorDao = requestedDoctorDao;
		this.baseMethod = baseMethod;
	}

	public void save(RequestedDoctorVo requestedDoctorVo) {

		requestedDoctorVo.setStatus(true);
		requestedDoctorVo.setReviewStatus(DoctorReviewStatus.NOT_REVIEWED);
		this.requestedDoctorDao.save(requestedDoctorVo);
	}

	public List<RequestedDoctorVo> search() {
		List<RequestedDoctorVo> reqDoctorList = this.requestedDoctorDao.search();
		return reqDoctorList;
	}

	public void doctorApproved(int id, RequestedDoctorVo reqDoctVo) {

		reqDoctVo.setId(id);
		reqDoctVo.setReviewStatus(DoctorReviewStatus.ACCEPTED);
		String recepient = reqDoctVo.getEmail();

		String password = baseMethod.password();
		String replaced_message = emailBody.replace("[doctor name]", reqDoctVo.getDoctorName()).replace("[serverPortnum]", serverPort)
				.replace("[username]", reqDoctVo.getEmail()).replace("[password]", password);

		baseMethod.sendMail(recepient, emailSubject, replaced_message);

		/*
		 * String subject = "Registration Approval"; String messageBody =
		 * "Thanks for your Registration request,admin have accepted it...";
		 * baseMethod.sendMail(recepient, subject, messageBody);
		 */
		// System.out.println(reqDoctVo.getStateName());
		this.requestedDoctorDao.save(reqDoctVo);

	}

	public void doctorRejected(int id,String reason, RequestedDoctorVo reqDoctvo) {
		reqDoctvo.setId(id);
		reqDoctvo.setStatus(false);
		reqDoctvo.setReason(reason);
		reqDoctvo.setReviewStatus(DoctorReviewStatus.REJECTED);
		
		String recepient = reqDoctvo.getEmail();
		
		String replaced_message = rejectedEmailBody.replace("[doctor name]", reqDoctvo.getDoctorName()).replace("[reason]", reqDoctvo.getReason());
		
		baseMethod.sendMail(recepient, rejectedEmailSubject, replaced_message);
		
		this.requestedDoctorDao.save(reqDoctvo);
	}

	public RequestedDoctorVo seachById(int id) {
		RequestedDoctorVo reqDocVo = this.requestedDoctorDao.seachById(id);
		return reqDocVo;
	}

	public Response filterByReviewStatus(String review) {

		Response response = new Response();

		if (review.equals("ACCEPTED")) {
			response.setBody(this.requestedDoctorDao.filterByReviewStatus(DoctorReviewStatus.ACCEPTED));
		} else if (review.equals("NOT_REVIEWED")) {
			response.setBody(this.requestedDoctorDao.filterByReviewStatus(DoctorReviewStatus.NOT_REVIEWED));
		} else if (review.equals("REJECTED")) {
			response.setBody(this.requestedDoctorDao.filterByReviewStatus(DoctorReviewStatus.REJECTED));
		} else if (review.equals("allDoctors")) {
			response.setBody(this.requestedDoctorDao.filterByReviewStatus(null));
		}
		response.setStatus(true);
		response.setMessage("List found");

		return response;
	}
}
