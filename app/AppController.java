import java.sql.*;

public class AppController{

  AppModel app;

  AppController(){
    this.app = new AppModel();
  }

  /*Create a course*/
  public int createCourse(String c_course,String title,String lvl,String description,String status,int price){
    return app.createCourse(c_course,title,lvl,description,status,price);
  }
  /*Delete a course*/
  public int deleteCourse(String c_course){
    return app.deleteCourse(c_course);
  }
  /*Create a job category*/
  public int createCate(String cate_code,String title,int pay_range_high,int pay_range_low,String parent_cate){
    return app.createCate(cate_code,title,pay_range_high,pay_range_low,parent_cate);
  }
  /*Delete a job category*/
  public int deleteCate(String cate_code){
    return app.deleteCate(cate_code);
  }
  /*Create a job position*/
  public int createPos(String pos_code,String pos_title,String emp_mode,int pay_rate,String pay_type,String cate_code,String comp_id){
    return app.createPos(pos_code,pos_title,emp_mode,pay_rate,pay_type,cate_code,comp_id);
  }
  /*Delete a job position*/
  public int deletePos(String pos_code){
    return app.deletePos(pos_code);
  }
  /*update has skill after updating takes*/
  public int updateHasSkillOnTakes(int per_id){
    return app.updateHasSkillOnTakes(per_id);
  }

  /*insert into takes table*/
  public int insertTakes(int per_id, String sec_no, String c_code, String complete_date, String offered_by){
    return app.insertTakes(per_id, sec_no, c_code, complete_date, offered_by);
  }

  /*Query 1*/
  public ResultData getCompWorkersByCompId(String comp_id){
    return app.getCompWorkersByCompId(comp_id);
  }

  /*Query 2*/
  public ResultData getCompWorkersSalaryByCompId(String comp_id){
    return app.getCompWorkersSalaryByCompId( comp_id);
  }

  /*Query 3*/
  public ResultData getAllCompLaborCosts(){
    return app.getAllCompLaborCosts();
  }

  /*Query 4*/
  public ResultData getAllPositionsByPersonId(int per_id){
    return app.getAllPositionsByPersonId( per_id);
  }

  /*Query 5*/
  public ResultData getKnowledgeSkillsByPersonId(int per_id){
    return app.getKnowledgeSkillsByPersonId( per_id);
  }

  /*Query 6*/
  public ResultData getSkillGapOfCurrentPositionsByPersonId(int per_id){
    return app.getSkillGapOfCurrentPositionsByPersonId( per_id);
  }

  /*Query 7.a*/
  public ResultData getReqKnowledgeSkillsByPositionCode(String pos_code){
    return app.getReqKnowledgeSkillsByPositionCode( pos_code);
  }

  /*Query 7.b*/
  public ResultData getReqKnowledgeSkillsByCateCode(String cate_code){
    return app.getReqKnowledgeSkillsByCateCode(cate_code);
  }

  /*Query 8 */
  public ResultData getSkillGapByPersoIdForPositionCode(int per_id, String pos_code){
    return app.getSkillGapByPersoIdForPositionCode( per_id,  pos_code);
  }

  /*Query 9*/
  public ResultData getCoursesThatTeachMissingSkillsByPersonIdForPositionCode(int per_id, String pos_code){
    return app.getCoursesThatTeachMissingSkillsByPersonIdForPositionCode( per_id,  pos_code);
  }

  /*Query 10 */
  public ResultData getQuickestCoursesThatTeachMissingSkillsByPersonIdForPositionCode(int per_id, String pos_code){
    return app.getQuickestCoursesThatTeachMissingSkillsByPersonIdForPositionCode(per_id,pos_code);
  }

  /*Query 10 */
  public ResultData query12(int per_id, String pos_code){
    return app.query12(per_id,pos_code);
  }

  /*Query 11 */
  public ResultData getCheapestCoursesThatTeachMissingSkillsByPersonIdForPositionCode(int per_id, String pos_code){
    return app.getCheapestCoursesThatTeachMissingSkillsByPersonIdForPositionCode(per_id, pos_code);
  }

  /*Query 13 */
  public ResultData jobCategory_person_qualified_for(int per_id) {
    return app.jobCategory_person_qualified_for(per_id);
  }

  /*Query 14 */
  public ResultData highest_pay_rate_position(int per_id) {
    return app.highest_pay_rate_position(per_id);
  }

  /*Query 15 */
  public ResultData qualified_for_position(String pos_code) {
    return app.qualified_for_position(pos_code);
  }

  /*Query 16 */
  public ResultData missing_one_list(String pos_code) {
    return app.missing_one_list(pos_code);
  }

  /*Query 17 */
  public ResultData missing_skills_missing_one_list(String pos_code) {
    return app.missing_skills_missing_one_list(pos_code);
  }

  /*Query 18 */
  public ResultData people_miss_least_number_of_skills(String pos_code) {
    return app.people_miss_least_number_of_skills(pos_code);
  }

  /*Query 19 */
  public ResultData k_list(String pos_code,int k) {
    return app.k_list(pos_code, k);
  }

  /*Query 20 */
  public ResultData missed_skills_from_k_list(String pos_code,int k) {
    return app.missed_skills_from_k_list(pos_code, k);
  }

  /*Query 21 */
  public ResultData national_crises_needed_people(String cate_code) {
    return app.national_crises_needed_people(cate_code);
  }

  /*Query 22 */
  public ResultData unemployed_people_for_old_position(String pos_code) {
    return app.unemployed_people_for_old_position(pos_code);
  }

  /*Query 23-A */
  public ResultData biggest_employer_by_number_employees() {
    return app.biggest_employer_by_number_employees();
  }

  /*Query 23-B */
  public ResultData biggest_employer_by_payroll() {
    return app.biggest_employer_by_payroll();
  }

  /*Query 24-A */
  public ResultData biggest_sector_by_number_of_employees() {
    return app.biggest_sector_by_number_of_employees();
  }

  /*Query 24-B */
  public ResultData biggest_sector_by_payroll() {
    return app.biggest_sector_by_payroll();
  }

  /*Query 25-A */
  public ResultData how_many_people_earning_increased() {
    return app.how_many_people_earning_increased();
  }

  /*Query 25-B */
  public ResultData how_many_people_earning_decreased() {
    return app.how_many_people_earning_decreased();
  }

  /*Query 25-C*/
  public ResultData ratio_increased_to_decreased_earnings() {
    return app.ratio_increased_to_decreased_earnings();
  }

  /*Query 25-D*/
  public ResultData average_of_earning_change_for_a_sector(String sec_id) {
    return app.average_of_earning_change_for_a_sector(sec_id);
  }

  /*Query 26*/
  public ResultData leaf_node_job_category() {
    return app.leaf_node_job_category();
  }

  /*Query 27*/
  public ResultData courses_help_jobless_people() {
    return app.courses_help_jobless_people();
  }





}
