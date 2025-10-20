def filter_students(criteria, students):
    """
    Filters a list of students based on a criteria function.

    Args:
      criteria: A function that returns True if a student meets the criteria.
      students: A list of student dictionaries.

    Returns:
      A new list containing only the students who match the criteria.
    """
    return [student for student in students if criteria(student)]


# --- Demonstration ---
students = [
    {"name": "Alice", "cgpa": 8.5},
    {"name": "Bob", "cgpa": 7.9},
    {"name": "Charlie", "cgpa": 9.1},
    {"name": "David", "cgpa": 8.9},
]

# Use a lambda function as the criteria to find students with CGPA > 8
high_achievers = filter_students(lambda s: s["cgpa"] > 8, students)

print("Students with CGPA > 8:")
for student in high_achievers:
    print(student)

# Expected Output:
# Students with CGPA > 8:
# {'name': 'Alice', 'cgpa': 8.5}
# {'name': 'Charlie', 'cgpa': 9.1}
# {'name': 'David', 'cgpa': 8.9}
