Reviewer: Hua Guo (u6419283)
Component: class StartingState
Author: Mei Yee Chin (u6700948)

Review Comments:

1. What are the best features of this code?
The best feature is use different methods to classify the different difficult level of this IQ-TWIST game. It is easy to call different method to set
start statement for this game. And use random method to output a random placement in this difficult placement array.


2. Is the code well-documented?
The documentation of this class are good but can add some comments for codes. Some method don't have documentations, like "Starter", "Junior",
"Expert", "Master", "Wizard", which can be improved.


3. Is the program decomposition (class and method structure) appropriate?
The program involves 5 seperate methods for five difficult level of starting statement. And each methods store corresponding difficult starting
statement arrays. The program can call different method when the game player choose difficult level and output a corresponding difficulty level game.
So it is decomposed appropriate with good structure.



4. Does it follow Java code conventions, and is the style consistent throughout?
The code follow Java code convention and the style is consistent with previous codes. But it can add more comments and documentation for each method.


5. Suggest a particular situation in which the program will not function correctly.
In fact, this class is not finished yet but most of the frameworks have been completed, like using different method to category difficult level.
The author of these codes should write codes to call different difficult methods, for example, it can be written by using switch statement.

In addition, placements in statement arrays are invalid, for example, in "Starter" method, the first placement is "26l27l28j29j30k31k", which is not
well formed. The author need to check the starting placements format and store valid placements in this class.

