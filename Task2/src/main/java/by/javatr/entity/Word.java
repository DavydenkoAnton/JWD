package by.javatr.entity;

import by.javatr.entity.composite.LeafText;
import by.javatr.entity.composite.SmartText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Word extends SmartText {

    private String word;
    private List<String> leftSymol;
    private List<String> rightSymol;

    public Word(TextTypeFunction textTypeFunction) {
        super(textTypeFunction);
        leftSymol = new ArrayList<>();
        rightSymol = new ArrayList<>();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getLeftSymol() {
        return leftSymol;
    }

    public void setLeftSymol(List<String> leftSymol) {
        this.leftSymol = leftSymol;
    }

    public List<String> getRightSymol() {
        return rightSymol;
    }

    public void setRightSymol(List<String> rightSymol) {
        this.rightSymol = rightSymol;
    }

    @Override
    public void add(LeafText leafText) {

    }

    @Override
    public List<LeafText> getL() {
        return Collections.emptyList();
    }

    @Override
    public int size() {
        return word.length();
    }

    @Override
    public String getLeaf() {
        String buffer = "";
        for (String l : leftSymol) {
            buffer += l;
        }
        buffer += word;
        for (String r : rightSymol) {
            buffer += r;
        }
        return buffer;
    }

    @Override
    public void sortBy() {

    }
}
