package Problems;


	import java.io.*;

	import java.time.LocalDate;

	import java.util.*;



	public class TournamentManager {

	    private List<Teams> teams;

	    private LinkedList<Match> matches;



	    public TournamentManager() {

	        teams = new ArrayList<>();

	        matches = new LinkedList<>();

	    }



	    public boolean addTeam(Teams team) {

	        for (Teams t : teams) {

	            if (t.getName().equalsIgnoreCase(team.getName())) return false;

	        }

	        teams.add(team);

	        return true;

	    }



	    public boolean addMatch(Match match) {

	        for (Match m : matches.toList()) {

	            if (m.getTeam1().equals(match.getTeam1()) &&

	                m.getTeam2().equals(match.getTeam2()) &&

	                m.getDate().equals(match.getDate())) return false;

	        }

	        matches.add(match);

	        updatePoints(match);

	        return true;

	    }



	    private void updatePoints(Match match) {

	        String[] scores = match.getResult().split(":");

	        try {

	            int score1 = Integer.parseInt(scores[0].trim());

	            int score2 = Integer.parseInt(scores[1].trim());

	            if (score1 > score2) {

	                match.getTeam1().setPoints(match.getTeam1().getPoints() + 3);

	            } else if (score1 < score2) {

	                match.getTeam2().setPoints(match.getTeam2().getPoints() + 3);

	            } else {

	                match.getTeam1().setPoints(match.getTeam1().getPoints() + 1);

	                match.getTeam2().setPoints(match.getTeam2().getPoints() + 1);

	            }

	        } catch (Exception e) {

	            System.out.println("Invalid result format: " + match.getResult());

	        }

	    }



	    public void sortTeamsByPoints() {

	        teams.sort(Comparator.comparingInt(Teams::getPoints).reversed());

	    }



	    public List<Teams> getTeams() {

	        return teams;

	    }



	    public LinkedList<Match> getMatches() {

	        return matches;

	    }



	    public List<Match> searchMatchesByDate(LocalDate date) {

	        List<Match> result = new ArrayList<>();

	        for (Match m : matches.toList()) {

	            if (m.getDate().equals(date)) result.add(m);

	        }

	        return result;

	    }



	    public List<Match> searchMatchesByTeam(String name) {

	        List<Match> result = new ArrayList<>();

	        for (Match m : matches.toList()) {

	            if (m.getTeam1().getName().equalsIgnoreCase(name) ||

	                m.getTeam2().getName().equalsIgnoreCase(name)) {

	                result.add(m);

	            }

	        }

	        return result;

	    }



	    public void saveToFile(String teamsFile, String matchesFile) throws IOException {

	        try (PrintWriter tOut = new PrintWriter(new FileWriter(teamsFile))) {

	            for (Teams t : teams) {

	                tOut.println(t.getName() + ";" + String.join(",", t.getPlayers()) + ";" + t.getPoints());

	            }

	        }

	        try (PrintWriter mOut = new PrintWriter(new FileWriter(matchesFile))) {

	            for (Match m : matches.toList()) {

	                mOut.println(m.getTeam1().getName() + ";" + m.getTeam2().getName() + ";" + m.getResult() + ";" + m.getDate());

	            }

	        }

	    }



	    public void loadFromFile(String teamsFile, String matchesFile) throws IOException {

	        teams.clear();

	        matches = new LinkedList<>();

	        Map<String, Teams> teamMap = new HashMap<>();



	        try (BufferedReader reader = new BufferedReader(new FileReader(teamsFile))) {

	            String line;

	            while ((line = reader.readLine()) != null) {

	                String[] parts = line.split(";");

	                String name = parts[0];

	                List<String> players = Arrays.asList(parts[1].split(","));

	                int points = Integer.parseInt(parts[2]);

	                Teams team = new Teams(name, players);

	                team.setPoints(points);

	                teams.add(team);

	                teamMap.put(name, team);

	            }

	        }



	        try (BufferedReader reader = new BufferedReader(new FileReader(matchesFile))) {

	            String line;

	            while ((line = reader.readLine()) != null) {

	                String[] parts = line.split(";");

	                Teams t1 = teamMap.get(parts[0]);

	                Teams t2 = teamMap.get(parts[1]);

	                String result = parts[2];

	                LocalDate date = LocalDate.parse(parts[3]);

	                Match m = new Match(t1, t2, result, date);

	                matches.add(m);

	            }

	        }

	    }

	}



