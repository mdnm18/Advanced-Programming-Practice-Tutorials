# --- Operation Functions ---
def add(a, b):
    return a + b


def sub(a, b):
    return a - b


def mul(a, b):
    return a * b


def div(a, b):
    if b == 0:
        return "Error: Division by zero"
    return a / b


# --- Higher-Order Function ---
def operate(func, a, b):
    """
    Applies a given math function to two numbers.

    Args:
      func: The math function to apply (e.g., add, sub).
      a: The first number.
      b: The second number.

    Returns:
      The result of the operation.
    """
    return func(a, b)


# --- Demonstration ---
print(f"Addition: {operate(add, 10, 5)}")  # Addition: 15
print(f"Subtraction: {operate(sub, 10, 5)}")  # Subtraction: 5
print(f"Multiplication: {operate(mul, 10, 5)}")  # Multiplication: 50


# --- Adding a new operation without changing 'operate' ---
def power(a, b):
    return a**b


print(f"Power: {operate(power, 10, 2)}")  # Power: 100
