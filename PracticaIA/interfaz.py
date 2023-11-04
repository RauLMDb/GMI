from tkinter import *
from paradas import paradas
import customtkinter
from tkintermapview import *
import src.main as mn
from src.utils.data import getNodes, getAristas
from PIL import Image, ImageTk
import os
from tkintermapview.canvas_button import CanvasButton

customtkinter.set_default_color_theme("blue")


class App(customtkinter.CTk):
    APP_NAME = "INTERFAZ METRO ATENAS"
    WIDTH = 1000
    HEIGHT = 525

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)

        self.title(App.APP_NAME)
        self.geometry(str(App.WIDTH) + "x" + str(App.HEIGHT))
        self.minsize(App.WIDTH, App.HEIGHT)

        self.protocol("WM_DELETE_WINDOW", self.on_closing)
        self.bind("<Command-Escape>", self.on_closing)
        self.createcommand("tk::mac::Quit", self.on_closing)

        def contains(l: list, n: any):
            res = FALSE
            for i in l:
                if i is n:
                    res = TRUE
                    break
                else:
                    continue
            return res

        # ---------Variables auxiliares-----------
        self.origin = StringVar()
        self.origin.set("")
        self.destiny = StringVar()
        self.destiny.set("")
        self.distancia = StringVar()
        self.distancia.set("")
        self.path = None
        self.par = 0
        self.origen = None
        self.destino = None
        self.recorrido2 = None
        self.recorrido3 = None
        # ============ create two CTkFrames ============

        self.grid_columnconfigure(0, weight=0)
        self.grid_columnconfigure(1, weight=1)
        self.grid_rowconfigure(0, weight=1)

        self.frame_left = customtkinter.CTkFrame(
            master=self, width=150, corner_radius=0, fg_color=None
        )
        self.frame_left.grid(row=0, column=0, padx=0, pady=0, sticky="nsew")

        self.frame_right = customtkinter.CTkFrame(master=self, corner_radius=0)
        self.frame_right.grid(row=0, column=1, rowspan=1, pady=0, padx=0, sticky="nsew")

        # ============ frame_right ============

        self.frame_right.grid_rowconfigure(1, weight=1)
        self.frame_right.grid_rowconfigure(0, weight=0)
        self.frame_right.grid_columnconfigure(0, weight=1)
        self.frame_right.grid_columnconfigure(1, weight=0)
        self.frame_right.grid_columnconfigure(2, weight=1)

        self.map_widget = TkinterMapView(self.frame_right, corner_radius=0)
        self.map_widget.grid(
            row=1,
            rowspan=1,
            column=0,
            columnspan=3,
            sticky="nswe",
            padx=(0, 0),
            pady=(0, 0),
        )
        self.map_widget.set_tile_server(
            "https://mt0.google.com/vt/lyrs=m&hl=en&x={x}&y={y}&z={z}&s=Ga", max_zoom=15
        )

        self.home = CanvasButton(
            self.map_widget,
            (20, 100),
            text="H",
            command=lambda: self.map_widget.set_address("Piraeus"),
        )
        # inicializamos las imagenes
        current_path = os.path.join(os.path.dirname(os.path.abspath(__file__)))
        self.red_circle = ImageTk.PhotoImage(
            Image.open(os.path.join(current_path, "images", "red_circle.png")).resize(
                (15, 15)
            )
        )
        self.blue_circle = ImageTk.PhotoImage(
            Image.open(os.path.join(current_path, "images", "blue_circle.png")).resize(
                (15, 15)
            )
        )
        self.green_circle = ImageTk.PhotoImage(
            Image.open(os.path.join(current_path, "images", "green_circle.png")).resize(
                (15, 15)
            )
        )
        # inicilizamos las lineas de metro
        self.marker_list = []
        self.estaciones = []
        self.linea_1 = []
        self.linea_1_pos = []
        self.linea_2 = []
        self.linea_2_pos = []
        self.linea_3 = []
        self.linea_3_pos = []
        for n in paradas:
            linea = n.get("linea")
            nombre = n.get("nombre")
            if contains(linea, 1):
                self.linea_1.append(
                    self.map_widget.set_marker(
                        n.get("coords").get("x"),
                        n.get("coords").get("y"),
                        text=nombre,
                        icon=self.green_circle,
                        text_color="green",
                        font="Arial 7 bold",
                        command=self.selected_marker,
                    )
                )
                self.linea_1_pos.append(
                    (n.get("coords").get("x"), n.get("coords").get("y"))
                )
                self.marker_list.append(self.linea_1[len(self.linea_1) - 1])
            if contains(linea, 2):
                self.linea_2.append(
                    self.map_widget.set_marker(
                        n.get("coords").get("x"),
                        n.get("coords").get("y"),
                        text=nombre,
                        icon=self.red_circle,
                        text_color="red",
                        font="Arial 7 bold",
                        command=self.selected_marker,
                    )
                )
                self.linea_2_pos.append(
                    (n.get("coords").get("x"), n.get("coords").get("y"))
                )
                self.marker_list.append(self.linea_2[len(self.linea_2) - 1])
            if contains(linea, 3):
                self.linea_3.append(
                    self.map_widget.set_marker(
                        n.get("coords").get("x"),
                        n.get("coords").get("y"),
                        text=nombre,
                        icon=self.blue_circle,
                        text_color="blue",
                        font="Arial 7 bold",
                        command=self.selected_marker,
                    )
                )
                self.linea_3_pos.append(
                    (n.get("coords").get("x"), n.get("coords").get("y"))
                )
                self.marker_list.append(self.linea_3[len(self.linea_3) - 1])
            if not contains(self.estaciones, nombre):
                self.estaciones.append(nombre)

        linea_1 = self.map_widget.set_path(self.linea_1_pos, color="green", width="5")
        linea_2 = self.map_widget.set_path(self.linea_2_pos, color="red", width="5")
        linea_3 = self.map_widget.set_path(self.linea_3_pos, color="blue", width="5")
        global origen_option_menu
        global destino_option_menu
        keys = [
            "q",
            "w",
            "e",
            "r",
            "t",
            "y",
            "u",
            "i",
            "o",
            "p",
            "a",
            "s",
            "d",
            "f",
            "g",
            "h",
            "j",
            "k",
            "l",
            "z",
            "x",
            "c",
            "v",
            "b",
            "n",
            "m",
            "Q",
            "W",
            "E",
            "R",
            "T",
            "Y",
            "U",
            "I",
            "O",
            "P",
            "A",
            "S",
            "D",
            "F",
            "G",
            "H",
            "J",
            "K",
            "L",
            "Z",
            "X",
            "C",
            "V",
            "B",
            "N",
            "M",
        ]

        # ============ frame_left ============

        self.EntryOrigen = customtkinter.CTkEntry(
            master=self.frame_left, placeholder_text="type origin"
        )
        self.EntryOrigen.grid(row=0, column=0, sticky="we", padx=(20, 20), pady=(20, 0))

        origen_option_menu = customtkinter.CTkOptionMenu(
            self.frame_left, values=self.estaciones, command=self.select_origen
        )
        origen_option_menu.grid(row=1, column=0, padx=(20, 20), pady=(10, 0))

        self.EntryDestino = customtkinter.CTkEntry(
            master=self.frame_left, placeholder_text="type destiny"
        )
        self.EntryDestino.grid(
            row=2, column=0, sticky="we", padx=(20, 20), pady=(20, 0)
        )

        destino_option_menu = customtkinter.CTkOptionMenu(
            self.frame_left, values=self.estaciones, command=self.select_destino
        )
        destino_option_menu.grid(row=3, column=0, padx=(20, 20), pady=(10, 0))

        for c in keys:
            self.EntryOrigen.entry.bind(c, self.search_origen)
            self.EntryOrigen.entry.bind(f"<Command -{c}>", self.search_origen)
            self.EntryDestino.entry.bind(f"<Command -{c}>", self.search_destino)
            self.EntryDestino.entry.bind(c, self.search_destino)
        self.EntryDestino.entry.bind("<Return>", self.search_destino)
        self.EntryDestino.entry.bind("<BackSpace>", self.search_destino)
        self.EntryOrigen.entry.bind("<Return>", self.search_origen)
        self.EntryOrigen.entry.bind("<BackSpace>", self.search_origen)

        self.desde = customtkinter.CTkLabel(
            self.frame_left, text="From: " + self.origin.get(), anchor="w"
        )
        self.desde.grid(row=4, column=0, sticky="we", padx=(20, 20), pady=(20, 0))

        self.hacia = customtkinter.CTkLabel(
            self.frame_left, text="To: " + self.destiny.get(), anchor="w"
        )
        self.hacia.grid(row=5, column=0, sticky="we", padx=(20, 20), pady=(20, 0))

        self.ir = customtkinter.CTkButton(
            master=self.frame_left, text="Show Path", command=self.show_path
        )
        self.ir.grid(row=6, column=0, padx=(20, 20), pady=(20, 0))

        self.dist = customtkinter.CTkLabel(
            self.frame_left, text=f"Distancia = {self.distancia.get()}", anchor="w"
        )
        self.dist.grid(row=7, column=0, padx=(20, 20), pady=(20, 0))

        self.l1 = customtkinter.CTkLabel(
            self.frame_left, text="Linea 1", fg_color="#118103", anchor="w"
        )
        self.l1.grid(row=8, column=0, padx=(20, 20), pady=(20, 0))

        self.l2 = customtkinter.CTkLabel(
            self.frame_left, text="Linea 2", fg_color="#A80511", anchor="w"
        )
        self.l2.grid(row=9, column=0, padx=(20, 20), pady=(20, 0))

        self.l2 = customtkinter.CTkLabel(
            self.frame_left, text="Linea 3", fg_color="#092e5a", anchor="w"
        )
        self.l2.grid(row=10, column=0, padx=(20, 20), pady=(20, 0))

        # Set default values

        self.map_widget.set_address("Piraeus")
        origen_option_menu.set(self.estaciones[0])
        destino_option_menu.set(self.estaciones[0])

    def select_origen(self, org: str):
        for p in paradas:
            if p.get("nombre") == org:
                self.origen = p
                self.origin.set(p.get("nombre"))
                break
            else:
                continue
        self.desde.set_text("From: " + self.origin.get())
        self.par += 1

    def select_destino(self, dest: str):
        for p in paradas:
            if p.get("nombre") == dest:
                self.destino = p
                self.destiny.set(p.get("nombre"))
                break
            else:
                continue
        self.hacia.set_text("To: " + self.destiny.get())
        self.par += 1

    def show_path(self):
        if self.path is not None:
            if self.recorrido2 is not None:
                self.recorrido2.destroy()
            if self.recorrido3 is not None:
                self.recorrido3.destroy()
            self.path.delete()
        ORIGEN = int(self.origen.get("id"))
        DESTINO = int(self.destino.get("id"))
        nodos = getNodes()
        aristas = getAristas(nodos)
        path, param, ruta = mn.run(ORIGEN, DESTINO, nodos, aristas)

        ruta = ruta.split("->")
        r1 = ""
        r2 = "->"
        r3 = "->"
        for i in range(0, len(ruta)):
            if i < 9:
                if i == len(ruta) - 1:
                    r1 += ruta[i]
                else:
                    r1 += ruta[i] + "->"
            if 9 <= i < 18:
                if i == len(ruta) - 1:
                    r2 += ruta[i]
                else:
                    r2 += ruta[i] + "->"
            if 18 <= i:
                if i == len(ruta) - 1:
                    r3 += ruta[i]
                else:
                    r3 += ruta[i] + "->"

        self.recorrido1 = customtkinter.CTkLabel(self.frame_right, text="", anchor="w")
        self.recorrido1.grid(row=2, column=0, sticky="we", padx=(12, 0), pady=1)

        self.recorrido1.set_text(r1)
        if r2 != "->":
            self.recorrido2 = customtkinter.CTkLabel(
                self.frame_right, text="", anchor="w"
            )
            self.recorrido2.set_text(r2)
            self.recorrido2.grid(row=3, column=0, sticky="we", padx=(12, 0), pady=1)
        if r3 != "->":
            self.recorrido3 = customtkinter.CTkLabel(
                self.frame_right, text="", anchor="w"
            )
            self.recorrido3.set_text(r3)
            self.recorrido3.grid(row=4, column=0, sticky="we", padx=(12, 0), pady=1)

        self.distancia.set(round(param[DESTINO].f / 10, 3))
        self.dist.set_text(f"Distancia = {self.distancia.get()} km")

        pos = []
        for p in path:
            pos.append((p.coord.x, p.coord.y))
        self.path = self.map_widget.set_path(pos, color="black", width="5")

    def search_origen(self, event=None):
        st = self.EntryOrigen.get()
        j = 0
        new = []
        for s in self.estaciones:
            igual = TRUE
            for i in range(0, len(st)):
                igual &= st[i] == s[i]
                if igual == FALSE:
                    break
            if igual:
                new.append(s)
            j += 1
        origen_option_menu = customtkinter.CTkOptionMenu(
            self.frame_left, values=new, command=self.select_origen
        )
        origen_option_menu.grid(row=1, column=0, padx=(20, 20), pady=(10, 0))
        if len(new) != 0:
            origen_option_menu.set(new[0])
        else:
            origen_option_menu.set("no results")

    def search_destino(self, event=None):
        st = self.EntryDestino.get()
        j = 0
        new = []
        for s in self.estaciones:
            igual = TRUE
            for i in range(0, len(st)):
                igual &= st[i] == s[i]
            if igual:
                new.append(s)
            j += 1
        destino_option_menu = customtkinter.CTkOptionMenu(
            self.frame_left, values=new, command=self.select_destino
        )
        destino_option_menu.grid(row=3, column=0, padx=(20, 20), pady=(10, 0))
        if len(new) != 0:
            destino_option_menu.set(new[0])
        else:
            destino_option_menu.set("no results")

    def selected_marker(self, marker):
        if self.par % 2 == 0:
            self.select_origen(marker.text)
        else:
            self.select_destino(marker.text)

    def on_closing(self, event=0):
        self.destroy()

    def start(self):
        self.mainloop()


if __name__ == "__main__":
    app = App()
    app.start()
