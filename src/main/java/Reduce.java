import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @version 1.0
 * @Name ZTY
 * @Date 2022-10-16 21:30
 * @注释
 */
public class Reduce {
    static class ReducerDemo extends Reducer<Text, IntWritable, Text, Text> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            //补充Reduce逻辑
            //补充Reduce逻辑
            //定义一个累加器
            int sum = 0;
            //遍历map输出的value值
            for (IntWritable value : values) {
                int count = value.get();
                sum+=count;
            }
            //   Reduce输出
            context.write(key,new Text(""+sum));
        }
    }
}
