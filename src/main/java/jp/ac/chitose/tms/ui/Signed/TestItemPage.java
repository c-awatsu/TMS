package jp.ac.chitose.tms.ui.Signed;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import jp.ac.chitose.tms.WicketSession;
import jp.ac.chitose.tms.Bean.TestItem;
import jp.ac.chitose.tms.Bean.TestRecordItem;
import jp.ac.chitose.tms.Service.ISignService;
import jp.ac.chitose.tms.Service.ITestRecordService;
import jp.ac.chitose.tms.Service.ITestService;
import jp.ac.chitose.tms.ui.Sign.SignInPage;
import lombok.val;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class TestItemPage extends AbstractSignedPage {

	@SpringBean
	private ITestService testService;

	@SpringBean
	private ISignService signService;

	@SpringBean
	private ITestRecordService testRecordService;

	public TestItemPage(final int testId, final int productId) {
		add(new FeedbackPanel("feedback"));

		add(new Link<Void>("logout"){
			@Override
			public void onClick() {
				setResponsePage(SignInPage.class);
			}
		});

		add(new Link<Void>("pageBack"){
			@Override
			public void onClick() {
				setResponsePage(new ProductPage(productId));
			}
		});

		val testItem = new WebMarkupContainer("testItem",
				new CompoundPropertyModel<TestItem>(
						testService.fetchTestItem(testId)));
		testItem.add(new Label("testId"));
		testItem.add(new Label("classification"));
		testItem.add(new Label("step"));
		testItem.add(new Label("expectedOutput"));
		add(testItem);

		val deleteMap = new HashMap<Integer, Model<Boolean>>();
		val sdf = new SimpleDateFormat("yyyy/MM/dd");
		val testRecordListModel = new ListModel<>(
				testRecordService.fetchTestRecordItems(testId));
		val testRecordList = new PropertyListView<TestRecordItem>(
				"testRecordList", testRecordListModel) {
			@Override
			protected void populateItem(ListItem<TestRecordItem> item) {
				val testRecordItem = item.getModelObject();
				item.add(new Label("testRecordId", item.getIndex() + 1));
				item.add(new Label("testDate", sdf.format(testRecordItem.getTestDate())));
				item.add(new Label("testerName", signService
						.fetchNickName(testRecordItem.getTesterId())));
				item.add(new Label("result", testRecordItem.getResult() ? "○"
						: "×"));
				item.add(new Label("note"));

				val deleteFlag = new Model<Boolean>(false);
				val deleteCheck = new Link<Boolean>("deleteCheck", deleteFlag){
					@Override
					public void onClick() {
						setModelObject(!getModelObject());//flagを反転させる
					}
				};
				deleteCheck.add(new Label("deleteLabel", new AbstractReadOnlyModel<String>(){
					@Override
					public String getObject() {
						return deleteCheck.getModelObject() ? "■":"□";
					}
				}));
				item.add(deleteCheck);

				deleteMap.put(testRecordItem.getTestRecordId(), deleteFlag);
			}
		};
		testRecordList.setReuseItems(true);
		add(testRecordList);

		val deleteButton = new Link<Void>("deleteButton"){
			@Override
			public void onClick() {
				for(Integer key : deleteMap.keySet()){
					if(deleteMap.get(key).getObject()){
						testRecordService.delete(key);
					}
				}
				deleteMap.clear();
				testRecordListModel.setObject(testRecordService.fetchTestRecordItems(testId));
				testRecordList.removeAll();
			}
		};
		add(deleteButton);

		val session = (WicketSession)WebSession.get();
		val testRecordInputForm = new Form<TestRecordItem>(
				"testRecordInputForm",
				new CompoundPropertyModel<TestRecordItem>(new TestRecordItem(0,
						new Date(), session.getAccountId(), false, "", testId))) {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				val testRecordItem = getModelObject();
				testRecordService.insert(testRecordItem);
				testRecordListModel.setObject(testRecordService.fetchTestRecordItems(testId));
			}
		};
		testRecordInputForm.add(new DateTextField("testDate", "yyyy/MM/dd"));
		testRecordInputForm.add(new Label("testerName", signService
				.fetchNickName(testRecordInputForm.getModelObject()
						.getTesterId())));
		testRecordInputForm.add(new CheckBox("result"));
		testRecordInputForm.add(new RequiredTextField<String>("note"));//TODO 空入力を許容できるよう変更する
		add(testRecordInputForm);

	}
}
