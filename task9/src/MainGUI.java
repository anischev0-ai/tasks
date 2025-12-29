import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainGUI extends JFrame {
    private JTable table;
    private JFileChooser fileChooser = new JFileChooser(".");

    public MainGUI() {
        setTitle("Переворот списков");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        table = new JTable(new DefaultTableModel(5, 5));
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnLoad = new JButton("Загрузить из файла");
        JButton btnSave = new JButton("Сохранить в файл");
        JButton btnProcess = new JButton("Перевернуть списки");

        JPanel panel = new JPanel();
        panel.add(btnLoad);
        panel.add(btnProcess);
        panel.add(btnSave);

        btnLoad.addActionListener(e -> {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    List<List<Integer>> data = ListUtils.readListsFromFile(fileChooser.getSelectedFile().getPath());
                    displayListsInTable(data);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка при чтении: " + ex.getMessage());
                }
            }
        });

        btnSave.addActionListener(e -> {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    List<List<Integer>> data = getListsFromTable();
                    ListUtils.writeListsToFile(fileChooser.getSelectedFile().getPath(), data);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка при сохранении: " + ex.getMessage());
                }
            }
        });

        btnProcess.addActionListener(e -> {
            try {
                List<List<Integer>> data = getListsFromTable();
                for (List<Integer> list : data) {
                    ListUtils.process(list);
                }
                displayListsInTable(data);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ошибка при обработке: " + ex.getMessage());
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    private void displayListsInTable(List<List<Integer>> lists) {
        if (lists == null || lists.isEmpty()) {
            table.setModel(new DefaultTableModel(0, 0));
            return;
        }

        int maxCols = 0;
        for (List<Integer> list : lists) {
            if (list.size() > maxCols) maxCols = list.size();
        }

        DefaultTableModel model = new DefaultTableModel(0, maxCols);
        for (List<Integer> list : lists) {
            Object[] row = new Object[maxCols];
            for (int i = 0; i < list.size(); i++) {
                row[i] = ListUtils.getElement(list, i);
            }
            model.addRow(row);
        }
        table.setModel(model);
    }

    private List<List<Integer>> getListsFromTable() {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < table.getRowCount(); i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < table.getColumnCount(); j++) {
                Object val = table.getValueAt(i, j);
                if (val != null && !val.toString().trim().isEmpty()) {
                    try {
                        row.add(Integer.parseInt(val.toString().trim()));
                    } catch (NumberFormatException e) {
                    }
                }
            }
            if (!row.isEmpty()) {
                result.add(row);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new MainGUI().setVisible(true);
    }
}
