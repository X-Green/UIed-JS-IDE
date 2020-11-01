package dev.eeasee.js_uied_ide.parser.container;

import dev.eeasee.js_uied_ide.parser.lex_analyzer.AbstractTokenMatcher;
import it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public class Char2ObjTreeContainer<T> {
    private boolean ready = false;

    private Map<String, T> tempElements = new HashMap<>();

    public void addElement(String s, T obj) {
        tempElements.put(s, obj);
    }

    public void doSort() {
        for (Map.Entry<String, T> entry : tempElements.entrySet()) {

        }

        ready = true;
        tempElements.clear();
    }

    public T findWord(AbstractTokenMatcher.CharIterator ci) {
        if (!ready) {
            throw new UnsupportedOperationException();
        }
        return null;
    }

    public static class Node<T> {
        private boolean isTerminal = true;
        private boolean hasChildren = true;
        private T object;
        private Char2ObjectArrayMap<Node<T>> children = new Char2ObjectArrayMap<>();

        private boolean sealed = false;

        public void attachObj(T object) {
            if (sealed) throw new UnsupportedOperationException();
            this.object = object;
        }

        public void attachChild(char c, Node<T> child) {
            if (sealed) throw new UnsupportedOperationException();
            this.children.put(c, child);
        }

        public void seal() {
            if (this.children.isEmpty()) {
                this.children = null;
                this.hasChildren = false;
            }
            if (object == null) {
                this.isTerminal = false;
            }

            this.sealed = true;
        }

        public boolean isTerminal() {
            return this.isTerminal;
        }

        public boolean hasChildren() {
            return this.hasChildren;
        }
    }
}
