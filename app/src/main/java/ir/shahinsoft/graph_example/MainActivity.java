package ir.shahinsoft.graph_example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import ir.shahinsoft.graphdraw.GraphView;
import ir.shahinsoft.graphdraw.OnNodeClickListener;
import ir.shahinsoft.graphdraw.graph.BinaryTree;
import ir.shahinsoft.graphdraw.model.Node;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphView graphView = findViewById(R.id.graphView);

        graphView.setGraph(
                BinaryTree.create(20)
        );
        graphView.setOnNodeClickListener(new OnNodeClickListener() {
            @Override
            public void onClick(GraphView view, Node node) {
                Toast.makeText(MainActivity.this, node.getLabel(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
