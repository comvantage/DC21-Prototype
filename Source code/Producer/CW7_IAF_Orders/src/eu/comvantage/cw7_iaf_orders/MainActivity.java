package eu.comvantage.cw7_iaf_orders;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import eu.comvantage.cw7_iaf_orders.R.drawable;
import android.R.color;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button bStep, bPrint, bPrintClose, bReject;
	private LinearLayout llPrintLabel, llButtons; 
	private int CurrentStep = 0, login, active01, active02, status01, status02;
	private TextView tvColor, tvSize, tvEmbroidery, tvCollar, tvSleeves, tvCompleted, tvShirt, tvOrderNo1, tvOrderNo, tvReceived, tvExpected, tvClient, tvProduct, tvStatus, tvSendName, tvSendAddress, tvSendCity, tvSendFromName, tvSendFromAddress, tvSendFromCity, tvOrderStat;
	private ImageView ivStep, ivShirt;
	private Dialog dialog;
	private String shirt;
	
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.setThreadPolicy(policy);
		
		ivShirt = (ImageView)findViewById(R.id.ivShirt);
		tvShirt = (TextView)findViewById(R.id.tvShirt);
		tvOrderNo1 = (TextView)findViewById(R.id.tvOrderNo1);
		tvReceived = (TextView)findViewById(R.id.tvReceived);
		tvExpected = (TextView)findViewById(R.id.tvExpected);
		tvClient = (TextView)findViewById(R.id.tvCustomer);
		tvProduct = (TextView)findViewById(R.id.tvProduct);
		tvStatus = (TextView)findViewById(R.id.tvStatus);
		tvSendName = (TextView)findViewById(R.id.tvSendName);
		tvSendAddress = (TextView)findViewById(R.id.tvSendAddress);
		tvSendCity = (TextView)findViewById(R.id.tvSendCity);
		tvSendFromName = (TextView)findViewById(R.id.tvSendFromName);
		tvSendFromAddress = (TextView)findViewById(R.id.tvSendFromAddress);
		tvSendFromCity = (TextView)findViewById(R.id.tvSendFromCity);
		tvOrderStat = (TextView)findViewById(R.id.tvOrderStat);
		tvEmbroidery = (TextView)findViewById(R.id.tvEmbroidery);
		tvCollar = (TextView)findViewById(R.id.tvCollar);
		tvSleeves = (TextView)findViewById(R.id.tvC1);
		tvSize = (TextView)findViewById(R.id.tvSize);
		tvColor = (TextView)findViewById(R.id.tvColor);
		
		
		final Context context = this;
		
		Bundle extraBundle = getIntent().getExtras();
		
        if (extraBundle != null) {
        	
        	shirt = extraBundle.getString("shirt");
        	tvShirt.setText(extraBundle.getString("shirtdesc"));
        	tvOrderNo1.setText(extraBundle.getString("shirt"));
    		tvReceived.setText(extraBundle.getString("received"));
    		tvExpected.setText(extraBundle.getString("expected"));
    		tvClient.setText(extraBundle.getString("customer"));
    		
    		CurrentStep = Integer.parseInt(extraBundle.getString("status"));
    		tvStatus.setText(getStatusName(CurrentStep));		
    		tvProduct.setText("Drescode 21 Shirt, " + extraBundle.getString("shirtdesc"));
    		
    		login = Integer.parseInt(extraBundle.getString("login"));
        	
        }
        else { shirt = ""; login = 1; }
        
        if (shirt.toString().equals("2014111001")) {
    		ivShirt.setImageResource(drawable.shirt2_embroidery);
    		active01 = 1; active02 = 0;
    		tvProduct.setText("First Generation Black / Red");
    		tvSize.setText("M");
    		
    	}
    	else if (shirt.toString().equals("2014111002")) {
    		ivShirt.setImageResource(drawable.shirt_individual_green);
    		active01 = 0; active02 = 1;
    		tvProduct.setText("Individual Design White / Green");
    		 
    		tvEmbroidery.setText("None");
    		tvCollar.setText("V-neck");
    		tvSleeves.setText("Long");
    		tvSize.setText("S");
    		tvColor.setText("White(#FFF), Green(#BBC)");
    	}
    	
        
		ivStep = (ImageView)findViewById(R.id.ivStep);
		llPrintLabel = (LinearLayout)findViewById(R.id.llPrintLabel);
		//llPrintLabel = (LinearLayout)findViewById(R.id.llPrintLabel);
		llButtons  = (LinearLayout)findViewById(R.id.llButtons);
		bPrint = (Button)findViewById(R.id.bPrint);
		bPrintClose = (Button)findViewById(R.id.bPrintC);
		bStep = (Button)findViewById(R.id.bStep);
		dialog = new Dialog(context);
		tvCompleted = (TextView)findViewById(R.id.tvCompleted);
		bReject = (Button)findViewById(R.id.bReject);
		ivStep.setVisibility(View.INVISIBLE);
		
				
		bPrint.setOnClickListener(new OnClickListener() { 
			public void onClick(View v) {
				dialog.show();
				
			}
		});
		
		/*
		bPrintClose.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		*/
		
		
bStep.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
							
				switch(CurrentStep){
	    			case 0: tvOrderStat.setVisibility(View.INVISIBLE);
	    					ivStep.setVisibility(View.VISIBLE);
	    					ivStep.setImageResource(R.drawable.order_step_01);
	    					bStep.setText("Finish Step");
	    					bPrint.setVisibility(View.VISIBLE);
	    					llPrintLabel.setVisibility(View.VISIBLE);
	    					dialog.setContentView(R.layout.print_embroidery_p);
	    					dialog.setTitle("Sending Order: 2014101001");
	    					tvCompleted.setVisibility(View.INVISIBLE);
	    					bReject.setVisibility(View.GONE);
	    					tvStatus.setText(getStatusName(CurrentStep));
	    					
	    					if (active01 == 1) {
								ShirtUpdate("update cv_p_orders set Status = 1 where id = 1");
							}
							else {
								ShirtUpdate("update cv_p_orders set Status = 1 where id = 2");
							}
	    					
	    					CurrentStep++;	
	    			break;
	    			
	    			case 1: tvOrderStat.setVisibility(View.INVISIBLE);
					ivStep.setVisibility(View.VISIBLE);
	    				
	    				ivStep.setImageResource(R.drawable.order_step_01_01);
							bStep.setText("Activate Step");
							bPrint.setVisibility(View.VISIBLE);
							llPrintLabel.setVisibility(View.VISIBLE);
					
							tvSendName.setText("ProShirt d.o.o.");
							tvSendAddress.setText("Mlinska 22");
							tvSendCity.setText("2000 Maribor");
							tvSendFromName.setText("StickIt d.o.o.");
							tvSendFromAddress.setText("Pohorska 13");
							tvSendFromCity.setText("2000 Maribor");
					
							dialog.setContentView(R.layout.print_embroidery); 
							dialog.setTitle("Sending Order: 2014101001");
							tvStatus.setText(getStatusName(CurrentStep));
							//dialog.setCancelable(true);
							
							if (active01 == 1) {
								ShirtUpdate("update cv_p_orders set Status = 2 where id = 1");
							}
							else {
								ShirtUpdate("update cv_p_orders set Status = 2 where id = 2");
							}
							
							
					     					
							CurrentStep++;	
							
							if (login == 1 && active01 == 1) { 
								
								AlertDialog.Builder builder = new AlertDialog.Builder(context);
								builder.setTitle("Order Status");
								builder
								.setMessage("Are you sure you want to send the shirt to StickIt?\n")
								.setCancelable(false)
								.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
										gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
										gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21#Mobile_IT_support_feature_G-512054-Dashboard");
										gotoApp.putExtra("login", Integer.toString(login));
										startService(gotoApp);
										finish();
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
							
							if (login == 1 && active02 == 1) {
								CurrentStep = 4;
								ivStep.setImageResource(R.drawable.order_step_03_01);
							}
						
					break;
	    			
	    			case 2: tvOrderStat.setVisibility(View.INVISIBLE);
					ivStep.setVisibility(View.VISIBLE);
					ivStep.setImageResource(R.drawable.order_step_02);
							bStep.setText("Finish Step");
							bPrint.setVisibility(View.VISIBLE);
							llPrintLabel.setVisibility(View.VISIBLE);
							
							tvSendName.setText("ProShirt d.o.o.");
							tvSendAddress.setText("Mlinska 22");
							tvSendCity.setText("2000 Maribor");
							tvSendFromName.setText("StickIt d.o.o.");
							tvSendFromAddress.setText("Pohorska 13");
							tvSendFromCity.setText("2000 Maribor");
							
							dialog.setContentView(R.layout.print_embroidery); 
							dialog.setTitle("Sending Order: 2014101001");
							//dialog.setCancelable(true);
	    					
							tvStatus.setText(getStatusName(CurrentStep));
							
							if (active01 == 1) {
								ShirtUpdate("update cv_p_orders set Status = 3 where id = 1");
							}
							else {
								ShirtUpdate("update cv_p_orders set Status = 3 where id = 2");
							}
							
							
							CurrentStep++;	
					break;
	    			
	    			case 3: tvOrderStat.setVisibility(View.INVISIBLE);
					ivStep.setVisibility(View.VISIBLE);
	    				ivStep.setImageResource(R.drawable.order_step_03);
	    					bStep.setText("Activate Step");
	    					bPrint.setVisibility(View.INVISIBLE);
	    					llPrintLabel.setVisibility(View.INVISIBLE);
	    					tvStatus.setText(getStatusName(CurrentStep));
	    					
	    					if (active01 == 1) {
								ShirtUpdate("update cv_p_orders set Status = 4 where id = 1");
							}
							else {
								ShirtUpdate("update cv_p_orders set Status = 4 where id = 2");
							}
	    					
	    					if (login == 2) { 
								
								AlertDialog.Builder builder = new AlertDialog.Builder(context);
								builder.setTitle("Order Status");
								builder
								.setMessage("Are you sure you want to send the shirt to Proshirt?\n")
								.setCancelable(false)
								.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
										gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
										gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21#Mobile_IT_support_feature_G-512054-Dashboard");
										gotoApp.putExtra("login", Integer.toString(login));
										startService(gotoApp);
										finish();
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
	    					
	    					CurrentStep++;
	    			break;
	    			
	    			case 4: tvOrderStat.setVisibility(View.INVISIBLE);
					ivStep.setVisibility(View.VISIBLE);
					ivStep.setImageResource(R.drawable.order_step_03_01);
					
					tvStatus.setText(getStatusName(CurrentStep));
					
	    					bStep.setText("Finish Step");
	    					
	    					if (active01 == 1) {
								ShirtUpdate("update cv_p_orders set Status = 5 where id = 1");
							}
							else {
								ShirtUpdate("update cv_p_orders set Status = 5 where id = 2");
							}
	    					
	    					CurrentStep++;
	    			break;
	    			
	    			case 5: tvOrderStat.setVisibility(View.INVISIBLE);
					ivStep.setVisibility(View.VISIBLE);
	    				ivStep.setImageResource(R.drawable.order_step_04);
	    					bStep.setText("Complete Order");
	    					bPrint.setVisibility(View.VISIBLE);
							llPrintLabel.setVisibility(View.VISIBLE);
							tvStatus.setText(getStatusName(CurrentStep));
							
							tvSendName.setText("Peter Novak");
							tvSendAddress.setText("Hugo wolf Gasse 6a");
							tvSendCity.setText("8010 Graz");
							tvSendFromName.setText("ProShirt d.o.o.");
							tvSendFromAddress.setText("Mlinska 22");
							tvSendFromCity.setText("2000 Maribor");
							
							dialog.setContentView(R.layout.print_embroidery_c); 
							
							if (active01 == 1) {
								ShirtUpdate("update cv_p_orders set Status = 6 where id = 1");
							}
							else {
								ShirtUpdate("update cv_p_orders set Status = 6 where id = 2");
							}
							
	    					CurrentStep++;
	    			break;
	    			
	    			case 6: tvOrderStat.setVisibility(View.INVISIBLE);
							ivStep.setVisibility(View.VISIBLE);
							ivStep.setImageResource(R.drawable.order_step_04_01);
	    					tvCompleted.setVisibility(View.VISIBLE);
	    					bStep.setText("Close");
	    					llButtons.setBackgroundColor(Color.parseColor("#E1EFB6"));
	    					llPrintLabel.setVisibility(View.INVISIBLE);
	    					bPrint.setVisibility(View.INVISIBLE);
	    							
	    					CurrentStep++;
	    			break;
	    			
	    			case 7:	tvOrderStat.setVisibility(View.INVISIBLE);
							ivStep.setVisibility(View.VISIBLE);
							tvStatus.setText(getStatusName(CurrentStep));
							
							if (login == 1 && active01 == 1) { 
								
								AlertDialog.Builder builder = new AlertDialog.Builder(context);
								builder.setTitle("Material running out of stock");
								builder
								.setMessage("Tencel, Feel+ Red #013 is running out of stock.\n\nReorder?\n")
								.setCancelable(false)
								.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
										gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
										gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21#Mobile_IT_support_feature_G-512045-Stock_list");
										gotoApp.putExtra("login", Integer.toString(login));
										startService(gotoApp);
										finish();
									}
								  })
								.setNegativeButton("No",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										dialog.cancel();
										Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
										gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
										gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21#Mobile_IT_support_feature_G-512054-Dashboard");
										gotoApp.putExtra("login", Integer.toString(login));
										startService(gotoApp);
										finish();
										
									}
								});

								AlertDialog dialog = builder.create();
								
								dialog.show();
								
								
							}
							else {
								Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
								gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
								gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21#Mobile_IT_support_feature_G-512054-Dashboard");
								gotoApp.putExtra("login", Integer.toString(login));
								startService(gotoApp);
								finish();
							}
	    				
					break;
				
				
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private String getStatusName (int s) {
		
		switch (s) {
		case 0: return "New";
		case 1: return "Production of Parts";
		case 2: return "Embroidery";
		case 3: return "Embroidery";
		case 4: return "Assembly of Shirt";
		case 5: return "Delivery";
		case 6: return "Completed";
			default: return null;
		}
		
		
	}
	
	private void Shirt1 () {		
		String result = null;
        InputStream is = null;
        
   		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("s1","select * from cv_p_orders where id = 1"));
            try
            {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://www.hornet.si/select.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost); 
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
            }
        catch(Exception e)
            {
                Log.e("log_tag", "Error in http connection "+e.toString());
                Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();
            }
                            
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) 
                {
                        sb.append(line + "\n");
                        
                }
                is.close();

                result=sb.toString();
                Hashtable h1 = result2HashTable(result);
                
            }
            catch(Exception e)
            {
               Log.e("log_tag", "Error converting result "+e.toString());

            }
            
            
	}
	
	private void Shirt2 () {		
		String result = null;
        InputStream is = null;
        
   		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("s1","select * from cv_p_orders where id = 2"));
            try
            {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://www.hornet.si/select.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost); 
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
            }
        catch(Exception e)
            {
                Log.e("log_tag", "Error in http connection "+e.toString());
                Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();
            }
                            
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) 
                {
                        sb.append(line + "\n");
                        
                }
                is.close();

                result=sb.toString();
                Hashtable h1 = result2HashTable(result);
                
                //active01 = Integer.parseInt(h1.get("Active").toString());
                //status01 = Integer.parseInt(h1.get("Status").toString());
            }
            catch(Exception e)
            {
               Log.e("log_tag", "Error converting result "+e.toString());

            }
            
            
	}
	
	private void ShirtUpdate(String u1) {
		String result = null;
        InputStream is = null;
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("u1", u1));
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.hornet.si/update.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost); 
            Log.e("log_tag", "connection success ");
            
        }
    catch(Exception e)
        {
            Log.e("log_tag", "Error in http connection "+e.toString());
            Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

        }
		
	}
	
	private Hashtable result2HashTable(String result)
	{
		Hashtable ht = new Hashtable();
		
		
		String tmp = result.substring(2, result.length());
		tmp = tmp.substring(0, tmp.length() - 4);
		
		String [] separated = tmp.split(",");
		
		for (int i = 0; i < separated.length; i++)
		{
			separated[i] = separated[i].replace("\"", "");
			String [] separated2 = separated[i].split(":");
			
			ht.put(separated2[0], separated2[1]);			
		}
						
		return ht;
	}
	
}
