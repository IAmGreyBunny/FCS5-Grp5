package view.general;

import project.Project;
import session.Session;
import view.MenuView;
import view.View;

import java.awt.*;

public class ProjectDetailView extends MenuView {

    View prevView;
    Project project;

    ProjectDetailView(View prevView,Project project)
    {
        if(project != null)
        {
            this.project = project;
        }else
        {
            System.out.println("Invalid Project");
            Session.getSession().setCurrentView(prevView);
        }
    }


    @Override
    public void show() {
    }
}
