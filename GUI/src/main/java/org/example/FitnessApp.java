package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class FitnessApp {

    private final JFrame frame = new JFrame("Fitness App");

    // Variables to store user data
    private int stepsGoal = 0;
    private String mealLogged = "";
    private String workoutIntensity = "";

    public FitnessApp() {
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240, 240, 240));
        Font customFont = new Font("Arial", Font.PLAIN, 14);
        UIManager.put("TextField.font", customFont);
        UIManager.put("Button.font", customFont);
        UIManager.put("Label.font", customFont);
        showHomeScreen();
        frame.setVisible(true);
    }

    private void showHomeScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel stepsLabel = new JLabel("Steps Taken: ");
        JTextField stepsField = new JTextField(10);
        JButton setGoalButton = new JButton("Set Goal");
        JButton goToWorkoutPlannerButton = new JButton("Workout Planner");

        setGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stepsGoal = Integer.parseInt(stepsField.getText());
                JOptionPane.showMessageDialog(frame, "Goal set to " + stepsGoal + " steps!");
            }
        });

        goToWorkoutPlannerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWorkoutPlanner();
            }
        });

        panel.add(stepsLabel);
        panel.add(stepsField);
        panel.add(setGoalButton);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(goToWorkoutPlannerButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showWorkoutPlanner() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel intensityLabel = new JLabel("Intensity Level: ");
        JComboBox<String> intensityComboBox = new JComboBox<>(new String[]{"Low", "Medium", "High"});
        JButton saveWorkoutButton = new JButton("Save Workout");
        JButton goToNutritionTrackerButton = new JButton("Nutrition Tracker");

        saveWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workoutIntensity = (String) intensityComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(frame, "Workout saved!");
            }
        });

        goToNutritionTrackerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNutritionTracker();
            }
        });

        panel.add(intensityLabel);
        panel.add(intensityComboBox);
        panel.add(saveWorkoutButton);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(goToNutritionTrackerButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showNutritionTracker() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel foodLabel = new JLabel("Food Item: ");
        JTextField foodField = new JTextField(10);
        JButton logMealButton = new JButton("Log Meal");
        JButton goToCommunityHubButton = new JButton("Community Hub");

        logMealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mealLogged = foodField.getText();
                JOptionPane.showMessageDialog(frame, "Meal logged!");
            }
        });

        goToCommunityHubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCommunityHub();
            }
        });

        panel.add(foodLabel);
        panel.add(foodField);
        panel.add(logMealButton);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(goToCommunityHubButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showCommunityHub() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JButton viewProfileButton = new JButton("View Profile");
        JButton participateButton = new JButton("Participate in Challenge");
        JButton goToHomeScreenButton = new JButton("Home Screen");

        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUserProfile();
            }
        });

        participateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showChallengeResults();
            }
        });

        goToHomeScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHomeScreen();
            }
        });

        panel.add(viewProfileButton);
        panel.add(participateButton);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(goToHomeScreenButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showChallengeResults() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // Generate random values for demonstration
        Map<String, Integer> userSteps = new HashMap<>();
        Random random = new Random();

        // Adding random users and their steps
        for (int i = 1; i <= 5; i++) {
            String username = "User" + i;
            int steps = random.nextInt(10000);
            userSteps.put(username, steps);
        }

        // Sorting users based on steps in descending order
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(userSteps.entrySet());
        sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Displaying ranked users
        for (int i = 0; i < sortedList.size(); i++) {
            Map.Entry<String, Integer> entry = sortedList.get(i);
            panel.add(new JLabel(entry.getKey() + ": " + entry.getValue() + " steps"));
        }

        // Determine user's rank based on their steps goal
        int userRank = calculateUserRank(sortedList);
        panel.add(new JLabel("Your Steps: " + stepsGoal));
        panel.add(new JLabel("Your Rank: " + userRank));
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());

        JButton goToHomeScreenButton = new JButton("Home Screen");

        goToHomeScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHomeScreen();
            }
        });

        panel.add(goToHomeScreenButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private int calculateUserRank(List<Map.Entry<String, Integer>> sortedList) {
        for (int i = 0; i < sortedList.size(); i++) {
            Map.Entry<String, Integer> entry = sortedList.get(i);
            if (entry.getValue() >= stepsGoal) {
                return i + 1;
            }
        }
        return sortedList.size() + 1; // If not found, assume last rank
    }

    private void showUserProfile() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // Display saved values
        panel.add(new JLabel("Steps Goal: " + stepsGoal));
        panel.add(new JLabel("Meal Logged: " + mealLogged));
        panel.add(new JLabel("Workout Intensity: " + workoutIntensity));
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());

        JButton goToHomeScreenButton = new JButton("Home Screen");

        goToHomeScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHomeScreen();
            }
        });

        panel.add(goToHomeScreenButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new FitnessApp();
            }
        });
    }
}
