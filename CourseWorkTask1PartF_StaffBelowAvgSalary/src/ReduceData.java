import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class ReduceData extends Reducer<Text, IntWritable,Text,IntWritable> {
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{

        int count = 0;
        int totalSalary = 0;
        float averageSalary = 0;
        ArrayList<Integer> valuesArray = new ArrayList<Integer>();
        String[] keyValues = key.toString().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        String employeeRole = keyValues[0];
        String employeeType = keyValues[1];

        //get the average salary for this Role and Type
        for (IntWritable value:values)
        {

    		int salary = value.get();
    		
        	totalSalary += salary;
            count += 1;
            valuesArray.add(salary);
        }
        averageSalary = totalSalary / count;

        //find out if employee is under average salary
        for (int salary:valuesArray)
        {        	
    		//if over cut of point output them
        	if(salary < averageSalary){
                context.write(new Text(employeeRole +" "+ employeeType +"(averageSalary="+averageSalary+") "+":"), new IntWritable(salary));
        	}
        }
    }
}