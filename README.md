https://github.com/Tiagonizer/simulador-substituicao-paginas-java


# Simulador de Algoritmos de Substituição de Páginas (Java)

Este projeto contém um simulador em **Java** para comparar o desempenho de diferentes algoritmos de substituição de páginas em um sistema de gerenciamento de memória virtual.

## Algoritmos Implementados

O simulador inclui a implementação dos seguintes algoritmos:

1.  **FIFO** (First In, First Out)
2.  **LRU** (Least Recently Used)
3.  **Ótimo** (Optimal)
4.  **Relógio** (Clock / Second Chance)

## Como Executar

Para executar o simulador, você precisa ter o **Java Development Kit (JDK)** instalado em seu sistema (versão 8 ou superior).

1.  **Baixe o arquivo:**
    Faça o download do arquivo `PageReplacementSimulator.java`.

2.  **Compile o código:**
    Abra o terminal ou prompt de comando, navegue até o diretório onde você salvou o arquivo e execute o seguinte comando para compilar:

    ```bash
    javac PageReplacementSimulator.java
    ```

3.  **Execute o programa:**
    Após a compilação, execute o programa com o seguinte comando:

    ```bash
    java PageReplacementSimulator
    ```

## Resultados

O programa irá imprimir no console os resultados da simulação, mostrando o número de faltas de página para cada um dos quatro algoritmos, utilizando a string de referência e o número de frames definidos no código.

**Exemplo de Saída:**

```
--- Simulador de Algoritmos de Substituição de Páginas ---
String de Referência: [7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1]
Número de Frames: 3

Faltas de Página:
- Método FIFO - 15 faltas de página
- Método LRU - 12 faltas de página
- Método Ótimo - 9 faltas de página
- Método Relógio - 14 faltas de página
```

## Configuração

Você pode alterar a **string de referência de páginas** (`PAGE_REFERENCE_STRING`) e o **número de frames** (`FRAME_COUNT`) diretamente no arquivo `PageReplacementSimulator.java` para testar diferentes cenários.
