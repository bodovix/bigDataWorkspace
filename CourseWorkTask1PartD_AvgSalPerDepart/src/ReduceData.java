import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class ReduceData extends Reducer<Text, FloatWritable,Text,FloatWritable> {
    @Override
    public void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException{

        int count = 0;
        int totalSalary = 0;
        float averageSalary = 0;
        String serviceType = key.toString();
        //get the average salary for this Role and Type
        for (FloatWritable value:values)
        {
    		float salary = value.get();
        	totalSalary += salary;
            count += 1;
        }
        
        averageSalary = totalSalary / count;
        context.write(new Text(serviceType), new FloatWritable(averageSalary));
    }
}