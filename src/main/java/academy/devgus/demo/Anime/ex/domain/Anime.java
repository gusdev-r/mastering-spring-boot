package academy.devgus.demo.Anime.ex.domain;

public class Anime {
    private String name;
    private int chapters;

    public Anime(String name, int chapters) {
        this.name = name;
        this.chapters = chapters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }
}
