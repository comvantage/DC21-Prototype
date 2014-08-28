package eu.comvantage.wp7_cv_products;

import android.graphics.Bitmap;

public class ProductsGridInfo {
	
		private Bitmap mIcon;
		private String mName;
		private String mText2;
		private String mText3;

		public ProductsGridInfo(Bitmap icon, String name, String text2, String text3) {
			mIcon = icon;
			mName = name;
			mText2 = text2;
			mText3 = text3;
		}
		
		public void setIcon(Bitmap icon) {
			mIcon = icon;
		}
		public Bitmap getIcon() {
			return mIcon;
		}

		public void setName(String name) {
			mName = name;
		}
		public String getName() {
			return mName;
		}
		
		public void setText2(String name) {
			mText2 = name;
		}
		public String getText2() {
			return mText2;
		}
		
		public void setText3(String name) {
			mText3 = name;
		}
		public String getText3() {
			return mText3;
		}

	}
