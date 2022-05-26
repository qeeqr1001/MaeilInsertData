package insert;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.*;
import java.awt.*;
public class insertDAO extends GUI {
	  	public static ArrayList<String> worddic = new ArrayList<String>();		
	    public static ArrayList<Integer> countdic = new ArrayList<Integer>();

	    public static String user = "dosly2";
	    public static String password = "maeil123";
	    public static String url = "jdbc:mysql://dosly2.cafe24.com/dosly2?useUnicode=true&serverTimezone=Asia/Seoul&autoReconnect=true";
	    
	    public static Connection conn=null;
	    public static Statement state=null;
	    



	public insertDAO(String str) {
		super(str);

	}

	public static boolean create(DTO dto) throws Exception{
		
		
		
		try{ //DB���� �� DB���� word������ ��������.
       	

       	  Class.forName("com.mysql.jdbc.Driver");
   			conn = DriverManager.getConnection(url, user, password);
   			state = conn.createStatement();
   			String sql; //SQL���� ������ String
   			String sql2;
   		
   			sql2="SELECT * FROM word";
   			ResultSet rs = state.executeQuery(sql2); //SQL���� �����Ͽ� ����
   			

   			 			
   			    while(rs.next()){
   					String content = rs.getString("word_content"); //word_content���� �� ��������.
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

    			countdic.clear(); //countdic ����Ʈ �ʱ�ȭ.
    			state.executeUpdate(sql);
    			state2.executeUpdate(sql2);
    			
    			for (int i=0;i<wordf.length;i++) { //�Է��� �ܾ�Ű���� �迭 ���̸�ŭ �ݺ�
    				String fword_content=wordf[i];
    				
    				int fword_id=word_id+i; //word_id�� i��ŭ ����.
		    		
    				int ffword_id=0; //Ű���尡 �̹� DB�� ���� ����� word_id

    				int count=0; //Ű���尡 DB�� �ִ����� �����ϱ� ���� ����.
    				
    				
    		    	
    		    	for (int j=0;j<worddic.size();j++) {
    		    		if (fword_content.equals(worddic.get(j))==true) { //Ű���尡 DB�� �ִ� �ܾ�� ��ġ�� ���
    		    	
    		    			ffword_id=j; //word_id�� ������ �ִ� �ܾ��� ���̵� ���� ����.
    		    			count+=1; 
    		    			countdic.add(count); //DB�� �ִ� Ű����� ��ġ�� �ܾ��� ���� �ľ��ϴ� �迭.
    		    			break; //��ġ�� break�ϰ� �� �ݺ����� Ż��(if������ �Ѿ.)
    		    			
    		    		}
    		    		
    		    	}
    		    
    		    	if (count==0) { //��ġ�� �ܾ ���� ���.(word���̺� ���ο� �� �߰�+connect���̺� ����)
    		    			fword_id=fword_id-countdic.size(); //word_id�� ������ ������ countdic����Ʈ�� ����� �� ��.
    		    			System.out.println(countdic.size()); // ������ �ִ��� Ȯ���ϱ� ���� ���� �ڵ�.
    		    			dto.setWordId(fword_id);
    		    			System.out.println("wordID : "+fword_id);
    		    			//sql�� ����
    		    			String sql3="INSERT INTO word(word_id,word_content) VALUES('"+fword_id+"','"+fword_content+"')";
    	    				state3=(Statement)con.createStatement();
    	    				state3.executeUpdate(sql3);
    	    				String sql4="INSERT INTO connect(connect_questid,connect_wordid) VALUES('"+quest_id+"','"+fword_id+"')";
    	    				state4=(Statement)con.createStatement();
    	    				state4.executeUpdate(sql4);
    		    				}
    		    	else { //��ġ�� �ܾ ���� ���(word���̺� �� �߰� X, connect���̺� ���Ḹ ����.)
    		    		
    		    		System.out.println(countdic.size());
    		    		System.out.println("wordID : "+ffword_id);
    		    		//sql�� ����
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


