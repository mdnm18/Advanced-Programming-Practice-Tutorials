import tkinter as tk
from tkinter import filedialog, messagebox
import os
import time  # Used to simulate a timestamp in a complex rename


class FileRenamerApp:
    """
    Tkinter application for bulk renaming files in a directory with a live preview.
    Focuses on the <KeyRelease> event on the Entry widget.
    """

    def __init__(self, master):
        self.master = master
        master.title("2. Bulk File Renamer")

        # Global state
        self.current_dir = ""
        self.original_files = []  # Stores base file names

        # --- UI Setup ---
        main_frame = tk.Frame(master, padx=10, pady=10)
        main_frame.pack(fill=tk.BOTH, expand=True)

        # 1. Directory Selection and Prefix Input
        dir_frame = tk.Frame(main_frame)
        dir_frame.pack(fill=tk.X, pady=5)

        tk.Label(dir_frame, text="Prefix to Add:", width=15, anchor="w").pack(
            side=tk.LEFT
        )
        self.prefix_entry = tk.Entry(dir_frame, width=40)
        self.prefix_entry.pack(side=tk.LEFT, fill=tk.X, expand=True)

        self.dir_path_label = tk.Label(
            main_frame, text="No folder selected.", fg="blue"
        )
        self.dir_path_label.pack(fill=tk.X, pady=5)

        self.select_button = tk.Button(
            main_frame, text="Select Folder", command=self.select_folder, bg="#BBDEFB"
        )
        self.select_button.pack(fill=tk.X, pady=5)

        # 2. Listboxes for Comparison
        list_frame = tk.Frame(main_frame)
        list_frame.pack(fill=tk.BOTH, expand=True, pady=10)

        tk.Label(list_frame, text="Original Filenames").grid(
            row=0, column=0, padx=5, pady=2, sticky="w"
        )
        tk.Label(list_frame, text="Live Preview (New Names)").grid(
            row=0, column=1, padx=5, pady=2, sticky="w"
        )

        self.file_listbox = tk.Listbox(list_frame, height=15, width=40)
        self.file_listbox.grid(row=1, column=0, padx=5, sticky="nsew")

        self.preview_listbox = tk.Listbox(list_frame, height=15, width=40, bg="#E8F5E9")
        self.preview_listbox.grid(row=1, column=1, padx=5, sticky="nsew")

        list_frame.grid_columnconfigure(0, weight=1)
        list_frame.grid_columnconfigure(1, weight=1)
        list_frame.grid_rowconfigure(1, weight=1)

        # 3. Rename Button
        self.rename_button = tk.Button(
            main_frame,
            text="APPLY RENAME",
            command=self.apply_rename,
            bg="#4CAF50",
            fg="white",
            state=tk.DISABLED,
        )
        self.rename_button.pack(fill=tk.X, pady=10)

        # --- Event Binding ---
        # Crucial event: Update the preview whenever a key is released in the Entry box
        self.prefix_entry.bind("<KeyRelease>", self.update_preview)

    def select_folder(self):
        """Handles the folder selection event."""
        selected_dir = filedialog.askdirectory()
        if selected_dir:
            self.current_dir = selected_dir
            self.dir_path_label.config(text=f"Folder: {self.current_dir}")

            # Clear existing lists
            self.file_listbox.delete(0, tk.END)
            self.original_files.clear()

            # Populate the listbox with file names (excluding directories)
            try:
                for item in os.listdir(self.current_dir):
                    full_path = os.path.join(self.current_dir, item)
                    if os.path.isfile(full_path):
                        self.original_files.append(item)
                        self.file_listbox.insert(tk.END, item)

                # Trigger the initial preview update
                self.update_preview(None)

            except Exception as e:
                messagebox.showerror("Error", f"Could not read directory: {e}")

    def update_preview(self, event):
        """
        Event handler bound to <KeyRelease> on the prefix Entry.
        Generates and displays the new file names instantly.
        """
        prefix = self.prefix_entry.get().strip()
        self.preview_listbox.delete(0, tk.END)

        if not self.original_files:
            self.rename_button.config(state=tk.DISABLED)
            return

        # Rename logic: add prefix + hyphen + original name
        for filename in self.original_files:
            if prefix:
                new_name = f"{prefix}-{filename}"
            else:
                new_name = filename  # If no prefix, name is unchanged

            self.preview_listbox.insert(tk.END, new_name)

        # Enable button if there are files
        self.rename_button.config(state=tk.NORMAL)

    def apply_rename(self):
        """Applies the renaming operation using os.rename."""
        if not self.current_dir or not self.original_files:
            messagebox.showwarning(
                "Warning", "Please select a folder and ensure files are loaded."
            )
            return

        confirmation = messagebox.askyesno(
            "Confirm Rename",
            f"Are you sure you want to rename {len(self.original_files)} files in this directory? This action cannot be undone.",
        )

        if confirmation:
            successful_renames = 0
            # Iterate through both original file list and preview list simultaneously
            for i, original_name in enumerate(self.original_files):
                new_name = self.preview_listbox.get(i)

                old_path = os.path.join(self.current_dir, original_name)
                new_path = os.path.join(self.current_dir, new_name)

                try:
                    os.rename(old_path, new_path)
                    successful_renames += 1
                except Exception as e:
                    print(f"Error renaming {original_name} to {new_name}: {e}")

            # Reload folder to update the original file listbox
            self.select_folder()
            messagebox.showinfo(
                "Success", f"Successfully renamed {successful_renames} files."
            )


if __name__ == "__main__":
    root = tk.Tk()
    app = FileRenamerApp(root)
    root.mainloop()
