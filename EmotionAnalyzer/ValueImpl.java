package EmotionAnalyzer;

public class ValueImpl {

    String stringNums;
    int intNums = 0;
    double doubleNums = 0;
    boolean booleanNums = false;
    int type = 0;

    public ValueImpl(String s) {
        stringNums = s;
        type = 1;

    }

    public ValueImpl(int s) {
        intNums = s;
        type = 2;
        //newでLexicalUnitのインスタンスを作成して、値を保存させる。
    }

    public ValueImpl(double s) {
        doubleNums = s;
        type = 3;
    }

    public ValueImpl(boolean s) {
        booleanNums = s;
        type = 4;
    }

    public void setNum(int a) {
        intNums = a;
    }

    public void setNumD(double a) {
        doubleNums = a;
    }


    public String getSValue() {//文字列として値を持ってくる

        return stringNums;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIValue() {//整数値として値を持ってくる
        /*switch(kata){
         //case 1:stringNumsを数値にして返す
         //case 2:int numsをそのまま帰す
         }
         */
        return intNums;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public double getDValue() {//ダブル型として持ってくる
        return doubleNums;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public boolean getBValue() {//true false
        return booleanNums;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public int getTypeNum() {//タイプとして値を持ってくる
        return type;
    }

}