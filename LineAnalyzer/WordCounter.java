package LineAnalyzer;

import EmotionAnalyzer.EmotionAnalyzer;
import EmotionAnalyzer.Feelings;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
import rikyu.Rikyu;

public class WordCounter {

    //他クラスファイルオブジェクト利用
    EmotionAnalyzer Emo = new EmotionAnalyzer();
    HashMap<String, EmotionAnalyzer> feelingMap = new HashMap<String, EmotionAnalyzer>();
    HashMap<String, Integer> wordMap;
    HashMap<String, Integer> personMap;
    HashMap<String,Integer> positiveLevelMap;
    
    File file;
    String read;
    FileReader fileReader;
    BufferedReader br;
    Tokenizer tokenizer;
    static final int name = 1;
    static final int talk = 2;
        
    

    public WordCounter(String filePath) throws FileNotFoundException, IOException {
        Rikyu.init();
        wordMap = new HashMap<String, Integer>();
        personMap = new HashMap<String, Integer>();
        positiveLevelMap = new HashMap<String,Integer>();
        this.file = new File(filePath);
        fileReader = new FileReader(file);
        br = new BufferedReader(fileReader);
        //形態素解析結果を保存するためのトークン
        tokenizer = Tokenizer.builder().build();
         //一行ずつ解析を行う
        while (true) {
            read = br.readLine();
            if (read == null) {
                break;
            }
            //入力されたテキストファイルをタブ区切りに読んでいく
            String[] splitWords = read.split("	");
               //もじ３つ　（時間　Tab 名前 Tab 発言　だった場合のみ処理する　Lineの規格がそんな感じだから
             
            if (splitWords.length >= 3) {
                //--------------処理したいカウントを呼びだそう------------------
                wordcount(splitWords);
                personCount(splitWords);
                PositiveLevelCount(splitWords);
                FeelingsAnalyze(splitWords);
                //---------------------------------------------------------
            
            }
        }
        
    }

    /*---------------------------------------------
                感情解析メソッド Use  Rikyu&5感情解析プログラム
    ---------------------------------------------*/
    //人間の基本5感情ごとにカウントして表示する
    public final HashMap<String ,HashMap<String,Integer>>FeelingsAnalyze(String[] words) throws IOException{
        //一回分の解析結果
        if(feelingMap.get(words[name])==null){
            feelingMap.put(words[name],new EmotionAnalyzer());
            feelingMap.get(words[name]).setFeelingMap(feelingMap.get(words[name]).analyze(words[talk]));
        }else{
            //発言者の感情解析プログラムEmotionクラスを取り出す
            EmotionAnalyzer thisPersonEmotion = feelingMap.get(words[name]);
            //発言内容の感情値を解析する
            thisPersonEmotion.analyze(words[talk]);
            //保存する
            feelingMap.put(words[name],thisPersonEmotion);
        }
        return null;
    }
    
    
    //ネガティブポジティブ判定
    public final HashMap<String,Integer> PositiveLevelCount(String[] words){
        if(positiveLevelMap.get(words[1])==null){
            positiveLevelMap.put(words[1],1);
        }else{
            double addPoint = Rikyu.analyze(words[2]).getPoint();
            int point = (positiveLevelMap.get(words[1])+(int) (0 + addPoint));
            positiveLevelMap.put(words[1], point);
        }
        return null;
    }
    
    /*---------------------------------------------
            実際にカウントを行っているメソッド達
    ---------------------------------------------*/
    public final LinkedHashMap<String, Integer> wordcount(String[] result1) {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
        //ユーザーの発言部分のみを取得する
        String counttext = "";
        for (int i = 0; i < result1.length; i++) {
            if (i >= 2) {
                counttext += result1[i];
            }
        }
        
        // この1行で形態素解析できる
        List<Token> tokens = tokenizer.tokenize(counttext);

        // 単語ごとにカウント開始
        for (Token token : tokens) {
            //ここで名詞とか副詞とかを書くとフィルタリング出来る
            if (token.getPartOfSpeech().contains("名詞")) {
                String word = token.getSurfaceForm();
                //-----正規表現カット------
                Pattern p = Pattern.compile("[!-~]");
                Matcher m = p.matcher(word);
                boolean b = m.matches();
                //-----------------------
                if(b==false){
                if (personMap.get(word) == null) {
                    if (wordMap.get(word) == null) {
                        wordMap.put(word, 1);
                    } else {
                        wordMap.put(word, wordMap.get(word) + 1);
                    }
                }
                }
            }
        }
        return result;
    }
    
    //人別発言数のカウント
    public final HashMap<String, Integer> personCount(String[] result1) {
        if (personMap.get(result1[1]) == null) {
            personMap.put(result1[1], 1);
        } else {
            personMap.put(result1[1], personMap.get(result1[1]) + 1);
        }
        
        return personMap;
    }
    
    /*---------------------------------------------
                    取得系メソッド
    ---------------------------------------------*/
    //会話の単語帳を取得する
    public HashMap<String, Integer> getWordMap() {
        return sort(wordMap);
    }

    //ユーザーの発言回数を取得する
    public HashMap<String, Integer> getPersonMap() {
        return sort(personMap);
    }
    
    /*---------------------------------------------
                    閲覧系メソッド
    ---------------------------------------------*/    
    public void showWordsMap(){
        showWordsMap(0);
    }
    public void showPersonMap(){
        showPersonMap(0);
    }
    public void showPositive(){
        showPositive(0);
    }
    
    public void showWordsMap(int filter){
        System.out.println("-----------------------------\n利用頻度の高い単語ランキング\n-------------------------------");
        //単語は数が多いので一回しかでてこないものは表示しない
        show(wordMap,filter);

    }

    public void showPersonMap(int filter){
        System.out.println("-----------------------------\n発言数ランキング\n-------------------------------");
        show(personMap,filter);
    }
    
    public void showPositive(int filter){
        System.out.println("-----------------------------\n発言のポジティブ度合い\n-------------------------------");
        show(positiveLevelMap,filter);
    }
    public void showFeelingMap(){
        System.out.println("-----------------------------\n発言者ごとの発言感情分析\n-----------------------------");
        for(String UserName : feelingMap.keySet()){
            System.out.println("-----------------\n"+UserName+"\n-----------------");
            /*そのユーザーの発言を解析し，感情値を保存した変数
                怒り：3
                喜び：4
                悲しみ：6  
            */
            HashMap<String,Feelings> UserFeelingMap = feelingMap.get(UserName).getFeelingsMap();
            
            for(String feelingName : UserFeelingMap.keySet()){
                System.out.println(feelingName + " : " + UserFeelingMap.get(feelingName).getPoint());
            }
        }
    }
    
    public void show(HashMap<String,Integer> map,int filter){
        map = sort(map);
        for(String key : map.keySet()){
            if(map.get(key)<=filter){
                continue;   
            }else{
                System.out.print(key+"  :  ");
                System.out.println(map.get(key));
            }
        }
    }

    /*---------------------------------------------
                    その他内部的な奴
    ---------------------------------------------*/
    private HashMap<String, Integer> sort(HashMap<String, Integer> sortMap) {
        //ちょっと頭の良くないソート
        List<Map.Entry> entries = new ArrayList<Map.Entry>(sortMap.entrySet());
        Collections.sort(entries, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Map.Entry e1 = (Map.Entry) o1;
                Map.Entry e2 = (Map.Entry) o2;
                return ((Integer) e1.getValue()).compareTo((Integer) e2.getValue());
                
            }
        });

        //並び替えたやつを再度HashMapに変換する
        LinkedHashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
        for (Map.Entry entry : entries) {
            String key = entry.getKey().toString();
            int val = Integer.parseInt(entry.getValue().toString());
            result.put(key, val);
        }
        return result;
    }
}
