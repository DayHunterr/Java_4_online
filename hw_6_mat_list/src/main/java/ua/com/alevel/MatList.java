package ua.com.alevel;

import java.util.*;

public class MatList<E extends Number> implements List<E> {

    private E[] numbers;

    private int size;

    private static final int DEFAULT_SIZE = 10;

    @SuppressWarnings("unchecked")
    public MatList() {
        this.numbers = (E[]) new Number[DEFAULT_SIZE];
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public MatList(E[]... numbers) {
        int size = 0;
        for (E[] array : numbers) {
            size += array.length;
        }
        this.numbers = (E[]) new Number[size];
        int index = 0;
        for (E[] array : numbers) {
            for (E e : array) {
                if (e.getClass().getSuperclass().equals(Number.class)) {
                    this.numbers[index++] = e;
                } else {
                    throw new IllegalArgumentException("Invalid input!");
                }
            }
        }
        this.size = this.numbers.length;
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public MatList(MatList<E>... numbers) {
        int size = 0;
        for (MatList<E> list : numbers) {
            size += list.size();
        }
        this.numbers = (E[]) new Number[size];
        int index = 0;
        for (MatList<E> list : numbers) {
            for (E e : list) {
                if (e.getClass().getSuperclass().equals(Number.class)) {
                    this.numbers[index++] = e;
                } else {
                    throw new IllegalArgumentException("Invalid input!");
                }
            }
        }
        this.size = this.numbers.length;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean add(E n) {
        if (size == numbers.length) {
            E[] temp = (E[]) new Number[numbers.length * 2];
            System.arraycopy(numbers, 0, temp, 0, size);
            numbers = temp;
        }
        numbers[size++] = n;
        return true;
    }
//just for repo
    @SafeVarargs
    public final void add(E... n) {
        for (E e : n) {
            add(e);
        }
    }

    @SafeVarargs
    public final void join(MatList<E>... ml) {
        for (MatList<E> numberList : ml) {
            for (E e : numberList) {
                add(e);
            }
        }
    }

    @SafeVarargs
    public final void intersection(MatList<E>... ml) {
        MatList<E> tempList = new MatList<>(this);
        for (MatList<E> matList : ml) {
            tempList.retainAll(matList);
        }
        clear();
        for (E e : tempList) {
            add(e);
        }
    }

    public void sortDesc() {
        sortDesc(0, size);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        for (int i = firstIndex; i < lastIndex - 1; i++) {
            for (int j = i + 1; j < lastIndex; j++) {
                if (numbers[i].doubleValue() < numbers[j].doubleValue()) {
                    E temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }

            }
        }
    }

    public void sortDesc(E value) {
        int index = indexOf(value);
        sortDesc(index, size);
    }

    public void sortAsc() {
        sortAsc(0, size);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        for (int i = firstIndex; i < lastIndex - 1; i++) {
            for (int j = i + 1; j < lastIndex; j++) {
                if (numbers[i].doubleValue() > numbers[j].doubleValue()) {
                    E temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }

    public void sortAsc(E value) {
        int index = indexOf(value);
        sortAsc(index, size);
    }

    @Override
    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return numbers[index];
    }

    public Number getMax() {
        sortDesc();
        return numbers[0];
    }

    public Number getMin() {
        sortAsc();
        return numbers[0];
    }

    public Number getAverage() {
        double sum = 0;
        for (E number : numbers) {
            sum += number.doubleValue();
        }
        return sum / size;
    }

    public Number getMedian() {
        sortAsc();
        int middle = size / 2;
        if (size % 2 == 0) {
            return (numbers[middle - 1].doubleValue() + numbers[middle].doubleValue()) / 2;
        } else {
            return numbers[middle];
        }
    }

    public Number[] toArray() {
        return toArray(0, size);
    }

    @SuppressWarnings("unchecked")
    public Number[] toArray(int firstIndex, int lastIndex) {
        E[] temp = (E[]) new Number[lastIndex - firstIndex];
        for (int i = firstIndex, j = 0; i < lastIndex; i++, j++) {
            temp[j] = numbers[i];
        }
        return temp;
    }

    public MatList<E> cut(int firstIndex, int lastIndex) {
        return (MatList<E>) subList(firstIndex, lastIndex);
    }

    public void clear() {
        size = 0;
    }

    void clear(E[] numbers) {
        MatList<E> tempList = new MatList<>(this.numbers);
        tempList.removeAll(List.of(numbers));
        this.numbers = (E[]) tempList.toArray();
        this.size = tempList.size();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MatListItr();
    }

    private class MatListItr implements Iterator<E> {
        int current = 0;

        public boolean hasNext() {
            return current < size;
        }

        public E next() {
            return numbers[current++];
        }
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(numbers, size, a.getClass());
        }
        System.arraycopy(numbers, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean isChanged = false;
        for (E e : c) {
            add(e);
            isChanged = true;
        }
        return isChanged;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        boolean isChanged = false;
        for (E e : c) {
            add(index++, e);
            isChanged = true;
        }
        return isChanged;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;
        for (Object o : c) {
            if (remove(o)) {
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(numbers[i])) {
                remove(i);
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = numbers[index];
        numbers[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        if (size == numbers.length) {
            E[] temp = (E[]) new Number[numbers.length * 2];
            System.arraycopy(numbers, 0, temp, 0, size);
            numbers = temp;
        }
        for (int i = size; i > index; i--) {
            numbers[i] = numbers[i - 1];
        }
        numbers[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        E oldValue = numbers[index];
        for (int i = index; i < size - 1; i++) {
            numbers[i] = numbers[i + 1];
        }
        size--;
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        return indexOfRange(o, size);
    }

    private int indexOfRange(Object o, int end) {
        Object[] es = numbers;
        if (o == null) {
            for (int i = 0; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < end; i++) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return lastIndexOfRange(o, size);
    }

    int lastIndexOfRange(Object o, int end) {
        Object[] es = numbers;
        if (o == null) {
            for (int i = end - 1; i >= 0; i--) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = end - 1; i >= 0; i--) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MatListIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MatListIterator(index);
    }

    private class MatListIterator implements ListIterator<E> {
        int current = 0;

        public MatListIterator(int index) {
            current = index;
        }

        public boolean hasNext() {
            return current < size;
        }

        public E next() {
            return numbers[current++];
        }

        public boolean hasPrevious() {
            return current > 0;
        }

        public E previous() {
            return numbers[--current];
        }

        public int nextIndex() {
            return current;
        }

        public int previousIndex() {
            return current - 1;
        }

        public void remove() {
            MatList.this.remove(current);
        }

        public void set(E e) {
            MatList.this.set(current, e);
        }

        public void add(E e) {
            MatList.this.add(current++, e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> subList(int fromIndex, int toIndex) {
        return (List<E>) new MatList<>(toArray(fromIndex, toIndex));
    }

    @Override
    public String toString() {
        return "MatList{" +
                "numbers=" + Arrays.toString(numbers) +
                ", size=" + size +
                '}';
    }
}