package transaction;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction {
    public static void main(String[] args) {
        Connection conn =null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);//关闭数据库的自动提交功能， 自动会开启事务
            //自动开启事务
            String sql = "update account set cash = cash-500 where id = 1";
            ps =conn.prepareStatement(sql);
            ps.executeUpdate();
            int x = 1/0;
            String sql2 = "update account set cash = cash-500 where id = 2";
            ps=conn.prepareStatement(sql2);
            ps.executeUpdate();

            //业务完毕，提交事务
            conn.commit();
            System.out.println("操作成功");
        } catch (Exception e) {
            try {
                //如果失败，则默认回滚
                conn.rollback();//如果失败，回滚
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.release(conn,ps,rs);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
