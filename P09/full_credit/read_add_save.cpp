#include <iostream>
#include <fstream>
#include "matrix3.h"

int main(int argc, char* argv[]) {
    try {
        if(argc < 3) throw std::runtime_error{"Too few arguments"}; 
        Matrix3 sum;
        Matrix3 addend;
        for(int i=2; i<argc; ++i) {
            std::string file{argv[i]};
            std::ifstream ist{file};	
            if (!ist) throw std::runtime_error{"can’t open input file " + file};
            ist >> addend;
            sum = sum + addend;
        }
        std::string file{argv[1]};
        std::ofstream ost{file};
        if (!ost) throw std::runtime_error{"can't write " + file};
        ost << sum << std::endl;
    } catch (std::exception e) {
        std::cerr << e.what() << std::endl;
        std::cerr << "usage: " << argv[0] << " out.m3 in1.m3 in2.m3..." << std::endl;
    }
}
