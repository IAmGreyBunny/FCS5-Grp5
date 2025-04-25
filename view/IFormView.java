package view;

public interface IFormView extends IView {
    public abstract void prompt();
    public abstract <T> T getUserInput();
}
