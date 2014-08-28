package eu.comvantage.wp7_cv_products;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.res.Resources;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	private GridView mGridMain;
	protected static final int RESULT_LOGIN = 0;
	private TextView tvEmail, tvMyAccount;
	private Button bLogin;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
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
		
		//CheckLogin();
		
		

        mGridMain = (GridView)findViewById(R.id.gvProducts);
        Resources res = getResources();
        List<ProductsGridInfo> listAppInfo = new ArrayList<ProductsGridInfo>();
        listAppInfo.add(new ProductsGridInfo(BitmapFactory.decodeResource(res, R.drawable.shirt1), "First Generation",  "Black / Red", "79,99 €"));
        listAppInfo.add(new ProductsGridInfo(BitmapFactory.decodeResource(res, R.drawable.shirt2), "First Generation",  "Black / Green", "69,99 €"));
        listAppInfo.add(new ProductsGridInfo(BitmapFactory.decodeResource(res, R.drawable.shirt3), "Dresscode21 Shirt3",  "White / Yellow", "69,99 €"));
        listAppInfo.add(new ProductsGridInfo(BitmapFactory.decodeResource(res, R.drawable.shirt4), "Dresscode21 Shirt4",  "White / Black", "59,99 €"));
        listAppInfo.add(new ProductsGridInfo(BitmapFactory.decodeResource(res, R.drawable.shirt5), "Dresscode21 Shirt5",  "Black / Yellow", "79,99 €"));
        listAppInfo.add(new ProductsGridInfo(BitmapFactory.decodeResource(res, R.drawable.shirt6), "Dresscode21 Shirt6",  "Black / White", "79,99 €"));
        listAppInfo.add(new ProductsGridInfo(BitmapFactory.decodeResource(res, R.drawable.shirt7), "Dresscode21 Shirt7",  "White / Black", "69,99 €"));
        listAppInfo.add(new ProductsGridInfo(BitmapFactory.decodeResource(res, R.drawable.shirt8), "Dresscode21 Shirt8",  "White / Purple", "69,99 €"));
        listAppInfo.add(new ProductsGridInfo(BitmapFactory.decodeResource(res, R.drawable.shirt9), "Dresscode21 Shirt9",  "Special edition", "89,99 €"));
        listAppInfo.add(new ProductsGridInfo(BitmapFactory.decodeResource(res, R.drawable.shirt9b), "Dresscode21 Shirt10","SAP edition", "89,99 €"));
        listAppInfo.add(new ProductsGridInfo(BitmapFactory.decodeResource(res, R.drawable.shirt4), "Dresscode21 Shirt11",   "White / Black", "99,99 €"));
        listAppInfo.add(new ProductsGridInfo(BitmapFactory.decodeResource(res, R.drawable.shirt7), "Dresscode21 Shirt12",  "White / Black", "99,99 €"));
        mGridMain.setAdapter(new ProductsGridInfoAdapter(this, listAppInfo));

        mGridMain.setOnItemClickListener(new OnItemClickListener() {
            
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {           
                 
        		String pos = "" + (position+1);
        		String var = "Product";
        		
        		Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
				gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
				gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512229-Shirt_detail");
				gotoApp.putExtra("Product", pos);
				//gotoApp.putExtra("Price", "79,99");
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
