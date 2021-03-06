# C++ meets Scala

In this coursework you will be working on writing C++ code that follows the functional programming style you have been introduced to in Scala.

The main part is worth 6 marks, and is due in *Thursday the 7th of December at 11pm*.  Part (b), the advanced part, is worth 4 marks and is due in *Thursday the 21st of December at 11pm*.  Note you are *strongly encouraged* to begin the advanced part well before the end of term, to ensure you have the opportunity to ask any questions you may have about the work.

# Important note

Because this work is asking you to write code in a functional programming style, then unless a part of a question specifically says otherwise, I do not want you to write loops in a standard imperative programming style.  That is, the following are all forbidden:

- `for`, `while`, `do..while`
- The `for_each` function in the `<algorithm>` header file
- Anything that fakes one of the above (e.g. a combination of `if` and `goto`)

It is expected, instead, that you will use the `<algorithm>` or `<numeric>` functions such as those discussed in the lecture.

Moreover, if you are using a lambda that captures variables, then do not capture by reference, unless it is a reference to something const.  This is the same as the 'no var' rule in Scala -- lambda functions should not have side effects.

Capturing by value is always okay, e.g:

```c++
vector<int> numbers{1,2,3};
vector<int> biggerNumbers(3);
int x = 42;
std::transform(numbers.begin(), numbers.end(),
               biggerNumbers.begin(),
               [x](const int elem) {  
                   return elem + x;
               });
```

...because the lambda function cannot change the value of x from 42.

This is also okay:

```c++
void someFunction(const int & x) {
  vector<int> numbers{1,2,3};
  vector<int> biggerNumbers(3);
  int x = 42;
  std::transform(numbers.begin(), numbers.end(),
                 biggerNumbers.begin(),
                 [&x](const int elem) {
                     return elem + x;
                 });
}
```

...as although x is captured by reference, it's const, so the lambda can't change its value.  (In Scala terms: like a `val`, x is immutable.)

This though is very much *not* okay:

```c++
vector<int> numbers{1,2,3};
vector<int> biggerNumbers(3);
int x = 42;
std::transform(numbers.begin(), numbers.end(),
               biggerNumbers.begin(),
               [&x](const int elem) {
                   ++x;
                   return elem + x;
               });
```

... as the lambda is changing the value of the variable x.  (In Scala terms: like a `var`, x is mutable.)

A mark of zero will be awarded for any parts of questions breaking this rule.

# a) Knight's Tour [6 marks]

## Background

The *Knight???s Tour Problem* is about finding a tour such that the knight visits
every field on an *n x n* chessboard once. For example on a 5 x 5 chessboard, a
Knight???s tour is:

`24 11 _6 17 _0`  
`19 16 23 12 _7`  
`10 _5 18 _1 22`  
`15 20 _3 _8 13`  
`_4 _9 14 21 _2`
 
The tour starts in the right-upper corner, then down 2 and left 1; then down 2 and right 1; and so on.  There are no knight???s tours on 2 x 2, 3 x 3 and 4 x 4 chessboards,
but for every bigger board there is.

To recap, knights in chess move in an L shape: two steps in one direction, then one step perpendicular to this.  For a Knight in the middle of a board, its 8 possible moves are:

`.5.4.`  
`6...3`  
`..K..`  
`7...2`  
`.8.1.`

## Given code

You should complete all your work in the file `knights.h`.  This contains the code:

`typedef vector<pair<int,int> > Path;`

A typedef is a type definition: from this line onwards, if we write `Path` this is the same as writing `vector<pair<int,int> >`.  But, it saves typing, and is clear what the variable represents.

A `Path` is a sequence of pairs of row and column positions, giving the order in which the squares on the board should be visited.  Your code will be searching for a `Path` that is a tour: for a chess board of size *n* the Path should be of length *n x n*, should visit every square on the board only once, and all the moves should follow the rules of how a knight moves.

Also provided is the following snippet:

```c++
pair<int,int> operator+(const pair<int,int> & a, const pair<int,int> & b) {
    return make_pair(a.first + b.first, a.second + b.second);
}
```

This allows two pairs of ints to be added together using `+`.

## A `moves` function

Implement a function `moves` that takes a `pair<int,int>` representing a position on the board, and returns a `vector` containing all the positions that could be reached from there.  These should be in anti-clockwise order, starting at 6 o'clock.  For the example moves above, they would be added to the vector in the order shown (1 to 8).


## A `legal_moves` function

Implement a function `legal_moves` that takes:

- The size of the board (an integer, e.g. 8 for an 8x8 board)
- A Path
- A `pair<int,int>` representing a position on the board

This should use your `moves` function to find all the squares that can be reached from the given position, and then return a `vector` containing only those that are legal, i.e:

- The square is inside the board (no negative positions, does not equal or exceed the board size)
- The square is not in the given Path

## Finding the first tour

*NB This is the only place in the coursework you can use an imperative for loop.  It can be done without using a for loop, but it's a bit of a hack.*

Implement a function `first_tour` that takes:

- The size of the board (an integer, e.g. 8 for an 8x8 board)
- A Path

...and searches recursively for an open tour.  You should use your `legal_moves` function here; recursion can stops when a tour has been found.

The return type of `first_tour` should be `pair<Path,bool>`.  If the `bool` is true, the `Path` is a valid tour.  If a valid tour cannot be found, return a pair of an empty Path, and false.

## Testing your code

To test your code, compile and run `TestKnights.cpp`.  This uses `first_tour` to look for tour on boards of size 1 to 8.  If a tour is found, it prints out the resulting board.

# b) Earlier assignments, reloaded

## Trade [2 marks]

The price of a given commodity, can be represented by a vector of prices.  For instance:

`vector<int> prices {28, 18, 20, 26, 24};`  

To maximise profit, we would want to buy low and sell high -- in this case, buy at time 1 (when the cost is 18), sell at time 3 (when the cost is 26).

In `Trade.h` define a template function `bestProfit` that takes two iterators, denoting the begin() and end() of a range of prices, and returns an integer denoting the *best profit* that could be made (for the example above: 8).

*Note: as with part (a), imperative loops are forbidden, and you must not write any functions other than `bestProfit`, as specified here.*

To test your code, compile and run `TestTrade.cpp`.

## Collatz [2 marks]

In the file collatz.h, complete the definition of the function `collatz` to meet the specification of the equivalent function in assignment 6.  The outline function given is:

```c++
uint64_t collatz(uint64_t n) {
    // TODO: your code goes here
}
```

The type `uint64_t` is an unsigned 64-bit integer.  This is used here instead of the standard `int` type, as the numbers get quite big (just like `Long` was used in Scala.)

Next, complete the definition of the function `collatz_max` to meet the specification of the equivalent function in assignment 6.  It takes an upper-bound `bound` and returns a pair, where the first element is the length of the longest collatz sequence found for numbers in the range *1..bound*, and the second element is the number that had that collatz sequence.

To perform tests on your code, compile and run `TestCollatz.cpp`.

*Hint: `transform_reduce` is your friend here.  If you do use `transform_reduce`, then once your code is working, just for your own curiosity, try adding `-fopenmp` as an extra argument to the compiler.  This will use the multi-core version of `transform_reduce` -- you should notice the tests complete much faster.*
