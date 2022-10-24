
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @version 1.0
 * @Name ZTY
 * @Date 2022-10-16 18:30
 * @注释
 */
public class BigData {

    //Map区
    static class MapperDemo extends Mapper<Object, Text, Text, IntWritable> {
        @Override
        protected void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {
            //补充Map逻辑
            //将map输入转成字符串
            String  line = value.toString();
            //切割
            String[] arr = line.split("::");
            int i=Integer.parseInt(arr[2]);
            context.write(new Text(arr[1]),new IntWritable(i));
        }
    }

    //Reduce区
    static class ReducerDemo extends Reducer<Text, IntWritable, Text, Text> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            //补充Reduce逻辑
            //补充Reduce逻辑
            //定义一个累加器
            double sum = 0;
            //遍历map输出的value值
            int a=0;
            for (IntWritable value : values) {
                int count = value.get();
                sum+=count;
                a++;
            }
            String b=String.format("%.2f", (sum /a));
            //   Reduce输出
            context.write(key,new Text(b));
        }
    }

    //Driver区
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        //创建一个任务
        Job job = Job.getInstance();

        //设置任务名称
        job.setJobName("BigData");

        //jar包在哪（会动态获取MRDemo.class所在的jar包）
        job.setJarByClass(BigData.class);

        //指定map逻辑
        job.setMapperClass(MapperDemo.class);
        //指定reduce逻辑
        job.setReducerClass(ReducerDemo.class);

        //map输出的数据类型是什么？
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //reduce输出的数据类型是什么？
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //数据在哪里？
        FileInputFormat.addInputPath(job, new Path("/data/in"));
        //数据输出到哪里？
        Path out = new Path("/data/out");

        FileOutputFormat.setOutputPath(job, out);

        //输出目录如果已经存在，会导致MapReduce运行出错，需要先删除
        FileSystem fs = FileSystem.get(new Configuration());
        if (fs.exists(out)) {
            fs.delete(out, true);
        }

        //设置reduce任务数量
        job.setNumReduceTasks(1);

        //执行MapReduce任务，客户端跟踪任务运行状态，完成后退出程序
        System.exit(job.waitForCompletion(true) ? 0 : -1);
    }
}
