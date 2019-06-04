package by.javatr.entity;

import by.javatr.entity.composite.LeafText;
import by.javatr.entity.composite.SmartText;

import java.util.LinkedList;
import java.util.List;

public class Sentence extends SmartText {

    private List<LeafText> words = new LinkedList<>();

    public Sentence(TextTypeFunction textTypeFunction) {
        super(textTypeFunction);
    }


    @Override
    public void add(LeafText word) {
        words.add(word);
    }

    @Override
    public List<LeafText> getL() {
        return words;
    }

    @Override
    public int size() {
        return words.size();
    }

    @Override
    public String getLeaf() {
        String sentence = new String();
        String space = " ";
        int symbolMaxValue=70;

        for (LeafText word : words) {
            sentence += word.getLeaf() + space;
            if (sentence.length()>symbolMaxValue){
                sentence+="\n";
                symbolMaxValue*=2;
            }
        }
        return sentence;

    }

    @Override
    public void sortBy() {

    }
}
