package com.geekster.ToDoApplication.Controller;

import com.geekster.ToDoApplication.ToDoEntity.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ToDoController {

    private List<Todo> myTodos;

    public ToDoController(){
        myTodos = new ArrayList<>();
    }

    @GetMapping("todos")
    public List<Todo> getAllTodos(){
        return myTodos;
    }

    @GetMapping("todo/done")
    public List<Todo> getDoneTodos()
    {
        List<Todo> doneTodos = new ArrayList<>();
        for(Todo myTodo:myTodos){
            if(myTodo.isTodoDoneStatus()){
                doneTodos.add(myTodo);
            }
        }
        return doneTodos;
    }
    @GetMapping("todo/undone")
    public List<Todo> getNotDoneTodos()
    {
        List<Todo> unDoneTodos = new ArrayList<>();
        for(Todo myTodo:myTodos){
            if(!myTodo.isTodoDoneStatus()){
                unDoneTodos.add(myTodo);
            }
        }
        return unDoneTodos;
    }
    @PostMapping("todo")
    public String  addTodo(@RequestBody Todo todo){
        myTodos.add(todo);
        return "To Do Added Successfully.";
    }

    @PutMapping("todo/{todoId}/{status}")
    public String markTodo(@PathVariable Integer todoId, @PathVariable boolean status){
        for(Todo myTodo: myTodos)
        {
            if(myTodo.getTodoId().equals(todoId))
            {
                myTodo.setTodoDoneStatus(status);
                return "to do updated successfully";
            }
        }
        return "to do is not found.";
    }

    @DeleteMapping("removeTodo")
    public String deleteTodo(@RequestParam Integer todoId){
        for(Todo todo:myTodos)
        {
            if(todo.getTodoId().equals(todoId))
            {
                myTodos.remove(todo);
                return "todo Successfully removed.";
            }
        }
        return "to do not found.";
    }

}
