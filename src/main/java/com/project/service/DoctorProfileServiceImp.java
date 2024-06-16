package com.project.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.model.DoctorVO;
import com.project.utils.BaseMethod;

@Service
@Transactional
public class DoctorProfileServiceImp implements DoctorProfileService {

	@Autowired
	private BaseMethod baseMethods;

	@Override
	public DoctorVO saveProfile(MultipartFile govermentId, MultipartFile addressProof, MultipartFile doctorCertificate,
		 HttpServletRequest request, DoctorVO doctorVo) {
		// Saving File there is new object
		String govermentFileName = "governmentId" + "-" + govermentId.getOriginalFilename();
		String addressProofFileName = "addressProof" + "-" + addressProof.getOriginalFilename();
		String doctorCertificateFileName = "doctorCertificate" + "-" + doctorCertificate.getOriginalFilename();
		
		String folderPath = request.getSession().getServletContext().getRealPath("/")
				+ "documents\\doctorProfileDetails\\" + BaseMethod.getUsername();
		File theDir = new File(folderPath);
		if (!theDir.exists()) {
			theDir.mkdirs();
		}

		try {
			byte barr1[] = addressProof.getBytes();
			byte barr2[] = doctorCertificate.getBytes();
			byte barr3[] = govermentId.getBytes();
		

			BufferedOutputStream bufferedOutputStream1 = new BufferedOutputStream(
					new FileOutputStream(folderPath + "\\" + addressProofFileName));
			BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(
					new FileOutputStream(folderPath + "\\" + doctorCertificateFileName));
			BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(
					new FileOutputStream(folderPath + "\\" + govermentFileName));
			
			bufferedOutputStream1.write(barr1);
			bufferedOutputStream2.write(barr2);
			bufferedOutputStream3.write(barr3);

			bufferedOutputStream1.flush();
			bufferedOutputStream2.flush();
			bufferedOutputStream3.flush();

			bufferedOutputStream1.close();
			bufferedOutputStream2.close();
			bufferedOutputStream3.close();


			doctorVo.setCertificatePath("\\documents\\doctorProfileDetails\\" + BaseMethod.getUsername() + "\\"
					+ doctorCertificateFileName);
			doctorVo.setAddressProofPath(
					"\\documents\\doctorProfileDetails\\" + BaseMethod.getUsername() + "\\" + addressProofFileName);
			doctorVo.setGovernmentIdPath(
					"\\documents\\doctorProfileDetails\\" + BaseMethod.getUsername() + "\\" + govermentFileName);
		

		} catch (Exception e) {
			e.printStackTrace();
		}

		return doctorVo;
	}

}
