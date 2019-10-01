# android-graph-draw
a simple library to draw graph in android

## Usage

Simply add graphDraw to your layout file
```
<ir.shahinsoft.graphdraw.GraphView
        android:id="@+id/graphView"
        android:layout_width="match_parent"
        android:layout_height="500dp"
        android:background="@android:color/white"
        android:clickable="true"
        android:focusable="true"
        android:paddingTop="8dp" />
```

If you want to get clicks on nodes set `clickable` and `focusable` to `true`.

the code above does nothing but add a empty view in your activity. to show graph you must create it and set it to the GraphView.

in  order to set a graph to your view use `setGraph` method.
```
graphView.setGraph(someGraph);
```

## creating a graph
To create a graph you can use classic `int[][]` to create graph
```
int[][] myGraph = new int[][] {
        0,0,1,1,
        0,0,1,0,
        1,1,0,1,
        1,0,1,0
}
Graph g = new Graph();
g.setGraph(myGraph);
```

Also you can use `Builder` class inside `Graph` but it's only for small data and it's not recommended to use for big graphs.
```
Graph g = new Graph.Builder(nodeCount)
              .setEdge(1,2) // set edge between first node and second node.
              .setEdge(2,4) // set edge between second node and third node.
              .setNodePosition(0,10,10) // set postion (10,10) to node in position 0 ( actualy with id=0)
              .setNodePosition(1,14,2)
              .setNodePosition(2,50,10)
              .setNodePosition(3,20,80)
              .setNodePosition(4,45,9)
              .setDirected(false)
              .setNodeLabel("node1",0) // set label 'node1' to node with id=0
              .build();
```

Also you can create a list of nodes and edgs and set them to your graph

```
ArrayList<Node> nodes = createNodes();
ArrayList<Edge> edges = createEdges();
Graph g = new Graph();
g.setNodes(nodes);
g.setEdges(edges);
```

## Classes and Methods
here is the list of classes and their methods in this library.

### Node.java
Node is the class to represent a node of a graph

#### variables
`static int DEFAULT_COLOR`= BLACK

default color of the node.

`int color`

the color of the node when it displayed.

`int id`

identifier for the node, used when for searching among nodes. its better to be the index of node in the list for better accessing.

`String label`

the label of the node. it is displayed when user clickes on the node.

`boolean hasFocus`

this variable is not accessable by you and it sets to `true` when user clickes on the node.

`float relativePositionX`

is the relative position x of the node. this variable can be any number between 0 and 100. if you set it to any number created than 100 the value wont be changed but when drawing node this variable will be considered as 100.

`float relativePositionY`

is the relative position y of the node. this variable can be any number between 0 and 100. if you set it to any number created than 100 the value wont be changed but when drawing node this variable will be considered as 100.

#### static methods
` createNode(int id);`

creates a node with given id. `relativePositionX` and `relativePositionY` will be 0

` createNodeWithColor(int id, int color);`

creates a node with given `id` and `color`. `relativePositionX` and `relativePositionY` will be 0

` createNodeWithLabel(int id, int color, String label);`

creates a node with given `id` and `color` and `label`. `relativePositionX` and `relativePositionY` will be 0

` createNode(int id, int posX, int posY);`

creates a node with given `id` and `posX` and `posY`.

` createNodeWithColor(int id, int posX, int posY, int color);`

creates a node with given `id` and `posX` and `posY` and `color`.

` createNodeWithLabel(int id, int posX, int posY, int color, String label);`

creates a node with given `id` and `posX` and `posY` and `color` and `label`.

### Edge.java
Edge is the class to represent an edge of the graph

#### variables
`static int DEFAULT_COLOR` = Color.BLACK

is Default color of the edge when it displayed.

`static int DEFAULT_WEIGHT` = 1

is default weight of the edge.

`int weight`

is weight of the edge


`int color`

is color of the edge when displayed.

`int startNodeId`

is id of the node that this edge starts with.

`int endNodeId`

is id of the node that this edge ends with.

`boolean isDirected`

shows if this node is directed or not. this varible is used by `GraphView` when drawing the graph and can be changed by `Graph` class. and cannot be changed directly.

#### static methods

` createEdge(int startNodeId, int endNodeId);`

creates an edge with given `startNodeId` and `endNodeId`

` createEdgeWidthWeight(int startNodeId, int endNodeId, int weight);`

creates an edge with given `startNodeId` and `endNodeId` and `weight`

` createEdgeWithColor(int startNodeId, int endNodeId, int color);`

creates an edge with given `startNodeId` and `endNodeId` and `color`

### Graph.java
Graph is the class to represent a graph

#### variables

` ArrayList<Node> nodes`

is the list of nodes in graph


` ArrayList<Edge> edges`

is the list of edges in graph

` Edge[][] graph`

is the matrix of edges. if in position `(i , j)` in this matrix a edge exists then there is a edge between `i`'th node and `j`'th node. else (`graph[i][j]` is null) then there is no Edge between `i'`th and `j`'th nodes.

` View view`

is the GraphView that this graph is assinged. the Graph class itself not using this variable but any other class that extends from Graph can use this view to organize the nodes positions.


#### methods

` setEdges(ArrayList<Edge> edges);`

assigns given `edges` to variable `edges` exist in object.

` setNodes(ArrayList<Node> nodes);`

assigns given `nodes` to vaiable `nodes` exist in object.

` setGraph(Node[][] graph);`

assigns give `graph` to variable `graph` exist in object and recreates list of nodes and list of edges.

` addNode(Node node);`

add a new node to nodes list.

` removeNode(Node node);`

remve node from nodes list.

` addEdge(Edge edge);`

add a new edge to edges list.

` addEdge(Node startNode,Node endNode);`

creates a new Edge and adds that edge to edges list. also returns created edge to you.

` removeEdge(Edge Edge);`

removes an edge from edges list.

` fineNode(int nodeId);`

searchs the nodes list and finds the node with given `nodeId`.

` setFocusForNode(int nodeId);`

searchs for node with id=`nodeId` and sets focus the node and removes focus from all other nodes.


### BinaryTree.java
`BinaryTree` extends from `Graph` and is a example of how you can create your custom graphs.


#### variables

`float levelPadding`

is the space between levels of the binary tree.

#### static methods

` create(int nodeCount);`

creates a graph with `nodeCount` nodes and sets the position of the nodes based on screen width.

### GraphView.java
`GraphView` is the main class of this library that draws graph on screen.

`float maxRelativeSizeX = 100f;`

is the max relative size x and uses to convert nodes postions from relative number to exact number based on view size.
this variable cannot be changed.

`float maxRelativeSizeY = 100f;`

is the max relative size y and uses to convert nodes postions from relative number to exact number based on view size.
this variable cannot be changed.

#### methods

` setGraph(Graph g);`

sets the tartget graph to be displayed and `invalidate`s the view.

` setOnNodeClickListener(OnNodeClickListener listener);`

sets a listener and notifys you when a node clicks. it will give you the clicked node and the `GraphView` that node clicked in it.

