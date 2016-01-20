package jp.ac.chitose.tms.ui.Signed;

import java.sql.Date;

import jp.ac.chitose.tms.Bean.TestItem;
import jp.ac.chitose.tms.Bean.TestRecordItem;
import jp.ac.chitose.tms.Service.ISignService;
import jp.ac.chitose.tms.Service.ITestRecordService;
import jp.ac.chitose.tms.Service.ITestService;
import lombok.val;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
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
	private ISignService signService;

	@SpringBean
	private ITestRecordService testRecordService;

	// テスト用コンストラクタ あとでけす
	public TestItemPage() {
		this(1);
	}

	public TestItemPage(final int testId) {

		val testItem = new WebMarkupContainer("testItem",
				new CompoundPropertyModel<TestItem>(
						testService.fetchTestItem(testId)));
		testItem.add(new Label("testId"));
		testItem.add(new Label("classification"));
		testItem.add(new Label("step"));
		testItem.add(new Label("expectedOutput"));
		add(testItem);

		val testRecordList = new PropertyListView<TestRecordItem>(
				"testRecordList", new ListModel<TestRecordItem>(
						testRecordService.fetchTestRecordItems(testId))) {
			@Override
			protected void populateItem(ListItem<TestRecordItem> item) {
				val testRecordItem = item.getModelObject();
				item.add(new Label("testRecordId"));
				item.add(new Label("testDate"));
				item.add(new Label("testerName", signService
						.fetchNickName(testRecordItem.getTesterId())));
				item.add(new Label("result", testRecordItem.getResult() ? "○"
						: "×"));
				item.add(new Label("note"));
			}
		};
		add(testRecordList);

		val testRecordInputForm = new Form<TestRecordItem>(
				"testRecordInputForm",
				new CompoundPropertyModel<TestRecordItem>(new TestRecordItem(0,
						new Date(0), 1, false, "", testId))) {//TODO testerIdをセッションから取ってくる
			@Override
			protected void onSubmit() {
				super.onSubmit();
				val testRecordItem = getModelObject();
				System.out.println("あおおおうういいえええあ"
						+ testRecordItem.getTestDate().toString());
			}
		};
		testRecordInputForm.add(new DateTextField("testDate", "yyyy-mm-dd"));
		testRecordInputForm.add(new Label("testerName", signService
				.fetchNickName(testRecordInputForm.getModelObject()
						.getTesterId())));
		testRecordInputForm.add(new CheckBox("result"));
		testRecordInputForm.add(new RequiredTextField<String>("note"));
		add(testRecordInputForm);

	}
}
