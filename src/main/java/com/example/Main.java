import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== 学生管理系统 =====");
            System.out.println("1. 添加学生");
            System.out.println("2. 根据ID查询学生");
            System.out.println("3. 显示所有学生");
            System.out.println("4. 查看各科平均分");
            System.out.println("5. 退出");
            System.out.print("请选择操作：");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("输入学生ID：");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("输入学生姓名：");
                    String name = scanner.nextLine();
                    System.out.print("输入学生年龄：");
                    int age = scanner.nextInt();
                    System.out.print("输入数学成绩：");
                    double math = scanner.nextDouble();
                    System.out.print("输入英语成绩：");
                    double english = scanner.nextDouble();
                    manager.addStudent(new Student(id, name, age, math, english));
                    break;
                case 2:
                    System.out.print("输入要查询的学生ID：");
                    int queryId = scanner.nextInt();
                    Student student = manager.getStudentById(queryId);
                    if (student != null) {
                        System.out.println("学生信息：ID=" + student.getId() + "，姓名=" + student.getName() + "，年龄=" + student.getAge() + "，数学=" + student.getMathScore() + "，英语=" + student.getEnglishScore());
                    } else {
                        System.out.println("未找到该学生！");
                    }
                    break;
                case 3:
                    manager.getAllStudents().forEach(s -> System.out.println("ID=" + s.getId() + "，姓名=" + s.getName() + "，年龄=" + s.getAge() + "，数学=" + s.getMathScore() + "，英语=" + s.getEnglishScore()));
                    break;
                case 4:
                    Map<String, Double> avgMap = manager.getAverageScore();
                    System.out.println("数学平均分：" + avgMap.get("数学") + "，英语平均分：" + avgMap.get("英语"));
                    break;
                case 5:
                    System.out.println("系统退出！");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效选项，请重新选择！");
            }
        }
    }
}