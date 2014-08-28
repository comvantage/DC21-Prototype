package eu.comvantage.wp7_cv_products;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductsGridInfoAdapter extends BaseAdapter {
	private Context mContext;
	private List<ProductsGridInfo> mListAppInfo;

	public ProductsGridInfoAdapter(Context context, List<ProductsGridInfo> list) {
		mContext = context;
		mListAppInfo = list;
	}

	public int getCount() {
		return mListAppInfo.size();
	}

	public Object getItem(int position) {
		return mListAppInfo.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ProductsGridInfo entry = mListAppInfo.get(position);
	
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.products_grid_item, null);
		}
	
		ImageView ivIcon = (ImageView)convertView.findViewById(R.id.ivProduct);
		ivIcon.setImageBitmap(entry.getIcon());

		TextView tvName = (TextView)convertView.findViewById(R.id.tvProductText1);
		tvName.setText(entry.getName());
		
		TextView tvText2 = (TextView)convertView.findViewById(R.id.tvProductText2);
		tvText2.setText(entry.getText2());
		
		TextView tvText3 = (TextView)convertView.findViewById(R.id.tvProductText3);
		tvText3.setText(entry.getText3());
		
		return convertView;
	}
	
	
	
	}



