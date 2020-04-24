package com.mayikt.comtroller;

import com.mayikt.entity.UserEntity;
import com.mayikt.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Reader;

/**
 * @author 杨李平
 * @date 2019/12/29 10:39
 */
@RestController
public class TestMybatisController {
    SqlSession sqlSession;
    @RequestMapping("/getUserInfo")
    public UserEntity getUserInfo(){

        if (false){
            UserEntity userEntity = new UserEntity();
            userEntity.setName("asdfas");
            userEntity.setUserName("admin");
            userEntity.setAge(1324);
            userEntity.setId(33L);
            return userEntity;
        }
        try {


            if (sqlSession==null){
                // 基本mybatis环境
                // 1.定义mybatis_config文件地址
                String resources = "mybatis_config.xml";
                // 2.获取InputStreamReaderIo流
                Reader reader = Resources.getResourceAsReader(resources);
                // 3.获取SqlSessionFactory
                SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
                // 4.获取Session
                sqlSession = sqlSessionFactory.openSession();
            }

            // 5.操作Mapper接口
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            UserEntity user = mapper.getUser(2);
            System.out.println(user.getUserName());
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getProjectName")
    public String getProjectName(){
        return "meite_mayikt_mybatis_day012";
    }
    static {

    }
}
