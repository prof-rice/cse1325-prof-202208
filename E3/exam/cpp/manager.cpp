#include "manager.h"

Manager::Manager(std::string name, double hourly_rate) : Employee(name, hourly_rate) { }
double Manager::paycheck(double hours) {return _hourly_rate * 40;}

