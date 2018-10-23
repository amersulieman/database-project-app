import java.util.*;

public class ResultData{
  int currentRowIndex = 0;
  int currentColIndex = 0;

  Object currentItem;

  ArrayList<String> columns;
  ArrayList< ArrayList<Object> > data;

  ResultData(){
    this.data = new ArrayList<>();
    this.columns = new ArrayList<>();
  }

  public void addData(int col, Object val){
    this.data.get(col).add(val);
  }

  public void addColumn(String name){
    columns.add(name);
    data.add(new ArrayList<Object>());
  }

  public int getRowCount(){
    return this.data.get(0).size();
  }

  public int getColumnCount(){
    return columns.size();
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder("");
    sb.append("| ");
    for(String name : this.columns){
      sb.append(name+" | ");
    }
    sb.append("\n");

    int height = this.data.get(0).size();
    int width = this.columns.size();

    for(int i=0; i< height; i++){
      for(int j=0; j<width; j++){
        sb.append(this.data.get(j).get(i) + ", ");
      }
      sb.append("\n");
    }

    return sb.toString();
  }

  public String toJsonString(){
    StringBuilder sb = new StringBuilder("");

    /*Column Labels*/
    sb.append("{\n");
    sb.append("\t\"column_labels\":[");

    for(String name : this.columns){
      sb.append("\""+ name + "\", ");
    }

    sb.delete(sb.length()-2, sb.length());
    sb.append("],\n");

    /*Row Data*/
    sb.append("\t\"data\": [\n");

    int height = this.data.get(0).size();
    int width = this.columns.size();

    for(int i=0; i< height; i++){//for each row.
      sb.append("\t\t{");//two tabs
      for(int j=0; j<width; j++){//for each column
        sb.append(String.format("\"%s\" : \"%s\", ", this.columns.get(j), this.data.get(j).get(i) ));
      }
      sb.delete(sb.length()-2, sb.length());
      sb.append("},\n");//create new line.
    }
    sb.delete(sb.length()-2, sb.length());

    sb.append("\n\t]\n");

    //close of the object
    sb.append("}");

    return sb.toString();
  }

}
