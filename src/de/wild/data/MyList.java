package de.wild.data;


import java.util.ArrayList;

public class MyList {

    public static class MyListEntry {
        private MyListEntry next;
        private MyListEntry previous;
        String current;
        public MyListEntry(String current, MyListEntry previous, MyListEntry next){
            this.current = current;
            this.previous = previous;
            this.next = next;
        }

        public boolean hasNext(){
            return this.next != null;
        }

        public boolean hasPrevious(){
            return this.previous != null;
        }

        public String getString(){
            return this.current;
        }

        public MyListEntry next(){
            return this.next;
        }

        public MyListEntry previous(){
            return this.previous;
        }

        private void setNext(MyListEntry next) {
            this.next = next;
        }

        private void setPrevious(MyListEntry previous) {
            this.previous = previous;
        }
    }

    private final ArrayList<MyListEntry> strings;
    private MyListEntry current;
    public MyList(){
        current = null;
        strings = new ArrayList<>();
    }


    public void add(String s){
        MyListEntry entry = new MyListEntry(s, (strings.size() != 0 ? strings.get(strings.size() - 1) : null), null);
        if (strings.size() != 0){
            strings.get(strings.size()-1).setNext(entry);
        }
        strings.add(entry);
        if (current == null) current = entry;
    }

    public int size(){
        return strings.size();
    }

    public MyListEntry get(int i){
        return strings.get(i);
    }

    public void remove(int i){
        if (get(i-1) != null && get(i+1) != null){
            get(i-1).setNext(get(i+1));
            get(i+1).setPrevious(get(i-1));
        }
        if (get(i-1) == null){
            if (get(i+1) != null) get(i+1).setPrevious(null);
        }
        if (get(i+1) == null){
            if (get(i-1) != null) get(i-1).setNext(null);
        }
        strings.remove(i);
    }

    public void setCurrent(MyListEntry current){
        this.current = current;
    }

    public boolean hasNext(){
        if (current != null){
            if (!current.hasNext()){
                firstElement = true;
            }
        }
        return (current != null && current.hasNext());
    }

    public MyListEntry getFirst(){
        return get(0);
    }

    private boolean firstElement = true;

    public MyListEntry next(){
        if (firstElement){
            current = getFirst();
            firstElement = false;
            return current;
        }
        current = current.next();
        return current;
    }

    public boolean hasPrevious(){
        return (current != null && current.hasPrevious());
    }

    public MyListEntry previous(){
        current = current.previous();
        return current;
    }

    public ArrayList<String> getStrings() {
        ArrayList<String> s = new ArrayList<>();
        for (MyListEntry m : strings){
            s.add(m.getString());
        }
        return s;
    }
}
