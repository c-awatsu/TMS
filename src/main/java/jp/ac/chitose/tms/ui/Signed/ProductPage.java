package jp.ac.chitose.tms.ui.Signed;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestItem;
import jp.ac.chitose.tms.Service.IProductService;
import jp.ac.chitose.tms.Service.ITestRecordService;
import jp.ac.chitose.tms.Service.ITestService;
import lombok.val;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
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
		val inputForm = new Form<Void>("inputForm");
		add(inputForm);

		val productNameModel = new AbstractReadOnlyModel<String>(){
			@Override
			public String getObject() {
				return productService.getProductItem(productId).getName();
			}
		};

		add(new Label("title", productNameModel));
		add(new Label("productName", productNameModel));

		val testItemsModel = new LoadableDetachableModel<List<TestItem>>() {
			@Override
			protected List<TestItem> load() {
				return testService.fetchTestItems(productId);
			}
		};

		//TODO 実施結果ページにジャンプする為の手段を作る
		//Behaviorを使って行全体をリンクボタンにしたい
		//DataViewに差し替えたい
		inputForm.add(new ListView<TestItem>("testList",testItemsModel){
			@Override
			protected void populateItem(ListItem<TestItem> item) {
				item.add(new Label("latestResult", new AbstractReadOnlyModel<String>() {
					@Override
					public String getObject() {
						val latest = testRecordService.getLatestTestRecord(item.getModelObject().getTestId());
						val result = latest != null ? latest.getResult() : false;
						return result ? "○":"×";
					}
				}));
				item.setDefaultModel(new CompoundPropertyModel<TestItem>(item.getModelObject()));
				item.add(new Label("testId"));
				item.add(new Label("classification"));
				item.add(new Label("step"));
				item.add(new Label("expectedOutput"));
			}
		});

		val inputFormVisibleModel = new Model<Boolean>();
		inputFormVisibleModel.setObject(false);

		val inputLine = new WebMarkupContainer("inputLine", new CompoundPropertyModel<TestItem>(new TestItem())){
			@Override
			public boolean isVisible() {
				return inputFormVisibleModel.getObject();
			}
		};
		inputForm.add(inputLine);

		inputLine.add(new TextField<String>("classification"));
		inputLine.add(new TextField<String>("step"));
		inputLine.add(new TextField<String>("expectedOutput"));
		inputLine.add(new SubmitLink("inputButton", inputForm){
			@Override
			public void onSubmit() {
				val testItem = (TestItem) inputLine.getDefaultModelObject();
				testItem.setProductId(productId);
				testService.inputTestItem(testItem);
			}
		});

		val inputFormChangeVisibleButton = new Link<Void>("inputFormChangeVisibleButton"){
			@Override
			public void onClick() {
				inputFormVisibleModel.setObject(!inputFormVisibleModel.getObject());
			}
		};
		add(inputFormChangeVisibleButton);

		inputFormChangeVisibleButton.add(new Label("inputFormChangeVisibleButtonLabel", new AbstractReadOnlyModel<String>(){
			@Override
			public String getObject() {
				return inputFormVisibleModel.getObject() ? "-":"+";
			}
		}));
	}
}
