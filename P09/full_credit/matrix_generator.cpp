#include <iostream>
#include <iomanip>
#include <ctime>

const int MIN = -50;
const int MAX =  50;

int main() {
    srand(time(NULL));
    for(int y=0; y<3; ++y) {
        for(int x=0; x<3; ++x) {
            std::cout << std::setw(5) << rand()%(MAX-MIN)+MIN;
        }
        std::cout << std::endl;
    }
}
