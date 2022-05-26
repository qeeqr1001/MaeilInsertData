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
		
		
		
		try{ //DB연결 후 DB에서 word데이터 가져오기.
       	

       	  Class.forName("com.mysql.jdbc.Driver");
   			conn = DriverManager.getConnection(url, user, password);
   			state = conn.createStatement();
   			String sql; //SQL문을 저장할 String
   			String sql2;
   		
   			sql2="SELECT * FROM word";
   			ResultSet rs = state.executeQuery(sql2); //SQL문을 전달하여 실행
   			

   			 			
   			    while(rs.next()){
   					String content = rs.getString("word_content"); //word_content에서 값 가져오기.
   					worddic.add(content);
   					
   					
   				}
   			    
   			    
   				rs.close();
   				state.close();
   				conn.close();
   			} catch(Exception e){
   				e.printStackTrace();
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

    			countdic.clear(); //countdic 리스트 초기화.
    			state.executeUpdate(sql);
    			state2.executeUpdate(sql2);
    			
    			for (int i=0;i<wordf.length;i++) { //입력한 단어키워드 배열 길이만큼 반복
    				String fword_content=wordf[i];
    				
    				int fword_id=word_id+i; //word_id가 i만큼 증가.
		    		
    				int ffword_id=0; //키워드가 이미 DB에 있을 경우의 word_id

    				int count=0; //키워드가 DB에 있는지를 구분하기 위한 변수.
    				
    				
    		    	
    		    	for (int j=0;j<worddic.size();j++) {
    		    		if (fword_content.equals(worddic.get(j))==true) { //키워드가 DB에 있는 단어와 일치할 경우
    		    	
    		    			ffword_id=j; //word_id는 기존에 있던 단어의 아이디 값을 전달.
    		    			count+=1; 
    		    			countdic.add(count); //DB에 있는 키워드와 겹치는 단어의 수를 파악하는 배열.
    		    			break; //겹치면 break하고 이 반복문을 탈출(if문으로 넘어감.)
    		    			
    		    		}
    		    		
    		    	}
    		    
    		    	if (count==0) { //겹치는 단어가 없을 경우.(word테이블에 새로운 값 추가+connect테이블에 연결)
    		    			fword_id=fword_id-countdic.size(); //word_id는 기존의 값에서 countdic리스트의 사이즈를 뺀 값.
    		    			System.out.println(countdic.size()); // 오류가 있는지 확인하기 위해 넣은 코드.
    		    			dto.setWordId(fword_id);
    		    			System.out.println("wordID : "+fword_id);
    		    			//sql문 실행
    		    			String sql3="INSERT INTO word(word_id,word_content) VALUES('"+fword_id+"','"+fword_content+"')";
    	    				state3=(Statement)con.createStatement();
    	    				state3.executeUpdate(sql3);
    	    				String sql4="INSERT INTO connect(connect_questid,connect_wordid) VALUES('"+quest_id+"','"+fword_id+"')";
    	    				state4=(Statement)con.createStatement();
    	    				state4.executeUpdate(sql4);
    		    				}
    		    	else { //겹치는 단어가 있을 경우(word테이블에 값 추가 X, connect테이블 연결만 진행.)
    		    		
    		    		System.out.println(countdic.size());
    		    		System.out.println("wordID : "+ffword_id);
    		    		//sql문 실행
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


