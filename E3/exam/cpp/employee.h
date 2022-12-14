#ifndef __EMPLOYEE_H
#define __EMPLOYEE_H

#include <iostream>

class Employee {
  public:
    Employee(std::string _name, double _hourly_rate);
    virtual double paycheck(double hours) = 0; // 4a ½ virtual ½ =0
    friend std::ostream& operator<<(std::ostream& ost, const Employee& e);
  protected: // 4b ½ protected ½ fields
    std::string _name;
    double _hourly_rate;
};
#endif
