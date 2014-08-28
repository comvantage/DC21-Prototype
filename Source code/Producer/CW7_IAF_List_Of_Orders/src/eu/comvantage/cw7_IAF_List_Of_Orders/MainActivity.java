package eu.comvantage.cw7_IAF_List_Of_Orders;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

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
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity  {
	private TableLayout tabOrder;
	private TableRow tabR, tabR0, tabR1;
	private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tvOrderNo, tvDetOrderNo, tvDetProcessor, tvDetStatus, tvDetShirtType, tvDetDelivered;
	private ImageView ivShirt, ivProcess;
	private ArrayList<Order> orders = DataGenerator.getDummyData();
	private int state = 0;
	private LinearLayout ll_details;
	private Button bNext, bPrint, bPrintLabel;
	private ImageButton ibFullScreen;
	private int currentOrder = 0, login, active01, active02, status01, status02;
  	private String extra1 = "0", Date, Date1;
	
  	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        StrictMode.setThreadPolicy(policy);
        
        Bundle extraBundle = getIntent().getExtras();
        if (extraBundle != null) {
        	extra1 = extraBundle.getString("login");
        }
        else extra1 = "0";
        
        login = Integer.parseInt(extra1.toString());
        
        Shirt1(); Shirt2();
        
        Calendar c = Calendar.getInstance();
    	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    	Date = df.format(c.getTime());
    	c.add(c.DATE, 6);
    	Date1 = df.format(c.getTime());
        
        renderTable(true);
        
        
        
    }


    private void renderTable(Boolean isEnabled){ 
    	tabOrder = (TableLayout)findViewById(R.id.tabOrders);
    	tabOrder.removeAllViews();  
    	
    	tabR0 = new TableRow(this);
    	tabR0.setPadding(1,1,1,1);
    	tabR0.setBackgroundColor(Color.parseColor("#999999"));
    	
    	tabR1 = new TableRow(this);
    	tabR1.setPadding(1,1,1,1);
    	tabR1.setBackgroundColor(Color.parseColor("#999999"));
    	
    	tabR = new TableRow(this);
    	tv1  = new TextView(this);
    	tv2  = new TextView(this);
    	tv3  = new TextView(this);
    	tv4  = new TextView(this);
    	tv5  = new TextView(this);
    	tv6  = new TextView(this);
    	tv7  = new TextView(this);
    	tv8  = new TextView(this);
    	
    	tabR.setPadding(0, 15, 0, 15);
    	tabR.setBackgroundColor(Color.parseColor("#D5D5D5"));
    	
    	tv1.setGravity(0);
    	tv1.setTextSize(18);
    	tv1.setTypeface(null, Typeface.BOLD);
    	tv1.setText("Order No.");
    	
    	tv2.setGravity(0);
    	tv2.setTextSize(18);
    	tv2.setTypeface(null, Typeface.BOLD);
    	tv2.setText("Received");
    	
    	tv3.setGravity(0);
    	tv3.setTextSize(18);
    	tv3.setTypeface(null, Typeface.BOLD);
    	tv3.setText("Expected");
    	
    	tv4.setGravity(0);
    	tv4.setTextSize(18);        
    	tv4.setTypeface(null, Typeface.BOLD);
    	tv4.setText("Status");
    	
    	tv5.setGravity(0);
    	tv5.setTextSize(18);        
    	tv5.setTypeface(null, Typeface.BOLD);
    	tv5.setText("Amount");
    	
    	tv6.setGravity(0);
    	tv6.setTextSize(18);        
    	tv6.setTypeface(null, Typeface.BOLD);
    	tv6.setText("Product");
    	
    	tv7.setGravity(0);
    	tv7.setTextSize(18);        
    	tv7.setTypeface(null, Typeface.BOLD);
    	tv7.setText("Customer");
    	
    	tv8.setGravity(0);
    	tv8.setTextSize(18);        
    	tv8.setTypeface(null, Typeface.BOLD);
    	tv8.setText("Processor");
    	
    	tabR.addView(tv1);
    	tabR.addView(tv2);
    	tabR.addView(tv3);
    	tabR.addView(tv4);
    	tabR.addView(tv5);
    	tabR.addView(tv6);
    	tabR.addView(tv7);
    	tabR.addView(tv8);
       
    	if(!isEnabled){
    		tv1.setTextColor(Color.GRAY);
    		tv2.setTextColor(Color.GRAY);
    		tv3.setTextColor(Color.GRAY);
    		tv4.setTextColor(Color.GRAY);
    		tv5.setTextColor(Color.GRAY);
    		tv6.setTextColor(Color.GRAY);
    		tv7.setTextColor(Color.GRAY);
    		tv8.setTextColor(Color.GRAY);
    	}else {
    		tv1.setTextColor(Color.BLACK);
    		tv2.setTextColor(Color.BLACK);
    		tv3.setTextColor(Color.BLACK);
    		tv4.setTextColor(Color.BLACK);
    		tv5.setTextColor(Color.BLACK);
    		tv6.setTextColor(Color.BLACK);
    		tv7.setTextColor(Color.BLACK);
    		tv8.setTextColor(Color.BLACK);
    	}
    	
    	tabOrder.addView(tabR0);
    	tabOrder.addView(tabR);
    	tabOrder.addView(tabR1);
    	
        for(int i=0;i<orders.size();i++){
        	
        	//exclude completed orders
        	//if(orders.get(i).getStatuscode()==6) continue;
        	
        	tabR = new TableRow(this);
        	tv1  = new TextView(this);
        	tv2  = new TextView(this);
        	tv3  = new TextView(this);
        	tv4  = new TextView(this);
        	tv5  = new TextView(this);
        	tv6  = new TextView(this);
        	tv7  = new TextView(this);
        	tv8  = new TextView(this);
        	
        	tabR.setPadding(0, 10, 0, 10);
        	
        	tv1.setText(orders.get(i).getId());
        	tv1.setGravity(0);
        	tv1.setTextSize(18);
        	
        	tv2.setText(orders.get(i).getOrderdate());
        	tv2.setGravity(0);
        	tv2.setTextSize(18);
        	
        	//tv3.setText(orders.get(i).getProcessor());
        	tv3.setText(orders.get(i).getDelivered());
        	tv3.setGravity(0);
        	tv3.setTextSize(18);
        	
        	tv4.setText(getStatusName(orders.get(i).getStatuscode()));
        	tv4.setGravity(0);
        	tv4.setTextSize(18);
        	
        	tv5.setText("1");
        	tv5.setGravity(0);
        	tv5.setTextSize(18);
        	
        	tv6.setText(orders.get(i).getShirttype());
        	tv6.setGravity(0);
        	tv6.setTextSize(18);
        	
        	tv7.setText(orders.get(i).getClient());
        	tv7.setGravity(0);
        	tv7.setTextSize(18);
        	
        	tv8.setText(orders.get(i).getProcessor());
        	tv8.setGravity(0);
        	tv8.setTextSize(18);
        	
        	tabR.addView(tv1);
        	tabR.addView(tv2);
        	tabR.addView(tv3);
        	tabR.addView(tv4);
        	tabR.addView(tv5);
        	tabR.addView(tv6);
        	tabR.addView(tv7);
        	tabR.addView(tv8);
        	
        	if(!isEnabled){
        		tv1.setTextColor(Color.GRAY);
        		tv2.setTextColor(Color.GRAY);
        		tv3.setTextColor(Color.GRAY);
        		tv4.setTextColor(Color.GRAY);
        		tv5.setTextColor(Color.GRAY);
        		tv6.setTextColor(Color.GRAY);
        		tv7.setTextColor(Color.GRAY);
        		tv8.setTextColor(Color.GRAY);
        	}else {
        		tv1.setTextColor(Color.BLACK);
        		tv2.setTextColor(Color.BLACK);
        		tv3.setTextColor(Color.BLACK);
        		tv4.setTextColor(Color.BLACK);
        		tv5.setTextColor(Color.BLACK);
        		tv6.setTextColor(Color.BLACK);
        		tv7.setTextColor(Color.BLACK);
        		tv8.setTextColor(Color.BLACK);
        	}
        	
        	if (orders.get(i).getStatuscode()==0) {
    			tabR.setBackgroundColor(Color.parseColor("#E1EFB6"));
        	}
        	
        	
        	
        	if (login == 0 ) {
        		switch(i){
        			case 0: if (active01 == 1) { 
        						tabOrder.addView(tabR); tv4.setText(getStatusName(status01)); 
        						tv2.setText(Date);
        						tv3.setText(Date1);
        			        
        						if (status01 == 0 || status01 == 1 || status01 == 4 || status01 == 5 || status01 == 6) tv8.setText("ProShirt d.o.o.");
        						else tv8.setText("StickIt d.o.o.");
        					}  				
        			break;
        			
        			case 1: if (active02 == 1) {
        						tabOrder.addView(tabR); tv4.setText(getStatusName(status02));
        						tv2.setText(Date);
        						tv3.setText(Date1);
        						if (status02 == 0 || status02 == 1 || status02 == 4 || status02 == 5 || status02 == 6) tv8.setText("ProShirt d.o.o.");
        						else tv8.setText("StickIt d.o.o.");
        					}  					
        			break;
        			
        			default: tabOrder.addView(tabR);
        		}
        	}
        	else if (login == 1) {
        		switch(i){
        			case 0: if (active01 == 1) {
        						if (status01 == 0 || status01 == 1 || status01 == 4 || status01 == 5 || status01 == 6) {
        							tabOrder.addView(tabR); tv4.setText(getStatusName(status01)); tv8.setText("ProShirt d.o.o."); 	
        							tv2.setText(Date);
        							tv3.setText(Date1);
        						}
        			}
        			break;
        			
        			case 1: if (active02 == 1) {
        						if (status02 == 0 || status02 == 1 || status02 == 4 || status02 == 5 || status02 == 6) {
        							tabOrder.addView(tabR); tv4.setText(getStatusName(status02)); tv8.setText("ProShirt d.o.o.");
        							tv2.setText(Date);
        							tv3.setText(Date1);
        				}
        			}
        				
        			break;
        			
        			default: if (orders.get(i).getProcessor() == "ProShirt d.o.o.") tabOrder.addView(tabR);
        		}
        	}
        	else if (login == 2) {
        		
        			switch(i){
        			case 0: if (active01 == 1) {
        				if (status01 == 2 || status01 == 3) {		
        					tabOrder.addView(tabR);  tv4.setText(getStatusName(status01)); tv8.setText("StickIt d.o.o."); 
        					tv2.setText(Date);
        					tv3.setText(Date1);
        				}
        			}
        			break;
        			
        			case 1: if (active02 == 1) {
        				if (status02 == 2 || status02 == 3) {
        					tabOrder.addView(tabR);  tv4.setText(getStatusName(status02)); tv8.setText("StickIt d.o.o."); 
        					tv2.setText(Date);
        					tv3.setText(Date1);
        				}
        			}
        				
        			break;
        			
        			default: if (orders.get(i).getProcessor() == "StickIt d.o.o.") tabOrder.addView(tabR);
        		
        		}
     
        	}
        	
        	if(isEnabled){
        	tabR.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					
					String order_id = ((TextView)((TableRow)v).getChildAt(0)).getText().toString();
					selectTableEntry(order_id);
					
					int count = tabOrder.getChildCount();
								
					for (int i = 0; i < count; i++) {
					    View child = tabOrder.getChildAt(i);
					    child.setBackgroundColor(Color.parseColor("#CFCFCF"));
					    ((TableRow)v).setBackgroundColor(Color.WHITE);

					}
				}
			});}
        	
        	
        }
    	
    }

    
    
    private void selectTableEntry(String order_id){
    /*	
    	currentOrder = getOrderbyId(order_id);
    	ll_details = (LinearLayout)findViewById(R.id.ll_details);
		ll_details.setVisibility(View.VISIBLE);
		tvOrderNo = (TextView)findViewById(R.id.tvOrderNo);
		tvOrderNo.setText("Order No.: " + orders.get(currentOrder).getId());
		ivShirt = (ImageView)findViewById(R.id.ivShirt);
		ivShirt.setImageResource(orders.get(currentOrder).getShirtimage());
		tvDetOrderNo = (TextView)findViewById(R.id.tvDetOrderNo);
		tvDetOrderNo.setText(orders.get(currentOrder).getId());
		tvDetProcessor = (TextView)findViewById(R.id.tvDetProcessor);
		tvDetProcessor.setText(orders.get(currentOrder).getProcessor());
		tvDetStatus = (TextView)findViewById(R.id.tvDetStatus);
		tvDetStatus.setText(getStatusName(orders.get(currentOrder).getStatuscode()));
		tvDetShirtType = (TextView)findViewById(R.id.tvDetShirtType);
		tvDetShirtType.setText(orders.get(currentOrder).getShirttype());
		prepareProcessBar(orders.get(currentOrder).getStatuscode());
		state = orders.get(currentOrder).getStatuscode();
		*/
    	currentOrder = getOrderbyId(order_id);
    	
    	Intent gotoNextApp = new Intent("eu.comvantage.iaf.NEXT");
    	Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
		gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
		gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21#Mobile_IT_support_feature_G-512051-Order_details_and_processing");
		gotoApp.putExtra("shirt", order_id);
		gotoApp.putExtra("shirtdesc", orders.get(currentOrder).getShirttype());
		gotoApp.putExtra("received", Date);
		gotoApp.putExtra("expected", Date1);
		gotoApp.putExtra("customer", orders.get(currentOrder).getClient());
		//gotoApp.putExtra("status", Integer.toString(orders.get(currentOrder).getStatuscode()));
		
		if (order_id  == "2014111001") gotoApp.putExtra("status", Integer.toString(status01));
		else if (order_id  == "2014111002") gotoApp.putExtra("status", Integer.toString(status02));
		else gotoApp.putExtra("status", "6");
		
		gotoApp.putExtra("login", Integer.toString(login));
				
		startService(gotoApp);
		
		finish();
    }

    private int getOrderbyId(String id){
    	for(int i=0;i<orders.size();i++) {
    		if (orders.get(i).getId().equals(id)) return i;
    	}
    	return -1;
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
                
                active01 = Integer.parseInt(h1.get("Active").toString());
                status01 = Integer.parseInt(h1.get("Status").toString());
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
                
                active02 = Integer.parseInt(h1.get("Active").toString());
                status02 = Integer.parseInt(h1.get("Status").toString());
            }
            catch(Exception e)
            {
               Log.e("log_tag", "Error converting result "+e.toString());

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
