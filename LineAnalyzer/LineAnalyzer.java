/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LineAnalyzer;


import java.io.IOException;

/**
 *
 * @author terada
 */
public class LineAnalyzer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        WordCounter count = new WordCounter("./src/javatest/log2.txt");
        count.showFeelingMap();
        count.showPersonMap(0);
        count.showPositive();
        count.showWordsMap(5);
        
        
    }
    
}
