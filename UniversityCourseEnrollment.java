import java.util.*;

public class UniversityCourseEnrollment {
    public static void main(String[] args) {
        LinkedList<String> courses = new LinkedList<>(List.of("Math", "Physics", "Computer Science", "English"));

        // Forward traversal
        System.out.println("Forward Traversal:");
        ListIterator<String> iterator = courses.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Backward traversal
        System.out.println("\nBackward Traversal:");
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }

        // Drop a course
        System.out.println("\nDropping a course: Physics");
        iterator = courses.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("Physics")) {
                iterator.remove();
            }
        }

        // Sorting courses alphabetically
        Collections.sort(courses);
        System.out.println("\nSorted Courses:");
        courses.forEach(System.out::println);
    }
}
