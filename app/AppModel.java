import java.sql.*;
import java.net.*;
import java.sql.Date;

public class AppModel {

    private OracleCon orcl = null; //connection
    private PreparedStatement ps =null; //prepared statement

    AppModel(){
      this.orcl = new OracleCon("dbsvcs.cs.uno.edu", "orcl", "aksuliem", "mFcdnCm7");
    }

    /*update has skill after updating takes*/
    public int updateHasSkillOnTakes(int per_id){
        String sql_statement ="INSERT INTO has_skill (per_id, ks_code) " +
                              "/*skills person learned*/ " +
                              "SELECT per_id, ks_code " +
                              "FROM takes NATURAL JOIN teaches " +
                              "WHERE per_id=? " +
                              "MINUS " +
                              "/*Skills he has*/ " +
                              "SELECT per_id, ks_code " +
                              "FROM has_skill " +
                              "WHERE per_id=?";

        Connection conn = null;
        PreparedStatement ps = null;
        int result = -1;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setInt(1,per_id);
            ps.setInt(2,per_id);
            //execute the query and store result object in result
            result = ps.executeUpdate();


        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          try { if (ps != null) ps.close(); } catch (Exception e) {};
          try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return result;
    }

    //creates a new job position based on the user choice
    public int createPos(String pos_code,String pos_title,String emp_mode,int pay_rate,String pay_type,String cate_code,String comp_id){
        String prepared_statement = "INSERT INTO job_position VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;
        int result = -1;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(prepared_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //set parameter 2 to the string user passes in
            ps.setString(2,pos_title);
            //set parameter 3 to the string user passes in
            ps.setString(3,emp_mode);
            //set parameter 4 to the string user passes in
            ps.setInt(4,pay_rate);
            //set parameter 5 to the string user passes in
            ps.setString(5,pay_type);
            //set parameter 6 to the string user passes in
            ps.setString(6,cate_code);
            //set parameter 7 to the string user passes in
            ps.setString(7,comp_id);

            //execute the query and store result object in result
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return result;

    }

    /*REMOVE JOB position based on user choice*/
    public int deletePos(String pos_code){
        String prepared_statement = "DELETE FROM job_position where pos_code=?";


        Connection conn = null;
        PreparedStatement ps = null;
        int result = -1;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(prepared_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //execute the query and store result object in result
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return result;

    }


    //creates a new job category based on the user choice
    public int createCate(String cate_code,String title,int pay_range_high,int pay_range_low,String parent_cate){
        String prepared_statement = "INSERT INTO job_category VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;
        int result = -1;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(prepared_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,cate_code);
            //set parameter 2 to the string user passes in
            ps.setString(2,title);
            //set parameter 3 to the string user passes in
            ps.setInt(3,pay_range_high);
            //set parameter 4 to the string user passes in
            ps.setInt(4,pay_range_low);
            //set parameter 5 to the string user passes in
            ps.setString(5,parent_cate);

            //execute the query and store result object in result
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return result;

    }

    /*REMOVE JOB CATEGORY based on user choice*/
    public int deleteCate(String cate_code){
        String prepared_statement = "DELETE FROM job_category where cate_code=?";


        Connection conn = null;
        PreparedStatement ps = null;
        int result = -1;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(prepared_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,cate_code);
            //execute the query and store result object in result
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return result;

    }

    //creates a new course based on the user choice
    public int createCourse(String c_course,String title,String lvl,String description,String status,int price){
        String prepared_statement = "INSERT INTO course VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;
        int result = -1;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(prepared_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,c_course);
            //set parameter 2 to the string user passes in
            ps.setString(2,title);
            //set parameter 3 to the string user passes in
            ps.setString(3,lvl);
            //set parameter 4 to the string user passes in
            ps.setString(4,description);
            //set parameter 5 to the string user passes in
            ps.setString(5,status);
            //set parameter 6 to the string user passes in
            ps.setInt(6,price);
            //execute the query and store result object in result
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return result;

    }
    //Remove a course based on the user choice, which will set course status to inactive
    public int deleteCourse(String c_course){
        String prepared_statement = "UPDATE course SET status ='expired' where c_code=?";

        Connection conn = null;
        PreparedStatement ps = null;
        int result = -1;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(prepared_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,c_course);

            //execute the query and store result object in result
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return result;

    }

    /*Insert Takes*/
    public int insertTakes(int per_id, String sec_no, String c_code, String complete_date, String offered_by){
      String sql_statement = "INSERT INTO takes (per_id, sec_no, complete_date, offered_by, c_code) " +
                             "VALUES (?, ?, ?, ?, ?)";

     Connection conn = null;
     PreparedStatement ps = null;
     int result = -1;

     try {
         //open Connection
         conn = orcl.getConnection();
         //create the prepared statement
         ps = conn.prepareStatement(sql_statement);
         //set parameter 1 to the string user passes in
         ps.setInt(1,per_id);
         ps.setString(2,sec_no);

         System.out.print("date string: '"+ complete_date +"'");
         ps.setDate(3, Date.valueOf(complete_date));
         ps.setString(4,offered_by);
         ps.setString(5,c_code);
         //execute the query and store result object in result
         result = ps.executeUpdate();


     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       try { if (ps != null) ps.close(); } catch (Exception e) {};
       try { if (conn != null) conn.close(); } catch (Exception e) {};
     }

     //return result.
     return result;

    }

    /*Query 1*/
    public ResultData getCompWorkersByCompId(String comp_id){

      //Query01 Solution which takes one parameter for comp_id
      String sql_statement = ""
              + "SELECT DISTINCT first_name, last_name "
              + "FROM  person NATURAL JOIN works NATURAL JOIN job_position "
              + "WHERE comp_id=? AND end_date IS NULL";

      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet result = null;
      ResultData data = null;

      try {
          //open Connection
          conn = orcl.getConnection();
          //create the prepared statement
          ps = conn.prepareStatement(sql_statement);
          //set parameter 1 to the string user passes in
          ps.setString(1,comp_id);
          //execute the query and store result object in result
          result = ps.executeQuery();

          data = ResultConverter.resultSetToData(result);

      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try { if (result != null) result.close(); } catch (Exception e) {};
        try { if (ps != null) ps.close(); } catch (Exception e) {};
        try { if (conn != null) conn.close(); } catch (Exception e) {};
      }

      //return result.
      return data;
    }

    /*Query 2*/
    public ResultData getCompWorkersSalaryByCompId(String comp_id){

        //Query02 Solution which takes one parameter for comp_id
        String sql_statement = ""
                + "SELECT per_id, pay_rate "
                + "FROM job_position NATURAL JOIN works "
                + "WHERE comp_id=? AND pay_type='salary' AND end_date IS NUll "
                + "ORDER BY pay_rate DESC";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,comp_id);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;

    }

    /*Query 3*/
    public ResultData getAllCompLaborCosts(){

        //Query03 Solution which takes doesn't take any parameter
        String sql_statement =""
                + "select comp_name, sum(salary) as salary "
                + "from ( "
                + "        ( "
                + "        SELECT comp_name, pay_rate*1920 as salary "
                + "        FROM (company NATURAL JOIN job_position) NATURAL JOIN works "
                + "        WHERE pay_type='wage' AND end_date IS NULL "
                + "        ) "
                + "        UNION "
                + "        ( "
                + "        SELECT comp_name, pay_rate as salary "
                + "        FROM (company NATURAL JOIN job_position) NATURAL JOIN works "
                + "        WHERE pay_type='salary' AND end_date IS NULL "
                + "        ) "
                + ") "
                + "group by comp_name "
                + "order by salary desc";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //execute the query and store result object in result
            result = ps.executeQuery();
            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;

    }

    /*Query 4*/
    public ResultData getAllPositionsByPersonId(int per_id){

        //Query 04 Solution which takes one parameter (per_id)
        String sql_statement =""
                + "SELECT pos_code "
                + "FROM  works "
                + "WHERE per_id=?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the Integer user passes in
            ps.setInt(1,per_id);
            //execute the query and store result object in result
            result = ps.executeQuery();
            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        //return result.
        return data;
    }

    /*Query 5*/
    public ResultData getKnowledgeSkillsByPersonId(int per_id){

        //Solution for Query 05 which takes one parameter
        String sql_statement =""
                + "SELECT ks_code, title AS skill, lvl AS experience_level "
                + "FROM has_skill NATURAL JOIN knowledge_skills "
                + "WHERE per_id=?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the Integer user passes in
            ps.setInt(1,per_id);
            //execute the query and store result object in result
            result = ps.executeQuery();
            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        //return result.
        return data;
    }


    /*Query 6*/
    public ResultData getSkillGapOfCurrentPositionsByPersonId(int per_id){

        //Solution for query 06 which takes one parameter
        String sql_statement = ""
                + "(SELECT ks_code "
                + " FROM works NATURAL JOIN requirements "
                + " WHERE per_id=? AND end_date IS NULL "
                + " ) "
                + "MINUS "
                + "(SELECT ks_code "
                + " FROM has_skill "
                + " WHERE per_id=? "
                + ")";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the Integer user passes in
            ps.setInt(1,per_id);
            //set parameter 2 to the Integer user passes in
            ps.setInt(2,per_id);
            //execute the query and store result object in result
            result = ps.executeQuery();
            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        //return result.
        return data;
    }

    /*Query 7.a*/
    public ResultData getReqKnowledgeSkillsByCateCode(String cate_code){

        //Solution for first part of query 07 takes one parameter(cate_code)
        String sql_statement = ""
                + "Select job_category, title as requires, lvl as with_level "
                + "FROM (knowledge_skills NATURAL JOIN requirements ) NATURAL JOIN "
                + "    ( "
                + "    SELECT pos_code, title as job_category "
                + "    FROM job_position NATURAL JOIN job_category "
                + "    WHERE cate_code=?"
                + "    )";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;
        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,cate_code);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;
    }

    /*Query 7.b*/

    public ResultData getReqKnowledgeSkillsByPositionCode(String pos_code){

        //Solution for Query 07 part 2 which takes one parameter(pos_code)
        String sql_statement =""
                + "select pos_title,title as requires,lvl as with_level "
                + "from job_position natural join requirements natural join knowledge_skills "
                + "where pos_code =?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;
    }

    /*Query 8 */
    public ResultData getSkillGapByPersoIdForPositionCode(int per_id, String pos_code){

        //Solution for Query 08 which takes two parameters (per_id,pos_code)
        String sql_statement =""
                + "SELECT ks_code, title "
                + "    FROM "
                + "    ( "
                + "        ( "
                + "        SELECT ks_code "
                + "        FROM requirements "
                + "        WHERE pos_code=? "
                + "        ) "
                + "        MINUS "
                + "        ( "
                + "        SELECT ks_code "
                + "        FROM has_skill "
                + "        WHERE per_id=? "
                + "        ) "
                + "    )NATURAL JOIN knowledge_skills";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //set parameter 2 to the Integer user passes in
            ps.setInt(2,per_id);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;
    }

    /*Query 9*/
    public ResultData getCoursesThatTeachMissingSkillsByPersonIdForPositionCode(int per_id, String pos_code){

        //Solution for Query 09 which takes two parameters (per_id,pos_code)
        String sql_statement =""
                + "WITH missing AS ( "
                + "SELECT ks_code "
                + "    FROM "
                + "    ( "
                + "        ( "
                + "        SELECT ks_code "
                + "        FROM requirements "
                + "        WHERE pos_code=? "
                + "        ) "
                + "        MINUS "
                + "        ( "
                + "        SELECT ks_code "
                + "        FROM has_skill "
                + "        WHERE per_id=? "
                + "        ) "
                + "    )) "
                + "SELECT c_code,title "
                + "FROM missing NATURAL JOIN teaches NATURAL JOIN course "
                + "where status='active' "
                + "GROUP BY c_code,title "
                + "HAVING COUNT(ks_code) = (select count(ks_code) "
                + "                         from missing)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //set parameter 2 to the Integer user passes in
            ps.setInt(2,per_id);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;
    }

    /*Query 12*/
    public ResultData query12(int per_id, String pos_code){

        //Solution for Query 09 which takes two parameters (per_id,pos_code)
        String sql_statement =""
                + "WITH missing AS ( "
                + "SELECT ks_code "
                + "    FROM "
                + "    ( "
                + "        ( "
                + "        SELECT ks_code "
                + "        FROM requirements "
                + "        WHERE pos_code=? "
                + "        ) "
                + "        MINUS "
                + "        ( "
                + "        SELECT ks_code "
                + "        FROM has_skill "
                + "        WHERE per_id=? "
                + "        ) "
                + "    )) "
                + "SELECT c_code,title "
                + "FROM missing NATURAL JOIN teaches NATURAL JOIN course "
                + "where status='active' "
                + "GROUP BY c_code,title "
                + "HAVING COUNT(ks_code) = (select count(ks_code) "
                + "                         from missing)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //set parameter 2 to the Integer user passes in
            ps.setInt(2,per_id);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;
    }


    /*Query 10*/
    public ResultData getQuickestCoursesThatTeachMissingSkillsByPersonIdForPositionCode(int per_id, String pos_code){

        String sql_statement =""
                + "WITH missing AS ( "
                + "SELECT ks_code "
                + "    FROM "
                + "    ( "
                + "        ( "
                + "        SELECT ks_code "
                + "        FROM requirements "
                + "        WHERE pos_code=? "
                + "        ) "
                + "        MINUS "
                + "        ( "
                + "        SELECT ks_code "
                + "        FROM has_skill "
                + "        WHERE per_id=? "
                + "        ) "
                + "    )), "
                + "right_course as( SELECT c_code,title "
                + "                FROM missing NATURAL JOIN teaches NATURAL JOIN course "
                + "                where status ='active' "
                + "                GROUP BY c_code,title "
                + "                HAVING COUNT(ks_code) = (select count(ks_code) "
                + "                                        from missing)) "
                + "select c_code,title,sec_no,complete_date,offered_by,format,price "
                + "from section natural join right_course "
                + "where complete_date = (select min(complete_date) "
                + "                        from right_course natural join section)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //set parameter 2 to the Integer user passes in
            ps.setInt(2,per_id);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;
    }
    /*Query 11*/
    public ResultData getCheapestCoursesThatTeachMissingSkillsByPersonIdForPositionCode(int per_id, String pos_code){

        String sql_statement =""
                + "WITH missing AS ( "
                + "SELECT ks_code "
                + "    FROM "
                + "    ( "
                + "        ( "
                + "        SELECT ks_code "
                + "        FROM requirements "
                + "        WHERE pos_code=? "
                + "        ) "
                + "        MINUS "
                + "        ( "
                + "        SELECT ks_code "
                + "        FROM has_skill "
                + "        WHERE per_id=? "
                + "        ) "
                + "    )), "
                + "right_course as( SELECT c_code,title "
                + "                FROM missing NATURAL JOIN teaches NATURAL JOIN course "
                + "                where status ='active' "
                + "                GROUP BY c_code,title "
                + "                HAVING COUNT(ks_code) = (select count(ks_code) "
                + "                                        from missing)) "
                + "select c_code,title,sec_no,complete_date,offered_by,format,price "
                + "from section natural join right_course "
                + "where price = (select min(price) "
                + "                from section natural join right_course)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //set parameter 2 to the Integer user passes in
            ps.setInt(2,per_id);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;
    }
    /*Query 13*/
    public ResultData jobCategory_person_qualified_for(int per_id){
        String sql_statement = ""
                + "SELECT cate_code "
                + "FROM ( "
                + "    SELECT cate_code, COUNT(skill_code) as reqs "
                + "    FROM core_skills "
                + "    GROUP BY cate_code "
                + "    ) "
                + "    NATURAL JOIN "
                + "    ( "
                + "    SELECT cate_code, COUNT(skill_code) as has "
                + "    FROM (SELECT DISTINCT cate_code, skill_code "
                + "        FROM has_skill NATURAL JOIN skills_category NATURAL JOIN core_skills "
                + "        WHERE per_id=?) "
                + "    GROUP BY cate_code "
                + "    ) "
                + "WHERE reqs=has";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setInt(1,per_id);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;

    }
    /*Query 14*/
    public ResultData highest_pay_rate_position(int per_id){
        String sql_statement =  ""
                + "WITH qualified_positions as ( "
                + "    SELECT pos_code "
                + "    FROM "
                + "        ( "
                + "            SELECT pos_code, count(ks_code) as reqs "
                + "            FROM requirements "
                + "            GROUP BY pos_code "
                + "        ) "
                + "        NATURAL JOIN "
                + "        ( "
                + "            SELECT pos_code, count(ks_code) as has "
                + "            FROM has_skill NATURAL JOIN requirements "
                + "            WHERE per_id=? "
                + "            GROUP BY pos_code "
                + "        ) "
                + "    WHERE reqs = has "
                + "), "
                + "converted_salaries as ( "
                + "    SELECT pos_code, pay_rate "
                + "    FROM qualified_positions NATURAL JOIN job_position "
                + "    WHERE pay_type='salary' "
                + "    UNION "
                + "    SELECT pos_code, pay_rate*1920 "
                + "    FROM qualified_positions NATURAL JOIN job_position "
                + "    WHERE pay_type='wage' "
                + ") "
                + "SELECT * "
                + "FROM converted_salaries "
                + "WHERE pay_rate = (SELECT MAX(pay_rate) FROM converted_salaries)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setInt(1,per_id);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;

    }
    /*Query 15*/
    public ResultData qualified_for_position(String pos_code){
        String sql_statement =""
                + "with qualified_per_id as(select per_id "
                + "from person  T "
                + "      where not exists ((select ks_code "
                + "       from requirements "
                + "        where pos_code=?) "
                + "    minus "
                + "      (select ks_code "
                + "       from has_skill S "
                + "       where T.per_id=S.per_id) "
                + "       )) "
                + "select first_name,last_name,email "
                + "    from qualified_per_id natural join person";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;

    }
    /*Query 16*/
    public ResultData missing_one_list(String pos_code){
        String sql_statement =""
                + "with missing_one AS( "
                + "    select per_id,count (ks_code) as missing "
                + "       from( "
                + "           (select per_id,ks_code "
                + "            from person,requirements "
                + "             where pos_code =?) "
                + "          minus "
                + "            (select per_id,ks_code "
                + "             from has_skill) "
                + "           ) "
                + "      group by per_id) "
                + "       "
                + "select per_id "
                + "from missing_one "
                + "where missing =1";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;

    }
    /*Query 17*/
    public ResultData missing_skills_missing_one_list(String pos_code){
        String sql_statement =""
                + "with missing_skills AS( "
                + "            select per_id,count (ks_code) as missing "
                + "            from( "
                + "               (select per_id,ks_code "
                + "                from person,requirements "
                + "                where pos_code =?) "
                + "              minus "
                + "                (select per_id,ks_code "
                + "                 from has_skill) "
                + "                ) "
                + "            group by per_id), "
                + "   missing_one As( "
                + "           select per_id "
                + "           from missing_skills "
                + "           where missing =1) "
                + "                         "
                + "select ks_code,count(per_id) AS people "
                + "   from ( "
                + "            (select per_id,ks_code "
                + "             from missing_one,requirements "
                + "             where pos_code =?) "
                + "          minus "
                + "             (select per_id,ks_code "
                + "              from has_skill) "
                + "         ) "
                + " group by ks_code "
                + " order by people asc";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //set parameter 2 to the string user passes in
            ps.setString(2,pos_code);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;

    }
    /*Query 18*/
    public ResultData people_miss_least_number_of_skills(String pos_code){
        String sql_statement =""
                + "with missing_skills AS( "
                + "    select per_id,count (ks_code) as missing "
                + "     from( "
                + "            (select per_id,ks_code "
                + "             from person,requirements "
                + "             where pos_code =?) "
                + "         minus "
                + "             (select per_id,ks_code "
                + "              from has_skill) "
                + "          ) "
                + "      group by per_id) "
                + " "
                + "select per_id,missing "
                + "from missing_skills "
                + "where missing = (select min (missing) "
                + "                    from missing_skills)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;

    }
    /*Query 19*/
    public ResultData k_list(String pos_code,int k){
        String sql_statement =""
                + "with missing_k AS( "
                + "    select per_id,count (ks_code) as missing "
                + "        from( "
                + "                (select per_id,ks_code "
                + "                 from person,requirements "
                + "                 where pos_code =?) "
                + "              minus "
                + "                 (select per_id,ks_code "
                + "                  from has_skill) "
                + "            ) "
                + "        group by per_id) "
                + "select per_id,missing "
                + "from missing_k "
                + "where missing <=?"
                + "order by missing asc";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //set parameter 2 to the int user passes, kth number
            ps.setInt(2,k);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;

    }
    /*Query 20*/
    public ResultData missed_skills_from_k_list(String pos_code,int k){
        String sql_statement =""
                + "with missing_skills AS( "
                + "    select per_id,count (ks_code) as missing "
                + "        from( "
                + "                (select per_id,ks_code "
                + "                 from person,requirements "
                + "                 where pos_code =?) "
                + "              minus "
                + "                 (select per_id,ks_code "
                + "                  from has_skill) "
                + "            ) "
                + "        group by per_id), "
                + " missing_k AS( "
                + "        select per_id,missing "
                + "        from missing_skills "
                + "        where missing <=? "
                + "            ) "
                + "select ks_code,count(per_id) AS people "
                + "   from ( "
                + "          (select per_id,ks_code "
                + "          from missing_k,requirements "
                + "          where pos_code =?) "
                + "        minus "
                + "          (select per_id,ks_code "
                + "          from has_skill) "
                + "        ) "
                + " group by ks_code "
                + " order by people desc";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //set parameter 2 to the int user passes, kth number
            ps.setInt(2,k);
            //set parameter 3 to the string user passes in
            ps.setString(3,pos_code);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;
    }
    /*Query 21*/
    public ResultData national_crises_needed_people(String cate_code){
        String sql_statement =""
                + "with cate_pos as (select pos_code,pos_title "
                + "    from job_position "
                + "    where cate_code =?), "
                + "pos_held as(select per_id,first_name,pos_code,pos_title,start_date,end_date "
                + "      from cate_pos natural join works natural join person "
                + "      where end_date is not null) "
                + "select * "
                + "from pos_held";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,cate_code);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;
    }
    /*Query 22*/
    public ResultData unemployed_people_for_old_position(String pos_code){
        String sql_statement =""
                + "( "
                + "    SELECT distinct per_id "
                + "    FROM works "
                + "    WHERE end_date IS NOT NULL AND pos_code=? "
                + ") "
                + "MINUS "
                + "( "
                + "    SELECT distinct per_id "
                + "    FROM WORKS "
                + "    WHERE end_date IS NULL "
                + ")";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 to the string user passes in
            ps.setString(1,pos_code);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;
    }
    /*Query 23-A*/
    public ResultData biggest_employer_by_number_employees(){
        String sql_statement =""
                + "WITH emp_count as ( "
                + "    SELECT comp_id, count(pos_code) as emp_num "
                + "    FROM job_position NATURAL JOIN works "
                + "    WHERE end_date IS NULL "
                + "    GROUP BY comp_id "
                + ") "
                + "SELECT comp_id "
                + "FROM emp_count "
                + "WHERE emp_num = (select max(emp_num) FROM emp_count)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;
    }
    /*Query 23-B*/
    public ResultData biggest_employer_by_payroll(){
        String sql_statement =""
                + "WITH pay_totals as ( "
                + "    SELECT comp_id, sal_total+wage_total as total "
                + "    FROM ( "
                + "        SELECT comp_id, SUM (pay_rate) as sal_total "
                + "        FROM job_position NATURAL JOIN works "
                + "        WHERE pay_type='salary' AND end_date IS NULL "
                + "        GROUP BY comp_id "
                + "        ) "
                + "        NATURAL JOIN "
                + "        ( "
                + "        SELECT comp_id, SUM (pay_rate*1920) as wage_total "
                + "        FROM job_position NATURAL JOIN works "
                + "        WHERE pay_type='wage' AND end_date IS NULL "
                + "        GROUP BY comp_id "
                + "        ) "
                + ") "
                + "SELECT * "
                + "FROM pay_totals "
                + "WHERE total = (SELECT MAX(total) FROM pay_totals)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;
    }
    /*Query 24-A*/
    public ResultData biggest_sector_by_number_of_employees(){
        String sql_statement =""
                + "WITH job_distribution as ( "
                + "    SELECT sec_id, COUNT(pos_code) as num_positions "
                + "    FROM comp_sectors NATURAL JOIN (SELECT pos_code, comp_id FROM job_position) "
                + "    GROUP By sec_id "
                + ") "
                + " "
                + "SELECT sec_id, num_positions as num_employees "
                + "FROM job_distribution "
                + "WHERE num_positions = (SELECT MAX(num_positions) FROM job_distribution)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        //return result.
        return data;
    }
    /*Query 24-B*/
    public ResultData biggest_sector_by_payroll(){
        String sql_statement =""
                + "WITH pay_distribution as ( "
                + "    SELECT sec_id, sal+wage as total "
                + "    FROM "
                + "    (SELECT sec_id, SUM(pay_rate) as sal "
                + "        FROM comp_sectors NATURAL JOIN job_position "
                + "        WHERE pay_type='salary' "
                + "        GROUP BY sec_id "
                + "    ) "
                + "    NATURAL JOIN "
                + "    (   SELECT sec_id, SUM(pay_rate*1920) as wage "
                + "        FROM comp_sectors NATURAL JOIN job_position "
                + "        WHERE pay_type='wage' "
                + "        GROUP BY sec_id "
                + "    ) "
                + ") "
                + " "
                + "SELECT sec_id, total "
                + "FROM pay_distribution "
                + "WHERE total = (SELECT MAX(total) FROM pay_distribution)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        //return result.
        return data;
    }
    /*Query 25-A*/
    public ResultData how_many_people_earning_increased(){
        String sql_statement =""
                + "with old_jobs AS( "
                + "                select per_id,pos_code,pay_rate as old_income "
                + "                from works S natural join job_position "
                + "                where end_date= (select max(end_date) "
                + "                                 from works T "
                + "                                 where S.per_id = T.per_id) "
                + "                ), "
                + "people as ( "
                + "                select per_id "
                + "                from old_jobs "
                + "              ), "
                + "sum_current As( "
                + "                select per_id,sum(pay_rate)AS current_pay "
                + "                from works natural join people natural join job_position "
                + "                where end_date is null "
                + "                group by per_id), "
                + "income_difference As( "
                + "        select (current_pay - old_income) as pay_difference,count (per_id) as pay_increase "
                + "        from sum_current natural join old_jobs "
                + "        group by current_pay - old_income) "
                + "select sum(pay_increase) as num_increased "
                + "from income_difference "
                + "where pay_difference >0";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        //return result.
        return data;
    }
    /*Query 25-B*/
    public ResultData how_many_people_earning_decreased(){
        String sql_statement =""
                + "with old_jobs AS( "
                + "                select per_id,pos_code,pay_rate as old_income "
                + "                from works S natural join job_position "
                + "                where end_date= (select max(end_date) "
                + "                                 from works T "
                + "                                 where S.per_id = T.per_id) "
                + "                ), "
                + "people as ( "
                + "                select per_id "
                + "                from old_jobs "
                + "              ), "
                + "sum_current As( "
                + "                select per_id,sum(pay_rate)AS current_pay "
                + "                from works natural join people natural join job_position "
                + "                where end_date is null "
                + "                group by per_id), "
                + "income_difference As( "
                + "        select (current_pay - old_income) as pay_difference,count (per_id) as pay_decrease "
                + "        from sum_current natural join old_jobs "
                + "        group by current_pay - old_income) "
                + "select sum(pay_decrease) as num_decreased "
                + "from income_difference "
                + "where pay_difference <0";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        //return result.
        return data;
    }
    /*Query 25-C*/
    public ResultData ratio_increased_to_decreased_earnings(){
        String sql_statement =""
                + "with old_jobs AS( "
                + "                select per_id,pos_code,pay_rate as old_income "
                + "                from works S natural join job_position "
                + "                where end_date= (select max(end_date) "
                + "                                 from works T "
                + "                                 where S.per_id = T.per_id) "
                + "                ), "
                + "people as ( "
                + "                select per_id "
                + "                from old_jobs "
                + "              ), "
                + "sum_current As( "
                + "                select per_id,sum(pay_rate)AS current_pay "
                + "                from works natural join people natural join job_position "
                + "                where end_date is null "
                + "                group by per_id), "
                + "income_difference As( "
                + "        select (current_pay - old_income) as pay_difference,count (per_id) as pay "
                + "        from sum_current natural join old_jobs "
                + "        group by current_pay - old_income), "
                + "pay_decrease As( "
                + "    select sum(pay) as pay_decrease "
                + "    from income_difference "
                + "    where pay_difference <0), "
                + "pay_increase AS( "
                + "    select sum(pay) as pay_increase "
                + "    from income_difference "
                + "    where pay_difference >0) "
                + "select pay_decrease, pay_increase "
                + "from pay_decrease,pay_increase";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        //return result.
        return data;
    }
    /*Query 25-D*/
    public ResultData average_of_earning_change_for_a_sector(String sec_id){
        String sql_statement =""
                + "with old_jobs AS( "
                + "                select per_id,pos_code,pay_rate as old_income "
                + "                from works S natural join job_position natural join company "
                + "                where sec_id=? and  end_date= (select max(end_date) "
                + "                                 from works T "
                + "                                 where S.per_id = T.per_id) "
                + "                ), "
                + "people as ( "
                + "                select per_id "
                + "                from old_jobs "
                + "              ), "
                + "sum_current As( "
                + "                select per_id,sum(pay_rate)AS current_pay "
                + "                from works natural join people natural join job_position "
                + "                where end_date is null "
                + "                group by per_id) "
                + "select avg(current_pay - old_income) as average_of_change "
                + "        from sum_current natural join old_jobs";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //set parameter 1 with the string the user input
            ps.setString(1,sec_id);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        //return result.
        return data;
    }
    /*Query 26*/
    public ResultData leaf_node_job_category(){
        String sql_statement =""
                + "with jobless_people AS( "
                + "    (select per_id "
                + "     from person) "
                + "     minus "
                + "     (select per_id "
                + "      from works "
                + "      where end_date is null) "
                + "    ), "
                + "leaf_nodes As( "
                + "        (select cate_code "
                + "         from job_category) "
                + "        minus "
                + "        (select parent_cate "
                + "        from job_category)), "
                + "cate_open_positions As( "
                + "    select cate_code,pos_code "
                + "         from ( "
                + "                (select pos_code "
                + "                from job_position) "
                + "                minus "
                + "                  (select pos_code "
                + "                    from works "
                + "                    where end_date IS null) "
                + "                )natural join job_position natural join leaf_nodes), "
                + "open_positions_requirements As( "
                + "      select pos_code,ks_code "
                + "      from cate_open_positions natural join requirements), "
                + " "
                + "jobless_people_skills As ( "
                + "        select * "
                + "        from jobless_people natural join has_skill), "
                + "     "
                + "pos_qualified_people_count As( "
                + "        select distinct per_id,pos_code, count(ks_code) As counting "
                + "        from jobless_people_skills natural join open_positions_requirements "
                + "        group by per_id,pos_code), "
                + "number_of_req_of_each_position AS( "
                + "        select pos_code,count(ks_code) As counting "
                + "        from open_positions_requirements "
                + "        group by pos_code), "
                + "num_spots_per_category AS( "
                + "        select cate_code,count(pos_code) as spots "
                + "        from cate_open_positions "
                + "        group by cate_code), "
                + "num_qualified_per_cate AS( "
                + "        select cate_code,count(per_id) as counts "
                + "        from( "
                + "                select * "
                + "                from( "
                + "                    select distinct per_id,T.pos_code "
                + "                    from pos_qualified_people_count T,number_of_req_of_each_position S "
                + "                    where T.pos_code = S.pos_code and T.counting = S.counting)natural join cate_open_positions "
                + "            ) "
                + "            group by cate_code "
                + "    ) "
                + "select cate_code "
                + "    from num_spots_per_category natural join num_qualified_per_cate "
                + "    where spots - counts =(select max(spots-counts) "
                + "                          from num_spots_per_category natural join num_qualified_per_cate)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        //return result.
        return data;
    }
    /*Query 27*/
    public ResultData courses_help_jobless_people(){
        String sql_statement = ""
                + "with jobless_people AS( "
                + "    (select per_id "
                + "     from person) "
                + "     minus "
                + "     (select per_id "
                + "      from works "
                + "      where end_date is null) "
                + "    ), "
                + "leaf_nodes As( "
                + "        (select cate_code "
                + "         from job_category) "
                + "        minus "
                + "        (select parent_cate "
                + "        from job_category)), "
                + "cate_open_positions As( "
                + "    select cate_code,pos_code "
                + "         from ( "
                + "                (select pos_code "
                + "                from job_position) "
                + "                minus "
                + "                  (select pos_code "
                + "                    from works "
                + "                    where end_date IS null) "
                + "                )natural join job_position natural join leaf_nodes), "
                + "open_positions_requirements As( "
                + "      select pos_code,ks_code "
                + "      from cate_open_positions natural join requirements), "
                + " "
                + "jobless_people_skills As ( "
                + "        select * "
                + "        from jobless_people natural join has_skill), "
                + "     "
                + "pos_qualified_people_count As( "
                + "        select distinct per_id,pos_code, count(ks_code) As counting "
                + "        from jobless_people_skills natural join open_positions_requirements "
                + "        group by per_id,pos_code), "
                + "number_of_req_of_each_position AS( "
                + "        select pos_code,count(ks_code) As counting "
                + "        from open_positions_requirements "
                + "        group by pos_code), "
                + "num_spots_per_category AS( "
                + "        select cate_code,count(pos_code) as spots "
                + "        from cate_open_positions "
                + "        group by cate_code), "
                + "num_qualified_per_cate AS( "
                + "        select cate_code,count(per_id) as counts "
                + "        from( "
                + "                select * "
                + "                from( "
                + "                    select distinct per_id,T.pos_code "
                + "                    from pos_qualified_people_count T,number_of_req_of_each_position S "
                + "                    where T.pos_code = S.pos_code and T.counting = S.counting)natural join cate_open_positions "
                + "            ) "
                + "            group by cate_code "
                + "    ), "
                + "category_ As( "
                + "    select cate_code "
                + "        from num_spots_per_category natural join num_qualified_per_cate "
                + "        where spots - counts =(select max(spots-counts) "
                + "                              from num_spots_per_category natural join num_qualified_per_cate)), "
                + "skills_needed As( "
                + "    select distinct ks_code "
                + "    from category_ natural join cate_open_positions natural join open_positions_requirements) "
                + "select distinct c_code "
                + "from teaches natural join skills_needed";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultData data = null;

        try {
            //open Connection
            conn = orcl.getConnection();
            //create the prepared statement
            ps = conn.prepareStatement(sql_statement);
            //execute the query and store result object in result
            result = ps.executeQuery();

            data = ResultConverter.resultSetToData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        //return result.
        return data;
    }

}
