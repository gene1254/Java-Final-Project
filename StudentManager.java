import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students;
    private final String FILE_NAME = "students.txt";

    public StudentManager() {
        students = new ArrayList<>();
        loadFromFile(); // 程式啟動時自動載入資料
    }

    // 新增學生
    public void addStudent(Student student) {
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                System.out.println("❌ 錯誤：學號 " + student.getId() + " 已存在！");
                return;
            }
        }
        students.add(student);
        System.out.println("✅ 學生資料新增成功！");
        saveToFile();
    }

    // 顯示所有學生
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("📭 目前沒有任何學生資料。");
            return;
        }
        System.out.println("\n--- 學生列表 ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // 根據學號查詢學生
    public void searchStudent(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                System.out.println("🔍 找到資料：" + s);
                return;
            }
        }
        System.out.println("❌ 找不到學號為 " + id + " 的學生。");
    }

    // 刪除學生
    public void deleteStudent(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                students.remove(s);
                System.out.println("🗑️ 成功刪除學生：" + s.getName());
                saveToFile();
                return;
            }
        }
        System.out.println("❌ 刪除失敗：找不到該學號。");
    }

    // 計算班級平均分數
    public void calculateAverage() {
        if (students.isEmpty()) {
            System.out.println("📊 目前無資料可計算平均分數。");
            return;
        }
        double sum = 0;
        for (Student s : students) {
            sum += s.getGrade();
        }
        System.out.printf("📊 班級總人數: %d 人 | 平均分數: %.2f 分\n", students.size(), (sum / students.size()));
    }

    // 儲存資料到文字檔 (CSV 格式)
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.write(s.getId() + "," + s.getName() + "," + s.getGrade());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ 儲存檔案時發生錯誤: " + e.getMessage());
        }
    }

    // 從文字檔載入資料
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String id = data[0];
                    String name = data[1];
                    double grade = Double.parseDouble(data[2]);
                    students.add(new Student(id, name, grade));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("⚠️ 載入檔案時發生錯誤，將啟用全新資料庫。");
        }
    }
}