package EmotionAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



/*
 * すべての感情を統括するエモーションアナライザー
 * 様々な感情とそれに対応する単語一覧表を所持している
 */
public class EmotionAnalyzer {

    
    HashMap<String, Feelings> map = new HashMap<String, Feelings>();//感情名とその感情オブジェクトを保存したMAP

    public EmotionAnalyzer() throws IOException {
        String filePath = "src/Types/";
        File[] files = new File(filePath).listFiles();
        for (int i = 0; i < files.length; i++) {
            String path = files[i].toString();
            String a = files[i].toString().substring(filePath.length());
            String feelName = a.substring(0, a.indexOf(".txt"));
            map.put(feelName, new Feelings(feelName, path));//ディレクトリにあるファイル名から新しい感情を生成
        }
    }

    
    public HashMap<String, Feelings> analyze(String say) {
        for (Map.Entry<String, Feelings> e : map.entrySet()) {
            e.getValue().calcurate(say);
        }
        return map;
    }
        public HashMap<String, Feelings> getFeelingsMap(){
        return map;
    }
    public void setFeelingMap(HashMap<String, Feelings> map){
        this.map = map;
    }
}
