import tkinter as tk 
from tkinter import font 


class TicTacToeBoard(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Tic-Tac-Toe Game")
        self.cells ={}


    def _create_board_display(self):
        #game's main window will be the frame's parent.
        display_frame = tk.Frame(master=self)
        # place the frame object on the main window's top border.
        display_frame.pack(fill= tk.X)
        self.display= tk.Label(
            master=display_frame, 
            text= "Ready", 
            font= font.FOnt(size=28, weight="bold"),
        )
        self.display.pack()

    def _create_board_grid(self):
        grid_frame= tk.Frame(master=self)
        grid_frame.pack()
        for row in range(3):
            self.rowconfigure(row, weight=1, minsize=50)
            self.columnconfigure(row, weight=1, minsize=75)
            for col in range(3):
                button = tk.Button(
                    master=grid_frame, 
                    text ="", 
                    font= font.Font(size=36, weight="bold"),
                    fg="black", 
                    width=3,
                    height=2,
                    highlightbackground="lightblue"
                )
                self.cells[button] =(row, col)
                button.grid(
                    row=row, 
                    column=col,
                    padx=5
                )
