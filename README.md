# INFO6205 Final Project (Generic Algorithm) 
Final project about Genetic Algorithm design and implementation of INFO6205 Program Structures and Algorithm in Spring 2019 semester at Northeastern University.

## Contributers
Zigeng Fu, Yitong Liu, Feng Qiu

## Project Overview
In this project, our goal is to apply the Generic Algorithm on the position selection of a radar station. There are 15 military bases in an area, we need to find the optimal area where the radar station shoud be established so that the total signal strengh is the best.
![scene](https://github.com/ZigengFU/INFO6205_501/blob/master/Reports/Materials/scene.png)

There are 5000 generations in our evolution and 1000 individuals (potential radar station locations) in each generation, after gene selection, crossover, mutation in each generation, the final optimal area to set the radar station is figured out. Shown as the following images.

3D diagram, in which the different colors represent for the amount of fitness, and the points represent for the individuals.
![3D diagram](https://github.com/ZigengFU/INFO6205_501/blob/master/Reports/Materials/distribution2.png)

Scatter diagram, in which the red points represent for the individuals in the 5000th generation.
![scatter diagram](https://github.com/ZigengFU/INFO6205_501/blob/master/Reports/Materials/distribution4.png)

## Program Instructions 
To launch and view the outputs of our GA program, please follow the following steps:
1. Clone "INFO6205_501/GA_Team501" to your local path;
2. Open the project using IDE (NetBeans recommended);
3. Add "INFO6205_501/GA_Team501/org.zip" into project library;
4. Run the project and wait for the GUI present, click "Start" button to run the evolution and then click "Report" button to see the result;
5. View the output data result which containing the information about the first and the last generation in "GA_Team501/codes/src/results.csv".
