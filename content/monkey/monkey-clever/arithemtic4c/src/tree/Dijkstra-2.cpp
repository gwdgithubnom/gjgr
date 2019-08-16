/*
*    Copyright (c) 2013 eryar All Rights Reserved.
*
*        File    : Main.cpp
*        Author  : eryar@163.com
*        Date    : 2013-1-1 16:50
*        Version : 0.1v
*
*    Description : Use adjacency matrix of a graph.
*                Use Dijkstra method to find the shortest path.
*
*/

#include <CFLOAT>
#include <IOSTREAM>
using namespace std;

const int VERTEX_NUM = 20;
const double INFINITY = DBL_MAX;

// Arc of the graph.
typedef struct SArc
{
     double  dWeight;
 }AdjMatrix[VERTEX_NUM][VERTEX_NUM];

 // The graph: include vertex size and
 // arc size also the adjacency matrix.
 typedef struct SGraph
 {
     int         mVertexSize;
     int         mArcSize;
     int         mVertex[VERTEX_NUM];
     AdjMatrix   mAdjMatrix;
 }Graph;

 // Function declarations.
 void    CreateGraph(Graph& graph, bool isDigraph = false);
 int     LocateVertex(const Graph& graph, int vertex);
 void    ShowGraph(const Graph& graph);
 void    Dijkstra(const Graph& graph, int src);

 // Main function.
 int main(int argc, char* argv[])
 {
     Graph   graph;
     int iSrc = 0;

     CreateGraph(graph, true);
//this is ShowGraph
    // ShowGraph(graph);

     cout<<"Input the source node of the shortest path:";
     cin>>iSrc;

     Dijkstra(graph, iSrc);

     return 0;
 }

 /**
 * brief  Create the graph.
 * param  [in/out] graph: the graph.
 *        [in] isDigraph: Create a digraph when this flag set true.
 * return none.
 */
 void CreateGraph( Graph& graph, bool isDigraph /*= false*/ )
{
     cout<<"Create the graph"<<endl;
     cout<<"Input vertex size:";
     cin>>graph.mVertexSize;

    cout<<"Input arc size:";
    cin>>graph.mArcSize;
     // Input vertex
     cout<<"Input the vertex value:";
   for (int iVertex = 0; iVertex < graph.mVertexSize; iVertex++)
     {

         cin>>graph.mVertex[iVertex];
     }

     // Initialize adjacency matrix.
     for (int i = 0; i < graph.mVertexSize; i++)
     {
         for (int j = 0; j < graph.mVertexSize; j++)
         {
             graph.mAdjMatrix[i][j].dWeight   = INFINITY;
         }
     }

     // Build adjacency matrix.
     int     iInitial  = 0;
     int     iTerminal = 0;
     int     xPos    = 0;
     int     yPos    = 0;
     double  dWeight = 0;

     for (int k = 0; k < graph.mArcSize; k++)
    {
    cout<<"Input "<<k+1<<" arc - initial node to the terminal node, the weight:";
    cin>>iInitial;
    cin>>iTerminal;
    cin>>dWeight;

     xPos  = LocateVertex(graph, iInitial);
       yPos  = LocateVertex(graph, iTerminal);

       graph.mAdjMatrix[xPos][yPos].dWeight = dWeight;

        if (!isDigraph)
        {
            graph.mAdjMatrix[yPos][xPos].dWeight = dWeight;
         }
   }
}

/**
 * brief  Show the weight of the graph arc.
 * param  [in] graph
 * return none.
 */
 void    ShowGraph(const Graph& graph)
 {
     cout<<"Show the graph represented by adjacency matrix:"<<endl;

    // Output adjacency matrix.
    for (int m = 0; m < graph.mVertexSize; m++)
     {
         for (int n = 0; n < graph.mVertexSize; n++)
         {
             cout<<graph.mAdjMatrix[m][n].dWeight<<"\t";
         }

         cout<<endl;
     }
 }

 /**
 * brief  Locate vertex position in the adjacency matrix.
 * param  [in] graph:
 *        [in] vertex:
 * return The position of the vertex. If not found return -1.
 */
 int LocateVertex( const Graph& graph, int vertex )
 {
     for (int i = 0; i < graph.mVertexSize; i++)
     {
        if (graph.mVertex[i] == vertex)
         {
             return i;
         }
     }

     return -1;
 }

 /**
 * brief  Dijkstra algorithm to find the shortest path.
 * param  [in] graph.
 *        [in] source node.
 * return none.
 */
void Dijkstra(const Graph& graph, int src )
 {
     int iMin = 0;
     double dMin = 0;
     double dTempMin = 0;

     // The distance between source node to the vi node.
    double dDist[VERTEX_NUM] = {0};

     // The set of all the shortest path node.
     bool bFinalSet[VERTEX_NUM] = {false};

     // Initialize status: if there is an arc between
     // source node and vi node, set the distance to
     // its weight value.
     for (int i = 0; i < graph.mVertexSize; i++)
     {
         bFinalSet[i] = false;

         dDist[i] = graph.mAdjMatrix[src][i].dWeight;
     }

     // Mark the visit flag.
     dDist[src] = 0;
     bFinalSet[src] = true;

     // Dijstra algorithm: other N-1 vertex.
     for (int j = 1; j < graph.mVertexSize; j++)
     {
         // Find a vertex that its distance is the shortest
         // to the source node.
         dMin = INFINITY;
        for (int k = 0; k < graph.mVertexSize; k++)
         {
             if ((!bFinalSet[k]) && (dDist[k] <= dMin))
             {
                iMin = k;
                 dMin = dDist[k];
             }
         }

         // Add the nearest vertex to the final set.
         bFinalSet[iMin] = true;

         // Output the shortest path vertex and its distance.
         cout<<"The shortest path between "<<src<<" and "<<iMin<<" is: "<<dMin<<endl;

         // Update the shortest path.
         for (int l = 0; l < graph.mVertexSize; l++)
         {
             dTempMin = dMin + graph.mAdjMatrix[iMin][l].dWeight;

             if ((!bFinalSet[l]) && (dTempMin < dDist[l]))
             {
                 dDist[l] = dTempMin;
            }
         }
    }
 }

