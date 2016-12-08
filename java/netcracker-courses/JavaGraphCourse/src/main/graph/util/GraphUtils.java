package main.graph.util;

import main.context.Beanable;
import main.graph.Graph;

import java.util.ArrayList;
import java.util.List;

public interface GraphUtils extends Beanable {

    List<ArrayList<Integer>> getAllPathsFromOneToAnother(Graph graph, Integer from, Integer to);
}
