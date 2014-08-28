package eu.comvantage.WP7_CV_Challanges;

import android.R.color;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.view.View.OnClickListener;
import android.widget.TextView;
import eu.comvantage.WP7_CV_Challanges.R;

import java.util.ArrayList;


public class MainActivity extends Activity {
	
	private static OnClickListener contest;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

           contest = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ------------------------
				// TODO KLIK NA DODAJ IDEJO
				// ------------------------
				
				Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
				gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
				gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512244-Chalange_detail");
				//gotoApp.putExtra("login", extra1);
				startService(gotoApp);
				finish();
				
			}
		};
		
		
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
		
		OnClickListener onSortClickListener = new OnClickListener()
		{
		
			@Override
			public void onClick(View v)
			{
				TextView tmp = (TextView) v;
			
				TextView v1 = (TextView)findViewById(R.id.txtTitle);
				TextView v2 = (TextView)findViewById(R.id.txtDurDate);
				TextView v3 = (TextView)findViewById(R.id.txtIdeas);
				TextView v4 = (TextView)findViewById(R.id.txtStatus);
				
				v1.setTextColor(Color.rgb(255, 136, 0));
				v2.setTextColor(Color.rgb(255, 136, 0));
				v3.setTextColor(Color.rgb(255, 136, 0));
				v4.setTextColor(Color.rgb(255, 136, 0));
						
				tmp.setTextColor(Color.BLACK);
			}
		};
		
		((TextView)findViewById(R.id.txtTitle)).setOnClickListener(onSortClickListener);
		((TextView)findViewById(R.id.txtDurDate)).setOnClickListener(onSortClickListener);
		((TextView)findViewById(R.id.txtIdeas)).setOnClickListener(onSortClickListener);
		((TextView)findViewById(R.id.txtStatus)).setOnClickListener(onSortClickListener);

		
		ListView myList = (ListView)findViewById(R.id.lstContest);
		myData md []={
		new myData("4th Dresscode21 Shirt Design Contest","Active", R.drawable.seznam_slika, R.drawable.imageselector_submit_idea, "Design the ultimate Dresscode21 Shirt with our designing tool. It should look linear, dynamic and young.", "Win your own collection and 2 shirts.", "01.10.2014 - 31.12.2014, 12pm."),				
		new myData("Let's SUIT up!","Finished", R.drawable.seznam_slika2, R.drawable.imageselector_view_winner, "Design a trendy suit outfit for the summer 2014 by using our designing tool. Be creative!", "Win your own collection and 1 suit.", "01.06.2014 - 30.08.2014, 12pm."),				
		new myData("Tieless Business Outfit","Finished", R.drawable.seznam_slika3, R.drawable.imageselector_view_winner, "Create the tieless business outfi 2014. Use the designing tool to design or upload images.", "Win your own collection and 1 suit.", "01.05.2014 - 15.07.2014, 12pm."),				
		new myData("3th Dresscode21 Shirt Design Contest","Finished", R.drawable.seznam_slika4, R.drawable.imageselector_view_winner, "Design an elegant Dresscode21 Shirt for women with our designing tool. Be creative!", "Win your own collection and 2 shirts.", "01.02.2014 - 15.04.2014, 12pm."),
		//new myData("3rd Dresscode21 Shirt Design Contest","Finished", R.drawable.imageselector_view_winner),
		//new myData("2nd Dresscode21 Shirt Design Contest","Finished", R.drawable.imageselector_view_winner),
		//new myData("1st Dresscode21 Shirt Design Contest","Finished", R.drawable.imageselector_view_winner),
		};
				
		myList.setAdapter(new myAdapter(this,md));	
		
		
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{	
		getMenuInflater().inflate(R.menu.action_menu, menu);
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

	//
	// class za listiteme
	//
	private class myData
	{
		public String Naziv;
		public String Gotof;
		public int imgSeznam;
		public int imageResource;
		public String Challenge;
		public String Prize;
		public String Duration;
		
		public myData(String Ime, String Tekst_za_gotovo, int ImageSeznam, int ImageResource, String Tekst_challange, String Tekst_prize, String Tekst_duration)
		{
			Naziv = Ime;
			Gotof = Tekst_za_gotovo;
			imgSeznam = ImageSeznam;
			imageResource = ImageResource;
			Challenge = Tekst_challange;
			Prize = Tekst_prize;
			Duration = Tekst_duration;
		}
	
	}
	
	// wrapper za listiteme
	private static class myAdapter extends BaseAdapter {

	    Context context;
	    myData[] data;
	    private static LayoutInflater inflater = null;

	    public myAdapter(Context context, myData[] data) 
	    {
	        // TODO Auto-generated constructor stub
	        this.context = context;
	        this.data = data;
	        inflater = (LayoutInflater) context
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }

	    @Override
	    public int getCount() 
	    {
	        // TODO Auto-generated method stub
	        return data.length;
	    }

	    @Override
	    public Object getItem(int position) 
	    {
	        // TODO Auto-generated method stub
	        return data[position];
	    }

	    @Override
	    public long getItemId(int position) 
	    {
	        // TODO Auto-generated method stub
	        return position;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) 
	    {
	        // TODO Auto-generated method stub
	        View vi = convertView;
	        if (vi == null)
	            vi = inflater.inflate(R.layout.listitem, null);
	        TextView text = (TextView) vi.findViewById(R.id.txtNaziv);
	        text.setText(data[position].Naziv);
	        TextView text2 = (TextView) vi.findViewById(R.id.txtDuration);
	        text2.setText(data[position].Gotof);
	        ImageView img = (ImageView) vi.findViewById(R.id.imgButton);
	        img.setImageResource(data[position].imageResource);
	        
	        TextView text3 = (TextView) vi.findViewById(R.id.txtChallenge);
	        text3.setText(data[position].Challenge);
	        
	        TextView text4 = (TextView) vi.findViewById(R.id.txtPrize);
	        text4.setText(data[position].Prize);
	        
	        TextView text5 = (TextView) vi.findViewById(R.id.txtDurDate);
	        text5.setText(data[position].Duration);
	        
	        img.setOnClickListener(contest);
	       
	        ImageView img1 = (ImageView) vi.findViewById(R.id.imageView1);
	        img1.setImageResource(data[position].imgSeznam);
	        
	        return vi;
	    }
	}
	
}




