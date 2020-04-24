import com.mayikt.entity.UserEntity;
import com.mayikt.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: TestMyBatis
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/6/420:46
 */

public class TestMyBatis {
    // 1.需要引入mybatisjar包
    // 2.配置核心mybatis文件 数据源、mapper接口映射
    // 3.需要sqlmapper文件 sql数据 orm
    // 4.通过mybatis操作../
    // 疑问：你们在mybatis整合springboot之后需要在每个mapper 需要加入注入spring容器注解 这是为什么呢？
    // 疑问：Mapper如何调用的呢
    public static void main(String[] args) {

        try {
            //1.加载配置文件
            String config="mybatis_config.xml";
            //2.把文件读取成流
            Reader resourceAsReader = Resources.getResourceAsReader(config);
            //3.构建会话工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
            //4.打开会话
            SqlSession sqlSession = sessionFactory.openSession();
            //5.获取代理对象
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //6.调用方法获取值
            UserEntity user = mapper.getUser(2);
            System.out.println(user.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 看源码的时候 看不懂 不知道如何下手。最重要的一点学会画图。 如何加源码呢？
     */
   //@Test
    public void testHttp(){
        String s = doGet("http://192.168.156.133:8080/getProjectName/");
        System.out.println(s);
    }

    public  String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }
}
