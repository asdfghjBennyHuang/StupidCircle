import java.util.Random;


public class fakeplayer {
	static public StupidCircle fsc = new StupidCircle();
	static int turn; 
	static public fakeBrain ffb = new fakeBrain();
	static Random fdice = new Random(); 
	
	static public int[] fakeplay5(int[] nowchess){
		
		turn=0;
		int[] fchessnum = nowchess.clone();
		int fchessnumsum=0;
		for(int i=0;i<15;i++)	fchessnumsum+=fchessnum[i];
		int[][] fakeplaynum = new int[150][3];
		for(int fakeplaynumn=0;fakeplaynumn<150;fakeplaynumn++){
			for(int fakeplaynumm=0;fakeplaynumm<3;fakeplaynumm++){
				fakeplaynum[fakeplaynumn][fakeplaynumm]=-1;
			}
		}
		int max=60;
		int fakeplaynumcount = produce(fchessnum, fakeplaynum, -1);
		
		
		for(int i=0 ; i< (fakeplaynumcount>max?max:fakeplaynumcount) ; i++){
			fchessnum = nowchess.clone();
//for(int jp=0;jp<3;jp++)System.out.print(fakeplaynum[i][jp] + " ");
//System.out.println();
			if(fsc.check( fakeplaynum[i], fchessnum )==0){
				fdel( fakeplaynum[i], fchessnum);
				int fchessnumsum2=0;
				for(int j:fchessnum)fchessnumsum2+=j;
				if(fchessnumsum2<5){
					if( odd(result(fchessnum))==1 ){
						System.out.println("I think I can do this...");
						return fakeplaynum[i];
					}
				}else{
					int damn = odd( shutout(fchessnum , -1) );
					if( damn == 1 ){
						return fakeplaynum[i];
					}
				}
			}else{
				System.out.println("problem in the fakeplay5function when checking.");
				for(int j=0;j<15;j++)System.out.print(fchessnum[j]);System.out.println();
				for(int j=0;j<3;j++)System.out.print(fakeplaynum[i]+" ");System.out.println();
				break;
			}
		}
		int[] fp5 = {-1,-1,-1};
		if(fakeplaynumcount>max){
			System.out.println("That's hard.");
			for(int j=0;j<3;j++)if(fakeplaynum[max][j]!=-1)fp5[j] = fakeplaynum[max][j];
//for(int iii=0;iii<3;iii++)System.out.print(fp5[iii]);
			return fp5;
		}else{
			System.out.println("Damn it!");
			for(int i=0;i<15;i++)if(nowchess[i]==1){
				fp5[0]=i;
				return fp5;
			}
		}
		return fp5;
	}
	static public int shutout(int[] s,int great){
		int left=0,ssum=0;
		int[] s2 = s.clone();
		int[][] sfilled = new int [100][3];
		for(int si=0;si<15;si++)ssum+=s[si];
//System.out.println("here is shutout " + ssum);
		int shcount = produce(s2, sfilled, -1);
		for(int shutsum=0;shutsum<shcount;shutsum++){
			int[] si = sfilled[shutsum];
			great=si[0];
			s2 = s.clone();
			if(fsc.check(si, s2)==0){
				fdel(si, s2);
				int ssum2=0;
				for(int si2:s2)ssum2+=si2;
				int st;
				if(ssum2>4){
					st = shutout(s2,great);
				}else{
//for(int sj=0;sj<3;sj++)System.out.print(si[sj] + " ");
//System.out.println();
					st = result(s2);
				}
				if( odd(st)==1 ){
//System.out.println("return");
					return 0;
				}
			}else{
				System.out.println("problem in shutout function when checking.");
				for(int i=0;i<15;i++)System.out.print(s2[i]);
				System.out.println();
				for(int i=0;i<3;i++)System.out.print(si[i] + " ");
				System.out.println();
				return -1;
			}
		}
		return 1;
	}
	
//produce function, producing all the possibilities.
	static public int produce(int now[], int filled[][],int great ){
		int procount=0;
		int nowsum=0;
		int[] nowexi = new int[15];
		nowsum=0;
		for(int proi=0;proi<15;proi++){
			if(now[proi]==1){
				nowexi[nowsum]=proi;
				nowsum++;
			}
		}
		for(int proi=0;proi<nowsum;proi++){
			if(nowexi[proi]>great){
				filled[procount][0]=nowexi[proi];
				filled[procount][1]=-1;
				filled[procount][2]=-1;
				procount++;
				if(ffb.besidexp(nowexi[proi], now, 1)>0){
					for(int j=0;j<ffb.besidexp(nowexi[proi], now ,1);j++){
						filled[procount][0]=nowexi[proi];
						filled[procount][1]=ffb.besidenp(nowexi[proi], now, 1)[j];
						filled[procount][2]=-1;
						procount++;
						int[] pvector = { fsc.board[ffb.besidenp(nowexi[proi], now, 1)[j]][0] - fsc.board[nowexi[proi]][0],
								fsc.board[ffb.besidenp(nowexi[proi], now, 1)[j]][1] - fsc.board[nowexi[proi]][1]};	
						for(int k=0;k<15;k++)
							if( fsc.board[ffb.besidenp(nowexi[proi], now, 1)[j]][0] + pvector[0] == fsc.board[k][0] 
									&& fsc.board[ffb.besidenp(nowexi[proi], now, 1)[j]][1] + pvector[1] == fsc.board[k][1] )
								if(now[k]==1){
									filled[procount][0]=nowexi[proi];
									filled[procount][1]=ffb.besidenp(nowexi[proi], now, 1)[j];
									filled[procount][2]=k;
									procount++;
								}
					}
				}
			}
		}
		return procount;
	}	
	
// result fuction
	static public int result(int[] c){
		int win=0;
		int[] resultc = c.clone();
		int[] resulta = new int[3];
		int resultco = 0;
		for(int resulti=0;resulti<15;resulti++)resultco+=resultc[resulti];
		if(resultco>=5){
			System.out.println("problem in result function! resultco = " + resultco );
			return -1;
		}
//for(int i=0;i<15;i++)System.out.print(c[i]);
//System.out.println();
		while(resultco<5){

			resultco=0;
			for(int resulti=0;resulti<15;resulti++)resultco+=resultc[resulti];
			if(resultco==0){
				return win;
			}
			for(int resulti=0;resulti<3;resulti++)resulta[resulti] = -1;
			resulta = ffb.bookrIn(resultc,0);
			if(fsc.check(resulta, resultc)==0){
				fdel(resulta,resultc);
				if(win==0){
					win++;
				}else{
					win=0;
				}
			}else{
				System.out.println("Error in result function when checking, error type = " + fsc.check(resulta, resultc) );
				for(int i=0;i<15;i++)System.out.print(resultc[i]);
				System.out.println();
				for(int i=0;i<3;i++)System.out.print(resulta[i]+" ");
				System.out.println();
				return 0;
			}	
		}
		return 200;
	}
	
// fdel function
	static public void fdel(int[] ans, int[] ch){
		if(ans[1]==-1){
			ch[ans[0]]=0;
		}else if(ans[2]==-1){
			ch[ans[0]]=0;
			ch[ans[1]]=0;
		}else{
			ch[ans[0]]=0;
			ch[ans[1]]=0;
			ch[ans[2]]=0;
		}
	}
// check odd or even
	static public int odd(int x){
		if(x<0){
			System.out.println("problem in odd function : " + x );
			return -1;
		}
		while(true){
			if(x==0){
				return 0;
			}else if(x==-1){
				return 1;
			}
			x-=2;
		}
	}
}
