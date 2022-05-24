package insert;
import java.sql.*;
import com.mysql.jdbc.*;
import java.awt.*;
public class insertDAO extends GUI {

	public insertDAO(String str) {
		super(str);
		// TODO Auto-generated constructor stub
	}

	public static boolean create(DTO dto) throws Exception{
		
		boolean flag=false;
		Connection con = null; 
	    Statement state = null; 
	    Statement state2=null;
	    Statement state3=null;
	    Statement state4=null;
	    
	    String quest_id=dto.getId();
	    String quest_content=dto.getContent();
	    
	    int word_id=dto.getWordId();
	    String word_content=dto.getWord();
	    String ans_content=dto.getText();
	    
	    String sql="INSERT INTO quest(quest_id,quest_content) VALUES('"+quest_id+"','"+quest_content+"')";

	    String sql2="INSERT INTO answer(ans_id,ans_content) VALUES('"+quest_id+"','"+ans_content+"')";
	
	
	 
	    try {
	    	
	    	

	    	
        	  Class.forName("com.mysql.jdbc.Driver");
    			con = DriverManager.getConnection("jdbc:mysql://dosly2.cafe24.com/dosly2?useUnicode=true&serverTimezone=Asia/Seoul&autoReconnect=true", "dosly2", "maeil123");
    			state =(Statement)con.createStatement();
    			state2=(Statement)con.createStatement();

    			
    			state.executeUpdate(sql);
    			state2.executeUpdate(sql2);
    			for (int i=0;i<wordf.length;i++) {
    				String fword_content=wordf[i];
    				dto.setWord(fword_content);
    				int fword_id=word_id+i;
    				dto.setWordId(fword_id);
    			
    				String sql3="INSERT INTO word(word_id,word_content) VALUES('"+fword_id+"','"+fword_content+"')";
    				state3=(Statement)con.createStatement();
    				state3.executeUpdate(sql3);	
    				String sql4="INSERT INTO connect(connect_questid,connect_wordid) VALUES('"+quest_id+"','"+fword_id+"')";
    				state4=(Statement)con.createStatement();
    				state4.executeUpdate(sql4);	
    			}
    		 
  
    	
    			flag=true;
    			 
    			} catch(Exception e){
    				System.out.println(e);
    				flag=false;
    						

    			} finally { 
    				
    				try{
    					
        				if(state!=null)
        					state.close();
        					state2.close();
        						
    					if(con!=null)
    						con.close();
    				}catch(SQLException e){
    					System.out.println(e.getMessage());
    				}
    			}
        return flag;
	}
	
}

