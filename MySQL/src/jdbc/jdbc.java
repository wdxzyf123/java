package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbc {
    public static void main(String[] args) throws Exception {
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");//固定写法
        //2.用户信息和url
        String url ="jdbc:mysql://localhost:3306/jdbc_test?useUnicode=true&characterEncoding=utf8&&useSSL=false";
        String name = "root";
        String password = "123456";
        //3.链接成功，数据库对象
        Connection connection= DriverManager.getConnection(url,name,password);
        //4.执行sql的对象
        Statement statement = connection.createStatement();
        //5.执行sql的对象 去执行sql，可能存在结果，查看返回的结果
        String sql="SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(sql);//返回的结果集,结果集中封装了我们全部查询的结果
        while(resultSet.next()){
            System.out.println("id+"+resultSet.getObject("id"));
            System.out.println("name+"+resultSet.getObject("NAME"));
            System.out.println("password+"+resultSet.getObject("PASSWORD"));
            System.out.println("email+"+resultSet.getObject("email"));
            System.out.println("birthday+"+resultSet.getObject("birthday"));
            System.out.println("====================================================");
        }
        //6.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
