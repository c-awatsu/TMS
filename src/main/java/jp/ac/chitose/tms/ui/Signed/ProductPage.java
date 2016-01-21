package jp.ac.chitose.tms.ui.Signed;

import java.util.List;

import jp.ac.chitose.tms.WicketSession;
import jp.ac.chitose.tms.Bean.TestItem;
import jp.ac.chitose.tms.Feedback.ErrorAlertPanel;
import jp.ac.chitose.tms.Service.IProductService;
import jp.ac.chitose.tms.Service.ITestRecordService;
import jp.ac.chitose.tms.Service.ITestService;
import lombok.val;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableLabel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
@MountPath("/productPage")
public class ProductPage extends WebPage {
	@SpringBean
	IProductService productService;

	@SpringBean
	ITestService testService;

	@SpringBean
	ITestRecordService testRecordService;
	public static final String NULL_ERROR = "入力を空にすることはできません";

	public ProductPage(int productId){

		add(new Link<Void>("logout"){

			@Override
			public void onClick() {
				WicketSession.get().invalidate();
				setResponsePage(getApplication().getHomePage());
			}

		});

		add(new ErrorAlertPanel("feedback"));

		val addTestVisibleContlloer = new Model<Boolean>();
		addTestVisibleContlloer.setObject(true);
		val testItemsModel = new ListModel<>(testService.fetchTestItems(productId));

		val inputForm = new Form <List<TestItem>>("inputForm",testItemsModel){
			@Override
			public void onSubmit() {
				if(getModelObject().get(getModelObject().size()-1).getClassification() == null
				   |getModelObject().get(getModelObject().size()-1).getExpectedOutput() == null
				   |getModelObject().get(getModelObject().size()-1).getStep() == null){
					error(NULL_ERROR);
				}else{
					getModelObject().stream()
						.forEach(g -> testService.upsert(new Model<TestItem>(g)));
					addTestVisibleContlloer.setObject(true);
					testItemsModel.setObject(testService.fetchTestItems(productId));
				}
			}
		};
		add(inputForm);


		val productNameModel = new AbstractReadOnlyModel<String>(){
			@Override
			public String getObject() {
				return productService.getProductItem(productId).getName();
			}
		};

		add(new Label("title", productNameModel));
		add(new Label("productName", productNameModel));
		val testList = new PropertyListView<TestItem>("testList",testItemsModel){
			@Override
			protected void populateItem(ListItem<TestItem> item){
				val testItem = item.getModelObject();

				item.add(new Label("testId",item.getIndex()+1));
				item.add(new AjaxEditableLabel<TestItem>("classification"));
				item.add(new AjaxEditableLabel<TestItem>("step"));
				item.add( new AjaxEditableLabel<TestItem>("expectedOutput"));

				if(testItemsModel.getObject().get(item.getIndex()).getTestId() != null){
					item.add(new Label("latestResult", new AbstractReadOnlyModel<String>() {
						@Override
						public String getObject() {
							val latest = testRecordService.getLatestTestRecord(testItemsModel.getObject().get(item.getIndex()).getTestId());
							val result = latest != null ? latest.getResult() : false;
							return result ? "○":"×";
						}
				}));
				}else/** 追加ボタンが押された時は絶対elseに入る **/{
					item.add(new Label("latestResult","×"));
				}
				item.add(new Link<Void>("testRecord"){
					@Override
					public void onClick() {
						setResponsePage(new TestItemPage(testItem.getTestId(), productId));
					}
				});
			}
		};
		inputForm.add(testList);

		val addTest =  new AjaxLink<Void>("addTest"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				if(addTestVisibleContlloer.getObject()){
					TestItem newTest = new TestItem();
					newTest.setProductId(productId);
					testItemsModel.getObject().add(newTest);
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
				return addTestVisibleContlloer.getObject() ? "テストを追加":"キャンセル";
			}
		}));
		add(new Link<Void>("backTopPage"){

			@Override
			public void onClick() {
				setResponsePage(new TopPage());
			}

		});
	}
}
