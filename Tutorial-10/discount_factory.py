def discount_calculator(rate):
    """
    A function factory that creates a discount-applying function.

    Args:
      rate: The discount rate (e.g., 0.1 for 10%).

    Returns:
      A function that takes a price and returns the price after the discount.
    """

    def apply_discount(price):
        """Calculates the final price after applying the remembered discount rate."""
        return price * (1 - rate)

    return apply_discount


# --- Demonstration ---
# Step 1: Create a specific 10% discount function
ten_percent_discount = discount_calculator(0.1)

# Step 2: Create a specific 25% discount function
twenty_five_percent_discount = discount_calculator(0.25)

# Step 3: Use the created functions
price = 2000
discounted_price_10 = ten_percent_discount(price)
print(
    f"Price: ${price}, after 10% discount: ${discounted_price_10}"
)  # Price: $2000, after 10% discount: $1800.0

discounted_price_25 = twenty_five_percent_discount(price)
print(
    f"Price: ${price}, after 25% discount: ${discounted_price_25}"
)  # Price: $2000, after 25% discount: $1500.0
