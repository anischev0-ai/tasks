import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import javax.swing.*;

public class MainGUI extends JFrame {
    private JTable table;
    private JFileChooser fileChooser = new JFileChooser(".");

    public MainGUI() {
        setTitle("Column Swapper");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        table = new JTable(new DefaultTableModel(10, 10)); // Начальный пустой размер
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnLoad = new JButton("Загрузить из файла");
        JButton btnSave = new JButton("Сохранить в файл");
        JButton btnProcess = new JButton("Выполнить");

        JPanel panel = new JPanel();
        panel.add(btnLoad);
        panel.add(btnProcess);
        panel.add(btnSave);

        btnLoad.addActionListener(e -> {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    int[][] array = ArrayUtils.readIntArrayFromFile(fileChooser.getSelectedFile().getPath());
                    displayArrayInTable(array);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
                }
            }
        });

        btnSave.addActionListener(e -> {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    int[][] array = readArrayFromTable();
                    ArrayUtils.writeIntArrayToFile(fileChooser.getSelectedFile().getPath(), array);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
                }
            }
        });

        btnProcess.addActionListener(e -> {
            try {
                int[][] array = readArrayFromTable();
                ArrayUtils.swapColumns(array);
                displayArrayInTable(array);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    private void displayArrayInTable(int[][] array) {
        String[] columns = new String[array[0].length];
        for (int i = 0; i < columns.length; i++) columns[i] = String.valueOf(i + 1);

        DefaultTableModel model = new DefaultTableModel(0, array[0].length);
        for (int[] row : array) {
            Object[] rowObj = new Object[row.length];
            for (int i = 0; i < row.length; i++) rowObj[i] = row[i];
            model.addRow(rowObj);
        }
        table.setModel(model);
    }

    private int[][] readArrayFromTable() {
        int rows = table.getRowCount();
        int cols = table.getColumnCount();
        
        java.util.List<int[]> list = new java.util.ArrayList<>();
        
        for (int i = 0; i < rows; i++) {
            boolean rowIsEmpty = true;
            java.util.List<Integer> rowData = new java.util.ArrayList<>();
            
            for (int j = 0; j < cols; j++) {
                Object val = table.getValueAt(i, j);
                if (val != null && !val.toString().trim().isEmpty()) {
                    try {
                        rowData.add(Integer.parseInt(val.toString().trim()));
                        rowIsEmpty = false;
                    } catch (NumberFormatException e) {
                    }
                }
            }
            
            if (!rowIsEmpty) {
                int[] row = new int[rowData.size()];
                for (int k = 0; k < rowData.size(); k++) row[k] = rowData.get(k);
                list.add(row);
            }
        }

        int[][] array = list.toArray(new int[0][]);
        
        return array;
    }

    public static void main(String[] args) {
        new MainGUI().setVisible(true);
    }

}
