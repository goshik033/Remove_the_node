/*
-- ПРИНЦИП РАБОТЫ --
Получам корень дерева и ключ, дальее находим элемент занченение которого равно ключу.
После этого ищем самого правого ребенка из левого поддерева или самого левого ребенка из правого поддерева,
встаялем его значение в ранее найденный элемент, а сомиого ребенка удаляем.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Когда идем по дереву проверяем занчение текущего узла и смотрим, если оно меньше, нашего ключа,
то идем в правое поддерево если больше, то идем в левое и при каждом переводе запоминаем родителя.
Если значение равно, то поиск останавливается. Дальше смотрим, есть ли у элемента дети, если нет,
то в узле родителя присваиваем этому элементу null, если есть только один или два ребенока,
которые являются лисатми, то присваиваем его значение нашему элементу, а самого ребенка удаляем.
Если дети не листы, то ищем самого правого ребенка из левого поддерева, параллельно запоминая родителя.
Когда найдем его значение присваиваем нашему элементу,
а в родителе в место этого элемента встаялем его левого(правго в зависимости от выбранного поддерева) ребенка.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
В лучшем случае, когда узел, который нужно удалить, является листом алгоритм выполняется за время O(log n),
n - количество узлов
В худшем случае за время O(n),n - количество узлов
В среднем за O(h), h- высота, так ка сначала мы ищем сам эелемент,
а потом от этого эелемента спускаемся в самый низ дерев к листьям, получается проходим путь рвавный высоте.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(1)
Так как все операции выполняются с узлами дерева и временными переменными, ничего дополнительного не создается

ID 90588790
*/

import java.util.*;

// <template>
class Node {
    private int value;
    private Node left;
    private Node right;

    Node(Node left, Node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
// <template>

public class Solution {
    public static Node remove(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.getValue()) {
            root.setLeft(remove(root.getLeft(), key));
        } else if (key > root.getValue()) {
            root.setRight(remove(root.getRight(), key));
        } else if (root.getLeft() == null) {
            return root.getRight();
        } else if (root.getRight() == null) {
            return root.getLeft();
        } else {
            Node newRoot = root.getRight();
            int minValue = newRoot.getValue();
            while (newRoot.getLeft() != null) {
                minValue = newRoot.getLeft().getValue();
                newRoot = newRoot.getLeft();
            }
            root.setValue(minValue);
            root.setRight(remove(root.getRight(), minValue));
        }

        return root;
    }


    private static void test() {
        Node node1 = new Node(null, null, 5);
        Node node2 = new Node(node1, null, 3);
        Node node3 = new Node(null, node2, 1);
        Node node4 = new Node(null, null, 6);
        Node node5 = new Node(node4, null, 8);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node3, node6, 5);
        Node newHead = remove(node7, 10);
        assert newHead.getValue() == 5;
        assert newHead.getRight() == node5;
        assert newHead.getRight().getValue() == 8;
    }
}







