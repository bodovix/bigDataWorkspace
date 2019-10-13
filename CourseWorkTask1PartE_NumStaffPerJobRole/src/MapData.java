import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapData extends Mapper<LongWritable,Text,Text,IntWritable> {
@Override

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{

		String agencyNumber;
		String employeeRole;
	    String line = value.toString();	    	
	    		
	    //get required values from JSon object
	    String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	   	agencyNumber = data[0];
	   	if(agencyNumber.equals("AGENCY #")){
	   		return;
	   	}
    	employeeRole = data[2];
	   	//add to output key pair
	   	context.write(new Text(employeeRole), new IntWritable(1));
	}
}