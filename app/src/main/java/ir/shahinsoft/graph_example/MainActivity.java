package ir.shahinsoft.graph_example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import ir.shahinsoft.graphdraw.GraphView;
import ir.shahinsoft.graphdraw.OnNodeClickListener;
import ir.shahinsoft.graphdraw.graph.BinaryTree;
import ir.shahinsoft.graphdraw.graph.Cube;
import ir.shahinsoft.graphdraw.graph.KReqular;
import ir.shahinsoft.graphdraw.model.Node;

public class MainActivity extends Activity {

    GraphView graphView;
    Spinner graphSpinner;
    Spinner cubeLevelsSpinner;
    Spinner treeLevelsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphView = findViewById(R.id.graphView);

//        graphSpinner = findViewById(R.id.graphs);
//        cubeLevelsSpinner = findViewById(R.id.levels);
//        cubeLevelsSpinner.setVisibility(View.GONE);
//        treeLevelsSpinner = findViewById(R.id.tree_levels);
//
//        graphSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    treeLevelsSpinner.setVisibility(View.VISIBLE);
//                    cubeLevelsSpinner.setVisibility(View.GONE);
//                } else if (position == 1) {
//                    treeLevelsSpinner.setVisibility(View.GONE);
//                    cubeLevelsSpinner.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        graphView.setOnNodeClickListener(new OnNodeClickListener() {
//            @Override
//            public void onClick(GraphView view, Node node) {
//                Toast.makeText(MainActivity.this, node.getLabel(), Toast.LENGTH_LONG).show();
//            }
//        });

        graphView.setGraph(new KReqular(21,4));

    }

    public void displayGraph(View view) {
//        if (!graphSpinner.isSelected()) {
//            Toast.makeText(this, "please select one graph", Toast.LENGTH_LONG).show();
//            return;
//        }
        if (graphSpinner.getSelectedItemPosition() == 0) {
            int nodeCount = Integer.parseInt((String) treeLevelsSpinner.getSelectedItem());
            graphView.setGraph(BinaryTree.create(nodeCount));
        } else if (graphSpinner.getSelectedItemPosition() == 1) {
            int level = Integer.parseInt((String) cubeLevelsSpinner.getSelectedItem());
            graphView.setGraph(new Cube(level));
            Log.d("MainActivity","cube");
        }
    }
}
