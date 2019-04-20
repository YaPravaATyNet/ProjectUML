package com.example.projectuml.model;

public class Task {
    private String question;
    private String answer;
    private Class<?> cls;

    public Task(String question, String answer, Class<?> cls) {
        this.question = question;
        this.answer = answer;
        this.cls = cls;
    }

    public String getQuestion() {

        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Class<?> getCls() {
        return cls;
    }
}
