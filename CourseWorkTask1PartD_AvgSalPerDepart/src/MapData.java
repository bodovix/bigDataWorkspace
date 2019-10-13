import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;




public class MapData extends Mapper<LongWritable,Text,Text,FloatWritable> {
@Override

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{

		String agencyNumber;
		String serviceType;
		Float salary;
	    String line = value.toString();
	    		
	    	//get required values from JSon object
	    	String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	    	//skip header row
	    	agencyNumber = data[0];
	    	if(agencyNumber.equals("AGENCY #")){
	    		return;
	    	}
	    	serviceType = data[3];
	    	salary = Float.parseFloat(data[5]);
	    	//add to output key pair
	    	context.write(new Text(serviceType), new FloatWritable(salary));
	}
}