#ifndef __HOURLY_H
#define __HOURLY_H

#include "employee.h"

class Hourly : public Employee {
  public:
    Hourly(std::string _name, double _hourly_rate);
    double paycheck(double hours) override;
};
#endif
