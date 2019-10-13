import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class ReduceData extends Reducer<Text, IntWritable,Text,IntWritable> {
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{

        int count = 0;
        String employeeRole;
        //get the average salary for this Role and Type
        for (IntWritable value:values)
        {
    		count += value.get();
        }
        employeeRole = key.toString();
            context.write(new Text(employeeRole), new IntWritable(count));
    }
}