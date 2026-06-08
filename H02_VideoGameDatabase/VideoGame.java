package H02_VideoGameDatabase;
//written by Teagan Donnelly

public class VideoGame {
    private String title;
    private String console;

    public VideoGame() {
        title = "none";
        console = "none";
    }

    public VideoGame(String t, String c) {
        setTitle(t);
        setConsole(c);
    }

    public String getTitle() {
        return title;
    }

    public String getConsole() {
        return console;
    }

    public void setTitle(String t) {
        if (t != null) {
            title = t;
        } else {
            title = "none";
        }
    }

    public void setConsole(String c) {
        if (c != null) {
            console = c;
        } else {
            console = "none";
        }
    }

    // checks if this game matches the search
    public boolean matches(String titleQuery, String consoleQuery) {
        if (titleQuery == null || consoleQuery == null) return false;

        String tq = titleQuery.trim().toLowerCase();
        String cq = consoleQuery.trim().toLowerCase();

        // wildcard: treat "*" as empty string which is contained in everything
        if (tq.equals("*")) tq = "";
        if (cq.equals("*")) cq = "";

        String t = (title == null) ? "" : title.toLowerCase();
        String c = (console == null) ? "" : console.toLowerCase();

        return t.contains(tq) && c.contains(cq);
    }


    public String toString() {
        return title + " " + console;
    }
}
