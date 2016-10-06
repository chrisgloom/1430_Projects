#include <iostream>
#include <string>
using namespace std;

// abstract animal class
class animal {
public:
    static int count;
    // pure virtual makes the class abstract
    virtual string speak() = 0;
    animal() { count++; }
};
// you are now looking at the canine class
class canine : public animal {
public:
    static int count;
    canine() { count++; }
    string speak() { return "WOOF"; }
};
// What a wonderful feline class
class feline : public animal {
public:
    static int count;
    feline() { count++; }
    string speak() { return "PURR"; }
};

// Oh wow! A dog class!
class dog : public canine {
private:
    string name;
    
public:
    dog() { name = "dog"; }
    dog(string myName) { name = myName; }
    string getName() { return name; }
};

// Watch out; it's a wolf class
class wolf : public canine {
public:
    wolf() {}
    string howl() { return "HOWL"; }
};

// Somebody get a bowl of milk for this cat class
class cat : public feline {
private:
    string name;
    
public:
    cat() { name = "cat"; }
    cat(string catName) { name = catName; }
    string getName() { return name; }
};

int animal::count = 0;
int canine::count = 0;
int feline::count = 0;

int main(int argc, const char* argv[]) {
    // cat dog and wolf with default constructors
    cat mycat;
    dog mydog;
    wolf mywolf;
    // cat and dog with name params passed in
    cat namedCat("fred");
    dog namedDog("jack");
    // array of three pointers to animals
    animal* myAnimals[3];
    // make the pointers point to actual instances of animals
    myAnimals[0] = new dog;
    myAnimals[1] = new cat;
    myAnimals[2] = new wolf;
    
    // Speak called on animals, different output due to polymorphism
    for (int i = 0; i < 3; i++) {
        cout << myAnimals[i]->speak() << endl;
    }
    // Wolf goes howl
    cout << mywolf.howl() << endl;
    
    // output the static counts of each class
    cout << "Animal count is " << mycat.animal::count << endl
    << "Canine count is " << mydog.canine::count << endl
    << "Feline count is " << mycat.feline::count << endl;
    
    return 0;
}
