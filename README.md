# COMP-354 Calculator Project  

This project is a calculator built using Java Swing. The calculator implements 8 advanced mathematical functions designed and implemented directly without Java Math libraries.

Built for the COMP-354 Introduction to Software Engineering project at Concordia University.

## Features  

The calculator includes the following functions:  

1. **arccos(x)**  
   - Calculates the arccosine of a given value.  
   - Implemented by: **Tristan**  

2. **ab^x**  
   - Calculates the exponential of the base `a` raised to the power `b^x`.  
   - Implemented by: **Zake**  

3. **logb(x)**  
   - Calculates the logarithm of `x` with base `b`.  
   - Implemented by: **Khang**  

4. **Γ(x) (Gamma Function)**  
   - Calculates the Gamma function of `x`.  
   - Implemented by: **Cyrus**  

5. **MAD (Mean Absolute Deviation)**  
   - Computes the Mean Absolute Deviation of a dataset.  
   - Implemented by: **Nick**  

6. **σ (Standard Deviation)**  
   - Computes the standard deviation of a dataset.  
   - Implemented by: **Jeremy**  

7. **sinh(x)**  
   - Calculates the hyperbolic sine of `x`.  
   - Implemented by: **Tristan**  

8. **x^y**  
   - Calculates `x` raised to the power of `y`.  
   - Implemented by: **Minh**  

## Technologies Used  

- **Programming Language**: Java  
- **GUI Framework**: Java Swing  

# How to Run Program

## Run the JAR files:

1. Get the jar binaries in the Release section of this repo.

1. Run cmd:
    ```bash
    java -jar <Name of Jar>.jar

## Alternatively, you can run the program using Java Compiler

- Clone the repository:  
   ```bash
   git clone <repository_url>
   cd COMP-354-Calculator

### Open the folder in any IDE and compile!
- This project was tested on Eclipse IDE and VSCode

### Comand line

1. Compile the Java files
    ```bash
    javac -d bin src/*.java

2. Run the application:
    ```bash
    java -cp bin Main