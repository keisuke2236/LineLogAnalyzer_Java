# LineLogAnalyzer
LINEの会話ログを解析するためのプログラムです．

スマートフォンのLINEの設定から会話ログを送信し，PCに移動します．

LineAnalyzerの中に入っているLineAnalyzer.javaがメインファイルです．


#使い方
すでにこのプログラムの中に入っていますがrikyuを利用しています

https://github.com/YoshiteruIwasaki/rikyu

##jarファイルで必要なやつ
lucene-gosen-4.4.0-ipadic.jar: 形態素解析ライブラリが必要です

https://code.google.com/archive/p/lucene-gosen/downloads

ここからDLできます

kuromoji-0.7.7.jar :kuromojiが必要です

https://github.com/atilika/kuromoji/downloads


#LineAnalyzer.java
このファイルの21行目

WordCounter count = new WordCounter("./src/javatest/log2.txt");

この文字列の部分がファイルパスになっています．

#使い方
WordCounter.javaが解析を行うメインのプログラムファイルになります

WordCounter count = new WordCounter("./src/javatest/log2.txt");

あとは適当にあるメソッドを使うと表示されます，
count.showFeelingMap();

count.showFeelingMap();

count.showPersonMap(0);

count.showPositive();

count.showWordsMap(5);

ちなみに引数はフィルターで5とした場合は単語の出現数が五回未満だった場合は表示しないということになります

フィルターを設定していない関数もあるのでエラーが出たらないと思ってください．

