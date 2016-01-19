package jp.ac.chitose.tms.ui.Signed;

import jp.ac.chitose.tms.Service.ITestRecordService;
import jp.ac.chitose.tms.Service.ITestService;
import lombok.val;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class TestItemPage extends WebPage {

	@SpringBean
	private ITestService testService;

	@SpringBean
	private ITestRecordService testRecordService;

	public TestItemPage(){
		int testId = 1;

		val testItem = testService;//Service
	}
}
