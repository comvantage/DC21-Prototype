package eu.comvantage.wp7_cv_price_and_delivery;


import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	private SeekBar sbDistance, sbPrice, sbTime;
	private TextView tvDistance, tvPrice, tvTime;
	private ImageView ivMap;
	
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.setThreadPolicy(policy);
		
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
		
		
		sbDistance = (SeekBar)findViewById(R.id.sbDistance);
		sbPrice = (SeekBar)findViewById(R.id.sbPrice);
		sbTime = (SeekBar)findViewById(R.id.sbTime);
		
		tvDistance = (TextView)findViewById(R.id.tvDistance);
		tvPrice = (TextView)findViewById(R.id.tvPrice);
		tvTime = (TextView)findViewById(R.id.tvTime);
		
		ivMap = (ImageView)findViewById(R.id.ivMap);
		
		sbDistance.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {       
   
		    public void onStopTrackingTouch(SeekBar seekBar) {      
		       
		    	//ivMap.setImageResource(R.drawable.map_cheap_far);
		    }       

		         
		    public void onStartTrackingTouch(SeekBar seekBar) {     
		        // TODO Auto-generated method stub      
		    }       

		        
		    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {     
		    	if (progress >= 1200) 
		    		ivMap.setImageResource(R.drawable.map_cheap_far);
		    	else if (progress >= 500)
		    		ivMap.setImageResource(R.drawable.map_cheap_far_fast);	
		    	else
		    		ivMap.setImageResource(R.drawable.map_expensive_close);    
		    	
		        tvDistance.setText(String.valueOf(progress + 20) + " km");
		       
		    }       
		});
		
		sbPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {       
			   
		    public void onStopTrackingTouch(SeekBar seekBar) {      
		        // TODO Auto-generated method stub      
		    }       

		         
		    public void onStartTrackingTouch(SeekBar seekBar) {     
		        // TODO Auto-generated method stub      
		    }       

		        
		    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {     
		    	
		    	if (progress >= 100) 
		    		ivMap.setImageResource(R.drawable.map_cheap_far);
		    	else if (progress >= 150)
		    		ivMap.setImageResource(R.drawable.map_cheap_far_fast);	
		    	else
		    		ivMap.setImageResource(R.drawable.map_expensive_close);     

		        tvPrice.setText(String.valueOf(progress + 50) + " €");
		    	

		    }       
		}); 
		
		sbTime.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {       
			   
		    public void onStopTrackingTouch(SeekBar seekBar) {      
		        // TODO Auto-generated method stub      
		    }       

		         
		    public void onStartTrackingTouch(SeekBar seekBar) {     
		        // TODO Auto-generated method stub      
		    }       

		        
		    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {     
		    	
		    	if (progress >= 20) 
		    		ivMap.setImageResource(R.drawable.map_cheap_far);
		    	else if (progress >= 6)
		    		ivMap.setImageResource(R.drawable.map_cheap_far_fast);	
		    	else
		    		ivMap.setImageResource(R.drawable.map_expensive_close); 

		        tvTime.setText(String.valueOf(progress + 2) + " Days");
		
		    }       
		}); 
		
		Button bAddToCart = (Button) findViewById(R.id.bAddToCart);
		bAddToCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
    			gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
    			gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512238-Shopping_cart");
    			//gotoApp.putExtra("login", extra1);
    			
    			
    			String result = null;
	            InputStream is = null;
	       		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	       		nameValuePairs.add(new BasicNameValuePair("u1","update cv_shoppingcart set Active = 1 where id = 1"));
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
