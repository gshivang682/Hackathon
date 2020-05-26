import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.text.DecimalFormat;

import java.io.FileNotFoundException; 
import java.io.PrintWriter; 
import java.util.LinkedHashMap; 
import java.util.Map; 
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 

class Hack
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		String text = "";
		

		ArrayList<Integer> values=new ArrayList<Integer>();
		ArrayList<Double> mb=new ArrayList<Double>();

		try {
			FileReader readfile = new FileReader("Memory.txt");
			BufferedReader readbuffer = new BufferedReader(readfile);
			
			for (int i = 1; i <= 1876; i++) {
				if (i % 2 == 0) {
					text = readbuffer.readLine() + "\n";
					text = text.replaceAll("( )+", " ");

					String[] str=text.split(" ");
					String value=str[2].toString();
					values.add(Integer.parseInt(value));

				} else
					readbuffer.readLine();

					
			}
			
			readbuffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		double avg=0.0;
		double max=0.0;
		
		DecimalFormat df = new DecimalFormat("#0.00");      
		

		for(int i=0;i<values.size();i++)
		{
			double mbValue=(double)values.get(i)/1000;
			mbValue = Double.valueOf(df.format(mbValue));

			avg=avg+mbValue;
			if(mbValue>max)
			{
				max=mbValue;
			}

			mb.add(mbValue);
		}
		max = Double.valueOf(df.format(max));

		// creating JSONObject 
        JSONObject jo = new JSONObject(); 

		avg=avg/mb.size();
		avg = Double.valueOf(df.format(avg));
		jo.put("AverageMemory(MB)", avg); 

        Map m = new LinkedHashMap(mb.size()); 
       
		for(int i=0;i<mb.size();i++)
		{	
			 m.put(i+"s", mb.get(i)); 
		}
		jo.put("values", m);

		jo.put("MaxMemory(MB)", max); 
		jo.put("Usecasename", "HomePage"); 
		
		// writing JSON to file:"JSONExample.json" in cwd 
        PrintWriter pw = new PrintWriter("JSONExample.json"); 
        pw.write(jo.toJSONString()); 
          
        pw.flush(); 
        pw.close(); 
	}
}

