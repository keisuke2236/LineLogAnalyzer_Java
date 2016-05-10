/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionAnalyzer;

import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author rorensu
 */
public class FeelingAnalyzer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        EmotionAnalyzer emo = new EmotionAnalyzer();
        HashMap<String, Feelings> analyze = emo.analyze("まじでうざいので悲しいのですよね");
        for(String key : analyze.keySet()){
            System.out.println(key + " : " + analyze.get(key).getPoint());
        }
    }
    
}
