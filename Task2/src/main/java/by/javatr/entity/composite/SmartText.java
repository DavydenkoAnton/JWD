package by.javatr.entity.composite;

import by.javatr.entity.Paragraphe;

public abstract class SmartText implements CompositeText {

    private final TextTypeFunction textTypeFunction;
    private SmartText parent;
    private SmartText lastChild;

    public SmartText(TextTypeFunction textTypeFunction) {
        this.textTypeFunction = textTypeFunction;
    }

    @Override
    public int size() {
        return 0;
    }

    public void smartAddText(SmartText text) {
        int compare = Integer.compare(this.textTypeFunction.type, text.textTypeFunction.type);
        switch (compare) {
            case 0:
                if (parent != null) {
                    parent.add(text);
                    text.parent = parent;
                    parent.lastChild = text;
                }
                break;
            case 1:
                if (parent != null) {
                    parent.smartAddText(text);
                }
                break;
            case -1:
                if (lastChild == null || lastChild.textTypeFunction.type == text.textTypeFunction.type) {
                    add(text);
                    text.parent = this;
                    lastChild = text;
                } else {
                    lastChild.smartAddText(text);
                }
                break;
            default:
                throw new IllegalStateException("Should never occur");
        }
    }


    @Override
    public void add(LeafText leafText) {

    }

    public Paragraphe[] getParagraphes(int size) {
        SmartText[] p = new SmartText[size];
        SmartText text = this.parent;
        int iter = 0;
        while (text.parent != null) {
            p[iter] = text.parent;
        }
        return (Paragraphe[]) p;
    }


    public SmartText findRoot() {
        if (this.parent == null) {
            return this;
        } else {
            SmartText text = this.parent;
            while (text.parent != null) {
                text = text.parent;
            }
            return text;
        }
    }

    public enum TextTypeFunction {
        TEXT("Text_ENUM", 1),
        PARAGRAPHE("Paragraphe_ENUM", 2),
        SENTENCE("Sentence_ENUM", 3),
        WORD("Word_ENUM", 4);

        private final int type;
        private final String title;

        TextTypeFunction(String title, int type) {
            this.title = title;
            this.type = type;
        }

        public int getLevel() {
            return this.type;
        }

        public String getTitle() {
            return this.title;
        }
    }

    public int getSize() {
        return 0;
    }
}
