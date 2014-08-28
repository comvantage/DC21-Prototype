package eu.comvantage.cw7_iaf_stock_managment;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TableRow;
import android.widget.Toast;


public class MainActivity extends Activity {

	private TextView tv11, tv12, tv13, tv14, tv15, tv16, tv21, tv22, tv23, tv24, tv25, tv26, tvPrice;
	private Button bOrder1, bClose;
	private EditText etOrder01, etOrder011, editText1;
	private Dialog dialog1;
	private TableRow tr02, tr03;
	private int login;
	private String price, amount;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Context context = this;
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		
		Bundle extraBundle = getIntent().getExtras();
		
        if (extraBundle != null) {
        	
    		login = Integer.parseInt(extraBundle.getString("login"));
        	
        }
        else login = 0;
        
        
        Calendar c = Calendar.getInstance();
    	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    	String Date = df.format(c.getTime());
		
		bOrder1 = (Button)findViewById(R.id.bOrder1);
		bClose = (Button)findViewById(R.id.bDashboard);
		etOrder01 = (EditText)findViewById(R.id.etOrder01);
		etOrder011 = (EditText)findViewById(R.id.etOrder011);
		editText1 = (EditText)findViewById(R.id.editText1);
		tr02 = (TableRow)findViewById(R.id.tableRow2);
		tr03 = (TableRow)findViewById(R.id.tableRow3);
		
		tv11 = (TextView)findViewById(R.id.tv11);
		tv12 = (TextView)findViewById(R.id.tvDate);
		tv13 = (TextView)findViewById(R.id.tvStatus1);
		tv14 = (TextView)findViewById(R.id.tv14);
		tv15 = (TextView)findViewById(R.id.tv15);
		tv16 = (TextView)findViewById(R.id.tv16);
		
		tv21 = (TextView)findViewById(R.id.tv21);
		tv22 = (TextView)findViewById(R.id.tv22);
		tv23 = (TextView)findViewById(R.id.tvStatus2);
		tv24 = (TextView)findViewById(R.id.tv24);
		tv25 = (TextView)findViewById(R.id.tv25);
		tv26 = (TextView)findViewById(R.id.tv26);
		tvPrice = (TextView)findViewById(R.id.tvPrice);
		
		tv12.setText(Date);
		
		etOrder011.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				//tvPrice.setText("10000");
				amount = etOrder011.getText().toString();
				price = Integer.toString(Integer.parseInt(amount) * 10) + ",00 €";
				tvPrice.setText(price);
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		bOrder1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle("Order Material");
				builder
				.setMessage("Are you sure you want to order: Tencel, Feel+, Red #013?\n")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						tr02.setBackgroundColor(Color.WHITE);
						tv11.setTypeface(null, Typeface.NORMAL);
						tv12.setTypeface(null, Typeface.NORMAL);
						tv13.setTypeface(null, Typeface.NORMAL);
						tv14.setTypeface(null, Typeface.NORMAL);
						tv15.setTypeface(null, Typeface.NORMAL);
						tv16.setTypeface(null, Typeface.NORMAL);
						tv13.setText("Ordered");
						editText1.requestFocus();
						
						//Toast.makeText(getApplicationContext(), "Stock update: The material was successfully ordered.", Toast.LENGTH_LONG).show();
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
				});

				AlertDialog dialog = builder.create();
				
				dialog.show();
			}
		});
		
		
		
		bClose.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
				gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
				gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21#Mobile_IT_support_feature_G-512054-Dashboard");
				gotoApp.putExtra("login", Integer.toString(login));
				startService(gotoApp);
				finish();
				
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
