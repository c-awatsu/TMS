package jp.ac.chitose.tms.ui.Signed;

import java.util.List;

import jp.ac.chitose.tms.Bean.ProductItem;
import jp.ac.chitose.tms.Service.IProductService;
import lombok.val;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/TopPage")
public class TopPage extends WebPage{
	@SpringBean
	private IProductService productService;
	public TopPage(){
		add(new Label("test","ログイン成功"));

		val productList = new LoadableDetachableModel<List<ProductItem>>() {

			@Override
			protected List<ProductItem> load() {
				return productService.fetchProductItems();
			}
		};
		//モックアップとしてテーブルをただ表示するだけ
		add(new PropertyListView<ProductItem>("productList",productList) {

			@Override
			protected void populateItem(ListItem<ProductItem> item) {
				item.add(new Link<Void>("productName"){
					@Override
					public void onClick() {
						//TODO 製品ごとのテスト項目表示ページへ
					}
				});

			}

		});
	}
}