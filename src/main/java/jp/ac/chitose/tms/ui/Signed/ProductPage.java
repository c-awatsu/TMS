package jp.ac.chitose.tms.ui.Signed;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestItem;
import jp.ac.chitose.tms.Service.IProductService;
import jp.ac.chitose.tms.Service.ITestRecordService;
import jp.ac.chitose.tms.Service.ITestService;
import lombok.val;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableLabel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ProductPage extends WebPage {
	@SpringBean
	IProductService productService;

	@SpringBean
	ITestService testService;

	@SpringBean
	ITestRecordService testRecordService;

	public ProductPage(int productId){

		val testItemsModel = new LoadableDetachableModel<List<TestItem>>() {
			@Override
			protected List<TestItem> load() {
				return testService.fetchTestItems(productId);
			}
		};

		val inputForm = new Form <List<TestItem>>("inputForm",testItemsModel);
		add(inputForm);
		inputForm.add(new Button("testInput") {
			@Override
			public void onSubmit() {
				System.out.println(testItemsModel);
				testItemsModel.getObject().stream()
					.forEach(t -> testService.upsert(new Model<TestItem>(t)));
			}
		});

		val productNameModel = new AbstractReadOnlyModel<String>(){
			@Override
			public String getObject() {
				return productService.getProductItem(productId).getName();
			}
		};

		add(new Label("title", productNameModel));
		add(new Label("productName", productNameModel));
		//TODO 実施結果ページにジャンプする為の手段を作る(idをリンク化？)
		val testList = new ListView<TestItem>("testList",testItemsModel){
			@Override
			protected void populateItem(ListItem<TestItem> item) {
				val testItem = item.getModelObject();
				item.setDefaultModel(new CompoundPropertyModel<TestItem>(testItem));
				item.add(new Label("testId",item.getIndex()+1));
				item.add(new AjaxEditableLabel<TestItem>("classification").setRequired(true));
				item.add(new AjaxEditableLabel<TestItem>("step").setRequired(true));
				item.add(new AjaxEditableLabel<TestItem>("expectedOutput").setRequired(true));
				item.add(new Label("latestResult", new AbstractReadOnlyModel<String>() {
					@Override
					public String getObject() {
						val latest = testRecordService.getLatestTestRecord(testItem.getTestId());
						val result = latest != null ? latest.getResult() : false;
						return result ? "○":"×";
					}
				}));
			}
		};
		inputForm.add(testList);

		val addTestVisibleContlloer = new Model<Boolean>();
		addTestVisibleContlloer.setObject(true);

		val addTest =  new AjaxButton("addTest"){
			protected void onSubmit(AjaxRequestTarget target,Form<?> form) {
				if(addTestVisibleContlloer.getObject()){
					testItemsModel.getObject().add(new TestItem());
					addTestVisibleContlloer.setObject(false);
					target.add(inputForm);
				}else{
					testItemsModel.getObject().remove(testItemsModel.getObject().size()-1);
					addTestVisibleContlloer.setObject(true);
					target.add(inputForm);
				}
			}

		};
		inputForm.add(addTest);

		addTest.add(new Label("addTestLabelController",new AbstractReadOnlyModel<String>() {
			@Override
			public String getObject() {
				return addTestVisibleContlloer.getObject() ? "テストを追加":"テストを削除";
			}
		}));


	}
}
