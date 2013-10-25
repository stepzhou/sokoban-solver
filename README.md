Sokoban Solver
==============

Stephen Zhou

szz2002

Fall 2013 AI Assignment 2

Goal
----

I tried to make this program as OO as possible! Fun stuff.

Heuristic Idea Source
---------------------

    http://fragfrog.nl/papers/solving_the_sokoban_problem.pdf

Specifically:

"In our case, some good heuristics, which contribute to the score of a map,
are: the number of boxes on goal positions, the distance from the boxes to the
goals, the distance from the player to the closest box."

Usage
-----

Compile files

    cd src
    javac *.java

The main method is SokobanMain. The program takes command-line arguments with
the following options.

    java SokobanMain [-option] [Sokoban input file]

    Options
        -b      Breadth-first search
        -d      Depth-first search
        -u      Uniform-cost search (move = 1, push = 2)
        -gb     Greedy best-first search with number of boxes on goal heuristic
        -gm     Greedy best-first search with Manhattan distances heuristic
        -ab     AStar with number of boxes on goal heuristic
        -am     AStar with goals and boxes Manhattan distances heuristic

NO command-line input validation is done. It's a very stupid command-line as I
didn't want to spend much time on it to make it nice and pretty.

Input
-----

The Sokoban files must be in the following format.

    [Number of columns]
    [Number of rows]
    [Rest of the puzzle]

With the following state mappings.

    #   (hash)      Wall 
    .   (period)    Empty goal 
    @   (at)        Player on floor 
    \+   (plus)      Player on goal 
    $   (dollar)    Box on floor 
    \*   (asterisk)  Box on goal 

Output
------

The output is in the following format.

    1. String representation of initial state
    2. String representation of the final state
    3. Move solution
    4. Number of nodes explored
    5. Number of previously seen nodes
    6. Number of nodes at the fringe
    7. Number of explored nodes
    8. Time elapsed in millis

