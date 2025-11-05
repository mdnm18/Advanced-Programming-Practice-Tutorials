import tkinter as tk
from tkinter import messagebox


class ScatterPlotApp:
    """
    Tkinter application for generating a scatter plot by mouse clicks.
    Focuses on the <Button-1> event handler.
    """

    def __init__(self, master):
        self.master = master
        master.title("1. Scatter Plot Generator")

        # Data storage list for (x, y) points
        self.data_points = []

        # --- UI Setup ---

        # Frame for controls
        self.control_frame = tk.Frame(master, padx=10, pady=10)
        self.control_frame.pack(side=tk.BOTTOM, fill=tk.X)

        tk.Label(
            self.control_frame,
            text="Click on the canvas to add data points (x, y).",
            font=("Arial", 10),
        ).pack(side=tk.LEFT)

        # Button to clear the data and canvas
        self.clear_button = tk.Button(
            self.control_frame,
            text="Clear Plot & Data",
            command=self.clear_plot,
            bg="#FFCDD2",
        )
        self.clear_button.pack(side=tk.RIGHT, padx=10)

        # Canvas for drawing the scatter plot
        self.canvas = tk.Canvas(
            master, width=500, height=400, bg="white", borderwidth=2, relief=tk.SUNKEN
        )
        self.canvas.pack(padx=10, pady=10)

        # --- Event Binding ---
        # Bind the left mouse button click event (<Button-1>) to the canvas
        self.canvas.bind("<Button-1>", self.add_point)

    def add_point(self, event):
        """
        Event handler for the mouse click (<Button-1>).
        Extracts coordinates, draws a point, and stores the data.
        """
        x, y = event.x, event.y
        self.data_points.append((x, y))

        # 1. Draw a small red circle (oval) at the click coordinates
        # We create a 6x6 pixel point centered at (x, y)
        point_size = 3
        self.canvas.create_oval(
            x - point_size,
            y - point_size,
            x + point_size,
            y + point_size,
            fill="red",
            outline="darkred",
            tags="point",
        )

        # 2. Log the data point to the console (for verification)
        print(f"Added point: ({x}, {y}). Total points: {len(self.data_points)}")

    def clear_plot(self):
        """
        Clears all points from the canvas and resets the data list.
        """
        # Delete all items tagged "point" on the canvas
        self.canvas.delete("point")
        self.data_points = []
        print("Plot cleared. Data list reset.")


if __name__ == "__main__":
    root = tk.Tk()
    app = ScatterPlotApp(root)
    root.mainloop()
