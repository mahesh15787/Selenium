package reports;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportFactory {
	public static ExtentReports getExtentInstance() {
		ExtentReports extent;
		String filePath = "D:\\Java_WorkSpace\\practise\\src\\main\\java\\reports\\result.html";
		extent = new ExtentReports(filePath,false);
		return extent;
	}
}
