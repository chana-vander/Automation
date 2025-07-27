package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/*public class ReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentHtmlReporter html = new ExtentHtmlReporter("target/extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(html);
        }
        return extent;
    }
}*/
public class ReportManager {
    private static ExtentReports extent;

    public static ExtentReports getReporter() {
        if (extent == null) {
            ExtentHtmlReporter html = new ExtentHtmlReporter("extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(html);
        }
        return extent;
    }
    public static ExtentReports getExtentReports() {
        if (extent == null) {
            extent = new ExtentReports(); // או כל קונפיגורציה שאתה עושה
        }
        return extent;
    }
}
