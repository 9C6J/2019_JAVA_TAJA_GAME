package taja;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

abstract public class Word{ // 추상클래스 선언
	public ArrayList<String> arr = new ArrayList<>(); // 어레이 리스트 arr정의
	
	abstract void create();
	
	abstract void shuffle();
}

class WordData extends Word{// 문제에 대한 클래스, 추상클래스 Word를 상속받는다.

	@Override
	public void create() { //문제를 생성하는 create메소드 추상메소드 create를 오버라이딩함
		// TODO Auto-generated method stub
		try{
			Scanner inputStream = new Scanner(new File("word.txt"));
		// inputStream 객체 생성, 파일이름은 word.txt이고 추후에 종류를받아 단어목록을 바꿀것이다.					
		
		while(inputStream.hasNextLine())//word.txt에 더이상 단어가 없을 때까지 읽는다.
			this.arr.add(inputStream.nextLine()); 
		
		// 읽어들인 어레이리스트를 arr에 추가한다.
		//sc.next() -> 다음 토큰을 읽습니다.
		//sc.nextLine -> 다음 행을 읽습니다.
		//sc.hasNext -> 토큰이 있는지 확인하고 boolean을 리턴합니다. 공백의 경우 무시합니다.
		//sc.hasNextLine -> 행이 있는지 확인 후 boolean을 리턴합니다. 줄바뀜이 있으면 true를 리턴합니다.
		
	}catch(FileNotFoundException e) { //파일을 찾을수 없을떄
	System.out.println("파일을 찾을수 없습니다."); // 파일을 찾을수 없다고 출력한다.
	}
}
	@Override
	void shuffle() {
		Collections.shuffle(this.arr);
		// 어레이리스트 arr의 값들을 무작위로 섞는다(랜덤으로 섞어주는것이라고한다)
		
	} 
	
	
	
	
}