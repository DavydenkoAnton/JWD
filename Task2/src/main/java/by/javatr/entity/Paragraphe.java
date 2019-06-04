package by.javatr.entity;

import by.javatr.entity.composite.LeafText;
import by.javatr.entity.composite.SmartText;

import java.util.LinkedList;
import java.util.List;

public class Paragraphe extends SmartText {

    private  List<LeafText> sentences = new LinkedList<>();

    public Paragraphe(TextTypeFunction textTypeFunction){
        super(textTypeFunction);
    }

    @Override
    public void add(LeafText sentence) {
        sentences.add(sentence);
    }

    @Override
    public List<LeafText> getL() {
        return sentences;
    }

    @Override
    public int size() {
        return sentences.size();
    }

    @Override
    public String getLeaf() {
        String paragraph = new String();
        String tab = "\t";
        paragraph += tab;
        for (LeafText sentence : sentences) {
            paragraph += sentence.getLeaf();
        }
        paragraph = paragraph.substring(0, paragraph.length() - 1) + "\n";

        return paragraph;
    }

    @Override
    public void sortBy() {

    }

    @Override
    public int getSize() {
        return sentences.size();
    }
}
