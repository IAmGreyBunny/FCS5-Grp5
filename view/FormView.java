package view;

public abstract class FormView implements IView {
    public abstract void prompt();
    public abstract <T> T getUserInput();
}
