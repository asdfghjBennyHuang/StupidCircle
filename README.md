# StupidCircle

Here are some java files for a simple game. I developped it for beating my friend.
The game is basicall like this: there is a triange<br><br>
.......1<br>
......1.1<br>
.....1.1.1<br>
....1.1.1.1<br>
...1.1.1.1.1<br>

Let's say the M th element in N th row is called (N, M)

You and another player take turn and pick. Each time the player can pick one to three pieces in line. 
For instance, you can pick {(1,1),(2,1)} or {(3,2)} or {(4,2),(4,3)} or {(2,2),(3,3),(4,4)}, 
but you can't pick {(1,1),(3,2)} or {(5,1),(5,4)} or {(5,1),(5,2),(5,5)}, for they are not connected or adjacent.

Whoever pick the last number loses the game.

StupidCircle.java is the entry point of hte program.

I use Monte Carlo method to get the must win strategy, the strategy is implemented in the fakeBrain.java and fakeplayer.java. I use a recursion to get the best option for each step.


