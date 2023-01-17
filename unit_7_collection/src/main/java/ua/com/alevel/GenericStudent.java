package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;

public class GenericStudent<S extends Student> {

    private List<S> list = new ArrayList<>();

    public List<S> getList() {
        return list;
    }

    public void add(S s) {
        list.add(s);
    }
}
