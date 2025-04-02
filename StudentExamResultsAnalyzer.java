// 3. Student Exam Results Analyzer
import java.util.*;

class Student {
    private int id;
    private String name;
    
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}

class ExamResult {
    private Student student;
    private String subject;
    private int score;
    private String grade;
    
    public ExamResult(Student student, String subject, int score) {
        this.student = student;
        this.subject = subject;
        this.score = score;
        this.grade = calculateGrade(score);
    }
    
    private String calculateGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }
    
    public Student getStudent() { return student; }
    public String getSubject() { return subject; }
    public int getScore() { return score; }
    public String getGrade() { return grade; }
    
    @Override
    public String toString() {
        return "ExamResult{" +
                "student=" + student +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                ", grade='" + grade + '\'' +
                '}';
    }
}

public class StudentExamResultsAnalyzer {
    // Map of student ID to student object
    private Map<Integer, Student> students;
    
    // Map of subject to list of exam results
    private Map<String, List<ExamResult>> subjectResults;
    
    // Map of student ID to map of subject to exam result
    private Map<Integer, Map<String, ExamResult>> studentResults;
    
    public StudentExamResultsAnalyzer() {
        students = new HashMap<>();
        subjectResults = new HashMap<>();
        studentResults = new HashMap<>();
    }
    
    public void addStudent(Student student) {
        students.put(student.getId(), student);
        studentResults.put(student.getId(), new HashMap<>());
    }
    
    public void addExamResult(ExamResult result) {
        Student student = result.getStudent();
        String subject = result.getSubject();
        
        // Update subject results
        List<ExamResult> results = subjectResults.getOrDefault(subject, new ArrayList<>());
        results.add(result);
        subjectResults.put(subject, results);
        
        // Update student results
        Map<String, ExamResult> studentSubjects = studentResults.get(student.getId());
        if (studentSubjects == null) {
            studentSubjects = new HashMap<>();
            studentResults.put(student.getId(), studentSubjects);
        }
        studentSubjects.put(subject, result);
    }
    
    // Get average score for a subject
    public double getAverageScoreForSubject(String subject) {
        List<ExamResult> results = subjectResults.get(subject);
        if (results == null || results.isEmpty()) {
            return 0.0;
        }
        
        int total = 0;
        for (ExamResult result : results) {
            total += result.getScore();
        }
        
        return (double) total / results.size();
    }
    
    // Get top performer for a subject
    public Student getTopPerformerForSubject(String subject) {
        List<ExamResult> results = subjectResults.get(subject);
        if (results == null || results.isEmpty()) {
            return null;
        }
        
        ExamResult top = Collections.max(results, Comparator.comparing(ExamResult::getScore));
        return top.getStudent();
    }
    
    // Get average score for a student across all subjects
    public double getAverageScoreForStudent(int studentId) {
        Map<String, ExamResult> studentSubjects = studentResults.get(studentId);
        if (studentSubjects == null || studentSubjects.isEmpty()) {
            return 0.0;
        }
        
        int total = 0;
        for (ExamResult result : studentSubjects.values()) {
            total += result.getScore();
        }
        
        return (double) total / studentSubjects.size();
    }
    
    // Get students sorted by overall average
    public List<Map.Entry<Student, Double>> getStudentsByOverallAverage() {
        Map<Student, Double> averages = new HashMap<>();
        
        for (Integer studentId : studentResults.keySet()) {
            Student student = students.get(studentId);
            double average = getAverageScoreForStudent(studentId);
            averages.put(student, average);
        }
        
        List<Map.Entry<Student, Double>> sortedEntries = new ArrayList<>(averages.entrySet());
        sortedEntries.sort(Map.Entry.<Student, Double>comparingByValue().reversed());
        
        return sortedEntries;
    }
    
    // Get grade distribution for a subject
    public Map<String, Integer> getGradeDistributionForSubject(String subject) {
        List<ExamResult> results = subjectResults.get(subject);
        if (results == null) {
            return new HashMap<>();
        }
        
        Map<String, Integer> distribution = new TreeMap<>();
        for (ExamResult result : results) {
            String grade = result.getGrade();
            distribution.put(grade, distribution.getOrDefault(grade, 0) + 1);
        }
        
        return distribution;
    }
    
    // Get subjects sorted by average score
    public List<Map.Entry<String, Double>> getSubjectsByAverageScore() {
        Map<String, Double> averages = new HashMap<>();
        
        for (String subject : subjectResults.keySet()) {
            double average = getAverageScoreForSubject(subject);
            averages.put(subject, average);
        }
        
        List<Map.Entry<String, Double>> sortedEntries = new ArrayList<>(averages.entrySet());
        sortedEntries.sort(Map.Entry.<String, Double>comparingByValue().reversed());
        
        return sortedEntries;
    }
    
    // Main method for demonstration
    public static void main(String[] args) {
        StudentExamResultsAnalyzer analyzer = new StudentExamResultsAnalyzer();
        
        // Add students
        Student s1 = new Student(1, "Alice");
        Student s2 = new Student(2, "Bob");
        Student s3 = new Student(3, "Charlie");
        Student s4 = new Student(4, "Diana");
        
        analyzer.addStudent(s1);
        analyzer.addStudent(s2);
        analyzer.addStudent(s3);
        analyzer.addStudent(s4);
        
        // Add exam results
        analyzer.addExamResult(new ExamResult(s1, "Math", 95));
        analyzer.addExamResult(new ExamResult(s1, "Physics", 88));
        analyzer.addExamResult(new ExamResult(s1, "Chemistry", 75));
        
        analyzer.addExamResult(new ExamResult(s2, "Math", 82));
        analyzer.addExamResult(new ExamResult(s2, "Physics", 79));
        analyzer.addExamResult(new ExamResult(s2, "Chemistry", 85));
        
        analyzer.addExamResult(new ExamResult(s3, "Math", 78));
        analyzer.addExamResult(new ExamResult(s3, "Physics", 92));
        analyzer.addExamResult(new ExamResult(s3, "Chemistry", 65));
        
        analyzer.addExamResult(new ExamResult(s4, "Math", 90));
        analyzer.addExamResult(new ExamResult(s4, "Physics", 68));
        analyzer.addExamResult(new ExamResult(s4, "Chemistry", 72));
        
        // Analysis demonstration
        System.out.println("Average score for Math: " + analyzer.getAverageScoreForSubject("Math"));
        
        Student topMath = analyzer.getTopPerformerForSubject("Math");
        System.out.println("Top performer in Math: " + topMath.getName());
        
        System.out.println("\nGrade distribution for Physics:");
        Map<String, Integer> physicsGrades = analyzer.getGradeDistributionForSubject("Physics");
        for (Map.Entry<String, Integer> entry : physicsGrades.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("\nStudents by overall average:");
        List<Map.Entry<Student, Double>> studentsByAvg = analyzer.getStudentsByOverallAverage();
        for (Map.Entry<Student, Double> entry : studentsByAvg) {
            System.out.println(entry.getKey().getName() + ": " + String.format("%.2f", entry.getValue()));
        }
        
        System.out.println("\nSubjects by average score:");
        List<Map.Entry<String, Double>> subjectsByAvg = analyzer.getSubjectsByAverageScore();
        for (Map.Entry<String, Double> entry : subjectsByAvg) {
            System.out.println(entry.getKey() + ": " + String.format("%.2f", entry.getValue()));
        }
    }
}
