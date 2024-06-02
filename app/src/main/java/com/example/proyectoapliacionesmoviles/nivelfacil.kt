package com.example.proyectoapliacionesmoviles

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private var ImageView.text: String
    get() {
        TODO("Not yet implemented")
    }
    set(value) {}

class nivelfacil : AppCompatActivity() {
    private val ROWS = 8
    private val COLS = 8
    private val NUM_MINES = 10
    private val gameLogic = logicadejuego(ROWS, COLS, NUM_MINES)

    private lateinit var timerTextView: TextView
    private var seconds = 0
    private var running = false
    private var timerStarted = false // Variable para rastrear si el cronómetro ha iniciado
    private val handler = Handler(Looper.getMainLooper())

    private val runnable = object : Runnable {
        @SuppressLint("DefaultLocale")
        override fun run() {
            if (running) {
                seconds++
                val minutes = seconds / 60
                val secs = seconds % 60
                val time = String.format("%02d:%02d", minutes, secs)
                timerTextView.text = time
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nivelfacil)

        timerTextView = findViewById(R.id.timerTextView)
        timerTextView.setTextColor(android.graphics.Color.WHITE)
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)

        // Crear tablero vacío
        gameLogic.createEmptyBoard()

        // Colocar minas aleatorias
        gameLogic.placeMines()

        // Contar minas adyacentes para cada celda
        gameLogic.countAdjacentMines()

        // Recorrer el tablero y agregar las imágenes correspondientes a cada celda
        for (i in 0 until ROWS) {
            for (j in 0 until COLS) {
                val cell = gameLogic.gameBoard[i][j]
                val imageView = ImageView(this)
                imageView.setImageResource(R.drawable.casilla)

                val layoutParams = GridLayout.LayoutParams().apply {
                    width = 100
                    height = 100
                    columnSpec = GridLayout.spec(j)
                    rowSpec = GridLayout.spec(i)
                    setMargins(1, 1, 1, 1)
                }

                imageView.layoutParams = layoutParams

                // Manejar la interacción del usuario con la celda
                imageView.setOnClickListener {
                    if (!timerStarted) {
                        startTimer()
                        timerStarted = true
                    }
                    gameLogic.revealCell(i, j)
                    updateCellUI(imageView, cell)
                    if (cell.isRevealed && cell.isMine) {
                        val intent = Intent(this, juegoperdido::class.java)
                        startActivity(intent)
                    }
                    // Verificar si todas las celdas sin minas han sido reveladas
                    if (gameLogic.allCellsRevealed()) {
                        stopTimer()
                        val intent = Intent(this, juegoganado::class.java)
                        startActivity(intent)
                    }
                }

                gridLayout.addView(imageView)
            }
        }
    }

    private fun startTimer() {
        running = true
        handler.post(runnable)
    }

    private fun stopTimer() {
        running = false
        handler.removeCallbacks(runnable)
    }

    // Método para actualizar la interfaz de usuario según el estado de la celda
    private fun updateCellUI(imageView: ImageView, cell: logicadejuego.Cell) {
        if (cell.isRevealed) {
            if (cell.isMine) {
                imageView.setImageResource(R.drawable.nivel2) // Muestra la imagen de la mina
                stopTimer()
            } else if (cell.value == 0) {
                imageView.setImageResource(R.drawable.casiila_blanca) // Muestra la imagen de la casilla blanca
                revealAdjacentCells(cell.row, cell.col) // Asegúrate de que `row` y `col` existan en `Cell`
            } else {
                val numberString = cell.value.toString()
                val parent = imageView.parent as GridLayout
                val index = parent.indexOfChild(imageView)

                // Crear un TextView en lugar de un ImageView para mostrar el número
                val numberTextView = TextView(this).apply {
                    text = numberString
                    textSize = 18f
                    textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = 100
                        height = 100
                        columnSpec = (imageView.layoutParams as GridLayout.LayoutParams).columnSpec
                        rowSpec = (imageView.layoutParams as GridLayout.LayoutParams).rowSpec
                        setMargins(1, 1, 1, 1)
                    }
                }

                // Remover el ImageView y añadir el TextView en la misma posición
                parent.removeViewAt(index)
                parent.addView(numberTextView, index)
            }
        } else {
            imageView.setImageResource(R.drawable.casilla) // Muestra la imagen de la casilla oculta
        }
    }

    // Método para revelar celdas adyacentes
    private fun revealAdjacentCells(row: Int, col: Int) {
        val directions = listOf(
            Pair(-1, -1), Pair(-1, 0), Pair(-1, 1),
            Pair(0, -1), /* current cell */ Pair(0, 1),
            Pair(1, -1), Pair(1, 0), Pair(1, 1)
        )

        for (direction in directions) {
            val newRow = row + direction.first
            val newCol = col + direction.second

            if (newRow in 0 until ROWS && newCol in 0 until COLS) {
                val cell = gameLogic.gameBoard[newRow][newCol]
                if (!cell.isRevealed) {
                    cell.isRevealed = true
                    val imageView = (findViewById<GridLayout>(R.id.gridLayout)).getChildAt(newRow * COLS + newCol) as ImageView
                    updateCellUI(imageView, cell)
                    if (cell.value == 0) {
                        revealAdjacentCells(newRow, newCol) // Llamada recursiva para revelar celdas adyacentes
                    }
                }
            }
        }
    }
}

