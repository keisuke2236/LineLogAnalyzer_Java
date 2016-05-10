package EmotionAnalyzer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class Feelings {
	Points point;
	String words[];
	String feelName;

	public Feelings(String feelName,String  filename) throws IOException{
		/*コンストラクタのお仕事
		 * pointを新規生成すること
		 * wordsにこのAwareという感情単語一覧を保存すること
		 *
		 * 現在の性格を取得して保存する
		 * 性格によって変更するポイントを変えること
		 *
		 */
		this.feelName = feelName;
		point = new Points();
		String readed = "";
		File file = new File(filename);
		FileReader filereader = new FileReader(file);
		BufferedReader br = new BufferedReader(filereader);
		words = new String[countTxtLine(file)];

		for(int i =0;readed!=null;i++){
			readed = br.readLine();
			if(readed != null){
				words[i] = readed;
			}
		}
		br.close();
	}

	//感情ポイント計算部分
	//ここでStatusクラスにある性格も用いて感情ポイントの計算をすればよい

	public void calcurate(String say){
		for(int i=0;i<words.length;i++){
			if(say.indexOf(words[i])!=-1){
				point.addpoints();
			}
		}
	}


	public int getPoint(){
		return point.getPoints();
	}


	//テキストファイルの行数を返す
	private int countTxtLine(File file) throws IOException{
		FileReader filereader = new FileReader(file);
		BufferedReader br = new BufferedReader(filereader);
		int wordlength =0;
		while(true){
			if(br.readLine()==null){break;}
			wordlength += 1;
		}
		br.close();
		return wordlength;
	}
}
