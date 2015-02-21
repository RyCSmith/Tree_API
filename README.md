# Tree_API
Overview: API for Tree and Expressions data structures.

Design: This project was assigned by Dr. Dave Matuszek at the University of Pennsylvania. Concept structure and was provided. All implementation completed by Ryan Smith.

Use: There are 2 APIs in this project. The primary is a Tree. The structure is of generic type and can be assigned any object or primitive value. It maintains an ArrayList of its children and has no limit on how many it can have. A variety of methods for working with the tree are provided. For Trees holding String values, the parse() method is particularly useful. It takes a String of the form "Zero (One (Seven Eight) Two (Three Four))" and constructs a Tree stucture from it.

The secondary API is titled Expressions. It constructs a tree structure for storing and working with arithmetic expressions. This uses the Tree structure and can parse and then evaluate String arguments such as "+(/ (+ (5 10 -( *(15 20) 25) 30) 3)3 4)".

Other Info: Javadocs are provided in a zip file.