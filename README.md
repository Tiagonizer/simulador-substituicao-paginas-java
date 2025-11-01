https://github.com/Tiagonizer/simulador-substituicao-paginas-java


# Simulador de Algoritmos de Substituição de Páginas (Java)

O simulador foi desenvolvido em Java para demonstrar e comparar o desempenho de quatro algoritmos de substituição de páginas:
FIFO (First In, First Out), LRU (Least Recently Used), Ótimo (Optimal) e Relógio (Clock). Permite a implementação clara das estruturas de dados necessárias para simular o gerenciamento de memória e inclui uma interface gráfica simples desenvolvida com **Java Swing**.

## Algoritmos Implementados

O simulador é implementado na classe PageReplacementSimulator.java, que contém quatro métodos estáticos, cada um implementando um algoritmo de substituição, e o método principal (main) que coordena o processo e apresenta os resultados.

1.
simulateFIFO(List<Integer> referenceString, int frameCount): Implementa a lógica FIFO, utilizando uma LinkedList para simular
 a fila de páginas na memória. A página mais antiga (primeira da lista) é substituída.

3.
simulateLRU(List<Integer> referenceString, int frameCount): Implementa a lógica LRU, também utilizando uma LinkedList. A página menos recentemente usada é sempre a primeira da lista, e a página acessada é movida para o final (mais recentemente usada).

5.
simulateOptimal(List<Integer> referenceString, int frameCount): Implementa o algoritmo Ótimo, que substitui a página que será usada mais tarde no futuro. Este algoritmo requer uma análise prospectiva da string de referência.

7.
simulateClock(List<Integer> referenceString, int frameCount): Implementa o algoritmo do Relógio (Second Chance), utilizando um array de objetos com um bit de uso (useBit) para dar uma "segunda chance" à página antes de ser substituída.



## Dados de Entrada

Para a simulação, foram utilizados os seguintes parâmetros, definidos como constantes na classe:

| Parâmetro | Valor |
| :--- | :--- |
| String de Referência | `[7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1]` |
| Número de Frames | `3` |





## Resultados

A execução do simulador com os dados de entrada definidos resultou no seguinte número de faltas de página para cada algoritmo:
| Algoritmo | Faltas de Página |
| :--- | :--- |
| **FIFO** | 15 |
| **LRU** | 12 |
| **Ótimo** | 9 |
| **Relógio** | 14 |


## Análise dos Resultados:

• O algoritmo Ótimo obteve o menor número de faltas (9), o que é esperado, pois ele representa o limite teórico de desempenho.

• O LRU (12 faltas) demonstrou ser mais eficiente que o FIFO (15 faltas) e o Relógio (14 faltas) para a string de referência utilizada, indicando que a heurística de substituir a página menos recentemente usada é mais eficaz neste cenário.

• O Relógio (14 faltas) apresentou um desempenho ligeiramente melhor que o FIFO, confirmando sua natureza como uma aproximação mais eficiente do LRU com menor overhead de implementação.

Estes resultados demonstram a importância da escolha do algoritmo de substituição de páginas para o desempenho do sistema de gerenciamento de memória virtual.

