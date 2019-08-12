package ir.shahinsoft.graphdraw.model;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.ArrayList;

public class BinaryTree extends Graph {

    public static BinaryTree create(int nodeCount) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int index = 0; index < nodeCount; index++) {
            nodes.add(Node.createNode(index));
        }
        return new BinaryTree(nodes);
    }

    private float width;

    private float levelPadding = 80;

    public BinaryTree(ArrayList<Node> nodes) {
        setNodes(nodes);
    }

    @Override
    public void setView(final View view) {
        super.setView(view);
        Log.d("binaryTree", "width: " + width);

        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                width = view.getWidth();
                buildTree();
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
    }

    private void buildTree() {
        ArrayList<Node> nodes = getNodes();
        float height = getLevel(nodes.size() - 1) * levelPadding;
        for (int i = 0; i < nodes.size(); i++) {
            if (isValidIndex(i)) {
                addEdge(i, rightChildIndex(i));
                addEdge(i, leftChildIndex(i));
            }
            int level = getLevel(i);

            Log.d("binaryTree", "index: " + i + ", level: " + level);

            int nodesInRow = (int) Math.pow(2, level);
            /*           position in list    the number of nodes in tree before our level   */
            int positionInRow = i - ((int) Math.pow(2, level) - 1);

            Log.d("BinaryTree", "nodesInRow: " + nodesInRow + ", positionInRow: " + positionInRow);

            float posX = getPosX(nodesInRow, positionInRow);

            float posY = getPosY(level);

            Node node = nodes.get(i);

            Log.d("BinaryTree", "posX: " + posX + ", posY: " + posY);

            node.setRelativePositionY((posY / height) * 100);
            node.setRelativePositionX((posX / width) * 100);
            node.setLabel(String.valueOf(i));
        }

        ViewGroup.LayoutParams params = getView().getLayoutParams();
        params.width = (int) width;
        params.height = (int) height;
        getView().setLayoutParams(params);
        getView().invalidate();
    }

    private float getPosY(int level) {
        return level * levelPadding;
    }

    private float getPosX(int nodesInRow, int positionInRow) {
        float step = width / nodesInRow;

        return (step * positionInRow) + (step / 2);
    }

    private boolean isValidIndex(int index) {
        return index < getNodes().size();
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    /**
     * get level of a node in the tree
     *
     * @param index index of the node in list
     * @return level of the node in tree
     */
    public int getLevel(int index) {
        int level = 0;
        int temp = 1;
        while (index >= temp) {
            index -= temp;
            level++;
            temp *= 2;
        }
        return level;
    }
}
