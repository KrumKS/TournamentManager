package Problems;


	





	import java.util.List;



	public class Teams {

	    private String name;

	    private List<String> players;

	    private int points;



	    public Teams(String name, List<String> players) {

	        this.name = name;

	        this.players = players;

	        this.points = 0;

	    }



	    public String getName() { return name; }

	    public List<String> getPlayers() { return players; }

	    public int getPoints() { return points; }

	    public void setPoints(int points) { this.points = points; }



	    @Override

	    public String toString() {

	        return name;

	    }

	}

