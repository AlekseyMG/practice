package practice;

import java.util.ArrayList;

public class TodoList {

    public ArrayList<String> todoArrayList = new ArrayList<>();

    public void add(String todo) {
        // TODO: добавьте переданное дело в конец списка
        todoArrayList.add(todo);
    }

    public void add(int index, String todo) {
        // TODO: добавьте дело на указаный индекс,
        //  проверьте возможность добавления
        if (todoArrayList.size() >= index) {
            todoArrayList.add(index, todo);
        } else {
            todoArrayList.add(todo);
        }
    }

    public void edit(int index, String todo) {
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения
        if (todoArrayList.size() >= index) {
            todoArrayList.set(index, todo);
        }
    }

    public void delete(int index) {
        // TODO: удалить дело находящееся по переданному индексу,
        //  проверьте возможность удаления дела
        if (todoArrayList.size() > index) {
            todoArrayList.remove(index);
        }
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел
        return todoArrayList;
    }

}