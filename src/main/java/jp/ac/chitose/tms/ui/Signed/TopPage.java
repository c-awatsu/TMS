package jp.ac.chitose.tms.ui.Signed;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableLabel;
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

import jp.ac.chitose.tms.WicketSession;
import jp.ac.chitose.tms.Bean.ProductItem;
import jp.ac.chitose.tms.Service.IProductService;
import jp.ac.chitose.tms.Service.ITestRecordService;
import jp.ac.chitose.tms.Service.ITestService;
import lombok.val;

@MountPath("/TopPage")
public class TopPage extends AbstractSignedPage{
	@SpringBean
	private IProductService productService;
	@SpringBean
	private ITestService tService;
	@SpringBean
	private ITestRecordService trService;
	public static final String NULL_ERROR = "入力を空にすることはできません";

	public TopPage(){


		add(new Link<Void>("logout"){

			@Override
			public void onClick() {
				WicketSession.get().invalidate();
				setResponsePage(getApplication().getHomePage());
			}

		});
		val addProdutContlloer = new Model<Boolean>();
		addProdutContlloer.setObject(true);
		val productList = new ListModel<>(productService.fetchProductItems());

		val form = new Form<List<ProductItem>>("form",productList){
			@Override
			protected void onSubmit() {
				if(getModelObject().stream().anyMatch(p -> p.getName()==null)){
					error(NULL_ERROR);
				}else{
					getModelObject().stream()
						.forEach(p -> productService.upsert(new Model<ProductItem>(p)));
						addProdutContlloer.setObject(true);
						productList.setObject(productService.fetchProductItems());
				}
			}
		};
		add(form);

		form.add(new PropertyListView<ProductItem>("productList",productList) {
			@Override
			protected void populateItem(ListItem<ProductItem> item) {
				val productLink =  new Link<Void>("toProductPage"){
					@Override
					public void onClick() {
						setResponsePage(new ProductPage(productList.getObject().get(item.getIndex()).getProductId()));
					}
				};
				item.add(productLink);
				productLink.add(new Label("productNumber",item.getIndex()+1));
				item.add(new AjaxEditableLabel<ProductItem>("name"));
				item.add(new Label("progressOfTest",new AbstractReadOnlyModel<String>(){
					@Override
					public String getObject() {
						val testProgressItem =  trService.getTestProgress(item.getModelObject().getProductId());
						return testProgressItem.toString();
					}
				}));
			}
		});

		val addProduct = new AjaxLink<Void>("addProduct"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				if(addProdutContlloer.getObject()){
					productList.getObject().add(new ProductItem());
					addProdutContlloer.setObject(false);
					target.add(form);
				}else{
					productList.getObject().remove(productList.getObject().size()-1);
					addProdutContlloer.setObject(true);
					target.add(form);
				}
			}

		};
		form.add(addProduct);
		addProduct.add(new Label("addProductLabelController",new AbstractReadOnlyModel<String>(){

			@Override
			public String getObject() {
				return addProdutContlloer.getObject() ? "製品を追加":"キャンセル";
			}

		}));

	}
}
