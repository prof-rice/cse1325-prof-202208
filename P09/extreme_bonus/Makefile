CXXFLAGS = --std=c++17

all: test_matrix3 interactive_matrix3

# Linkers
test_matrix3: test_matrix3.o matrix3.o *.h
	$(CXX) $(CXXFLAGS) test_matrix3.o matrix3.o -o test_matrix3
# Linkers
interactive_matrix3: interactive_matrix3.o matrix3.o *.h
	$(CXX) $(CXXFLAGS) interactive_matrix3.o matrix3.o -o interactive_matrix3

# Class compilation
test_matrix3.o: test_matrix3.cpp *.h
	$(CXX) $(CXXFLAGS) -c test_matrix3.cpp -o test_matrix3.o
# Class compilation
interactive_matrix3.o: interactive_matrix3.cpp *.h
	$(CXX) $(CXXFLAGS) -c interactive_matrix3.cpp -o interactive_matrix3.o

matrix3.o: matrix3.cpp
	$(CXX) $(CXXFLAGS) -c matrix3.cpp -o matrix3.o

clean:
	-rm -f *.o *.gch *~ a.out matrix_generator simple_matrix3_test matrix3 test_matrix3 interactive_matrix3 read_add_save
