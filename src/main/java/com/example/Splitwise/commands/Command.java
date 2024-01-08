package com.example.Splitwise.commands;

public interface Command {
     boolean matches(String input);
     void execute(String input);
}
