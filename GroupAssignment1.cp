#include <iostream>
#include <fstream>
using namespace std;

// This is our array of words
char* words[9];

// Help us keep the code DRY
struct ArrayHolder {
    ArrayHolder(int a) { myNumberIs = a; }
    char** t;
    int filled = 0, howManyOfMe = 0;
    int myNumberIs;
    
} threes(3), fours(4), fives(5), sixes(6);

// takes a char array and returns num of chars
int length(const char* a) {
    int counter = 0;
    while (!(a[counter] == '\0')) {
        counter++;
    }
    return counter;
}
// Prints our object as specified
void printItOut(ArrayHolder workingarray) {
    for (int i = 0; i < workingarray.filled; i++) {
        cout << workingarray.t[i] << endl;
        for (int j = 0; j < workingarray.myNumberIs; j++) {
            cout << workingarray.t[i][j] << endl;
        }
        cout << endl;
    }
}

int main(int argc, const char* argv[]) {
    // open our file
    ifstream myfile("file.txt");
    
    for (int i = 0; i < 9; i++) {
        // prep the pointer
        words[i] = new char();
        // get ourselves a word
        myfile >> words[i];
        
        switch (length(words[i])) {
            case 3:
                threes.howManyOfMe++;
                break;
            case 4:
                fours.howManyOfMe++;
                break;
            case 5:
                fives.howManyOfMe++;
                break;
            case 6:
                sixes.howManyOfMe++;
                break;
            default:
                break;
        }
    }
    // declared now that we know how many of them there are
    char* t3[threes.howManyOfMe];
    char* t4[fours.howManyOfMe];
    char* t5[fives.howManyOfMe];
    char* t6[sixes.howManyOfMe];
    
    // assigned to our objects
    threes.t = t3;
    fours.t = t4;
    fives.t = t5;
    sixes.t = t6;
    
    for (int i = 0; i < 9; i++) {
        // Fills our new arrays up
        switch (length(words[i])) {
            case 3:
                threes.t[threes.filled] = words[i];
                threes.filled++;
                break;
            case 4:
                fours.t[fours.filled] = words[i];
                fours.filled++;
                break;
            case 5:
                fives.t[fives.filled] = words[i];
                fives.filled++;
                break;
            case 6:
                sixes.t[sixes.filled] = words[i];
                sixes.filled++;
                break;
            default:
                break;
        }
    }
    
    printItOut(threes);
    printItOut(fours);
    printItOut(fives);
    printItOut(sixes);
    return 0;
}