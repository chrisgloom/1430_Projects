#include <iostream>
#include <fstream>
#include <string>
#include <math.h>
using namespace std;

enum Status { WIN, LOSE, DRAW, CONTINUE };

class TicTacToe {
private:
    char myArray[3][3];
    int noOfMoves, inputcol, inputrow, computerX, computerY;
    char playerChar, myChar;
    
public:
    void play() {
        cout << "Playing with x's or o's?";
        cin >> playerChar;
        playerChar == 'x' ? myChar = '0' : myChar = 'x';
        
        while (gameStatus() == CONTINUE) {
            // Game loop
            gameStatus();
            displayBoard();
            cout << "Which row are you wanting to place your " << playerChar << " ?:";
            cin >> inputcol;
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            cout << "and which column are you wanting to place your " << playerChar
            << " ?:";
            cin >> inputrow;
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            
            // so that there's no row or column zero
            inputcol--;
            inputrow--;
            if (isValidMove(inputcol, inputrow)) {
                myArray[inputcol][inputrow] = playerChar;
            } else {
                cout << "Sorry that's not valid input." << endl;
            }
            srand(time(NULL));
            
            // Computer's turn to move
            // Random nums here and also in the while
            computerX = rand() % 3;
            computerY = rand() % 3;
            
            while (!isValidMove(computerX, computerY)) {
                computerX = rand() % 3;
                computerY = rand() % 3;
            }
            myArray[computerX][computerY] = myChar;
        }
    };
    Status gameStatus() {
        // has someone won
        // has the game been drawn
        if (threeInARow(playerChar)) {
            cout << "Player wins!" << endl;
            displayBoard();
            cout << "Total number of moves was " << noOfMoves << endl;
            return WIN;
        } else if (threeInARow(myChar)) {
            cout << "You actually lost against a random computer how did that even "
            "happen." << endl;
            displayBoard();
            cout << "Total number of moves was " << noOfMoves << endl;
            return LOSE;
        } else {
            return CONTINUE;
        }
    }
    bool isValidMove(int x, int y) {
        if (myArray[x][y] == ' ') {
            noOfMoves++;
            return true;
        } else {
            return false;
        }
    }
    bool threeInARow(char inputChar) {
        // Check the rows
        for (int i = 0; i < 3; i++) {
            if (myArray[i][0] == inputChar && myArray[i][1] == inputChar &&
                myArray[i][2] == inputChar) {
                return true;
            }
        }
        // Check the columns
        for (int i = 0; i < 3; i++) {
            if (myArray[0][i] == inputChar && myArray[1][i] == inputChar &&
                myArray[2][i] == inputChar) {
                return true;
            }
        }
        if (myArray[0][0] == inputChar && myArray[1][1] == inputChar &&
            myArray[2][2] == inputChar) {
            return true;
        }
        if (myArray[0][2] == inputChar && myArray[1][1] == inputChar &&
            myArray[2][0] == inputChar) {
            return true;
        }
        
        return false;
    }
    void reStart() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                myArray[i][j] = ' ';
            }
        }
        play();
    }
    void displayBoard() const {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                cout << "|" << myArray[x][y] << "|";
            }
            cout << endl;
        }
    }
    // Constructor
    TicTacToe() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                myArray[i][j] = ' ';
            }
        }
        noOfMoves = 0;
    }
};

int main(int argc, const char* argv[]) {
    TicTacToe game;
    game.play();
    return 0;
}
