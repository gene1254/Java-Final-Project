import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n===== 學生成績管理系統 =====");
            System.out.println("1. 新增學生資料");
            System.out.println("2. 顯示所有學生");
            System.out.println("3. 搜尋學生 (學號)");
            System.out.println("4. 刪除學生資料");
            System.out.println("5. 計算班級平均分數");
            System.out.println("6. 離開系統");
            System.out.print("請選擇操作選項 (1-6): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("輸入學號: ");
                    String id = scanner.nextLine();
                    System.out.print("輸入姓名: ");
                    String name = scanner.nextLine();
                    System.out.print("輸入成績: ");
                    double grade;
                    try {
                        grade = Double.parseDouble(scanner.nextLine());
                        if (grade < 0 || grade > 100) {
                            System.out.println("❌ 成績請輸入 0 ~ 100 之間！");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 錯誤：成績必須是數字！");
                        break;
                    }
                    manager.addStudent(new Student(id, name, grade));
                    break;

                case "2":
                    manager.displayAllStudents();
                    break;

                case "3":
                    System.out.print("請輸入要搜尋的學號: ");
                    String searchId = scanner.nextLine();
                    manager.searchStudent(searchId);
                    break;

                case "4":
                    System.out.print("請輸入要刪除的學號: ");
                    String deleteId = scanner.nextLine();
                    manager.deleteStudent(deleteId);
                    break;

                case "5":
                    manager.calculateAverage();
                    break;

                case "6":
                    System.out.println("👋 系統已關閉，資料已安全存檔。再見！");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("❌ 無效的選項，請重新輸入 1 到 6 之間的數字。");
            }
        }
    }
}