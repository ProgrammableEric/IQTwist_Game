Reviewer: Mei Yee Chin (u6700948)
Component: assignment task 7
Author: Chunze Fu (u5885811)

Review Comments:

1. What are the best features of this code?
The attention to detail to make the game board easily played and "bugless". Eric has run into some problems with placing pieces on the grid. Instead
of snapping the pieces precisely onto squares of the grid, the pieces get stuck onto intersections of the grid. He has fixed this problem by making
adjustments to the snapToGrid method by implementing more refined versions of getLayout functions.
The size of the grid corresponds to the size of the pieces and the layout of the game board is set out clearly. Pieces can be dragged, dropped and
rotated successfully. A possible improvement that can be made is by including a reset button so that the player do not have to drag every single piece
away from the grid to start over.


2. Is the code well-documented?
Yes, the comments before the codes are very helpful because it gives the reader an explanation of what the code implements. The comments were written
clearly and are good representations of the code. However, it can be more concise as some comments were too elaborated.
As this class contains many methods and calls for different classes, Eric have used multiple inner classes which allows easier reference and
maintenance. Using these inner classes also increases encapsulation. Layout of the code is tidy and arranged in a logical way (i.e: MousePressed ->
MouseDragged -> MouseReleased).


3. Is the program decomposition (class and method structure) appropriate?
Yes. Although this class is complicated and involves many other classes and functions, it does not take long to start running. This signifies low
redundancy. However, unused import statements and fields and redundant castings can be removed to further refine the class.


4. Does it follow Java code conventions, and is the style consistent throughout?
Yes. In this class, five switch statements were used. the switch statements were repetitive and relatively lengthy. Because the structure of the
statements are consistent (i.e equal spacing, breaking), this makes it easily understandable. However, a default statement may be inserted if a case
does not fall through the cases. Many if-else statements were also used and were kept consistent throughout (i.e conditions were given, braces were
used).


5. Suggest a particular situation in which the program will not function correctly.
When the last piece with a solid fill is placed on a peg of its own colour, the game displays "Well done!". This is evidently incorrect as a filled
piece should not be able to be placed on a peg. Eric suggested that the inner class DraggablePiece has mistakes and will be fixed.

