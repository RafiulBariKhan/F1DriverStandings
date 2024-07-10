package wdc.pojo;

public class DriversPojo {
    
    private int rank;
    private String name;
    private int wins;
    private int points;

    public DriversPojo(int rank, String name, int wins, int points) {
        this.rank = rank;
        this.name = name;
        this.wins = wins;
        this.points = points;
    }
    
    public DriversPojo(){}

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    } 
    
}
