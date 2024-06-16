package com.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.PatientDoctorMappingDao;
import com.project.model.DoctorVO;
import com.project.model.PatientDoctorMappingVO;
import com.project.utils.BaseMethod;

@Service
@Transactional
public class PatientDoctorMappingServiceImp implements PatientDoctorMappingService {

	@Autowired
	private PatientDoctorMappingDao patientdoctorMappingDao;

	//
	// public PatientDoctorMappingServiceImp(PatientDoctorMappingDao
	// patientdoctorMappingDao) {
	// this.patientdoctorMappingDao = patientdoctorMappingDao;
	// }

	public void save(PatientDoctorMappingVO pdvo) {
		this.patientdoctorMappingDao.save(pdvo);
	}

	public PatientDoctorMappingVO searchByEmail(String email, DoctorVO doctorvo) {

		PatientDoctorMappingVO pdVo = this.patientdoctorMappingDao.searchByEmail(email, doctorvo);

		return pdVo;

	}

	public PatientDoctorMappingVO searchById(int pid, String doctorun) {

		PatientDoctorMappingVO pdVo = this.patientdoctorMappingDao.searchById(pid, doctorun);

		return pdVo;

	}

	public List<PatientDoctorMappingVO> search(){
		List<PatientDoctorMappingVO> searchList = this.patientdoctorMappingDao.search();
		return searchList;
	}
	
	public List<PatientDoctorMappingVO> searchByDoctor(String doctorun) {
		List<PatientDoctorMappingVO> searchList = this.patientdoctorMappingDao.searchByDoctor(doctorun);
		return searchList;
	}

	public List<String> getPatientsEmails() {
		List<PatientDoctorMappingVO> patientDoctorMappingList = this.patientdoctorMappingDao
				.searchByDoctor(BaseMethod.getUsername());
		List<String> emails = patientDoctorMappingList.stream().map(p -> p.getPatientvo().getEmail())
				.collect(Collectors.toList());
		return emails;
	}

	public Response seachDoctorFilterValue(int value) {
		Response response = new Response();
		String doctorun = BaseMethod.getUsername();
		response.setBody(this.patientdoctorMappingDao.seachDoctorFilterValue(value, doctorun));
		// List<PatientVo> searchList =
		// this.patientdao.seachByFilterValue(value);
		// return searchList;
		return response;

	}
	
	public void delete(PatientDoctorMappingVO pdvo){
		pdvo.setStatus(false);
		this.patientdoctorMappingDao.save(pdvo);
	}

}
