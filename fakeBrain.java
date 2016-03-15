
import java.util.Random;

public class fakeBrain {
	static StupidCircle sc = new StupidCircle();
	static fakeplayer fp = new fakeplayer();
	static Random dice = new Random(); 
	static int[][] vector = {{0,2},{1,1},{1,-1},{0,-2},{-1,-1},{-1,1}};
	static int froll, turn;
	
	static public int[] book(int[] here){
		int[] bookr = {-1,-1,-1};
		int heresum=0;
		for(int i=0;i<15;i++)heresum+=here[i];
		if(heresum<=4){
			bookr = bookrIn(here,1);
		}else if(heresum<=13){
			bookr = fp.fakeplay5(here);
		}else if(heresum==15){
			bookr[0]=0;
		}else if(heresum==14){
			int empty = 0;
			for(int j=0;j<15;j++){
				if(here[j]==0){
					empty=j;
					break;
				}
			}
			int rolldice = dice.nextInt(15);
			int rollwin = 0;
			switch(empty){
				case 0:bookr[0]=1;break;
				case 1:rollwin++;bookr[0]=8;bookr[1]=9;break;
				case 2:rollwin++;bookr[0]=6;bookr[1]=7;break;
				case 3:bookr[0]=0;break;
				case 4:bookr[0]=0;break;
				case 5:bookr[0]=0;break;
				case 6:rollwin++;bookr[0]=8;bookr[1]=13;break;
				case 7:bookr[0]=0;break;
				case 8:bookr[0]=0;break;
				case 9:rollwin++;bookr[0]=7;bookr[1]=11;break;
				case 10:bookr[0]=1;break;
				case 11:rollwin++;bookr[0]=1;bookr[1]=4;break;
				case 12:bookr[0]=0;break;
				case 13:rollwin++;bookr[0]=2;bookr[1]=4;break;
				case 14:bookr[0]=1;break;
			}
			if(rollwin==1){
				if(rolldice<5){
					System.out.println("Let's do this!");
				}else if(rolldice<10){
					System.out.println("You are making mistakes!");
				}
			}else if(rollwin==0){
				switch(rolldice){
					case 0:System.out.println("I am losing, but I will try to win.");break;
					case 1:System.out.println("losing...");break;
					case 2:System.out.println("You have figured it out, haven't you?");break;
					case 3:System.out.println("Oh! No! I want ot be first next time...");break;
					case 4:System.out.println("You are torturing me...");break;
					case 5:System.out.println("Giving this up.");break;
					case 6:System.out.println("Ouch!");break;
					case 7:System.out.println("You have figured it out, haven't you?");break;
					case 8:System.out.println("You have figured it out, haven't you?");break;
					case 9:System.out.println("I am losing, but I will try to win.");break;
					case 10:System.out.println("I am losing, but I will try to win.");break;
				}
			}
		}else{
			for(int i=0;i<15;i++){
				if(here[i]==1){
					bookr[0]=i;
					break;
				}
			}
		}
		if(turn==2||turn==1){
			switch(froll){
			case 0:System.out.println("How are you today?");break;
			case 1:System.out.println("I can't understand what you are thinking.");break;
			case 2:System.out.println("Is it raining outside?");break;
			case 3:System.out.println("I am produced by Benny.");break;
			case 4:System.out.println("Do you know Benny, my producer?");break;
			case 5:System.out.println("Is it strange that computer can play this game?");break;
			case 6:System.out.println("I want to sleep...");break;
			}
		}else if(turn==3||turn==4){
			switch(froll){
			case 2:System.out.println("I don't like rainind days.");break;
			case 3:System.out.println("He is smart!");break;
			case 4:System.out.println("I hope he can make me better.");break;
			case 5:System.out.println("I feel strange.");break;
			case 6:System.out.println("I am hungry now...");break;
			case 7:System.out.println("I am playing with you CPU!");break;
			case 8:System.out.println("Do you feel bad when you lose?");break;
			case 9:System.out.println("I feel HongKong is really far, not only physically.");break;
			case 10:System.out.println("How much is your computer?");break;
			}
		}else if(turn==5||turn==6){
			switch(froll){
			case 6:System.out.println("I feel hot.");break;
			case 7:System.out.println("Now I am playing with you GPU!");break;
			case 8:System.out.println("I feel bad, but Benny don't let say Fuck You.");break;
			case 10:System.out.println("It's not cheap, right?");break;
			case 11:System.out.println("What's your name?");break;
			case 12:System.out.println("I like spaghetti.");break;
			case 13:System.out.println("Yesterday I went to Japan. Ha! I lied!");break;
			}
		}
		return bookr;
	}
// bookrIn function display == 1 means speak , other means slient. 
	static public int[] bookrIn(int[] r,int display){
		int[] bookri = {-1,-1,-1};
		int rsum=0;
		for(int i=0;i<15;i++)rsum+=r[i];
		if(rsum==4){
			int[] exi = new int[4];
			int count4=0;
			for(int i=0;i<15;i++){
				if(r[i]==1){
					exi[count4]=i;
					count4++;
				}
			}
			int straight=0;
			for(int n1=0;n1<4;n1++){
				for(int j=0;j<3;j++){
					for(int n2=0;n2<15;n2++){
						if(  sc.board[n2][0]==(sc.board[exi[n1]][0]+vector[j][0]) && sc.board[n2][1]==(sc.board[exi[n1]][1]+vector[j][1]) ){
							if(r[n2]==1){
								for(int n3=0;n3<15;n3++){
									if( sc.board[n3][0]==sc.board[n2][0]+vector[j][0] && sc.board[n3][1]==sc.board[n2][1]+vector[j][1] ){
										if(r[n3]==1){
											bookri[0]=exi[n1];				
											bookri[1]=n2;
											bookri[2]=n3;
											straight=1;
											if(display==1)System.out.println("You Stupid!");
											break;
										}
									}
								}
							}
						}
						if(straight==1)break;
					}
					if(straight==1)break;
				}
				if(straight==1)break;
			}
			if(straight==0){
//System.out.print("exi " + besidexp(exi[0], r, 0) + " " +exi[0]+ " a ");
				if(besidexp(exi[0], r, 0)==0){
					if( besidexp(exi[1], r ,0)==0 ){
						if(display==1)System.out.println("I told you I will win.");
						bookri[0]=exi[2];
					}else if( besidexp(exi[1], r ,0)==1 ){
						if(display==1)System.out.println("YES!");
						bookri[0]=besidenp(exi[1], r, 0)[0];
					}else{
						bookri[0]=exi[1];
					}
				}else if(besidexp(exi[0], r, 0)==1){
					if( besidexp(besidenp(exi[0], r, 0)[0], r, 0 )==1 ){
						bookri[0]=exi[0];
					}else if( besidexp(besidenp(exi[0], r, 0)[0], r, 0)==2 ){
						if(straight==0){
							bookri[0]=besidenp(exi[0], r, 0)[0];
						}
					}else if( besidexp(besidenp(exi[0], r, 0)[0], r, 0)==3 ){
						bookri[0]=besidenp(exi[0], r, 0)[0];
					}
				}else{
					bookri[0]=exi[0];
				}
			}
// 3		
		}else if(rsum==3){
			for(int i=0;i<15;i++){
				if(r[i]==1){
					int[] besidenum = new int[3];
					besidenum[0] = besidexp(i, r, 0);
					if(besidenum[0]==0){
						for(int j=i+1;j<15;j++){
							if(r[j]==1){
								besidenum[1] = besidexp(j, r, 0);
								if(besidenum[1]==0){
									if(display==1){
										if(j>10){
											System.out.println("I GIVE UP!!");
										}else{
											System.out.println("Sigh...");
										}
									}
									bookri[0]=i;
									break;
								}else{
									bookri[0]=j;
									bookri[1]=besidenp(j, r, 0)[0];
								}
								break;
							}
						}
					}else{
						bookri[0]=i;
						bookri[1]=besidenp(i, r, 0)[0];
					}
					break;
				}
			}
		}else if( rsum==2 || rsum==1 ){
			for(int i=0;i<15;i++){
				if(r[i]==1){
					bookri[0]=i;
					break;
				}	
			}
		}
		return bookri;
	}
// besidexp function
	static public int besidexp(int numchecked, int[] bchecked, int save){
		int besidenumx = 0;
		for(int j=0;j<15;j++)	
			for(int k=0;k< (save==0?6:3) ;k++)	
				if( sc.board[j][0]==sc.board[numchecked][0]+vector[k][0] && sc.board[j][1]==sc.board[numchecked][1]+vector[k][1])
					if(bchecked[j]==1){
						besidenumx++;
						break;
					}	
			
			
		return besidenumx;
	}
// besidenp function
	static public int[] besidenp(int numchecked, int[] bchecked, int save){
		int[] besidenumn = new int[6];
		int besidenumx = 0;
		for(int j=0;j<15;j++){
			for(int k=0;k< (save==0?6:3) ;k++){
				if( sc.board[j][0]==sc.board[numchecked][0]+vector[k][0] && sc.board[j][1]==sc.board[numchecked][1]+vector[k][1]){
					if(bchecked[j]==1){
						besidenumn[besidenumx] = j;
						besidenumx++;
						break;
					}
				}
			}
		}
		return besidenumn;
	}
}
