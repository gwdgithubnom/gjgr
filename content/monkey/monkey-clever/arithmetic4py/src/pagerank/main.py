import graphviz as gv
from pygraph.classes.digraph import digraph
from pygraph.readwrite.dot import write

# Define pagerank function
def pagerank(graph, damping_factor=0.85, max_iterations=100, min_delta=0.00001):
    nodes = graph.nodes()
    graph_size = len(nodes)
    if graph_size == 0:
        return {}
    # value for nodes without inbound links
    min_value = (1.0-damping_factor)/graph_size
  #  print(graph_size)
    # itialize the page rank dict with 1/N for all nodes
    #pagerank = dict.fromkeys(nodes, 1.0/graph_size)
    pagerank = dict.fromkeys(nodes, 1.0)
#    print(pagerank);
    for i in range(max_iterations):
        diff = 0 #total difference compared to last iteraction
        # computes each node PageRank based on inbound links
        for node in nodes:
            rank = min_value
          #  print(node);
            print(pagerank)
            for referring_page in graph.incidents(node):
                print(referring_page,":",rank,damping_factor,"last:pageRank->",pagerank[referring_page],len(graph.neighbors(referring_page)),"neighbors:",graph.neighbors(referring_page))
                rank += damping_factor * pagerank[referring_page] /len(graph.neighbors(referring_page))
                #print(referring_page,graph.incidents(node),node);
            diff += abs(pagerank[node] - rank)
            pagerank[node] = rank
        #print('This is NO.%s iteration' % (i+1))
        #print(pagerank)
        #print('')

        #stop if PageRank has converged
        if diff < min_delta:
            break

    return pagerank

# Graph creation
gr = digraph()

# Add nodes and edges
gr.add_nodes(["1","2","3","4","5","6"])
gr.add_edge(("2","1"))
gr.add_edge(("3","2"))
gr.add_edge(("4","2"))
gr.add_edge(("4","3"))
gr.add_edge(("5","3"))
gr.add_edge(("6","3"))
gr.add_edge(("1","4"))
gr.add_edge(("6","5"))
gr.add_edge(("1","6"))
print(pagerank(gr))
