#ifndef __MATRIX3_H
#define __MATRIX3_H

#include <vector>
#include <iostream>

class Array3 {
  public:
    Array3(int m0, int m1, int m2);
    int operator[](int index);
  private:
    int data[3];
};

class Matrix3 {
  public:
    // Notice that default parameter values are ONLY declared here! 
    Matrix3(int m00 = 0, int m10 = 0, int m20 = 0,
            int m01 = 0, int m11 = 0, int m21 = 0,
            int m02 = 0, int m12 = 0, int m22 = 0); 

    Matrix3 operator+ (Matrix3 rhs); // + (addition)

    Matrix3 operator* (int rhs);     // * (scalar multiplication)
    friend Matrix3 operator* (int lhs, Matrix3 rhs); // symmetry
    
    Array3 operator[](int index);

    // These are NOT methods - they are friend functions
    friend std::ostream& operator<<(std::ostream& out, const Matrix3& m);
    friend std::istream& operator>>(std::istream& in, Matrix3& m);

    int get(int x, int y);

  private:
      std::vector<std::vector<int>> data; //or int data[3][3]; // as array
};

#endif
