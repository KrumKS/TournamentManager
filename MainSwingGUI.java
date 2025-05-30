package Problems;


	import Problems.*;



	import javax.swing.*;

	import javax.swing.table.DefaultTableModel;

	import java.awt.*;

	import java.awt.event.ActionEvent;

	import java.time.LocalDate;

	import java.util.List;



	public class MainSwingGUI {

	    private TournamentManager manager = new TournamentManager();

	    private JFrame frame;

	    private JTable teamTable;

	    private DefaultTableModel tableModel;



	    public MainSwingGUI() {

	        frame = new JFrame("Sports Tournament Manager");

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        frame.setSize(800, 600);



	        JPanel mainPanel = new JPanel(new BorderLayout());

	        mainPanel.add(createInputPanel(), BorderLayout.NORTH);

	        mainPanel.add(createTeamTablePanel(), BorderLayout.CENTER);



	        frame.setContentPane(mainPanel);

	        frame.setVisible(true);

	    }



	    private JPanel createInputPanel() {

	        JPanel inputPanel = new JPanel();

	        inputPanel.setLayout(new GridLayout(3, 1));



	        // Add Team Panel

	        JPanel teamPanel = new JPanel();

	        JTextField teamNameField = new JTextField(10);

	        JTextField playersField = new JTextField(20);

	        JButton addTeamBtn = new JButton("Add Team");



	        addTeamBtn.addActionListener((ActionEvent e) -> {

	            String name = teamNameField.getText().trim();

	            String[] players = playersField.getText().split(",");

	            Teams team = new Teams(name, List.of(players));

	            if (!manager.addTeam(team)) {

	                showMessage("Team already exists.");

	            } else {

	                refreshTable();

	            }

	        });



	        teamPanel.add(new JLabel("Team Name:"));

	        teamPanel.add(teamNameField);

	        teamPanel.add(new JLabel("Players (comma separated):"));

	        teamPanel.add(playersField);

	        teamPanel.add(addTeamBtn);



	        // Add Match Panel

	        JPanel matchPanel = new JPanel();

	        JTextField team1Field = new JTextField(8);

	        JTextField team2Field = new JTextField(8);

	        JTextField resultField = new JTextField(5);

	        JTextField dateField = new JTextField(10);

	        JButton addMatchBtn = new JButton("Add Match");



	        addMatchBtn.addActionListener((ActionEvent e) -> {

	            Teams t1 = findTeamByName(team1Field.getText());

	            Teams t2 = findTeamByName(team2Field.getText());

	            if (t1 == null || t2 == null) {

	                showMessage("One or both teams not found.");

	                return;

	            }

	            try {

	                LocalDate date = LocalDate.parse(dateField.getText());

	                Match match = new Match(t1, t2, resultField.getText(), date);

	                if (!manager.addMatch(match)) {

	                    showMessage("Duplicate match.");

	                } else {

	                    refreshTable();

	                }

	            } catch (Exception ex) {

	                showMessage("Invalid date format. Use YYYY-MM-DD");

	            }

	        });



	        matchPanel.add(new JLabel("Team 1:"));

	        matchPanel.add(team1Field);

	        matchPanel.add(new JLabel("Team 2:"));

	        matchPanel.add(team2Field);

	        matchPanel.add(new JLabel("Result (e.g. 2:1):"));

	        matchPanel.add(resultField);

	        matchPanel.add(new JLabel("Date (YYYY-MM-DD):"));

	        matchPanel.add(dateField);

	        matchPanel.add(addMatchBtn);



	        inputPanel.add(teamPanel);

	        inputPanel.add(matchPanel);

	        return inputPanel;

	    }



	    private JPanel createTeamTablePanel() {

	        JPanel panel = new JPanel(new BorderLayout());

	        String[] columnNames = {"Team", "Points"};

	        tableModel = new DefaultTableModel(columnNames, 0);

	        teamTable = new JTable(tableModel);

	        panel.add(new JScrollPane(teamTable), BorderLayout.CENTER);

	        return panel;

	    }



	    private void refreshTable() {

	        manager.sortTeamsByPoints();

	        tableModel.setRowCount(0);

	        for (Teams team : manager.getTeams()) {

	            tableModel.addRow(new Object[]{team.getName(), team.getPoints()});

	        }

	    }



	    private Teams findTeamByName(String name) {

	        return manager.getTeams().stream()

	                .filter(t -> t.getName().equalsIgnoreCase(name))

	                .findFirst().orElse(null);

	    }



	    private void showMessage(String message) {

	        JOptionPane.showMessageDialog(frame, message);

	    }



	    public static void main(String[] args) {

	        SwingUtilities.invokeLater(MainSwingGUI::new);

	    }

	}

