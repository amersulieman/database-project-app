import java.sql.*;
import java.net.*;
import java.util.jar.*;

/*
*
* THIS CLASS NEEDS ORACLE DRIVER IN CLASS PATH
* compile with: javac -classpath ojdbc8.jar *.java
* runs wtih: java -cp :ojdbc6.jar:AppModel.class AppModel
*/
class OracleCon{
  private String host;
  private String port = "1521";
  private String serviceId;
  private String user;
  private String password;

  private static final String STATUS_CONNECTED = "connected";
  private static final String STATUS_DISCONNECTED = "disconnected";
  public String status = STATUS_DISCONNECTED;

  private Connection conn;

  public OracleCon(String host, String sid, String user, String password){
    this(host, sid, "1521", user, password);
  }

  public OracleCon(String host, String sid, String port, String user, String password){
    this.host = host;
    this.serviceId = sid;
    this.port = port;
    this.user = user;
    this.password = password;

    try{

      //step1 load the driver class
      // Class.forName("oracle.jdbc.driver.OracleDriver");
      DriverManager.registerDriver (new oracle.jdbc.OracleDriver());


    }catch(Exception e){ System.out.println(e);}



  }

  public Connection getConnection(){
    //step2 create  the connection object
    try{
      this.conn = DriverManager.getConnection(
          String.format("jdbc:oracle:thin:@%s:%s:%s", this.host, this.port, this.serviceId), this.user, this.password);
      this.status = STATUS_CONNECTED;
    }catch(Exception e){e.printStackTrace();}


    return this.conn;
  }

  public void closeConnection(){
    try{
      conn.close();
      this.status = STATUS_DISCONNECTED;
    }catch(Exception e){ System.out.println(e);}

  }
}
