package com.project.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dao.ReportDao;
import com.project.model.ReportVo;
import com.project.utils.BaseMethod;

@Service
@Transactional
public class ReportServiceImp implements ReportService {

	@Autowired
	private BaseMethod baseMethods;

	@Autowired
	private ReportDao reportDao;

	public void save(ReportVo reportVo, MultipartFile reportFile, HttpServletRequest request) {

		String reportFileName = reportVo.getReporttypevo().getReportTypeName() + "-" + reportFile.getOriginalFilename();

		String folderPath = request.getSession().getServletContext().getRealPath("/") + "documents\\patientReports\\"
				+ reportVo.getPatientvo().getEmail();

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
		reportVo.setReportPath(
				"\\documents\\patientReports\\" + reportVo.getPatientvo().getEmail() + "\\" + reportFileName);
		this.reportDao.save(reportVo);

	}

	public List<ReportVo> search() {
		List<ReportVo> searchList = this.reportDao.search();
		return searchList;
	}

}
