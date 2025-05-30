package Problems;


	import java.time.LocalDate;



	public class Match {

	    private Teams team1;

	    private Teams team2;

	    private String result;

	    private LocalDate date;



	    public Match(Teams team1, Teams team2, String result, LocalDate date) {

	        this.team1 = team1;

	        this.team2 = team2;

	        this.result = result;

	        this.date = date;

	    }



	    public Teams getTeam1() { return team1; }

	    public Teams getTeam2() { return team2; }

	    public String getResult() { return result; }

	    public LocalDate getDate() { return date; }



	    @Override

	    public String toString() {

	        return team1.getName() + " vs " + team2.getName() + " | " + result + " | " + date;

	    }

	}

