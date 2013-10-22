Sokoban Solver
==============

Stephen Zhou
szz2002
Fall 2013 AI Assignment 2

Files
-----

/
    README.md                   -       You're here, silly!
    resource/                   -       Sokoban tests
        simple.txt              -       Simple test from assignment
        moderate-6.txt          -       Moderate test from puzzles on website
        3-Sophia.txt            -       Simple puzzle from JSoko
        27-Kristi.txt           -       Relatively hard puzzle from JSoko
    src/                        -       Source files
        ...                     -       Too many to list. Read docs for info

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

Sample Output
-------------

### simple

    ####  
    # .#  
    #  ###
    #*@  #
    #  $ #
    #    #
    ######

BFS

    ####  
    # *#  
    # @###
    #*   #
    #    #
    #    #
    ######

    Solution: r, r, d, l, d, l, u, u, u
    Nodes explored: 633
    Previously seen: 151
    Fringe: 196
    Explored set: 286
    Millis elapsed: 268

DFS
    ####  
    # *#  
    # @###
    #*   #
    #    #
    #    #
    ######

    Solution: d, l, d, r, r, r, u, l, d, l, l, u, u, d, d, r, r, r, u, l, u, l, u, u, l, d, d, r, r, d, d, l, u, d, l, u, d, r, r, r, u, l, l, u, u
    Nodes explored: 808
    Previously seen: 204
    Fringe: 34
    Explored set: 570
    Millis elapsed: 61

UFS

    ####  
    # *#  
    # @###
    #*   #
    #    #
    #    #
    ######

    Solution: r, r, d, l, d, l, u, u, u
    Nodes explored: 980
    Previously seen: 350
    Fringe: 219
    Explored set: 411
    Millis elapsed: 86

Greedy BFS Box-goal

    ####  
    # *#  
    # @###
    #*   #
    #    #
    #    #
    ######

    Solution: d, l, d, r, r, u, l, l, d, r, r, r, u, u, l, d, l, u, u
    Nodes explored: 135
    Previously seen: 6
    Fringe: 43
    Explored set: 86
    Millis elapsed: 14

Greedy BFS Manhattan

    ####  
    # *#  
    # @###
    #*   #
    #    #
    #    #
    ######

    Solution: d, l, d, r, r, u, r, u, l, d, l, u, u
    Nodes explored: 50
    Previously seen: 1
    Fringe: 25
    Explored set: 24
    Millis elapsed: 7

A\* Box-goal

    ####  
    # *#  
    # @###
    #*   #
    #    #
    #    #
    ######

    Solution: d, l, d, r, r, u, l, l, d, r, r, r, u, u, l, d, l, u, u
    Nodes explored: 136
    Previously seen: 6
    Fringe: 44
    Explored set: 86
    Millis elapsed: 20

A\* Manhattan

    ####  
    # *#  
    # @###
    #*   #
    #    #
    #    #
    ######

    Solution: d, l, d, r, r, u, r, u, l, d, l, u, u
    Nodes explored: 51
    Previously seen: 1
    Fringe: 26
    Explored set: 24
    Millis elapsed: 6

### Sophia

    #######
    #     #
    #@$.# #
    #*$  .#
    # $$  #
    # . . #
    #######

BFS

    #######
    #     #
    #  *# #
    #*   *#
    #   @ #
    # * * #
    #######

    Solution: d, r, r, r, d, d, l, l, l, u, u, r, d, r, u, u, u, l, l, d, r, d,
r, r, d
    Nodes explored: 1018557
    Previously seen: 625397
    Fringe: 233206
    Explored set: 159954
    Millis elapsed: 10516

DFS

    #######
    #     #
    # @*# #
    #*   *#
    #     #
    # * * #
    #######

    Solution: d, r, l, u, r, l, d, r, r, l, l, u, r, u, r, d, l, l, u, r, r, r, r, d, d, d, l, d, l, l, l, u, d, r, r, r, r, u, u, u, u, l, l, l, l, d, r, d, d, l, d, r, l, u, r, r, l, l, d, r, r, l, l, u, r, r, u, l, d, l, u, d, d, r, r, u, l, u, u, u, l, d, d, r, u, l, u, r, r, d, l, l, u, r, r, r, r, d, d, d, l, l, d, l, l, u, d, r, r, u, r, r, u, u, u, l, l, l, l, d, r, d, d, l, u, d, r, r, r, r, u, u, u, l, l, l, l, d, r, r, u, r, r, d, d, d, l, l, u, l, d, l, u, d, r, r, r, r, u, l, l, d, l, l, u, r, l, d, r, r, r, r, u, u, u, l, l, l, l, d, r, d, d, r, r, r, u, l, l, d, l, u, d, l, u, d, r, r, r, r, u, u, u, l, l, l, l, d, u, r, d, l, u, r, r, d, l, l, u, r, r, r, r, d, d, l, d, l, l, l, u, d, r, u, l, d, r, r, r, r, u, u, u, l, l, l, l, d, u, r, r, r, r, d, d, l, d, l, l, u, r, l, d, l, u, d, r, r, r, r, u, u, u, l, l, l, l, d, u, r, d, l, u, r, r, d, d, d, l, l, u, d, r, r, r, r, u, u, u, l, l, l, d, r, d, r, l, d, l, l, u, r, l, d, r, r, r, r, u, l, l, d, l, l, u, r, l, d, r, r, u, l, u, u, l, d, r, u, r, d, l, l, u, r, r, r, r, d, u, l, l, l, l, d, r, d, d, r, r, r, u, l, l, d, l, u, d, l, u, d, r, r, r, r, u, l, l, u, u, l, l, d, u, r, d, l, u, r, r, r, r, d, u, l, l, l, l, d, r, r, d, d, l, u, d, l, u, d, r, r, r, u, l, u, u, l, l, d, r
    Nodes explored: 400513
    Previously seen: 97934
    Fringe: 239
    Explored set: 302340
    Millis elapsed: 5658

UFS

    #######
    #     #
    #  *# #
    #*   *#
    #   @ #
    # * * #
    #######

    Solution: r, l, d, r, r, r, d, d, l, l, l, u, d, r, r, r, u, u, l, l, d, r, u, r, d
    Nodes explored: 798010
    Previously seen: 481585
    Fringe: 167737
    Explored set: 148688
    Millis elapsed: 14218

Greedy BFS Box-goal

    #######
    #     #
    #  *# #
    #*   *#
    # @   #
    # * * #
    #######

    Solution: r, u, r, r, r, d, d, l, l, d, l, u, d, l, l, u, r, u, d, l, d, r, r, r, r, u, u, u, l, d, r, d, l, u, l, l, d, r, r, u, r, r, u, u, u, l, l, l, l, d, d, r, r, r, l, l, l, u, u, r, d, d, r, r, d, l, d, l, l, u, r, u, r, r, d, l, u, l, d
    Nodes explored: 80691
    Previously seen: 26760
    Fringe: 7412
    Explored set: 46519
    Millis elapsed: 969

Greedy BFS Manhattan

    #######
    #     #
    #  *# #
    #*   *#
    #   @ #
    # * * #
    #######

    Solution: r, l, d, r, r, r, d, d, l, l, l, u, d, r, r, r, u, u, l, l, d, r, u, r, d
    Nodes explored: 212834
    Previously seen: 68497
    Fringe: 21098
    Explored set: 123239
    Millis elapsed: 3288

A\* Box-goal

    #######
    #     #
    #  *# #
    #*  @*#
    #     #
    # * * #
    #######

    Solution: r, u, r, r, r, d, d, l, l, d, l, u, r, d, l, l, l, u, r, u, r, d, l, l, d, r, r, u, r, r, u, u, u, l, l, l, d, d, d, r, r, r
    Nodes explored: 46657
    Previously seen: 16714
    Fringe: 3595
    Explored set: 26348
    Millis elapsed: 602

A\* Manhattan

    #######
    #     #
    #  *# #
    #*   *#
    #   @ #
    # * * #
    #######

    Solution: r, l, d, r, r, r, d, d, l, l, l, u, d, r, r, r, u, u, l, l, d, r, u, r, d
    Nodes explored: 173403
    Previously seen: 55158
    Fringe: 15790
    Explored set: 102455
    Millis elapsed: 2604

### medium-6

      ######
      # ..@#
      # $$ #
      ## ###
       # #  
       # #  
    #### #  
    #    ## 
    # #   # 
    #   # # 
    ###   # 
      ##### 

BFS

      ######
      # ** #
      # @  #
      ## ###
       # #  
       # #  
    #### #  
    #    ## 
    # #   # 
    #   # # 
    ###   # 
      ##### 

    Solution: l, l, d, d, d, d, d, d, l, d, d, d, r, r, u, u, l, u, u, u, u, u, u, u, r, r, d, l, u, l, d, d, d, d, d, d, l, l, l, d, d, r, r, u, d, l, l, u, u, r, r, d, d, d, r, r, u, u, l, u, u, u, u, u, u, l, u, r, d, d, d, d, d, d, l, l, l, d, d, r, r, u, d, l, l, u, u, r, r, d, r, u, u, u, u, u, u
    Nodes explored: 7569
    Previously seen: 3271
    Fringe: 143
    Explored set: 4155
    Millis elapsed: 162

DFS

      ######
      # ** #
      # @  #
      ## ###
       # #  
       # #  
    #### #  
    #    ## 
    # #   # 
    #   # # 
    ###   # 
      ##### 

    Solution: l, l, d, d, d, d, d, d, l, l, l, d, d, r, r, d, r, r, u, u, l, r, d, d, l, l, u, l, l, u, u, r, r, r, u, u, u, u, u, l, u, r, r, r, d, l, r, u, l, l, d, d, d, d, d, d, l, l, l, d, d, r, r, u, d, l, l, u, u, r, r, l, l, d, d, r, r, d, r, r, u, u, l, r, d, d, l, l, u, l, l, u, u, r, r, d, r, u, l, d, r, r, d, d, l, l, u, l, l, u, u, r, r, r, u, d, l, l, l, d, d, r, r, u, d, d, r, r, u, u, l, u, l, d, d, d, r, r, u, u, l, u, u, u, d, d, l, d, d, l, l, u, u, r, l, d, d, r, r, d, r, r, u, u, l, u, u, u, u, d, d, d, l, d, d, d, r, r, u, u, l, u, u, u, u, u, d, d, d, d, l, d, d, l, l, u, u, r, l, d, d, r, r, d, r, r, u, u, l, u, u, u, u, u, u, l, u, r, l, d, r, d, d, d, d, d, l, d, d, l, l, u, u, r, r, l, l, d, d, r, r, d, r, r, u, u, l, u, u, u, u, u, u
    Nodes explored: 2186
    Previously seen: 255
    Fringe: 66
    Explored set: 1865
    Millis elapsed: 110

UFS

      ######
      # ** #
      # @  #
      ## ###
       # #  
       # #  
    #### #  
    #    ## 
    # #   # 
    #   # # 
    ###   # 
      ##### 

    Solution: l, l, d, d, d, d, d, d, l, d, d, d, r, r, u, u, l, u, u, u, u, u, u, u, r, r, d, l, u, l, d, d, d, d, d, d, l, l, l, d, d, r, r, u, d, l, l, u, u, r, r, d, d, d, r, r, u, u, l, u, u, u, u, u, u, l, u, r, d, d, d, d, d, d, d, r, d, d, l, l, u, u, d, l, l, u, u, r, r, d, r, u, u, u, u, u, u
    Nodes explored: 7852
    Previously seen: 3544
    Fringe: 89
    Explored set: 4219
    Millis elapsed: 202

Greedy BFS Box-goal

      ######
      # ** #
      # @  #
      ## ###
       # #  
       # #  
    #### #  
    #    ## 
    # #   # 
    #   # # 
    ###   # 
      ##### 

    Solution: l, l, d, d, d, d, d, d, l, l, l, d, d, r, r, d, r, r, u, u, l, r, d, d, l, l, u, l, l, u, u, r, r, r, u, u, u, u, u, l, u, r, r, r, d, l, r, u, l, l, d, d, d, d, d, d, l, l, l, d, d, r, r, u, d, l, l, u, u, r, r, l, l, d, d, r, r, d, r, r, u, u, l, r, d, d, l, l, u, u, d, d, r, r, u, u, l, u, l, d, r, r, u, d, l, d, d, l, l, u, u, r, l, d, d, r, r, d, r, r, u, u, l, u, u, u, d, d, l, d, d, d, r, r, u, u, l, u, u, u, u, d, d, d, l, d, d, l, l, u, u, r, l, d, d, r, r, d, r, r, u, u, l, u, u, u, u, u, u, d, d, d, d, d, l, r, u, u, u, u, u, l, u, r, l, d, r, d, d, d, d, d, l, d, d, l, l, u, u, r, r, d, r, u, u, u, u, u, u
    Nodes explored: 2074
    Previously seen: 432
    Fringe: 134
    Explored set: 1508
    Millis elapsed: 140

Greedy BFS Manhattan

      ######
      # ** #
      # @  #
      ## ###
       # #  
       # #  
    #### #  
    #    ## 
    # #   # 
    #   # # 
    ###   # 
      ##### 

    Solution: l, l, d, d, d, d, d, d, l, d, d, d, r, r, u, u, l, r, d, d, l, l, u, l, l, u, u, r, r, r, u, u, u, u, u, l, u, u, r, r, d, l, u, l, d, d, d, d, d, d, l, l, l, d, d, r, r, u, d, l, l, u, u, r, r, d, d, d, r, r, u, u, l, u, u, u, u, u, u, l, u, r, d, d, d, d, d, d, l, l, l, d, d, r, u, u, d, l, l, u, u, r, r, d, r, u, u, u, u, u, u
    Nodes explored: 2823
    Previously seen: 630
    Fringe: 42
    Explored set: 2151
    Millis elapsed: 214

A\* Box-goal

      ######
      # ** #
      # @  #
      ## ###
       # #  
       # #  
    #### #  
    #    ## 
    # #   # 
    #   # # 
    ###   # 
      ##### 

    Solution: l, l, d, d, d, d, d, d, l, l, l, d, d, r, r, d, r, r, u, u, l, r, d, d, l, l, u, l, l, u, u, r, r, r, u, u, u, u, u, l, u, r, r, r, d, l, r, u, l, l, d, d, d, d, d, d, l, l, l, d, d, r, r, u, d, l, l, u, u, r, r, l, l, d, d, r, r, d, r, r, u, u, l, u, l, l, l, d, d, r, r, u, d, d, r, r, u, u, l, u, l, d, r, r, u, d, l, d, d, l, l, u, u, r, l, d, d, r, r, d, r, r, u, u, l, u, u, u, d, d, l, d, d, d, r, r, u, u, l, u, u, u, u, d, d, d, l, d, d, l, l, u, u, r, l, d, d, r, r, d, r, r, u, u, l, u, u, u, u, u, u, d, d, d, d, d, l, r, u, u, u, u, u, l, u, r, d, d, d, d, d, d, d, l, d, l, l, u, u, r, r, d, r, u, u, u, u, u, u
    Nodes explored: 2770
    Previously seen: 560
    Fringe: 103
    Explored set: 2107
    Millis elapsed: 165

A\* Manhattan

      ######
      # ** #
      # @  #
      ## ###
       # #  
       # #  
    #### #  
    #    ## 
    # #   # 
    #   # # 
    ###   # 
      ##### 

    Solution: l, l, d, d, d, d, d, d, l, d, d, d, r, r, u, u, l, r, d, d, l, l, u, l, l, u, u, r, r, u, u, u, u, u, u, u, r, r, d, l, u, l, d, d, d, d, d, d, l, l, l, d, d, r, r, u, d, l, l, u, u, r, r, d, d, d, r, r, u, u, l, u, u, u, u, u, u, l, u, r, d, d, d, d, d, d, l, l, l, d, d, r, u, u, d, l, l, u, u, r, r, d, r, u, u, u, u, u, u
    Nodes explored: 2797
    Previously seen: 598
    Fringe: 44
    Explored set: 2155
    Millis elapsed: 177

### Kristi

    #######
    # @   #
    #$* **#
    #. #$ #
    # . $ #
    #  .  #
    #######

BFS
    
    Out of Memory Exception

DFS

    #######
    #     #
    # * **#
    #* #@ #
    # *   #
    #  *  #
    #######

    Solution: l, d, d, r, d, d, r, r, r, u, l, d, l, l, u, u, l, u, u, r, r, r, r, d, l, d, u, r, d, l, u, r, u, l, l, l, l, d, d, r, d, d, l, u, d, r, r, r, r, u, d, l, l, l, l, u, u, d, d, r, r, r, r, u, u, l, u, u, l, l, l, d, d, r, d, d, l, u, d, r, r, r, r, u, u, l, u, u, l, l, d, l, d, u, r, d, l, u, r, u, r, r, d, l, r, d, r, d, d, l, l, l, l, u, d, r, r, r, r, u, u, l, u, l, u, l, l, d, r, l, u, r, r, r, d, d, r, d, d, l, l, l, l, u, u, d, d, r, r, r, r, u, u, l, u, u, l, l, d, d, d, l, d, r, l, u, r, u, u, u, l, d, d, r, d, d, l, u, d, r, r, l, l, u, r, u, u, l, u, r, r, r, d, l, r, d, r, d, d, l, u, l, r, u
    Nodes explored: 1714975
    Previously seen: 353735
    Fringe: 101
    Explored set: 1361139
    Millis elapsed: 45105

UFS

Greedy BFS Box-goal

    #######
    #     #
    # * **#
    #* #  #
    # *@  #
    #  *  #
    #######

    Solution: l, d, d, u, u, r, r, r, r, d, l, u, l, l, l, d, d, r, d, d, r, r, r, u, u, d, l, u, d, d, l, l, u, u, l, u, u, r, d, d, u, u, r, r, d, l, r, d, r, d, d, l, l, l, l, u, d, r, r, r, r, u, u, l, u, l, u, l, l, d, r, u, r, r, d, d, r, d, d, l, u, u, d, d, l, l, l, u, u, r, u, u, l, d, r, d, d, l, d, r, u, u, u, u, r, r, d, l, r, d, r, d, d, l, u, u, d, l
    Nodes explored: 1684551
    Previously seen: 530587
    Fringe: 105738
    Explored set: 1048226
    Millis elapsed: 47175

Greedy BFS Manhattan

    #######
    #     #
    # * **#
    #* #  #
    # *@  #
    #  *  #
    #######

    Solution: l, d, u, r, r, r, r, d, l, u, l, l, l, d, d, r, d, d, r, r, r, u, u, d, l, d, l, l, u, u, l, u, u, r, d, d, u, u, r, r, d, l, r, d, r, d, d, l, l, l, l, u, d, r, r, r, r, u, u, l, u, l, u, l, l, d, r, u, r, r, d, d, r, d, d, l, u, u, d, d, l, l, l, u, u, r, u, u, l, d, r, d, d, l, d, r, u, u, u, u, r, r, d, l, r, d, r, d, d, l, u, u, d, l
    Nodes explored: 530912
    Previously seen: 128970
    Fringe: 89015
    Explored set: 312927
    Millis elapsed: 9735

A\* Box-goal

    #######
    #     #
    # * **#
    #* #  #
    # *@  #
    #  *  #
    #######

    Solution: d, d, u, r, u, r, r, d, l, u, l, l, d, d, l, d, d, r, r, r, r, u, u, d, l, u, d, d, l, l, l, u, u, r, u, u, l, d, r, d, d, l, d, r, u, u, u, u, r, r, d, l, r, d, r, d, d, l, u, u, d, l
    Nodes explored: 1483319
    Previously seen: 437633
    Fringe: 115700
    Explored set: 929986
    Millis elapsed: 40355

A\* Manhattan

    #######
    #     #
    # * **#
    #* #  #
    # *@  #
    #  *  #
    #######

    Solution: l, d, d, r, d, r, d, r, r, u, l, d, l, l, u, u, l, u, u, r, r, r, r, d, l, u, l, l, l, d, d, r, d, d, r, r, r, u, u, d, l, d, l, l, u, u, l, u, u, r, d, d, u, u, r, r, d, l, r, d, r, d, d, l, l, l, l, u, d, r, r, r, r, u, u, l, u, u, l, l, l, d, r, u, r, r, d, d, r, d, d, l, u, u, d, d, l, l, l, u, u, r, u, u, l, d, r, d, d, l, d, r, u, u, u, u, r, r, d, l, r, d, r, d, d, l, u, u, d, l
    Nodes explored: 632363
    Previously seen: 166675
    Fringe: 96468
    Explored set: 369220
    Millis elapsed: 11384
