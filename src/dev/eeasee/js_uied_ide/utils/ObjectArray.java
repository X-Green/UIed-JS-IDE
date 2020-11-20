package dev.eeasee.js_uied_ide.utils;

public class ObjectArray<T> {
    public final Object[] objectArray;

    public ObjectArray(int size) {
        this.objectArray = new Object[size];
    }

    public ObjectArray(Object[] array) {
        this.objectArray = array;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) objectArray[index];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Array Object {");
        for (Object o : this.objectArray) {
            stringBuilder.append(o);
            stringBuilder.append(", ");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
