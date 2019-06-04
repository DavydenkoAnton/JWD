package by.javatr.entity;

import by.javatr.entity.composite.LeafText;
import by.javatr.entity.composite.SmartText;

import java.util.LinkedList;
import java.util.List;

public class Text extends SmartText {

    private List<LeafText> paragraphes = new LinkedList<>();

    public Text(TextTypeFunction textTypeFunction){
        super(textTypeFunction);
    }

    @Override
    public void add(LeafText paragraph) {
        this.paragraphes.add(paragraph);
    }

    @Override
    public List<LeafText> getL() {
        return paragraphes;
    }

    @Override
    public int size() {
       return paragraphes.size();
    }

    @Override
    public String getLeaf() {
        String text=new String();
        for (LeafText paragraphe : paragraphes) {
            text+=paragraphe.getLeaf();
        }
        return text;
    }

    @Override
    public void sortBy() {

    }

    @Override
    public int getSize() {
        return paragraphes.size();
    }

    public LeafText[] getParagrafes(){
        LeafText[] p=new Paragraphe[paragraphes.size()];
        int iter=0;
        for (LeafText paragraphe : paragraphes) {
            p[iter]=paragraphe;
        }
        return  p;
    }
}
