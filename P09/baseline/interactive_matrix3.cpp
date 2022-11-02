// Interactive Matrix3 Tester
//
// This streams in two matrices and then streams out their sum

#include "matrix3.h"

int main() {
    Matrix3 m1, m2;
    std::cout << "Enter two 3x3 matrices:" << std::endl;
    std::cin >> m1 >> m2;
    std::cout << m1 << "     +" << m2 << "      =" << m1 + m2 << std::endl;
}
