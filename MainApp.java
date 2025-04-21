import session.Session;

public class MainApp {
    public static void main(String[] args){

        //Load up session
        Session session = Session.getSession();
        session.loadInitialData();

        while(session.getCurrentView() != null){
            session.getCurrentView().show();
        }
    }
}
