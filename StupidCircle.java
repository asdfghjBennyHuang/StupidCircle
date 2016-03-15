import java.util.Random;
import java.util.Scanner;

public class StupidCircle {
	static int[][] board = {{1,5},{2,4},{2,6},{3,3},{3,5},{3,7},{4,2},{4,4},{4,6},{4,8},{5,1},{5,3},{5,5},{5,7},{5,9},};
	static private int[] chessnum = new int[15];
	static private int chessnumsum=0;
	static Random dice = new Random(); 
	static Scanner scan = new Scanner(System.in);
	static fakeBrain fb = new fakeBrain();
	static int[] temp = new int[10];
	//temp 4
	static int[] a = new int[3] , b = new int[3];
	static int mode,turn=0;
	static int[] reply = new int[3];
	static int[][] history = new int[15][3];
	static int temp2fun=0;
	
	public static void main(String arg[]){
		fb.froll = dice.nextInt(20);
		fb.turn = 0;
		for(int i=0;i<15;i++)chessnum[i]=1;

		System.out.println("mode 1: Human VS. Human.\nmode 2: Human VS. computer.");
		for(int i=0;i<15;i++)chessnumsum+=chessnum[i];
		while(true){
			System.out.print("please select mode:");
			mode = scan.nextInt();
			if(mode>0 && mode<=2){
				break;
			}else{
				System.out.println("there is only mode 1 and 2!");
			}
		}
	// mode 1
		if(mode==1){
			while(true){
				reply = choose();
				deletechessnum();
				printboard();
				// chessnumsum is process in the printboard function;
				if(chessnumsum==0){
					System.out.println("OVER!");
					break;
				}
				turn++;
			}
	//mode 2
		}else if(mode==2){
			System.out.print("input 1 for player go first, else for computer go first:");
			int whogofirst = scan.nextInt();
			if(whogofirst==1){
				reply = choose();
				deletechessnum();
				printboard();
			}
			fb.turn++;
			turn++;
			while(true){	
				reply = fb.book(chessnum);
				temp[4] = check(reply, chessnum);
				if(temp[4]>0){
					System.out.println("Error in book function.\nError type :" + temp[4]);
					for(int print=0;print<3;print++)System.out.println(reply[print] + " ");
					break;
				}
				deletechessnum();
				printboard();
				if(chessnumsum==0){
					System.out.println("OVER! The player wins!");
					break;
				}
				fb.turn++;
				turn++;
				reply = choose();
				deletechessnum();
				printboard();
				if(chessnumsum==0){
					System.out.println("OVER! Computer wins!");
					break;
				}
				fb.turn++;
				turn++;
			}		
		}
	}
// printboard function
	static public void printboard(){
		chessnumsum=0;
		temp[0] = 0;
		for(int i=1;i<6;i++){
			if(i<5)System.out.print(" ");
			for(int p=1;p<10;p++){
				if( i==board[temp[0]][0] && p==board[temp[0]][1] ){	
					if(chessnum[temp[0]]==1){
						System.out.print( (temp[0]+1) + "" );
						chessnumsum++;
					}else{
						if(chessnumsum<10){
							System.out.print( " " );
						}else{
							System.out.print( "  " );
						}
					}
					temp[0]++;				
				}else{
						System.out.print("  ");
				}
			}
			System.out.println("");
		}
		System.out.println("-------------------------------");
	}
// deletechessnum function
	static public void deletechessnum(){
		if(reply[1]==-1){
			chessnum[reply[0]]=0;
		}else if(reply[2]==-1){
			chessnum[reply[0]]=0;
			chessnum[reply[1]]=0;
		}else{
			chessnum[reply[0]]=0;
			chessnum[reply[1]]=0;
			chessnum[reply[2]]=0;
		}
	}
// Choose function
	static public int[] choose(){
		System.out.println("please input the chessnum from 1 to 15:");
		while(true){
			temp2fun = 0;
			for(int i=0;i<3;i++){
				temp[1]=0;
				temp[2]=1;
				while(temp[2]>0){
					temp[2]=0;
					switch(i){
						case 0 :System.out.print("The first num:");break;
						case 1 :System.out.print("The second num:");break;
						case 2 :System.out.print("The third num:");break;
					}
					a[i]=scan.nextInt();
					if(a[i]>=0 && a[i]<=15){
						if(a[i]!= 0){
							if( ! (chessnum[a[i]-1]==1)){
								if(temp2fun>0)System.out.println("Oh! My!");
								System.out.println(String.format("number %d is empty!", a[i]));
								temp[2]++;
							}
						}else{
							if(i!=0){
								temp[1]++;
								break;
							}else{
								if(temp2fun>0)System.out.println("Come on!");	
								System.out.println("The first one must be from 1 to 15!");
								temp[2]++;
								temp2fun++;
							}
						}
					}else{
						if(temp2fun>0)System.out.println("Come on!");
						System.out.println("please follow the rule!");
						temp[2]++;
						temp2fun++;
					}
					for(int p=0;p<i;p++){
						if(a[p]==a[i]){
							System.out.println("Do not repeat!");
							temp[2]++;
						}
					}
				}
				if(temp[1]>0)break;
			}
			if(a[1]==0){
				break;
			}else if(a[2]==0){
				if( !(Math.abs(board[a[0]-1][0]-board[a[1]-1][0])<=1 && 
						Math.abs(board[a[0]-1][1]-board[a[1]-1][1])+Math.abs(board[a[0]-1][0]-board[a[1]-1][0])<=2) ){
					System.out.println("It has to be straight!");
				}else{
					break;
				}
			}else{
				int[][] vector = new int[2][2];
				vector[0][0] = board[a[1]-1][0]-board[a[0]-1][0];
				vector[0][1] = board[a[1]-1][1]-board[a[0]-1][1];
				vector[1][0] = board[a[2]-1][0]-board[a[1]-1][0];
				vector[1][1] = board[a[2]-1][1]-board[a[1]-1][1];
				if( !(Math.abs(vector[0][0])<=1 && Math.abs(vector[0][0])+Math.abs(vector[0][1])<=2 
						&& vector[0][0]==vector[1][0] && vector[0][1]==vector[1][1]) ){	
					System.out.println("It has to be straight!");
				}else{
					break;
				}
			}
		}
		for(int i=0;i<3;i++)a[i]--;
		return a;
	}
// Check function
	static public int check(int[] r, int[] checkboard){
		for(int i=0;i<3;i++){
			if(r[i]>=-1 && r[i]<15){
				if(r[i]==-1){
					if(i!=0){
						for(int p=0;p<i;p++){
							if(r[i]==r[p]){
								return 3;
							}
						}
						break;
					}else{
						return 2;
					}
				}else{
					for(int p=0;p<i;p++){
						if(r[i]==r[p]){
							return 3;
						}
					}
				}
				if(r[1]==-1){
				}else if(r[2]==-1){
					if( !(Math.abs(board[r[0]][0]-board[r[1]][0])<=1 && 
							Math.abs(board[r[0]][1]-board[r[1]][1])+Math.abs(board[r[0]][0]-board[r[1]][0])<=2) ){
						return 4;
					}
				}else{
					int[][] checkvector = new int[2][2];
					checkvector[0][0] = board[r[1]][0]-board[r[0]][0];
					checkvector[0][1] = board[r[1]][1]-board[r[0]][1];
					checkvector[1][0] = board[r[2]][0]-board[r[1]][0];
					checkvector[1][1] = board[r[2]][1]-board[r[1]][1];
					if( !(Math.abs(checkvector[0][0])<=1 && Math.abs(checkvector[0][0])+Math.abs(checkvector[0][1])<=2 
							&& checkvector[0][0]==checkvector[1][0] && checkvector[0][1]==checkvector[1][1]) ){	
						return 5;
					}
				}
			}else{
				return 1;
			}
		}
		for(int i=0;i<3;i++){
			if(r[i]!=-1){
				if(checkboard[r[i]]==0){
					return 6;
				}
			}
		}
		
		return 0;
		/*
		type 1 :bigger than 15 or less than 0.
		type 2 :the first number is 0.
		type 3 :numbers are same.
		type 4 :two numbers are not a line.
		type 5 :three number are not a line.
		type 6 :the chosen number has already been 0.
		*/
	}
}
