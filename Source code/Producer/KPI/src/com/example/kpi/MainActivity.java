package com.example.kpi;


import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;
import android.content.Intent;

public class MainActivity extends Activity {

	
	public static OnClickListener mygridItemClick;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
					
		mygridItemClick = new OnClickListener() {
			
			@Override
			public void onClick(View v) {							
								
				Intent intent = new Intent (MainActivity.this, KpiList.class);
				intent.putExtra("pos", ((Integer) v.getTag()) + 1);
				MainActivity.this.startActivity(intent);
				//startActivity(new Intent(MainActivity.this, KpiList.class));
				// TODO Auto-generated method stub
				
			}};
		
		GridView myGrid = (GridView)findViewById(R.id.gridView1);
		

		ImageButton ib = (ImageButton)findViewById(R.id.btnallkpi);
		ib.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (MainActivity.this, KpiList.class);
				intent.putExtra("pos", 0);
				MainActivity.this.startActivity(intent);
				
			}
		});
		
		
		myData md []={
		new myData("Customers Acquired:", "Customers Orders:", "32", "19", R.drawable.marketing),				
		new myData("Effectiveness:", "Rejection Rate:", "85 %", "1 %", R.drawable.manufacturing),
		new myData("Sales Forecast:", "Materials Procurement:", "145.000 €", "120 units", R.drawable.revenue),
		new myData("Carbon Footprint:", "", "85 %", "", R.drawable.sustainability),
		};
				
		myGrid.setAdapter(new myAdapter(this,md));
				
		
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

	        return null;
	    }
	}		
	
	
	//
	// class za listiteme
	//
	private class myData
	{
		public String lbl1;
		public String lbl2;
		
		public String txt1;
		public String txt2;

		public int rID;
		
		public myData(String Lbl1, String Lbl2, String Txt1, String Txt2, int RID)
		{
			lbl1 = Lbl1;
			lbl2 = Lbl2;
			txt1 = Txt1;
			txt2 = Txt2;
			rID = RID;
						
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
	        {
	        		vi = inflater.inflate(R.layout.griditem, null);
	        }
	        
	        TextView text = (TextView) vi.findViewById(R.id.lbl1);
	        text.setText(data[position].lbl1);
	        TextView text2 = (TextView) vi.findViewById(R.id.lbl2);
	        text2.setText(data[position].lbl2);
	        	    	        
	        ImageView img1 = (ImageView) vi.findViewById(R.id.imgButton);
	        img1.setImageResource(data[position].rID);
	        img1.setOnClickListener(mygridItemClick);					
	        img1.setTag(position);
	        
	        TextView text3= (TextView) vi.findViewById(R.id.txt1);
	        text3.setText(data[position].txt1);
	        TextView text4 = (TextView) vi.findViewById(R.id.txt2);
	        text4.setText(data[position].txt2);
	        	
	        return vi;
	    }
	}		
	
}
