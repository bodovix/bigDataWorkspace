import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class ReduceData extends Reducer<Text, IntWritable,Text,IntWritable> {
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{

        int count = 0;
        String employeeType;
        //get the average salary for this Role and Type
        for (IntWritable value:values)
        {
    		count += value.get();
        }
        employeeType = key.toString();
            context.write(new Text(employeeType), new IntWritable(count));
    }
}