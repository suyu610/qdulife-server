package qdu.life.config;

/**
 * @ClassName BlobToStringTypeHandler
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/229:49 下午
 * @Version 0.1
 **/

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class BlobToStringTypeHandler extends BaseTypeHandler<String> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter);
  }

  @Override
  public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
    Blob blob = rs.getBlob(columnName);
    return new String(blob.getBytes(1, (int)blob.length()));
  }

  @Override
  public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    Blob blob = rs.getBlob(columnIndex);
    return new String(blob.getBytes(1, (int)blob.length()));
  }

  @Override
  public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    Blob blob = cs.getBlob(columnIndex);
    return new String(blob.getBytes(1, (int)blob.length()));
  }
}

