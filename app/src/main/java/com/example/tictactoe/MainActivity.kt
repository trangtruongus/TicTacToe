package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var message : TextView
    private var playerX: Boolean = true

    private lateinit var buttons: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)

        buttons = arrayOf(arrayOf(button1, button2, button3),
                          arrayOf(button4, button5, button6),
                          arrayOf(button7, button8, button9))

        for (row in buttons) {
            for (button in row) {
                button.setOnClickListener {
                    onClick(button)
                }
            }
        }

        message = findViewById<TextView>(R.id.messageView)

        displayPlayersTurn()

        // New Game Button
        val buttonNewGame = findViewById<Button>(R.id.buttonNewGame)
        buttonNewGame.setOnClickListener{
            newGame()
        }
    }

    private fun onClick(button : Button) {
        if (button.text == "X" || button.text == "O") {
            return
        }
        else {
            setSymbol(button)
            if (gameOver()) {
                freezeButtons()
                if (playerX) {
                    message.text = "Player X won the game!"
                }
                else {
                    message.text = "Player O won the game!"
                }
            }
            else if (isTie()) {
                freezeButtons()
                message.text = "It's a Tie. \n No one wins!"
            }
            else {
                playerX = !playerX
                displayPlayersTurn()
            }
        }
    }

    private fun setSymbol(button: Button) {
        if (playerX) {
            button.text = "X"
        }
        else {
            button.text = "O"
        }
    }

    private fun displayPlayersTurn() {
        if (playerX) {
            message.text = "Player X's turn"
        }
        else {
            message.text = "Player O's turn"
        }
    }

    private fun newGame() {
        playerX = true
        displayPlayersTurn()
        reset9Buttons()
    }

    private fun reset9Buttons() {
        for (row in buttons) {
            for (button in row) {
                button.text = ""
                button.isEnabled = true
            }
        }
    }

    private fun gameOver() : Boolean {
        for (i in 0..2) {
            if (!buttons[0][i].text.isNullOrEmpty()
                && buttons[0][i].text == buttons[1][i].text
                && buttons[0][i].text == buttons[2][i].text) {
                return true
            }
        }

        for (i in 0..2) {
            if (!buttons[i][0].text.isNullOrEmpty()
                && buttons[i][0].text == buttons[i][1].text
                && buttons[i][0].text == buttons[i][2].text) {
                return true
            }
        }

        if (!buttons[0][0].text.isNullOrEmpty()
            && buttons[0][0].text == buttons[1][1].text
            && buttons[0][0].text == buttons[2][2].text) {
            return true
        }

        if (!buttons[2][0].text.isNullOrEmpty()
            && buttons[2][0].text == buttons[1][1].text
            && buttons[2][0].text == buttons[0][2].text) {
            return true
        }

        return false
    }

    private fun freezeButtons() {
        for (row in buttons) {
            for (button in row) {
                button.isEnabled = false
            }
        }
    }

    private fun isTie() : Boolean {
        for (row in buttons) {
            for (button in row) {
                if (button.text.isNullOrEmpty()) {
                    return false  // If any button is empty, it's not a tie
                }
            }
        }
        return true  // If all buttons are filled, it's a tie
    }
}