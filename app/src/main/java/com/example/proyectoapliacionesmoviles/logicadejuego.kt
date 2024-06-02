package com.example.proyectoapliacionesmoviles

class logicadejuego(private val rows: Int, private val cols: Int, private val numMines: Int) {
    val gameBoard: Array<Array<Cell>> = Array(rows) { row ->
        Array(cols) { col -> Cell(row, col) }
    }

    // Método para crear un tablero vacío
    fun createEmptyBoard() {
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                gameBoard[i][j] = Cell(i, j)
            }
        }
    }

    // Método para colocar minas aleatoriamente en el tablero
    fun placeMines() {
        var minesPlaced = 0
        while (minesPlaced < numMines) {
            val randomRow = (0 until rows).random()
            val randomCol = (0 until cols).random()
            val cell = gameBoard[randomRow][randomCol]
            if (!cell.isMine) {
                cell.isMine = true
                minesPlaced++
            }
        }
    }

    fun allCellsRevealed(): Boolean {
        for (row in gameBoard) {
            for (cell in row) {
                if (!cell.isRevealed && !cell.isMine) {
                    return false
                }
            }
        }
        return true
    }

    // Método para contar minas adyacentes para cada celda
    fun countAdjacentMines() {
        val directions = arrayOf(
            Pair(-1, -1), Pair(-1, 0), Pair(-1, 1),
            Pair(0, -1), /* Current Cell */ Pair(0, 1),
            Pair(1, -1), Pair(1, 0), Pair(1, 1)
        )

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                var adjacentMines = 0
                for ((di, dj) in directions) {
                    val ni = i + di
                    val nj = j + dj
                    if (ni in 0 until rows && nj in 0 until cols && gameBoard[ni][nj].isMine) {
                        adjacentMines++
                    }
                }
                gameBoard[i][j].value = adjacentMines
            }
        }
    }

    // Método para revelar una celda
    fun revealCell(row: Int, col: Int) {
        val cell = gameBoard[row][col]
        if (!cell.isRevealed) {
            cell.isRevealed = true
            // Aquí puedes agregar la lógica para manejar la revelación de la celda
        }
    }

    // Clase interna para representar las celdas del tablero
    data class Cell(val row: Int, val col: Int, var isMine: Boolean = false, var isRevealed: Boolean = false, var value: Int = 0)
}
