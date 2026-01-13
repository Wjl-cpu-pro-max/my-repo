public class StudentManager {
    // 添加学生
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (id, name, age, math_score, english_score) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setInt(3, student.getAge());
            pstmt.setDouble(4, student.getMathScore());
            pstmt.setDouble(5, student.getEnglishScore());
            pstmt.executeUpdate();
            System.out.println("学生信息添加成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据ID查询学生
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("math_score"),
                        rs.getDouble("english_score")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 查询所有学生
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("math_score"),
                        rs.getDouble("english_score")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // 计算平均分
    public Map<String, Double> getAverageScore() {
        Map<String, Double> avgMap = new HashMap<>();
        String sql = "SELECT AVG(math_score) as math_avg, AVG(english_score) as english_avg FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                avgMap.put("数学", rs.getDouble("math_avg"));
                avgMap.put("英语", rs.getDouble("english_avg"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avgMap;
    }
}