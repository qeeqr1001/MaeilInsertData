package insert;
import java.sql.*;
 
public class DataProcess {
	 public static String user = "dosly2";
	    public static String password = "maeil123";
	    public static String url = "jdbc:mysql://dosly2.cafe24.com/dosly2?useUnicode=true&serverTimezone=Asia/Seoul&autoReconnect=true";
	   
    public DataProcess(){ 
        Connection conn = null; 
    	Statement state = null; 
   
       
        try{
        	  Class.forName("com.mysql.jdbc.Driver");
    			conn = DriverManager.getConnection(url, user, password);
    			state = conn.createStatement();
    			String sql; //SQL문을 저장할 String
    			sql = "SELECT * FROM quest";
    			ResultSet rs = state.executeQuery(sql); //SQL문을 전달하여 실행
    						
    			    while(rs.next()){
    					String id = rs.getString("quest_id");
    					String content = rs.getString("quest_content");
    	
    				
    					System.out.println("id: "+id + "\ncontent: " + content ); 
    					
    				}
    				
    				rs.close();
    				state.close();
    				conn.close();
    			} catch(Exception e){
    						//예외 발생 시 처리부분

    			} finally { //예외가 있든 없든 무조건 실행
    				try{
    					if(state!=null)
    						state.close();
    				}catch(SQLException ex1){
    					//
    				}
    						
    				try{
    					if(conn!=null)
    						conn.close();
    				}catch(SQLException ex1){
    					//
    				}
    			}
        System.out.println("My SQL Close");
    }
        public static void main(String[] args) {
    		DataProcess dbConn = new DataProcess();

    	}
        
}



