import tkinter as tk
from tkinter import messagebox


class SnippetManagerApp:
    """
    Tkinter application for managing and viewing code snippets.
    Focuses on <<ListboxSelect>> and <Control-s> event handlers.
    """

    def __init__(self, master):
        self.master = master
        master.title("3. Interactive Code Snippet Manager")

        # --- Data Storage ---
        self.snippets = {
            "Python List Comp": "result = [item * 2 for item in range(10) if item % 2 == 0]",
            "Tkinter Hello": "root = tk.Tk()\nlabel = tk.Label(root, text='Hello')\nlabel.pack()",
            "Event Binding Example": "widget.bind('<Button-1>', handle_click)",
            "Simple Loop": "for i in range(5):\n    print(f'Count: {i}')",
        }

        # --- UI Setup ---
        main_frame = tk.Frame(master, padx=10, pady=10)
        main_frame.pack(fill=tk.BOTH, expand=True)

        # 1. Snippet List (Left Panel)
        list_frame = tk.Frame(main_frame)
        list_frame.pack(side=tk.LEFT, fill=tk.Y, padx=(0, 10))

        tk.Label(list_frame, text="Snippets", font=("Arial", 10, "bold")).pack(
            pady=(0, 5)
        )

        self.listbox = tk.Listbox(list_frame, height=20, width=25, selectmode=tk.SINGLE)
        self.listbox.pack(fill=tk.Y, expand=True)

        # Populate Listbox
        self.update_listbox()

        # 2. Code Content Display (Right Panel)
        content_frame = tk.Frame(main_frame)
        content_frame.pack(side=tk.RIGHT, fill=tk.BOTH, expand=True)

        tk.Label(content_frame, text="Code Content", font=("Arial", 10, "bold")).pack(
            anchor="w", pady=(0, 5)
        )

        self.text_widget = tk.Text(
            content_frame,
            wrap=tk.WORD,
            height=20,
            width=60,
            font=("Consolas", 10),
            bg="#F5F5F5",
        )
        self.text_widget.pack(fill=tk.BOTH, expand=True)

        # 3. Control Buttons
        button_frame = tk.Frame(content_frame, pady=5)
        button_frame.pack(fill=tk.X)

        tk.Button(
            button_frame, text="Save Snippet", command=self.manual_save, bg="#A5D6A7"
        ).pack(side=tk.RIGHT)
        tk.Button(
            button_frame, text="New Snippet", command=self.new_snippet, bg="#BBDEFB"
        ).pack(side=tk.RIGHT, padx=5)

        # --- Event Bindings ---
        self.listbox.bind("<<ListboxSelect>>", self.load_snippet)
        master.bind("<Control-s>", self.save_snippet_event)
        master.bind("<Command-s>", self.save_snippet_event)  # For Mac users

    def update_listbox(self):
        self.listbox.delete(0, tk.END)
        for title in self.snippets.keys():
            self.listbox.insert(tk.END, title)

    def load_snippet(self, event):
        selection = self.listbox.curselection()
        if selection:
            title = self.listbox.get(selection[0])
            content = self.snippets.get(title, "Error: Content not found.")
            self.text_widget.delete("1.0", tk.END)
            self.text_widget.insert("1.0", content)

    def new_snippet(self):
        self.listbox.selection_clear(0, tk.END)
        self.text_widget.delete("1.0", tk.END)
        messagebox.showinfo(
            "New Snippet", "Enter your code, then click 'Save Snippet' or press Ctrl+S."
        )

    def save_snippet_event(self, event):
        self.manual_save()
        return "break"

    def manual_save(self):
        content = self.text_widget.get("1.0", tk.END).strip()
        if not content:
            messagebox.showwarning("Warning", "The code content is empty.")
            return

        title_window = tk.Toplevel(self.master)
        title_window.title("Save As")
        title_window.geometry("300x100")
        tk.Label(title_window, text="Enter Snippet Title:").pack(pady=5)

        entry = tk.Entry(title_window)
        entry.pack(pady=5, padx=10, fill=tk.X)
        entry.focus_set()

        def save_and_close():
            title = entry.get().strip()
            if title:
                self.snippets[title] = content
                self.update_listbox()
                title_window.destroy()
                messagebox.showinfo("Success", f"Snippet '{title}' saved successfully.")
            else:
                messagebox.showerror("Error", "Title cannot be empty.")

        tk.Button(
            title_window, text="Save", command=save_and_close, bg="#4CAF50", fg="white"
        ).pack(pady=5)


if __name__ == "__main__":
    root = tk.Tk()
    app = SnippetManagerApp(root)
    root.mainloop()
