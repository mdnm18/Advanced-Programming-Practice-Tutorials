def festival_bonus(salary):
    """Adds a fixed festival bonus of 5000."""
    return salary + 5000


def performance_bonus(salary):
    """Adds a 15% performance bonus."""
    return salary * 1.15


def apply_bonus(func, salary):
    """
    Applies a given bonus function to a salary.

    Args:
      func: A function that calculates the bonus (e.g., festival_bonus).
      salary: The initial salary.

    Returns:
      The updated salary after applying the bonus function.
    """
    return func(salary)


# --- Demonstration ---
initial_salary = 50000
updated_salary = apply_bonus(performance_bonus, initial_salary)

print(f"Initial Salary: ${initial_salary}")  # Initial Salary: $50000
print(
    f"Salary after Performance Bonus: ${updated_salary}"
)  # Salary after Performance Bonus: $57500.0

updated_salary_festival = apply_bonus(festival_bonus, initial_salary)
print(
    f"Salary after Festival Bonus: ${updated_salary_festival}"
)  # Salary after Festival Bonus: $55000
