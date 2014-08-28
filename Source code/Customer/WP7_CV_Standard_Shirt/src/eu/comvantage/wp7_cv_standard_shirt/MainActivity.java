package eu.comvantage.wp7_cv_standard_shirt;

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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button bSizeXS, bSizeS, bSizeM, bSizeL, bSizeXL, bSizeXXL, bColor01, bColor02, bColor03, bColor04, bColor05, bColor06;
	private int i = 1;
	private ImageView productImage;
	
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
			
		
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
			    if (extras.containsKey("Product")) {
			    	CharSequence textProductNum = extras.getCharSequence("Product");
			    	i = Integer.parseInt(textProductNum.toString());
			    }
			    
			    //price = extras.getCharSequence("Price").toString();
			}
			else {
				i = 1;
			}
				
			
	        TextView productName = (TextView)findViewById(R.id.tvName);        
	        productImage = (ImageView)findViewById(R.id.ivProduct); 
	        TextView tvPrice = (TextView)findViewById(R.id.tvPrice);    
	        
	        switch (i) {
	        case 1: 
	        	productImage.setImageResource(R.drawable.shirt1);
	            productName.setText("First Generation Black / Red");
	            tvPrice.setText("79,99" + " €");
	          break;
	        case 2: 
	        	productImage.setImageResource(R.drawable.shirt2);
	            productName.setText("First Generation Black / Green");
	          break;
	        case 3: 
	        	productImage.setImageResource(R.drawable.shirt3);
	            productName.setText("Dresscode21 Shirt3");
	          break;
	        case 4: 
	        	productImage.setImageResource(R.drawable.shirt4);
	            productName.setText("Dresscode21 Shirt4");
	          break;
	        case 5: 
	        	productImage.setImageResource(R.drawable.shirt5);
	            productName.setText("Dresscode21 Shirt5");
	          break;
	        case 6: 
	        	productImage.setImageResource(R.drawable.shirt6);
	            productName.setText("Dresscode21 Shirt6");
	          break;
	        case 7: 
	        	productImage.setImageResource(R.drawable.shirt7);
	            productName.setText("Dresscode21 Shirt7");
	          break;
	        case 8: 
	        	productImage.setImageResource(R.drawable.shirt8);
	            productName.setText("Dresscode21 Shirt8");
	          break;
	        case 9: 
	        	productImage.setImageResource(R.drawable.shirt9);
	            productName.setText("Dresscode21 Shirt9");
	          break;
	        case 10: 
	        	productImage.setImageResource(R.drawable.shirt9b);
	            productName.setText("Dresscode21 Shirt10");
	          break;
	        case 11: 
	        	productImage.setImageResource(R.drawable.shirt4);
	            productName.setText("Dresscode21 Shirt11");
	          break;
	        case 12: 
	        	productImage.setImageResource(R.drawable.shirt7);
	            productName.setText("Dresscode21 Shirt12");
	          break;
	        case 99: 
	        	productImage.setImageResource(R.drawable.shirt2_cv_text_logo);
	            productName.setText("Dresscode21 ComVantage");
	            tvPrice.setText("99,99" + " €");
	          break;
	        default: 
	        	productImage.setImageResource(R.drawable.shirt1);
	            productName.setText("Dresscode21 Shirt");
	            tvPrice.setText("79,99" + " €");
	      }
				
		 
		 Button bAddC = (Button) findViewById(R.id.bAddToCart);
		 bAddC.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
					gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
					gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512238-Shopping_cart");
								
		
					ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		       		nameValuePairs.add(new BasicNameValuePair("u1","update cv_shoppingcart set Active = 1 where id = 1"));
		                try
		                {
		                    HttpClient httpclient = new DefaultHttpClient();
		                    HttpPost httppost = new HttpPost("http://www.hornet.si/update.php");
		                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		                    HttpResponse response = httpclient.execute(httppost); 
		                    HttpEntity entity = response.getEntity();
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
		 
		 Button bEmbroidery = (Button) findViewById(R.id.bEmbroidery);
		 bEmbroidery.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	/*new AlertDialog.Builder(MainActivity.this).setTitle("To-Do")
	            		.setMessage("IAF will trigger Next App when this button is clicked. ")
	            		.setNeutralButton("Close", null)
	            		.show(); */
	            	
	            	Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
					gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
					gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512232-Embroidery");
					//gotoApp.putExtra("login", extra1);
					startService(gotoApp);
					finish();
	            }
	        
	        });
		 
		 Button bProduction = (Button) findViewById(R.id.bProduction);
		 bProduction.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	/*new AlertDialog.Builder(MainActivity.this).setTitle("To-Do")
	            		.setMessage("IAF will trigger Next App when this button is clicked. ")
	            		.setNeutralButton("Close", null)
	            		.show(); */
	            	
	            	Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
					gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
					gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512235-Price_and_delivery");
					//gotoApp.putExtra("login", extra1);
					startService(gotoApp);
	            }
	        
	        });
		 	
	        bSizeXS = (Button) findViewById(R.id.bSizeXs);
	        bSizeXS.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                ChangeSize(1);           
	            	
	            }
	        
	        });
	        
	        bSizeS = (Button) findViewById(R.id.bSizeS);
	        bSizeS.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	ChangeSize(2);           
	            	
	            }
	        
	        });
	        
	        bSizeM = (Button) findViewById(R.id.bSizeM);
	        bSizeM.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	ChangeSize(3);           
	            	
	            }
	        
	        });
	        
	        bSizeL = (Button) findViewById(R.id.bSizeL);
	        bSizeL.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	ChangeSize(4);           
	            	
	            }
	        
	        });
	        
	        bSizeXL = (Button) findViewById(R.id.bSizeXL);
	        bSizeXL.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	ChangeSize(5);           
	            	
	            }
	        
	        });
	        
	        bSizeXXL = (Button) findViewById(R.id.bSizeXXL);
	        bSizeXXL.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	ChangeSize(6);           
	            	
	            }
	        
	        });
	        
	        bColor01 = (Button) findViewById(R.id.ibColor01);
	        bColor01.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                ChangeColor(1);           
	            	
	            }
	        
	        });
	        
	        bColor02 = (Button) findViewById(R.id.ibColor02);
	        bColor02.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	ChangeColor(2);
	            }
	        
	        });
	        
	        bColor03 = (Button) findViewById(R.id.ibColor03);
	        bColor03.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	ChangeColor(3);           
	            }
	        
	        });
	        
	        bColor04 = (Button) findViewById(R.id.ibColor04);
	        bColor04.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	ChangeColor(4);           
	            	
	            }
	        
	        });
	        
	        bColor05 = (Button) findViewById(R.id.ibColor05);
	        bColor05.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	ChangeColor(5);           
	            	
	            }
	        
	        });
	        
	        bColor06 = (Button) findViewById(R.id.ibColor06);
	        bColor06.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	ChangeColor(6);           
	            	
	            }
	        
	        });
	        
	        
		 
		 
	}

	  
    private void ChangeSize(int i) {
    	switch (i) {
    	case 1:
    		ClearSize();
    		bSizeXS.setBackgroundResource(R.drawable.b_selected);
    	break;
    	case 2:
    		ClearSize();
			bSizeS.setBackgroundResource(R.drawable.b_selected);
		break;
    	case 3:
    		ClearSize();
			bSizeM.setBackgroundResource(R.drawable.b_selected);
		break;
    	case 4:
    		ClearSize();
			bSizeL.setBackgroundResource(R.drawable.b_selected);
		break;
    	case 5:
    		ClearSize();
			bSizeXL.setBackgroundResource(R.drawable.b_selected);
		break;
    	case 6:
    		ClearSize();
			bSizeXXL.setBackgroundResource(R.drawable.b_selected);
		break;
    	default:
    		ClearSize();
    	}
		
	}
    
    private void ChangeColor(int i) {
    	switch (i) {
    	case 1:
    		ClearColor();
    		bColor01.setBackgroundResource(R.drawable.colour_black_blue_selected);
    		productImage.setImageResource(R.drawable.fg_black_blue);
    	break;
    	case 2:
    		ClearColor();
    		bColor02.setBackgroundResource(R.drawable.colour_black_green_selected);
    		productImage.setImageResource(R.drawable.shirt2);
		break;
    	case 3:
    		ClearColor();
    		bColor03.setBackgroundResource(R.drawable.colour_black_red_selected);
    		productImage.setImageResource(R.drawable.shirt1);
		break;
    	case 4:
    		ClearColor();
    		bColor04.setBackgroundResource(R.drawable.colour_black_white_selected);
    		productImage.setImageResource(R.drawable.fg_black_white);
		break;
    	case 5:
    		ClearColor();
    		bColor05.setBackgroundResource(R.drawable.colour_white_black_selected);
    		productImage.setImageResource(R.drawable.fg_white_black);
		break;
    	case 6:
    		ClearColor();
    		bColor06.setBackgroundResource(R.drawable.colour_white_blue_selected);
    		productImage.setImageResource(R.drawable.fg_white_blue);
		break;
    	default:
    		ClearColor();
    	}
		
	}
    
    
    private void ClearSize() {
    	bSizeXS.setBackgroundResource(R.drawable.b_ozadje);
    	bSizeS.setBackgroundResource(R.drawable.b_ozadje);
    	bSizeM.setBackgroundResource(R.drawable.b_ozadje);
    	bSizeL.setBackgroundResource(R.drawable.b_ozadje);
    	bSizeXL.setBackgroundResource(R.drawable.b_ozadje);
    	bSizeXXL.setBackgroundResource(R.drawable.b_ozadje);
	}

    private void ClearColor () {
    	bColor01.setBackgroundResource(R.drawable.color_black_blue);
    	bColor02.setBackgroundResource(R.drawable.color_black_green);
    	bColor03.setBackgroundResource(R.drawable.color_black_red);
    	bColor04.setBackgroundResource(R.drawable.color_black_white);
    	bColor05.setBackgroundResource(R.drawable.color_white_black);
    	bColor06.setBackgroundResource(R.drawable.color_white_blue);
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
