package dev.eeasee.js_uied_ide.parser.container;

import com.sun.istack.internal.Nullable;
import it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectMaps;
import it.unimi.dsi.fastutil.chars.Char2ObjectOpenHashMap;
import org.w3c.dom.Node;

public class CharObjOpenHashTree<T> {

    public final Char2ObjectMap<Node<T>> root = new Char2ObjectOpenHashMap<>();

    public CharObjOpenHashTree() {

    }

    public void put(char[] key, T obj) {
        Node<T> node = null;
        char c;
        int keyLength = key.length;
        if (keyLength == 0) {
            throw new UnsupportedOperationException();
        }
        for (int i = 0; i < keyLength; i++) {
            c = key[i];
            node = (i == 0) ? this.getNodeAndCreateIfAbsent(c) : node.getNodeAndCreateIfAbsent(c);
        }
        node.leaf = obj;
    }

    public void cutBranches() {
        for (Node<T> node : this.root.values()) {
            node.cutBranches();
        }
    }

    public Node<T> getNodeAndCreateIfAbsent(char c) {
        if (this.root.containsKey(c)) {
            return this.root.get(c);
        } else {
            Node<T> n = new Node<T>(c);
            this.root.put(c, n);
            return n;
        }
    }

    public static class Node<T> {
        public char head;

        public T leaf;

        public Char2ObjectMap<Node<T>> branches;

        public boolean cut = false;

        public Node(char head, @Nullable T leaf, Char2ObjectMap<Node<T>> branches) {
            this.head = head;
            this.branches = new Char2ObjectArrayMap<>(branches);
            this.leaf = leaf;
        }

        public Node(char head) {
            this.head = head;
            this.branches = new Char2ObjectOpenHashMap<>();
            this.leaf = null;
        }

        public void cutBranches() {
            if (this.branches.isEmpty()) {
                this.branches = Char2ObjectMaps.emptyMap();
            } else {
                for (Node sub : this.branches.values()) {
                    sub.cutBranches();
                }
            }
            this.cut = true;
        }

        public Node<T> getNodeAndCreateIfAbsent(char c) {
            if (this.branches.containsKey(c)) {
                return this.branches.get(c);
            } else {
                Node<T> n = new Node<T>(c);
                this.branches.put(c, n);
                return n;
            }
        }

        @Override
        public String toString() {
            return "Node: " +
                    "Leaf=" +
                    this.leaf +
                    ", Branches=" +
                    this.branches;
        }
    }
}
