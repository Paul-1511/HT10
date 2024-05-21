import networkx as nx

def agregar_arco(G, ciudad1, ciudad2, distancia):
    G.add_edge(ciudad1, ciudad2, weight=distancia)

def eliminar_arco(G, ciudad1, ciudad2):
    if G.has_edge(ciudad1, ciudad2):
        G.remove_edge(ciudad1, ciudad2)

def floyd_warshall(G):
    return nx.floyd_warshall(G)

def obtener_centro_grafo(G):
    return nx.center(G)

def main():
    G = nx.DiGraph()
    # Leer el grafo desde un archivo
    with open('guategrafo.txt', 'r') as f:
        for line in f:
            ciudad1, ciudad2, distancia = line.strip().split()
            agregar_arco(G, ciudad1, ciudad2, int(distancia))
    # Aplicar el algoritmo de Floyd
    distancias = floyd_warshall(G)
    # Calcular el centro del grafo
    centro = obtener_centro_grafo(G)
    print(f'El centro del grafo es: {centro}')

if __name__ == '__main__':
    main()
