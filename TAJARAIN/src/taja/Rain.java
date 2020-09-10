package taja;

public class Rain extends Thread{ // 글자들을 떨어지게하고 클레스. 쓰레드를 상속받는다.
	
	
	public int life = 3; // 초기 라이프의 값은 = 3
	
	boolean lifePlus = false;
	
	public boolean isLifePlus() {
		return lifePlus;
	}
	public void setLifePlus(boolean lifePlus) {
		this.lifePlus = lifePlus;
	}
	public int rankSpeed = 1000;
	public int count=0;
	public void run() {
		while(true) {
		
				try {
					
					for (int j = 0; j < Gui.arrJLabel.length; j++) {
					
						int x = Gui.arrJLabel[j].getX();
						//GUi 의 j번째 arrJLabel의 x좌표값을 가져와 x에 저장한다.
						
						int y = Gui.arrJLabel[j].getY();
						//Gui 의 j번째 arrJLabel의 y좌표값을 가져와 y에 저장한다.
						
						y -= 10; // 좌표를 10씩 증가시킨다. -> 비가 내리는것처럼
						Gui.arrJLabel[j].setLocation(x,y); 
						//그 좌표로 다시 설정해준다.
						
						
			if(Gui.arrJLabel[j].isVisible()&&
					Gui.arrJLabel[j].getY()<0) //단어가 Y좌표에 닿으면 0이상 닿으면 
						{
							Gui.arrJLabel[j].setVisible(false); // 하늘에닿은 단어를 없앤다.
							//라이프를 깎는다. 
							life -= 1; // 라이프를 1깎는다.
							
							switch(life){ // 라이프를 1감소시킨다.
							
							/*
							 * case 5: Gui.lifeMark[5].setVisible(true); break; case 4:
							 * Gui.lifeMark[4].setVisible(true); break; case 3: // life가 2일때
							 * Gui.lifeMark[3].setVisible(true); // break;
							 */
							case 5:
								Gui.lifeMark[5].setVisible(false);
								Gui.lifeMark[4].setVisible(true); // 생명하나를 안보이게한다.
								Gui.lifeMark[3].setVisible(true); 
								Gui.lifeMark[2].setVisible(true); 
								Gui.lifeMark[1].setVisible(true);
								Gui.lifeMark[0].setVisible(true);
								break;
							case 4:
								Gui.lifeMark[4].setVisible(false); // 생명하나를 안보이게한다.
								Gui.lifeMark[3].setVisible(true); 
								Gui.lifeMark[2].setVisible(true); 
								Gui.lifeMark[1].setVisible(true);
								Gui.lifeMark[0].setVisible(true);
								break;
							case 3:
								Gui.lifeMark[3].setVisible(false); // 생명하나를 안보이게한다.
								Gui.lifeMark[2].setVisible(true); 
								Gui.lifeMark[1].setVisible(true);
								Gui.lifeMark[0].setVisible(true);
								break;
								
							case 2: // life가 2일때
								Gui.lifeMark[2].setVisible(false); // 생명하나를 안보이게한다.
								Gui.lifeMark[1].setVisible(true);
								Gui.lifeMark[0].setVisible(true);
							break;
							case 1: // life가 2일때
								Gui.lifeMark[1].setVisible(false); // 생명하나를 안보이게한다.
								Gui.lifeMark[0].setVisible(true);
							break;
							case 0: // life가 2일때
								Gui.lifeMark[0].setVisible(false); // 생명하나를 안보이게한다.
								
								for (int k = 0; k < Gui.arrJLabel.length; k++) {

									Gui.arrJLabel[k].setVisible(false);
								
								}
							
								break;
							}
						}
			
			
			
//				if(lifePlus==true) {
//				
//				life++;
//				
//				switch(life){ // 라이프를 1감소시킨다.
//				
//				/*
//				 * case 5: Gui.lifeMark[5].setVisible(true); break; case 4:
//				 * Gui.lifeMark[4].setVisible(true); break; case 3: // life가 2일때
//				 * Gui.lifeMark[3].setVisible(true); // break;
//				 */
//				case 5:
//					Gui.lifeMark[5].setVisible(false);
//					Gui.lifeMark[4].setVisible(true); 
//					Gui.lifeMark[3].setVisible(true); 
//					Gui.lifeMark[2].setVisible(true); 
//					Gui.lifeMark[1].setVisible(true);
//					Gui.lifeMark[0].setVisible(true);
//					break;
//				case 4:
//					Gui.lifeMark[4].setVisible(false); 
//					Gui.lifeMark[3].setVisible(true); 
//					Gui.lifeMark[2].setVisible(true); 
//					Gui.lifeMark[1].setVisible(true);
//					Gui.lifeMark[0].setVisible(true);
//					break;
//				case 3:
//					Gui.lifeMark[3].setVisible(false); 
//					Gui.lifeMark[2].setVisible(true); 
//					Gui.lifeMark[1].setVisible(true);
//					Gui.lifeMark[0].setVisible(true);
//					break;
//				case 2: // life가 2일때
//					Gui.lifeMark[2].setVisible(false); 
//					Gui.lifeMark[1].setVisible(true);
//					Gui.lifeMark[0].setVisible(true);
//				break;
//				case 1: // life가 2일때
//					Gui.lifeMark[1].setVisible(false); 
//					Gui.lifeMark[0].setVisible(true);
//				break;
//				case 0: // life가 2일때
//					Gui.lifeMark[0].setVisible(false); // 생명하나를 안보이게한다.
//					
//					for (int k = 0; k < Gui.arrJLabel.length; k++) {
//
//						Gui.arrJLabel[k].setVisible(false);
//					
//					}
//					lifePlus = false;
//			}
//				}
					
				} //고치기
					if(Gui.rank_YN==true){
						
					if((Gui.speed-(count*10))<=300) {
							Thread.sleep(Gui.speed-400);
							 System.out.println(Gui.speed-(count*10));
						}else {
							count++;
							Thread.sleep(Gui.speed-(count*10));
							System.out.println(Gui.speed-(count*10));
						}
							System.out.println(count);
					}
				 else{
						Thread.sleep(Gui.speed);
						}
					
			}catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // Gui의 speed변수만큼 떨어지는 속도를 지정한다.
					
				
				
				
				
			}
		
			
		}
	}

