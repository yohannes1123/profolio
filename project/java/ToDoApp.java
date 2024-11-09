package todoapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoApp extends JFrame {

    private JTextField taskField;
    private JButton addButton;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JButton completeButton;

    public ToDoApp() {
        // Setting up the main frame
        setTitle("To-Do List App");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for adding tasks
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        taskField = new JTextField(20);
        addButton = new JButton("Add Task");

        topPanel.add(new JLabel("New Task:"));
        topPanel.add(taskField);
        topPanel.add(addButton);

        // Center panel for displaying tasks
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane taskScrollPane = new JScrollPane(taskList);

        // Bottom panel for marking tasks as completed
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        completeButton = new JButton("Mark as Complete");

        bottomPanel.add(completeButton);

        // Adding panels to the main frame
        add(topPanel, BorderLayout.NORTH);
        add(taskScrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Adding Action Listeners
        addButton.addActionListener(new AddTaskListener());
        completeButton.addActionListener(new CompleteTaskListener());

        setVisible(true);
    }

    // Action listener for adding tasks
    private class AddTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String task = taskField.getText();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(ToDoApp.this, "Task cannot be empty!");
            }
        }
    }

    // Action listener for marking tasks as completed
    private class CompleteTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                String completedTask = taskListModel.getElementAt(selectedIndex) + " (Completed)";
                taskListModel.setElementAt(completedTask, selectedIndex);
            } else {
                JOptionPane.showMessageDialog(ToDoApp.this, "Please select a task to complete!");
            }
        }
    }

    public static void main(String[] args) {
        new ToDoApp();
    }
}
