package cn.nightwee.mybatis.test;

import cn.nightwee.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    @Test
    public void testInsert() throws IOException {
        // 获取核心配置文件的输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 获取SqlSessionFactoryBuilder 对象 -> 工厂构建器
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 创建 SqlSession 工厂 -> 创建会话
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);

        // 获取 会话 对象 -> MyBatis 提供的操作数据库的对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获得Mapper接口的代理类 -> 操纵Mapper类执行数据库操作
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 执行SQL操作
        Integer rows = userMapper.insertUser();
        System.out.println("rows = " + rows);

        // 提交事务 -> 事务是默认开启的
        sqlSession.commit();

        // 关闭资源
        sqlSession.close();
    }
}
