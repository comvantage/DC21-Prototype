package com.example.individual_shirt_design;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.individual_shirt_design.ExpandableListAdapter.myGroup;
import com.example.individual_shirt_design.ExpandableListAdapter.myItem;
import com.example.individual_shirt_design.R;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;


public class MainActivity extends Activity {

	
	private List<myGroup> listDataHeader;
    private HashMap<myGroup, List<myItem>> listDataChild;	
	
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.setThreadPolicy(policy);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//actionBar.setDisplayShowTitleEnabled(false);
		
		// dodajanje 3 tabov v actionbar
		Tab tab = actionBar.newTab();
		tab.setText(R.string.tab1);
		tab.setTabListener(new TabListener<DetailsFragment>(this, "Naziv", DetailsFragment.class)); // listenerji so dummy
		actionBar.addTab(tab);
		
		tab = actionBar.newTab();
		tab.setText(R.string.tab2);
		tab.setTabListener(new TabListener<DetailsFragment>(this, "Naziv", DetailsFragment.class));	// listenerji so dummy	
		actionBar.addTab(tab);

		tab = actionBar.newTab();
		tab.setText(R.string.tab3);
		tab.setTabListener(new TabListener<DetailsFragment>(this, "Naziv", DetailsFragment.class)); // listenerji so dummy
		actionBar.addTab(tab);		
		
		expListView = (ExpandableListView) findViewById(R.id.expandableListView1);
		
		generateData();	
		
		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
		expListView.setAdapter(listAdapter);		
		
		Button bAddC = (Button) findViewById(R.id.btn_add_to_cart);
		 bAddC.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
					gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
					gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512238-Shopping_cart");
					//gotoApp.putExtra("login", extra1);
					
		
					String result = null;
		            InputStream is = null;
		       		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		       		nameValuePairs.add(new BasicNameValuePair("u1","update cv_shoppingcart set Active = 1 where id = 2"));
		                try
		                {
		                    HttpClient httpclient = new DefaultHttpClient();
		                    HttpPost httppost = new HttpPost("http://www.hornet.si/update.php");
		                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		                    HttpResponse response = httpclient.execute(httppost); 
		                    HttpEntity entity = response.getEntity();
		                    is = entity.getContent();

		                    Log.e("log_tag", "connection success ");
		                    
		                }
		            catch(Exception e)
		                {
		                    Log.e("log_tag", "Error in http connection "+e.toString());
		                    Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

		                }
					
					startService(gotoApp);
					
					
	            }
	        
	        });
		
		expListView.setOnChildClickListener(new OnChildClickListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				if (parent.isGroupExpanded(groupPosition))
					parent.collapseGroup(groupPosition);
														
				
				((myGroup)listAdapter.getGroup(groupPosition)).subHeader = ((myItem) listAdapter.getChild(groupPosition, childPosition)).naziv;
				
				listAdapter.notifyDataSetChanged();
				
				if (((myGroup)listAdapter.getGroup(0)).subHeader == "Women")
				{
					if (((myGroup)listAdapter.getGroup(1)).subHeader == "Long Sleeve")
					{
						if (((myGroup)listAdapter.getGroup(2)).subHeader == "V-shaped")
						{
							if (((myGroup)listAdapter.getGroup(3)).subHeader == "Design 1")
							{
								if (((myGroup)listAdapter.getGroup(4)).subHeader == "White")
								{
									if ((((myGroup)listAdapter.getGroup(5)).subHeader) == "Green")
										setPicture(R.drawable.woman_shirt_long_v_neck_stripes_green, "75 €");
									else if ((((myGroup)listAdapter.getGroup(5)).subHeader) == "Blue")
										setPicture(R.drawable.woman_shirt_long_v_neck_stripes_blue, "75 €");
									else if ((((myGroup)listAdapter.getGroup(5)).subHeader) == "Black")
										setPicture(R.drawable.woman_shirt_long_v_neck_stripes_black, "75 €");
								}
								else
								{
									setPicture(R.drawable.woman_shirt_long_v_neck_stripes_black, "75 €");
								}
							}
							else
							{
								setPicture(R.drawable.woman_shirt_long_v_neck, "65 €");
							}
						}
						else
						{
							setPicture(R.drawable.woman_shirt_long_round, "65 €");
						}
					}
					else
					{
						setPicture(R.drawable.woman_shirt_short_round, "55 €");
					}
				}
				
				return false;
				
								
			}
		});		
		
	}
	
	private void setPicture (int rID, String price)
	{
		ImageView v1 = (ImageView) findViewById(R.id.imageView1);
		TextView txtPrice = (TextView) findViewById(R.id.cena);
		v1.setImageResource(rID);
		txtPrice.setText(price);					
	}
	
	private void generateData()
	{
		listDataHeader = new ArrayList<myGroup>();
        listDataChild = new HashMap<myGroup, List<myItem>>();
		
		listDataHeader.add(new myGroup("Basic Shape",""));
		listDataHeader.add(new myGroup("Sleeve", ""));
		listDataHeader.add(new myGroup("Collar", ""));
		listDataHeader.add(new myGroup("Design", ""));
		listDataHeader.add(new myGroup("Shirt colour", ""));
		listDataHeader.add(new myGroup("Stripes colour", ""));
		listDataHeader.add(new myGroup("Size / Amount", ""));
		//listDataHeader.add(new myGroup("Embroidery (Optional)", ""));
		//listDataHeader.add(new myGroup("Producer (Optional)", ""));
					
		List<myItem> basic_shape = new ArrayList<myItem>();
		basic_shape.add(new myItem(R.drawable.basic_women, "Women",0));
		basic_shape.add(new myItem(R.drawable.basic_men, "Men",0));
		basic_shape.add(new myItem(R.drawable.basic_uni, "Unisex",0));
				
		List<myItem> sleeve = new ArrayList<myItem>();
		sleeve.add(new myItem(R.drawable.sleeve_short, "Short Sleeve",0));
		sleeve.add(new myItem(R.drawable.sleeve_34, "3/4 Sleeve",0));
		sleeve.add(new myItem(R.drawable.sleeve_long, "Long Sleeve",0));		

		List<myItem> collar = new ArrayList<myItem>();
		collar.add(new myItem(R.drawable.collar_round, "Rounded men",0));
		collar.add(new myItem(R.drawable.collar_round_women, "Rounded women",0));
		collar.add(new myItem(R.drawable.collar_v, "V-shaped",0));
		collar.add(new myItem(R.drawable.collar_standup, "Standup",0));		
		
		List<myItem> design = new ArrayList<myItem>();
		
		design.add(new myItem(R.drawable.design_plain, "Plain",0));
		design.add(new myItem(R.drawable.design_3, "Design 1",0));
		design.add(new myItem(R.drawable.design_2, "Design 2",0));	
		design.add(new myItem(R.drawable.design_4, "Design 3",0));
		design.add(new myItem(R.drawable.design_5, "Design 4",0));
		design.add(new myItem(R.drawable.design_6, "Design 5",0));

		List<myItem> shirt_color = new ArrayList<myItem>();
		
		shirt_color.add(new myItem(R.drawable.colour_, "Black",0));
		shirt_color.add(new myItem(R.drawable.colour_blue, "Blue",0));
		shirt_color.add(new myItem(R.drawable.colour_red, "Red",0));	
		shirt_color.add(new myItem(R.drawable.colour_white, "White",0));
		shirt_color.add(new myItem(R.drawable.colour_green, "Green",0));
		
		List<myItem> size = new ArrayList<myItem>();
		
		size.add(new myItem(-1, "1",1));
		
		listDataChild.put(listDataHeader.get(0), basic_shape);
		listDataChild.put(listDataHeader.get(1), sleeve);
		listDataChild.put(listDataHeader.get(2), collar);
		listDataChild.put(listDataHeader.get(3), design);
		listDataChild.put(listDataHeader.get(4), shirt_color);
		listDataChild.put(listDataHeader.get(5), shirt_color);
		listDataChild.put(listDataHeader.get(6), size);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.action_menu, menu);
		return true;
	}

	
	public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
	    private Fragment mFragment;
	    private final Activity mActivity;
	    private final String mTag;
	    private final Class<T> mClass;

	    public TabListener(Activity activity, String tag, Class<T> clz) 
	    {
	        mActivity = activity;
	        mTag = tag;
	        mClass = clz;
	    }

	    public void onTabSelected(Tab tab, FragmentTransaction ft) 
	    {
	        if (mFragment == null) 
	        {
	            mFragment = Fragment.instantiate(mActivity, mClass.getName());
	            ft.add(android.R.id.content, mFragment, mTag);
	        } else 
	        {
	            ft.attach(mFragment);
	        }
	    }

	    public void onTabUnselected(Tab tab, FragmentTransaction ft) 
	    {
	        if (mFragment != null) 
	            ft.detach(mFragment);
	    }

	    public void onTabReselected(Tab tab, FragmentTransaction ft) 
	    {
	    }
	}	
	
	//
	// Fragment za actionbar tab
	//
	public static class DetailsFragment extends Fragment 
	{
	    public static DetailsFragment newInstance(int index) {
	        DetailsFragment f = new DetailsFragment();

	        Bundle args = new Bundle();
	        args.putInt("index", index);
	        f.setArguments(args);

	        return f;
	    }

	    public int getShownIndex() {
	        return getArguments().getInt("index", 0);
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        if (container == null) {
	            return null;
	        }

	        //ScrollView scroller = new ScrollView(getActivity());
	        //TextView text = new TextView(getActivity());
	        //int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
	        //        4, getActivity().getResources().getDisplayMetrics());
	        //text.setPadding(padding, padding, padding, padding);
	        //scroller.addView(text);
	        //text.setText("");
	        return null;
	    }
	}		





}
