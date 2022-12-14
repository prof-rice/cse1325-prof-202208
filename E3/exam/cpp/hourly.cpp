#include "hourly.h"

// 4e ½ : ½ Employee(name, hourly_rate)
Hourly::Hourly(std::string name, double hourly_rate) : Employee(name, hourly_rate) { }
double Hourly::paycheck(double hours) {
    if(hours > 40) hours += (0.5 * (hours - 40));
    return hours * _hourly_rate;
}

