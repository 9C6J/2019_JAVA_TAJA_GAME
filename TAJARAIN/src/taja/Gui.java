package taja;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;






public class Gui extends JPanel implements ActionListener, KeyListener{
	public static JLabel[] arrJLabel = new JLabel[50];
	public static JLabel[] rankViewJLabel = new JLabel[100];
	/* Jlabel 배열 생성한다. n개의 문제가 나오므로 n개를 생성한다.
	 * Rain클래스에서 사용해야 하므로
	 * 정적(Sataic) 필드 
	 */
	
	public static JLabel[] lifeMark = new JLabel[3];
	/*
	 * 또다른 JLabel 배열, life가 3개이므로 3개 생성
	 */
	public static int speed = 1000;// 글자가 내려오는 속도를 정적으로 정수형 변수로 선언한다. 초기값은 1000
	private JLabel numLabel, nameLabel, resultName, resultNumber, resultScore, resultTime;
	private JButton startButton, lowButton, highButton, midButton, quitButton,// JButton 정의
	rankButton, rankViewButton,beforButton;
	private JTextField inputText, inputName, inputNum; // JTextField 정의
	private Random myRandom = new Random(); // 랜덤함수정의
	totalPlayTime total_play_time = new totalPlayTime(); // 시간 타이머 쓰레드 클래스 생성	
	Rain data_rain = new Rain();
	WordData word_create = new WordData(); // 문제를 생성하는 클래스 생성
	private JLabel taja_score;
	public int score = 0;
	public static boolean rank_YN = false;
	public static ImageIcon icon, buttonIcon, buttonOnclick,midIcon,midIconClick,rankIcon,
	rankOnClick,lifeIcon, lowIcon, lowIconClick, highIcon, highIconClick, rankviewIcon, rankviewOnClick,
	beforIcon,beforOnclick;// ImageIcon 정의
	private String studentName, studentNumber; // 학번과, 학생이름 변수 String으로 정의
			
	public Gui() {
		
		setSize(800,596); // 패널 사이즈를 800x600지정한다.
		setLayout(null); // 위치를 절대값 위치로 지정하기 때문에, 레이아웃 null로 지정한다.
		
		icon = new ImageIcon("img/background1.jpg");
		buttonIcon = new ImageIcon("img/button.png");
		buttonOnclick = new ImageIcon("img/buttonclick.png");
		lifeIcon = new ImageIcon("img/life3.png");
		lowIcon = new ImageIcon("img/low.png");
		lowIconClick = new ImageIcon("img/lowclick.png");
		midIcon = new ImageIcon("img/mid.png");
		midIconClick = new ImageIcon("img/midclick.png");
		highIcon = new ImageIcon("img/high.png");
		highIconClick = new ImageIcon("img/highclick.png");		
		rankIcon = new ImageIcon("img/rank.png");
		rankOnClick = new ImageIcon("img/rankonclick.png");
		rankviewIcon = new ImageIcon("img/rankview.png");
		rankviewOnClick = new ImageIcon("img/rankviewonclick.png");
		beforIcon = new ImageIcon("img/yellowButton.png");
		beforOnclick = new ImageIcon("img/yellowButtonOnclick.png");
		
		
		startButton = new JButton(buttonIcon);
		startButton.setRolloverIcon(buttonOnclick);		
		//startButton에 마우스를 가져다 놓았을때의 아이콘
		//setRolloverIcon : 버튼에 마우스가 올라갔을때 이미지 변동시키기
		
		startButton.setOpaque(false); // startButton을 보이게한다.
		startButton.setBounds(560, 360, 100, 43); // startButton의 좌표와,범위 지정
		
		add(startButton); // Gui JPanel에 startButton을 추가한다.
		startButton.addActionListener(this); // startButton에 액션리스너를 추가한다.
		//
		
		startButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		startButton.setBorderPainted(false);
		startButton.setFocusPainted(false);
		startButton.setContentAreaFilled(false); //startButton을 투명으로 만든다.
		
		lowButton = new JButton(lowIcon);
		lowButton.setOpaque(false); // lowButton을 보이게한다.
		lowButton.setBounds(555,310,40,43);
		add(lowButton); // 패널에 난이도 하 버튼을 추가한다.
		lowButton.addActionListener(this);
		lowButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());//외곽선을 지워준다.
		lowButton.setBorderPainted(false); 
		lowButton.setFocusPainted(false); // 선택되었을때 테두리사용안함
		lowButton.setContentAreaFilled(false); // 내용영역 채우기않함
		
		midButton = new JButton(midIcon);
		midButton.setOpaque(false); // midButton을 보이게한다.
		midButton.setBounds(590,310,40,43);
		add(midButton); // 패널에 난이도 중 버튼을 추가한다.
		midButton.addActionListener(this);
		midButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		midButton.setBorderPainted(false);
		midButton.setFocusPainted(false); // 선택되었을때 테두리사용안함
		midButton.setContentAreaFilled(false); // 내용영역 채우기 않함
		
		highButton = new JButton(highIcon);
		highButton.setOpaque(false);
		highButton.setBounds(625,310,40,43);
		add(highButton); // 패널에 난이도 상 버튼을추가한다.
		highButton.addActionListener(this);
		highButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		highButton.setBorderPainted(false);
		highButton.setFocusPainted(false);
		highButton.setContentAreaFilled(false); 
		
		rankButton = new JButton(rankIcon);
		rankButton.setOpaque(false);
		rankButton.setBounds(560, 260, 100, 43);
		add(rankButton); // 패널에 난이도 상 버튼을추가한다.
		rankButton.addActionListener(this);
		rankButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		rankButton.setBorderPainted(false);
		rankButton.setFocusPainted(false);
		rankButton.setContentAreaFilled(false); 
		
		rankViewButton = new JButton(rankviewIcon);
		rankViewButton.setOpaque(false);
		rankViewButton.setBounds(560, 400, 100, 43);
		add(rankViewButton);
		rankViewButton.addActionListener(this);
		rankViewButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		rankViewButton.setBorderPainted(false);
		rankViewButton.setFocusPainted(false);
		rankViewButton.setContentAreaFilled(false); 
		
		inputNum = new JTextField(10);
		inputNum.setOpaque(false); //있으면 안쪽을 흰색 아니면 투명화
		inputNum.setBounds(360,295,180,35);
		add(inputNum);// 이름 입력 텍스트필드 추가
		inputNum.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		// 외곽선 제거
		
		
		inputName = new JTextField(10);
		inputName.setOpaque(false); //있으면 안쪽을 흰색 아니면 투명화
		inputName.setBounds(360,370,180,35);
		add(inputName);
		inputName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
	
		total_play_time.playTime.setBounds(390,0,200,50);
		total_play_time.playTime.setFont(new Font("Dialog",Font.BOLD,30));
		add(total_play_time.playTime); // 패널에 추가
		total_play_time.playTime.setVisible(false); // total_play_time의 palyTime을 안ㅇ보이게한다
		word_create.create(); // word_create클래스에서 단어를 생성하는 create메소드를 실행한
	}

	@Override
	public void actionPerformed(ActionEvent e) { // 버튼이벤트 정의
		if(e.getSource() == startButton) { //startButton을 클릭하게되면

			studentName = inputName.getText(); // studentName은 inputName텍스트필드에 입력한 값이된다.
			studentNumber = inputNum.getText(); // studentNumber은 inputNum텍스트필드에 입력한 값이된다.
			
			if(studentNumber.length()!=7) {//학번이 7자리가 아닐때 return시킴
				JOptionPane.showMessageDialog(null, "학번을 입력해주세요");
				return;
			}
			
			if(studentName.length()==0) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
				return;
			}
		
			
			nameLabel = new JLabel(studentName);
			numLabel = new JLabel(studentNumber);
			
			firstStart(); // firstStart = 게임시작  메소드실행
			
		}else if(e.getSource() == lowButton) {// lowButton 버튼을 클릭하게 되면
			lowButton.setIcon(lowIconClick); // 난이도 하가 눌린이미지로 바뀌고
			midButton.setIcon(midIcon); // 난이도 중은 원래 이미지로
			highButton.setIcon(highIcon); // 난이도 상은 원래 이미지로 되돌린다.
			rankButton.setIcon(rankIcon);
			rankViewButton.setIcon(rankviewIcon);
			speed = 1200; // speed를 1200으로 한다.
			rank_YN = false;
			
		}else if(e.getSource() == midButton){// midButton 버튼을 클릭하게된다면
			midButton.setIcon(midIconClick);
			lowButton.setIcon(lowIcon);
			highButton.setIcon(highIcon);
			rankButton.setIcon(rankIcon);
			rankViewButton.setIcon(rankviewIcon);
			speed = 700;
			rank_YN = false;
		}else if(e.getSource() == highButton){ // highButton 버튼을 클릭하게되면
			highButton.setIcon(highIconClick);
			lowButton.setIcon(lowIcon);
			midButton.setIcon(midIcon);
			rankButton.setIcon(rankIcon);
			rankViewButton.setIcon(rankviewIcon);
			speed = 300; // highButton으로 바꾸고, speed를 200으로바군다.
			rank_YN = false;
			
		}else if(e.getSource()==rankButton) {
			
			rankButton.setIcon(rankOnClick);
			midButton.setIcon(midIcon);
			lowButton.setIcon(lowIcon);
			highButton.setIcon(highIcon);
			rankViewButton.setIcon(rankviewIcon);
			rank_YN = true;
			speed = 700;
			
		
		
			
		}else if(e.getSource()==rankViewButton) {
			
			rankButton.setIcon(rankIcon);
			midButton.setIcon(midIcon);
			lowButton.setIcon(lowIcon);
			highButton.setIcon(highIcon);
			rankViewButton.setIcon(rankviewOnClick);
			
			
			rankView();
			setSize(800,596);
			
		}
		else if(e.getSource()==beforButton) {
			
		beforView();	
		
		}
		else if(e.getSource()==quitButton) {
			
			System.exit(0); // 프로그램을 종료한다
			
			}
	
		
	}
	private void beforView() {
		
		
		
		icon = new ImageIcon("img/background1.jpg");
		startButton.setVisible(true); 
		inputName.setVisible(true);
		inputNum.setVisible(true);
		lowButton.setVisible(true);
		midButton.setVisible(true);
		highButton.setVisible(true);
		rankButton.setVisible(true);
		rankViewButton.setVisible(true);
		beforButton.setVisible(false);
		setSize(800,597);
		
		DbConnection db = DbConnection.getInstance();
		ArrayList<String> rank = new ArrayList<String>();
		rank = db.rank();
		
		for (int i = 0; i < rankViewJLabel.length; i++) {
			rankViewJLabel[i].setVisible(false);
		}
		
		
		
	}
	private void firstStart() {// 시작하기 버튼을 눌렀을 때의 메소드(게임시작)
		if(rank_YN==true)
		{
			arrJLabel = new JLabel[100]; //랭크전이면 많이뜨게하기
		}
		inputText = new JTextField(2);
		inputText.addKeyListener(this); // 텍스트필드에 키 이벤트 추가(엔터를 추가위해)
		inputText.setBounds(363,499,150,58);
		inputText.setOpaque(false); // 입력창 투명화시켜줌 아니면 흰색
		inputText.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //입력창 외곽선을 없애준다.
		add(inputText); // 답을 입력하는 텍스트필드를 추가한다.
		
		taja_score = new JLabel("0");
		
		
		
		for (int i = 0; i < 3; i++) {
			lifeMark[i] = new JLabel(lifeIcon);
			lifeMark[i].setOpaque(false);
			lifeMark[i].setBounds(0+(i*80),460,80,80);
			add(lifeMark[i]);
			lifeMark[i].setBorder(javax.swing.BorderFactory.createEmptyBorder());
			lifeMark[i].setVisible(true);
			}
		
		//게임 왼쪽상단위에 학번과 이름이 표시되게한다.
		add(numLabel);
		add(nameLabel);
		
		add(taja_score); //점수표시부분
		taja_score.setBounds(693,497,100,60);
		numLabel.setBounds(105,4,100,60);
		nameLabel.setBounds(110,34,100,60);
		
		//게임시작을 위해 메인화면에 있던것을 가려준다.
		startButton.setVisible(false); // 시작 버튼 안보이게
		inputName.setVisible(false);
		inputNum.setVisible(false);
		inputText.setVisible(true); // 답 입력창 보이게 함
		lowButton.setVisible(false);
		midButton.setVisible(false);
		highButton.setVisible(false);
		rankButton.setVisible(false);
		rankViewButton.setVisible(false);
		
		// 게임배경으로 바꿔준다.
		icon = new ImageIcon("img/background2.png");
		
		word_create.shuffle(); // 게임시작을위해 나올단어를 섞어준다.
		for (int i = 0; i < arrJLabel.length; i++){
	
			int s = i/10;
			arrJLabel[i] = new JLabel(word_create.arr.get(i));
			arrJLabel[i].setBounds(0,0,500,30); // arrJLabel의 범위지정
			arrJLabel[i].setFont(new Font("Dialog",Font.BOLD,20)); // 나올단어의폰트지정
			if(arrJLabel[i].getText().equals("영진전문대학교")||arrJLabel[i].getText().equals("차재영")||arrJLabel[i].getText().equals("컴퓨터정보계열")) {
				
				arrJLabel[i].setForeground(Color.RED);
				
					
			}
			switch (s) { //단어를 뿌려줄 좌표지정 10개씩 끊어서 뿌려준다. 겹치지않기위해 설정
			case 0:
				arrJLabel[i].setLocation(i * 70, (myRandom.nextInt(700)+myRandom.nextInt(100)+300));
				break;
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				arrJLabel[i].setLocation(i* 70-700*s,  (myRandom.nextInt(700)+myRandom.nextInt(100)+300)*s);
				break;
			
			
			
			}
						
			add(arrJLabel[i]); // arrJLabel을 패널에 추가한다.
			}

		
		for(int j=0;j<3; j++) {
			lifeMark[j].setVisible(true);
		}
		total_play_time.playTime.setVisible(true);
		total_play_time.start();
		data_rain.start();
		
		
		
	}
		
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) { //키이벤트정의
		// TODO Auto-generated method stub
		if(e.getKeyCode()== 10) {//엔터가 눌리면
			
			removeAnswer();
			endAnswer();
		}
	}

	private void removeAnswer() {
		// TODO Auto-generated method stub
		for (int i = 0; i < arrJLabel.length; i++) {
			if(arrJLabel[i].isVisible()==true &&inputText.getText().equals(word_create.arr.get(i))&&
					(arrJLabel[i].getY()>10&&arrJLabel[i].getY()<575)) {
				//입력값이 word에 있는 어떤 단어와 같고 그 단어가 바다그림안에서 존재할때 
				if(arrJLabel[i].getForeground()==Color.RED)
				{	
					System.out.println("빨강을입력했군");
					data_rain.setLifePlus(true);
					
					
				}
				//입력된값ㅇㅣ 어레이리스트의 어느값과 일치하면
				arrJLabel[i].setVisible(false);
				score += (1300-speed);
				taja_score.setText(""+score);
				
			
			}
			
		}
		inputText.setText("");
		// 엔터를친후 입력된값을 다시 초기화해주기위해서!
	}
	public void rankView() {
		
		beforButton = new JButton(beforIcon);
		beforButton.setOpaque(false); // lowButton을 보이게한다.
		beforButton.setBounds(370,480,95, 30);
		add(beforButton); // 패널에 난이도 하 버튼을 추가한다.
		beforButton.addActionListener(this);
		beforButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());//외곽선을 지워준다.
		beforButton.setBorderPainted(false); 
		beforButton.setFocusPainted(false); // 선택되었을때 테두리사용안함
		beforButton.setContentAreaFilled(false); // 내용영역 채우기않함
		beforButton.setRolloverIcon(beforOnclick);		
		//startButton에 마우스를 가져다 놓았을때의 아이콘
		//setRolloverIcon : 버튼에 마우스가 올라갔을때 이미지 변동시키기
		
		startButton.setVisible(false); 
		inputName.setVisible(false);
		inputNum.setVisible(false);
		lowButton.setVisible(false);
		midButton.setVisible(false);
		highButton.setVisible(false);
		rankButton.setVisible(false);
		rankViewButton.setVisible(false);
		
		icon = new ImageIcon("img/rankviewboard.png");
		
		
		DbConnection db = DbConnection.getInstance();
		ArrayList<String> rank = new ArrayList<String>();
		rank = db.rank();
		
		for (int i = 0; i < 5; i++) {

		
			for (String s : rank) {
			
				if (s.substring(0,s.indexOf("★")).equals(""+(i+1))) {
					rankViewJLabel[i] = new JLabel(s.substring(s.indexOf("在")+1,s.indexOf("映"))+"점 "+s.substring(s.indexOf("★")+1,s.indexOf("車"))+" "+s.substring(s.indexOf("車")+1,s.indexOf("在")));
					 rankViewJLabel[i].setBounds(0,0,300,30); // arrJLabel의 범위지정
					 rankViewJLabel[i].setFont(new Font("Dialog",Font.BOLD,20)); // 나올단어의폰트지정
					 rankViewJLabel[i].setForeground(Color.BLACK); // 글자색을 흰색으로
					 rankViewJLabel[i].setLocation(320, 130+(i*75));
					 
					 add(rankViewJLabel[i]); // arrJLabel을 패널에 추가한다.
				}
				
			}
			 
			 	
			
		if(i==4)	 {
			for (String s : rank) {
				System.out.println(s);
				
				System.out.println("순위 :" +s.substring(0,s.indexOf("★"))); 
				System.out.println("학번 :"+s.substring(s.indexOf("★")+1,s.indexOf("車")));
				System.out.println("이름 :"+s.substring(s.indexOf("車")+1,s.indexOf("在")));
				System.out.println("점수 :"+s.substring(s.indexOf("在")+1,s.indexOf("映")));
				System.out.println("시간 :"+s.substring(s.indexOf("映")+1,s.length()));
			
				}
		}
		
		
		
		
		}
		
	
	}
	public void endAnswer() { // 게임이 끝났을때 메소드
		int result=0;
		
		for (int i = 0; i < arrJLabel.length; i++) {

			if(arrJLabel[i].isVisible()==false) {
				result++;
			}
			
			
		}

		if(result==arrJLabel.length) {
			data_rain.stop();
			total_play_time.stop();
			icon = new ImageIcon("img/background3.png");
			setSize(800,596);
			resultNumber = new JLabel(studentNumber);
			resultNumber.setBounds(360, 156, 200, 100);
			resultNumber.setFont(new Font("굴림", Font.BOLD, 22));
			resultNumber.setForeground(Color.WHITE);
			add(resultNumber); // 결과창에 학번레이블을 생성해서 추가한다

			resultName = new JLabel(studentName);
			resultName.setBounds(360, 206, 200, 100);
			resultName.setFont(new Font("굴림", Font.BOLD, 22));
			resultName.setForeground(Color.WHITE);
			add(resultName);// 결과창에 이름레이블을 생성해서 추가한다
			
			resultTime = new JLabel((Integer.toString(total_play_time.gamePlayTime) + "초"));
			/*
			 * 게임시간을 String으로 바꾸어서 JLabel을 만든다
			 */
			resultTime.setBounds(360, 263, 200, 100);
			resultTime.setFont(new Font("굴림", Font.BOLD, 22));
			resultTime.setForeground(Color.WHITE);
			add(resultTime); // 결과창에 시간 레이블을 생성해서 추가한다
			
			
			resultScore = new JLabel(score + "점");
			resultScore.setBounds(360, 313, 200, 100);
			resultScore.setFont(new Font("굴림", Font.BOLD, 22));
			resultScore.setForeground(Color.WHITE);
			add(resultScore); //결과창에 점수레이블을 생성해 추가한다.

			System.out.println(resultNumber.getText());
			System.out.println(resultName.getText());
			System.out.println(taja_score.getText());

			quitButton = new JButton();
			quitButton.setOpaque(false);
			quitButton.setBounds(360, 400, 80, 30);
			add(quitButton);
			quitButton.addActionListener(this);
			quitButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			quitButton.setBorderPainted(false);
			quitButton.setFocusPainted(false);
			quitButton.setContentAreaFilled(false); // 종료하기 버튼을 만들어서 투명으로 설정한후
													// ActionListener 추가한다
			
			
			for (int j = 0; j < lifeMark.length; j++) {
				lifeMark[j].setVisible(false);
			} // 생명을 보이지 않게 한다
			
			taja_score.setVisible(false);
			inputText.setVisible(false);
			
			
			 DbConnection db = DbConnection.getInstance();
			 db.rank();
			 if(rank_YN==true)
			 db.insert(studentNumber,studentName,taja_score.getText(),Integer.toString(total_play_time.gamePlayTime));
			
			
		
			
		}
		
		 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void paintComponent(Graphics g) {
		g.drawImage(icon.getImage(),0,0,null); // 0,0좌표부터 이미지를 뿌림
		
		setOpaque(false); //그림을 투명하게
		super.paintComponent(g);
	}
	
}
