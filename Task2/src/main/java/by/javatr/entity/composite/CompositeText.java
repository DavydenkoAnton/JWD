package by.javatr.entity.composite;

import java.util.List;

public interface CompositeText extends LeafText {

    void add(LeafText leafText);

    List<LeafText> getL();

    int size();
}
