CXXFLAGS = --std=c++17

all: matrix_generator simple_matrix3_test test_matrix3 test_matrix3 interactive_matrix3 read_add_save

# Linkers
matrix_generator: matrix_generator.o 
	$(CXX) $(CXXFLAGS) matrix_generator.o -o matrix_generator

simple_matrix3_test: simple_matrix3_test.o matrix3.o *.h
	$(CXX) $(CXXFLAGS) simple_matrix3_test.o matrix3.o -o simple_matrix3_test

test_matrix3: test_matrix3.o matrix3.o *.h
	$(CXX) $(CXXFLAGS) test_matrix3.o matrix3.o -o test_matrix3

interactive_matrix3: interactive_matrix3.o matrix3.o *.h
	$(CXX) $(CXXFLAGS) interactive_matrix3.o matrix3.o -o interactive_matrix3

read_add_save: read_add_save.o matrix3.o *.h
	$(CXX) $(CXXFLAGS) read_add_save.o matrix3.o -o read_add_save

# Class compilation
matrix_generator.o: matrix_generator.cpp *.h
	$(CXX) $(CXXFLAGS) -c matrix_generator.cpp -o matrix_generator.o

simple_matrix3_test.o: simple_matrix3_test.cpp *.h
	$(CXX) $(CXXFLAGS) -c simple_matrix3_test.cpp -o simple_matrix3_test.o

test_matrix3.o: test_matrix3.cpp *.h
	$(CXX) $(CXXFLAGS) -c test_matrix3.cpp -o test_matrix3.o

interactive_matrix3.o: interactive_matrix3.cpp *.h
	$(CXX) $(CXXFLAGS) -c interactive_matrix3.cpp -o interactive_matrix3.o

read_add_save.o: read_add_save.cpp *.h
	$(CXX) $(CXXFLAGS) -c read_add_save.cpp -o read_add_save.o

matrix3.o: matrix3.cpp
	$(CXX) $(CXXFLAGS) -c matrix3.cpp -o matrix3.o

clean:
	-rm -f *.o *~ a.out matrix_generator simple_matrix3_test matrix3 test_matrix3 interactive_matrix3 read_add_save
