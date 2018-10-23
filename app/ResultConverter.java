import java.sql.*;

public class ResultConverter{

  public static ResultData resultSetToData(ResultSet rs){

    ResultData data = null;
    try{
      data = new ResultData();
      ResultSetMetaData rsmeta = rs.getMetaData();

      //add columns
      for(int i=1; i<=rsmeta.getColumnCount(); i++){
        data.addColumn(rsmeta.getColumnLabel(i));
      }

      //add data
      int cnum = rsmeta.getColumnCount();
      while (rs.next()) {
          for (int i = 1; i <= cnum; i++) {
              data.addData(i-1, rs.getObject(i) );
          }
      }

    } catch (SQLException e){ e.printStackTrace();}

    return data;
  }
}
