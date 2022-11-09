#include "matrix3.h"
#include <iomanip>

Matrix3::Matrix3(int m00, int m10, int m20,
                 int m01, int m11, int m21,
                 int m02, int m12, int m22) 
            : data {{m00,     m01,     m02},
                    {m10,     m11,     m12},
                    {m20,     m21,     m22}} { }

// + (addition)
Matrix3 Matrix3::operator+ (Matrix3 rhs) {
    return Matrix3{
        data[0][0] + rhs.get(0,0), data[1][0] + rhs.get(1,0), data[2][0] + rhs.get(2,0),
        data[0][1] + rhs.get(0,1), data[1][1] + rhs.get(1,1), data[2][1] + rhs.get(2,1),
        data[0][2] + rhs.get(0,2), data[1][2] + rhs.get(1,2), data[2][2] + rhs.get(2,2)
    };         
}

// * (scalar multiplication)
Matrix3 Matrix3::operator* (int rhs) {
    return Matrix3{
        rhs * data[0][0], rhs * data[1][0], rhs * data[2][0],
        rhs * data[0][1], rhs * data[1][1], rhs * data[2][1],
        rhs * data[0][2], rhs * data[1][2], rhs * data[2][2]
    };
}
Matrix3 operator* (int lhs, Matrix3 rhs) {
    return rhs * lhs; // just reverse the multiplicand order!
}

// << and >>
std::ostream& operator<<(std::ostream& out, const Matrix3& m) {
    int width = 1;
    for(int y=0; y<3; ++y) {
        for(int x=0; x<3; ++x) {
            int w = std::to_string(m.data[x][y]).length();
            if(w > width) width = w;
        }
    }

    for(int y=0; y<3; ++y) {
        out << "\n";
        for(int x=0; x<3; ++x) {
            out << std::setw(width+1) << m.data[x][y];
        }
    }
    out << "\n";
    return out;

/* Alternate, more explicit, definition
    return out << pre  << std::setw(m.width+1) << m.data[0][0]
                       << std::setw(m.width+1) << m.data[1][0]
                       << std::setw(m.width+1) << m.data[2][0] << "\n  "
                       << std::setw(m.width+1) << m.data[0][1]
                       << std::setw(m.width+1) << m.data[1][1]
                       << std::setw(m.width+1) << m.data[2][1] << "\n  "
                       << std::setw(m.width+1) << m.data[0][2]
                       << std::setw(m.width+1) << m.data[1][2]
                       << std::setw(m.width+1) << m.data[2][2];
*/

}

std::istream& operator>>(std::istream& in, Matrix3& m) {
    for(int y=0; y<3; ++y) {
        for(int x=0; x<3; ++x) {
                 in >> m.data[x][y];
        }
    }
    return in;
}

    // Utilities
int Matrix3::get(int x, int y) {
    if(x<0 || x>2 || y<0 || y>2) 
        throw std::runtime_error("Invalid matrix indices for get: " 
            + std::to_string(x) + "," + std::to_string(y));
    return data[x][y];
}

