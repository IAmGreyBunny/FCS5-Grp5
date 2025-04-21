package view;

import java.util.HashMap;
import java.util.Objects;

public abstract class FormView extends View{
    public abstract void prompt();
    public abstract <T> T getUserInput();
}
