package EmotionAnalyzer;
/*
 * 性格によって変動するポイント数が違う
 *
 * 禿げをネタにしている人に禿げっていうのと
 * 禿げを気にしている人に禿げっていうのでは　心の動き方に差がある
 *
 *
 */


public class Points {
	int point;

	public Points(){
		point = 0;
	}

	public void addpoints(){
		point +=1;
	}

	public void delpoints(){
		point -=1;
	}
	public void addpoints(int a){
		point +=a;
	}

	public void delpoints(int a){
		point -=a;
	}

	public int getPoints(){
		return point;
	}
}
