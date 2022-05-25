package insert;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener, ItemListener,Runnable {

	DTO dto=new DTO(); 
	TextField quest_id; 
	TextArea quest_content;
	TextField word_id;
	TextField word_content;
	TextArea ans_content;
	JButton b1; 
	JButton b2;
	public static String[] wordf;
	
	private boolean is;
	public GUI(String str) {
		super(str);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();

		
		this.setLocation(100,100); 
		super.setVisible(true);
		super.setSize(700,700);
		
	}
	public void init() {
		Panel p=new Panel();
		Panel bp=new Panel();
		setLayout(new BorderLayout());
		Label id=new Label("질문/답변 아이디");
		Label content=new Label("질문 내용");
		Label word=new Label("질문 핵심 단어");
		Label wordid=new Label("단어 시작 아이디");
		Label answer=new Label("답변 내용");
		b1=new JButton ("전송");
		b1.addActionListener(this); 
		b2=new JButton ("리셋");
		b2.addActionListener(this);
		
		quest_id=new TextField(20);
		quest_content=new TextArea(5,20);
		word_id=new TextField(20);
		word_content=new TextField(20);
		ans_content=new TextArea(10,50);
		
		GridLayout g=new GridLayout(6,2);
		p.setLayout(g);
		p.add(id);
		p.add(quest_id);
		p.add(content);
		p.add(quest_content);
		p.add(wordid);
		p.add(word_id);
		p.add(word);
		p.add(word_content);
		
		p.add(answer);
		p.add(ans_content);
		
		bp.add(b1);
		bp.add(b2);
		add("Center",p);
		add("South",bp);

		
	}

	public static void main(String[] args) {
	
		GUI Quest=new GUI("데이터 입력");

	}

	@Override
	public void run() {
		   
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		

		if ((JButton)obj==b1) {

		
			dto.setId(quest_id.getText()); 
			dto.setContent(quest_content.getText());
		
//			dto.setWordId(word_id.getText());
//			dto.setWord(word_content.getText());
			
	
			wordf = word_content.getText().split(",");
			
			for (int i=0;i<wordf.length;i++) {
				dto.setWord(wordf[i]);
				
				dto.setWordId(Integer.parseInt(word_id.getText()));
					
				
			}

			dto.setText(ans_content.getText());
			try {
				insertDAO.create(dto); 

				
				
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			
		}
		else if ((JButton)obj==b2) {
			quest_id.setText("");
			quest_content.setText("");
			word_id.setText("");
			word_content.setText("");
			ans_content.setText("");


		}
	}
		


	@Override
	public void itemStateChanged(ItemEvent e) {

		
	}

}
