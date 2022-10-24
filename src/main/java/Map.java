import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @version 1.0
 * @Name ZTY
 * @Date 2022-10-16 21:31
 * @注释
 */
public class Map {
    static class MapperDemo extends Mapper<Object, Text, Text, IntWritable> {
        @Override
        protected void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {
            //补充Map逻辑
            //将map输入转成字符串
            String line = value.toString();
            //切割
            String[] arr = line.split("::");

            context.write(new Text(arr[1]),new IntWritable(1));
        }
    }
}
