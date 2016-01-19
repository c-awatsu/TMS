package jp.ac.chitose.tms.ui.Signed;

import jp.ac.chitose.tms.Bean.TestItem;
import jp.ac.chitose.tms.Bean.TestRecordItem;
import jp.ac.chitose.tms.Service.ITestRecordService;
import jp.ac.chitose.tms.Service.ITestService;
import lombok.val;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/test")
public class TestItemPage extends WebPage {

	@SpringBean
	private ITestService testService;

	@SpringBean
	private ITestRecordService testRecordService;

	public TestItemPage(){
		int testId = 1;

		val testItem = new WebMarkupContainer("testItem", new CompoundPropertyModel<TestItem>(testService.fetchTestItem(testId)));
		testItem.add(new Label("testId"));
		testItem.add(new Label("classification"));
		testItem.add(new Label("step"));
		testItem.add(new Label("expectedOutput"));
		add(testItem);

		val RecordListModel = new ListModel<TestRecordItem>(testRecordService.fetchTestRecordItems(testId));
		val testRecordList = new PropertyListView<TestRecordItem>("testRecordList", RecordListModel){
			@Override
			protected void populateItem(ListItem<TestRecordItem> item) {
				item.add(new Label("testRecordId"));
				item.add(new Label("testDate"));
				item.add(new Label("testerId"));
				item.add(new Label("result"));
				item.add(new Label("note"));
			}
		};
		add(testRecordList);
	}
}
