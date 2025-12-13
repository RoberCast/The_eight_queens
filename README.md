# The eight queens

## Introduction
The eight queens problem is a classic problem used in the study of algorithms to illustrate the *backtracking* scheme. The classic problem consists of placing eight queens on an 8x8 chessboard so that they cannot attack each other, taking into account the queen's movement according to the rules of chess. In this implementation, it is solved in its general form; that is, N queens are placed on an NxN chessboard. The program output should display a numbered list of solutions, indicated by the board coordinates. A limit of n = 13 has been set since too many solutions are generated beyond n > 13. 

## Instructions
To use the program, copy the *Queens.jar* file to a folder of your choice. Then, open the operating system's terminal, navigate to the folder where the .jar file was copied, and run the program using the following syntax:

* `java -jar queens [-t] [-g] [-h] n [output_file]`

The arguments are as follows:
* `-t`: Traces each step in a way that describes the application of the algorithm used, showing the rejected and valid positions. Incomplete positions are not traced.
* `-g`: Graphical mode. A visual format is used that represents the board and the solution to the problem.
* `-h`: Displays help and syntax for the execution command.

Possible options:
* `java -jar queens -h`
* `java -jar queens n`
* `java -jar queens -t n`
* `java -jar queens n output_file`
* `java -jar queens -g n output_file`
* `java -jar queens -t n output_file`
* `java -jar queens -t -g n output_file`

*Considerations*: The board size must be a positive integer greater than zero and less than or equal to 13; otherwise, the program will display an error. Additionally, the output file must have a .txt extension, or the application will display an error. A log is generated that saves all the events that occur in the program.

## Demo
Below are some sample images of the program.

* Solution to the problem seen in the cmd with n = 5.
  
![muestra de soluciones](Images/Solutions_n_5.jpg)

* Trace of the problem for n = 4.

![traza](Images/solution_n_4_trace.jpg)

* Graphical solution of the problem for n = 4. The asterisk represents a black square and the letter "R" a queen.

![solucion_grafica](Images/g_output_file_n_4_trace2.png)

## License
This project is licensed under the GNU General Public License v3 (GPLv3).

© 2025 Roberto Castillejo Embid.
See the [LICENSE](./LICENSE) file for more details.
