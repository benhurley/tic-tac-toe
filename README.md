# Tic Tac Toe

Simple java program to play tic tac toe. Can play against another human player, or the computer. You can even make the computers play each other if you are really bored.

## Getting Started

To get started, grab the https link above and clone this repo to your local machine
```
git clone https://github.com/benhurley/tic-tac-toe.git
```

Next, run the following command with the makefile to build the executable JAR
```
make
```

Finally, run the executable
```
java -jar TicTacToe.jar
```

The above will default to the 2-person ode asking for keyboard entry. To play the computer, use the 'c' flag followed by which player number to assign to.

Example:
```
java -jar TicTacToe.jar -c 1
```
to have the computer play first (X's) as player 1 or
```
java -jar TicTacToe.jar -c 2
```
to have the computer play second (O's) as player 2

**Note: If a player number is not assigned, then both players will be assigned to computer players, and a simulation will occur.**

## Prerequisites

In order to clone and run this project, you will need the following:
1. java sdk (link to SE 11: https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html) 

## Acknowledgments

FSU Department of CS for project skeletons
