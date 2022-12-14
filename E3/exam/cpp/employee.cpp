#include "employee.h"

// 4c 2 = ½ : 1 init list ½ throw
Employee::Employee(std::string name, double hourly_rate) 
    : _name{name}, _hourly_rate{hourly_rate} {
    if(hourly_rate < 0) throw std::runtime_error{"Negative hourly rate"};
}

// 4d 3 = ½ ost << ½ e. ½ variables ½ ($ ) return 1
std::ostream& operator<<(std::ostream& ost, const Employee& e) {
    return ost << e._name << " ($" << e._hourly_rate << ")";
}

