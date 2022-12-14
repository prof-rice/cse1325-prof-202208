#include "hourly.h"
#include "manager.h"

#include <vector>
#include <fstream>

int main() {
    std::string type, name;
    double wage, hours;
    Employee* e;
    
    std::ifstream ifs{"payroll.dat"}; // 1
    while(ifs) { // ½
        ifs >> type >> hours >> wage; // ½
        std::getline(ifs, name);      // ^
        if(type == "H") e = new Hourly(name, wage); // ½
        else if (type == "M") e = new Manager(name, wage);
        else continue;
        std::cout << *e << " is paid $" << e->paycheck(hours) << std::endl; // ½
    }
}
