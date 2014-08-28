package eu.comvantage.wp7_cv_recommendation;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView ivR01, ivR02, ivR03, ivR04, ivR05, ivR06;
	private TextView tvR01, tvR02, tvR03, tvR04, tvR05, tvR06;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
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
		
		tvR01 = (TextView)findViewById(R.id.tvR01);  
		tvR02 = (TextView)findViewById(R.id.tvR02);  
		tvR03 = (TextView)findViewById(R.id.tvR03);  
		tvR04 = (TextView)findViewById(R.id.tvR04);  
		tvR05 = (TextView)findViewById(R.id.tvR05);  
		tvR06 = (TextView)findViewById(R.id.tvR06);  
				
        ivR01 = (ImageView)findViewById(R.id.ivR01);
        ivR02 = (ImageView)findViewById(R.id.ivR02); 
        ivR03 = (ImageView)findViewById(R.id.ivR03); 
        ivR04 = (ImageView)findViewById(R.id.ivR04); 
        ivR05 = (ImageView)findViewById(R.id.ivR05); 
        ivR06 = (ImageView)findViewById(R.id.ivR06); 
		
        ivR01.setVisibility(View.INVISIBLE);
        ivR02.setVisibility(View.INVISIBLE);
        ivR03.setVisibility(View.INVISIBLE);
        ivR04.setVisibility(View.INVISIBLE);
        ivR05.setVisibility(View.INVISIBLE);
        ivR06.setVisibility(View.INVISIBLE);
        
        tvR01.setVisibility(View.INVISIBLE);
        tvR02.setVisibility(View.INVISIBLE);
        tvR03.setVisibility(View.INVISIBLE);
        tvR04.setVisibility(View.INVISIBLE);
        tvR05.setVisibility(View.INVISIBLE);
        tvR06.setVisibility(View.INVISIBLE);
        
        Button bRec = (Button)findViewById(R.id.bRec);
        bRec.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				ivR01.setVisibility(View.VISIBLE);
		        ivR02.setVisibility(View.VISIBLE);
		        ivR03.setVisibility(View.VISIBLE);
		        ivR04.setVisibility(View.VISIBLE);
		        ivR05.setVisibility(View.VISIBLE);
		        ivR06.setVisibility(View.VISIBLE);
		        
		        tvR01.setVisibility(View.VISIBLE);
		        tvR02.setVisibility(View.VISIBLE);
		        tvR03.setVisibility(View.VISIBLE);
		        tvR04.setVisibility(View.VISIBLE);
		        tvR05.setVisibility(View.VISIBLE);
		        tvR06.setVisibility(View.VISIBLE);
				
			}
		});
        
        Button bClear = (Button)findViewById(R.id.bClear);
        bClear.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				ivR01.setVisibility(View.INVISIBLE);
		        ivR02.setVisibility(View.INVISIBLE);
		        ivR03.setVisibility(View.INVISIBLE);
		        ivR04.setVisibility(View.INVISIBLE);
		        ivR05.setVisibility(View.INVISIBLE);
		        ivR06.setVisibility(View.INVISIBLE);
		        
		        tvR01.setVisibility(View.INVISIBLE);
		        tvR02.setVisibility(View.INVISIBLE);
		        tvR03.setVisibility(View.INVISIBLE);
		        tvR04.setVisibility(View.INVISIBLE);
		        tvR05.setVisibility(View.INVISIBLE);
		        tvR06.setVisibility(View.INVISIBLE);
				
			}
		});
        
        Button bClose = (Button)findViewById(R.id.bClose);
        bClose.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
				gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
				gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512214-DC21_Shop_Dashboard");
				gotoApp.putExtra("login", "1");
				startService(gotoApp);
				finish();
				
			}
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_menu, menu);
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.action_cart:
	    	Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
			gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
			gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512238-Shopping_cart");
			//gotoApp.putExtra("login", extra1);
			startService(gotoApp); 
	      break;
	    case R.id.action_filter:
	      Toast.makeText(this, "Filter selected.", Toast.LENGTH_SHORT)
	          .show();
	      break;
	    default:
	      break;
	    }

	    return true;
	  } 
	
	//
	// tablistener template class 
	//
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

	}


}
