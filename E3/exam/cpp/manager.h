#ifndef __MANAGER_H
#define __MANAGER_H

#include "employee.h"

class Manager : public Employee {
  public:
    Manager(std::string _name, double _hourly_rate);
    double paycheck(double hours) override;
};
#endif
