package insert;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.*;
import java.awt.*;
public class insertDAO extends GUI {
	  public static ArrayList<String> worddic = new ArrayList<String>();		
	  public boolean overlap;

	    public static String user = "dosly2";
	    public static String password = "maeil123";
	    public static String url = "jdbc:mysql://dosly2.cafe24.com/dosly2?useUnicode=true&serverTimezone=Asia/Seoul&autoReconnect=true";
	  
	    public static Connection conn=null;
	    public static Statement state=null;
	    



	public insertDAO(String str) {
		super(str);
		// TODO Auto-generated constructor stub
	}

	public static boolean create(DTO dto) throws Exception{
		
		try{ //DB���� �� DB���� word������ ��������.
       	 System.out.println("Connect ����");

       	  Class.forName("com.mysql.jdbc.Driver");
   			conn = DriverManager.getConnection(url, user, password);
   			state = conn.createStatement();
   			String sql; //SQL���� ������ String
   			String sql2;
   		
   			sql2="SELECT * FROM word";
   			ResultSet rs = state.executeQuery(sql2); //SQL���� �����Ͽ� ����
   			

   			 			
   			    while(rs.next()){
   					String content = rs.getString("word_content");
   					worddic.add(content);
   					
   					
   				}
   			    
   			    
   				rs.close();
   				state.close();
   				conn.close();
   			} catch(Exception e){
   				e.printStackTrace();
   						//���� �߻� �� ó���κ�

   			} finally { //���ܰ� �ֵ� ���� ������ ����
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
    				
    				int fword_id=word_id+i;
		    		dto.setWordId(fword_id);
    				int ffword_id=0;

    				
    				int count=0;
    		    	
    		    		
    		    	for (int j=0;j<worddic.size();j++) {
    		    		if (fword_content.equals(worddic.get(j))==true) {
    		    	
    		    			ffword_id=j;
    		    			count+=1;
    		    		}
    		    		else {continue;}
    		    	}
    		    	if (count==0) {
    		    				
    		    			String sql3="INSERT INTO word(word_id,word_content) VALUES('"+fword_id+"','"+fword_content+"')";
    	    				state3=(Statement)con.createStatement();
    	    				state3.executeUpdate(sql3);
    	    				String sql4="INSERT INTO connect(connect_questid,connect_wordid) VALUES('"+quest_id+"','"+fword_id+"')";
    	    				state4=(Statement)con.createStatement();
    	    				state4.executeUpdate(sql4);
    		    				}
    		    	else {
    		    		fword_id=fword_id-1;
    		    		String sql4="INSERT INTO connect(connect_questid,connect_wordid) VALUES('"+quest_id+"','"+ffword_id+"')";
    		    		state4=(Statement)con.createStatement();
    		    		state4.executeUpdate(sql4);
    		    		
    		    				
    		    				
    		    			
    		    		}
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


