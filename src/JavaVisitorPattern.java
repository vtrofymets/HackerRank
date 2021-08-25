import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Vlad Trofymets
 */
public class JavaVisitorPattern {

    private static final Map<Integer, TreeNode> nodes = new HashMap<>();
    private static final Map<Integer, Set<Integer>> map = new LinkedHashMap<>();
    private static int[] values;
    private static Color[] colors;


    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }

    public static Tree solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        values = new int[n];
        colors = new Color[n];

        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            values[i] = value;
        }

        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            colors[i] = from(value);
        }

        for (int i = 1; i < n; i++) {
            int vi = scanner.nextInt(); // parent
            int ui = scanner.nextInt(); // child
            map.computeIfAbsent(vi, v -> new LinkedHashSet<>())
                    .add(ui);
            map.computeIfAbsent(ui, v -> new LinkedHashSet<>())
                    .add(vi);
        }

        map.forEach((k, v) -> {
            if (k == 1) {
                var root = new TreeNode(values[0], colors[0], 0);
                nodes.put(k, root);
            }
            buildTree(k, v);
        });

        return nodes.get(1);
    }

    private static void buildTree(Integer p, Set<Integer> childs) {
        new HashSet<>(childs).forEach(c -> buildNode(p, c));
    }

    private static void buildNode(Integer p, Integer c) {
        var parent = nodes.get(p);
        if (map.get(c)
                .size() > 1) {
            var node = new TreeNode(values[c - 1], colors[c - 1], parent.getDepth() + 1);
            nodes.put(c, node);
            parent.addChild(node);
        } else {
            var leaf = new TreeLeaf(values[c - 1], colors[c - 1], parent.getDepth() + 1);
            parent.addChild(leaf);
        }
        map.get(p)
                .remove(c);
        map.get(c)
                .remove(p);
        buildTree(c, map.get(c));
    }

    private static Color from(int e) {
        return e > 0 ? Color.GREEN : Color.RED;
    }

}

enum Color {
    RED,
    GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return value == tree.value && depth == tree.depth && color == tree.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, color, depth);
    }
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    @Override
    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    @Override
    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis {

    public abstract int getResult();

    public abstract void visitNode(TreeNode node);

    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {

    private int result;

    public int getResult() {
        return result;
    }

    public void visitNode(TreeNode node) {
        //implement this
    }

    public void visitLeaf(TreeLeaf leaf) {
        result += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {

    private long result = 1;

    public int getResult() {
        return (int) result;
    }

    public void visitNode(TreeNode node) {
        calc(node);
    }

    public void visitLeaf(TreeLeaf leaf) {
        calc(leaf);
    }

    private void calc(Tree tree) {
        if (tree.getColor() == Color.RED) {
            result *= tree.getValue();
            result %= 1000000007;
        }
    }
}

class FancyVisitor extends TreeVis {

    private int nonLeafSum;
    private int greenNodesSum;

    public int getResult() {
        return Math.abs(nonLeafSum - greenNodesSum);
    }

    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0) {
            nonLeafSum += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.GREEN) {
            greenNodesSum += leaf.getValue();
        }
    }
}
