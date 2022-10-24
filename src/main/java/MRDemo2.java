import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URISyntaxException;
    //一个MapReduce程序脚手架
    public class MRDemo2 {

        //Map区
        static class MapperDemo extends Mapper<Object, Text, Text, Text> {
            @Override
            protected void map(Object key, Text value, Context context)
                    throws IOException, InterruptedException {
                //补充Map逻辑
                    String line = value.toString();
                    String [] arr=line.split("\t");
                    String t=arr[0];
                    String p=arr[4];
                    int PM= Integer.parseInt(p);
                    if (PM>=30) {
                        String a=arr[2]+"\t"+arr[4];
                        context.write(new Text(t), new Text(a));
                    }
            }
        }
        static  class Ba extends Partitioner<Text,Text> {
            @Override
            public int getPartition(Text text, Text intWritable, int i) {
                String time = text.toString();
                int start = time.indexOf(" ");
                int end = time.indexOf(":");
                String sub = time.substring(start + 1, end);
                int index = Integer.parseInt(sub);
                return index;
            }
        }
        //Reduce区
        static class ReducerDemo extends Reducer<Text, Text, Text, Text> {
            @Override
            protected void reduce(Text key, Iterable<Text> values, Context context)
                    throws IOException, InterruptedException {
                //补充Reduce逻辑
                    for (Text value : values){
                        context.write(key,new Text(value));
                    }

            }
        }

        //Driver区
        public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
            //创建一个任务
            Job job = Job.getInstance();

            //设置任务名称
            job.setJobName("MRDemo2");

            //jar包在哪（会动态获取MRDemo.class所在的jar包）
            job.setJarByClass(MRDemo2.class);

            //指定map逻辑
            job.setMapperClass(MapperDemo.class);
            //指定reduce逻辑
            job.setReducerClass(ReducerDemo.class);
            //指定分区逻辑
            job.setPartitionerClass(Ba.class);
//            指定Combiner
//            job.setCombinerClass(BallCombiner.class);
            //map输出的数据类型是什么？
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            //reduce输出的数据类型是什么？
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            //数据在哪里？
            FileInputFormat.addInputPath(job, new Path("/data/in/an"));
            //数据输出到哪里？
            Path out = new Path("/data/out");
            FileOutputFormat.setOutputPath(job, out);

            //输出目录如果已经存在，会导致MapReduce运行出错，需要先删除
            FileSystem fs = FileSystem.get(new Configuration());
            if(fs.exists(out)) {
                fs.delete(out, true);
            }

            //设置reduce任务数量
            job.setNumReduceTasks(7);

            //执行MapReduce任务，客户端跟踪任务运行状态，完成后退出程序
            System.exit(job.waitForCompletion(true)?0:-1);
        }
    }


