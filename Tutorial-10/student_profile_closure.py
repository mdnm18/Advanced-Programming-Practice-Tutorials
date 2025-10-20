def student_profile(name):
    """
    Creates a profile for a student that can track marks.

    Args:
      name: The name of the student.

    Returns:
      A nested function 'add_marks' that can be used to add marks to this profile.
    """
    print(f"Profile created for {name}.")
    marks = {}  # This dictionary is in the enclosing scope

    def add_marks(subject, mark):
        """
        Adds a mark for a subject and prints the updated average.
        This is the nested function.
        """
        marks[subject] = mark
        average = sum(marks.values()) / len(marks)
        print(
            f"  -> Added {subject}: {mark}. Current Marks: {marks}. New Average: {average:.2f}"
        )

    return add_marks


# --- Demonstration ---
john_profile = student_profile("John")

# 'john_profile' is now the 'add_marks' function with its own 'marks' dictionary
john_profile("Math", 95)
john_profile("Science", 88)
john_profile("History", 92)

# Create a separate profile for another student
jane_profile = student_profile("Jane")
jane_profile("Math", 98)
jane_profile("Art", 85)
