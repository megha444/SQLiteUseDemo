package com.example.newdbapp;

public class Tasks {
private int _id;
private String _taskname;

    public Tasks(String _taskname) {
        this._taskname = _taskname;
    }

    public Tasks() {
    }

    public int get_id() {
        return _id;
    }

    public String get_taskname() {
        return _taskname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_taskname(String _taskname) {
        this._taskname = _taskname;
    }
}
