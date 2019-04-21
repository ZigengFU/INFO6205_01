# INFO6205 Final Project (Generic Algorithm) 
Final project about Genetic Algorithm design and implementation of INFO6205 Program Structures and Algorithm in Spring 2019 semester at Northeastern University.

## Contributers
Zigeng Fu, Yitong Liu, Feng Qiu

## Project Overview
### Goal
In this project, our goal is to apply the Generic Algorithm on the position selection of a radar station. There are 15 military bases in an area, we need to find the optimal area where the radar station should be established so that the total signal strength is the best.

![scene](https://github.com/ZigengFU/INFO6205_501/blob/master/Reports/Materials/scene.png)

### Result and Analysis
There are 5000 generations in our evolution and 1000 individuals (potential radar station locations) in each generation, after gene selection, crossover, mutation in each generation, the final optimal area to set the radar station is figured out. Shown as the following images.

- 3D diagram, in which the different colors represent for the amount of fitness, and the points represent for the individuals.
![3D diagram](https://github.com/ZigengFU/INFO6205_501/blob/master/Reports/Materials/distribution2.png)

- Scatter diagram, in which the red points represent for the individuals in the 5000th generation.
![scatter diagram](https://github.com/ZigengFU/INFO6205_501/blob/master/Reports/Materials/distribution4.png)

### Graphical User Interfaces
We have GUI for displaying the evolution information, and can generate a report about the generic trend. The effect images are shown as the follows.

- Control panel
![Control panel](https://github.com/ZigengFU/INFO6205_501/blob/master/Analysis/effect%20images/GUI%201.png)

- Report panel
![Report panel](https://github.com/ZigengFU/INFO6205_501/blob/master/Analysis/effect%20images/GUI%202.png)

## Launching Instructions
To launch and view the outputs of our GA program, please follow the following steps:
1. Clone "INFO6205_501" to your local path;
2. Open the project using IDE (NetBeans recommended);
3. Download dependent org.zip file by the google link: https://drive.google.com/file/d/1OLRGcEJIyQL_drWWOs6frtFfQX7xg_I0/view?usp=sharing;
4. Add "org.zip" into project library;
5. Run the project and wait for the GUI present, click "Start" button to run the evolution and then click "Report" button to see the result;
6. View the output data result which containing the information about the first and the last generation in "GA_Team501/codes/src/results.csv".
