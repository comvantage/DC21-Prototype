package eu.comvantage.wp7_cv_embroidery;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ImageView ivProduct;
	private EditText etLogo;
	private TextView tvPrice;
	private Boolean cvText, cvLogo;
	
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
		
		
		ivProduct = (ImageView)findViewById(R.id.ivProduct); 
        etLogo = (EditText)findViewById(R.id.editText1); 
        cvText = false;
        cvLogo = false;
        
        Spinner s_font = (Spinner) findViewById(R.id.s_font_type);
        ArrayAdapter<CharSequence> adapter01 = ArrayAdapter.createFromResource(
                this, R.array.Font, android.R.layout.simple_spinner_item);
        adapter01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_font.setAdapter(adapter01);
        
        Spinner s_size = (Spinner) findViewById(R.id.s_font_size);
        ArrayAdapter<CharSequence> adapter02 = ArrayAdapter.createFromResource(
                this, R.array.FontSize, android.R.layout.simple_spinner_item);
        adapter02.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_size.setAdapter(adapter02);
        
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        
        Button upload = (Button) findViewById(R.id.button1);
        upload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                       	
            	if (!cvText) {
            		ivProduct.setImageResource(R.drawable.shirt2_cv_logo);
            		tvPrice.setText("89,99 €");
            	}
            	else {
            		ivProduct.setImageResource(R.drawable.shirt2_cv_text_logo);
            		tvPrice.setText("99,99 €");
            	}
            	cvLogo = true;
            	etLogo.setText("ComVantage_Logo.jpg");
            	
            }
        
        });
        
        Button useText = (Button) findViewById(R.id.bUseText);
        useText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	if (!cvLogo) {
                	ivProduct.setImageResource(R.drawable.shirt2_cv_text);
                	tvPrice.setText("89,99 €");
            	}
                else {
                	ivProduct.setImageResource(R.drawable.shirt2_cv_text_logo);
                	tvPrice.setText("99,99 €");
                }
                cvText = true;
                	
            }
        
        });
        
        Button clearAll = (Button) findViewById(R.id.bClear);
        clearAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cvText = false;
                cvLogo = false;
                etLogo.setText("");
                ivProduct.setImageResource(R.drawable.shirt2);
                tvPrice.setText("79,99 €");
            }
        
        });
        
        Button bSave = (Button) findViewById(R.id.bSave);
        bSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
				gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
				gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512229-Shirt_detail");
				gotoApp.putExtra("Product", "99");
				startService(gotoApp); 
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
