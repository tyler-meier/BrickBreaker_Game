# Game Plan
## Tyler Meier, tkm22


### Breakout Variant
One Breakout variant I thought was interesting was the Brick
n Balls variant. I liked how during the game the paddle shot
out multiple balls and ended when it reached its certain amount.
I also liked how you didn't necessarily complete a level and move
on. Instead, the layers of bricks just got lower after every 
shooting of the balls. However, I think this variant would 
be a lot to code due to the many factors that go into it, such
as the amount of balls on screen and the different ways they 
bounce after hitting bricks, the numbers on each brick, the
shapes and sizes of the bricks, the moving layers (and the 
combinations of each layer itself), etc. Just how the game play
is set up shows how much work was put/needs to be put into 
the creation of it and how complex the code must be. Also, 
another reason I liked this variant is because I've actually 
played this game (not this specific one but a game 
like it) and really enjoyed playing it.


### General Level Descriptions
Want to have at least three levels with each level harder to 
complete than the last. The first level will be set up in an 
arrangement that is not that hard, with an average moving speed
for the ball and a regular paddle size, with the bricks only being
1 and 2 hits to break. The setup would be something like:  
0 0 2 2 0 0     
1 0 1 0 1 0     
0 1 0 1 0 1     
0 0 0 0 0 0         
1 0 2 0 1 0     
0 1 0 2 0 1     
0 0 0 0 0 0     
1 1 0 0 1 1         
0 0 1 1 0 0     
0 0 0 0 0 0     
0 0 0 0 0 0     
The length may vary of the setup once I start coding but the general
idea and arrangement should stay the same.

For the second level, I want to speed up the ball and make the 
arrangement of the level harder, adding more bricks and ones that
are harder to break. In this level there will also be power ups added,
explained below in the power ups section. Below is rough design:    
0 3 2 2 3 0     
1 2 1 2 1 2     
2 1 2 1 2 1     
0 0 1 1 0 0         
2 0 2 0 2 0     
0 1 0 1 0 1     
0 0 0 0 0 0     
2 1 0 0 1 2         
0 0 3 3 0 0     
0 0 0 0 0 0     
0 0 0 0 0 0     

For the final level, I want to make the paddle shorter than it was,
while also rearranging the bricks in a slightly harder  arrangement
(more bricks, more hard to break ones) and possibly speed up ball again
depending on how fast I do for the second. Again, power ups will be 
present in this level. Below is another rough sketch for arrangement:   
0 3 4 4 3 0     
3 2 3 2 3 2     
4 2 2 2 2 4     
0 0 0 0 0 0         
2 3 2 3 2 3     
0 1 0 1 0 1     
0 0 0 0 0 0     
2 1 0 0 1 2         
2 0 2 0 2 0     
0 1 0 1 0 1     
0 0 0 0 0 0  

For the paddle, in all levels I want it to have the ability to hit the
ball in different directions depending on where it hits the paddle itself
(left side goes more left, middle goes straight, right side more right).
Talked already about how on the third level it gets smaller.
### Bricks Ideas
For my bricks, I want ones that take 1, 2, 3 and 4 hits to break to
increase the difficulty as the levels increase. I also want there to be
a brick that releases power ups (power ups described below), that when
hit will drop it and the paddle must catch the power up. I possibly might
have permanent bricks in the later levels to make it harder. I think
I will make the bricks different colors so that they differ between how
many times you need to hit them.

### Power Up Ideas
I want one of the power up ideas to be a second ball that comes out.
I want another one to be extension of the paddle, which should help on
the last level. I think a third cool power up would be one that allows
the ball to count as 2, meaning when it hits a brick it acts as two hits.
I might change the last one though if I think of something else.

### Cheat Key Ideas
For cheat keys, I will obviously include the ones needed (L, R and 1-9).
I was also thinking I could include "W" which means you automatically win,
or "D" which doubles your current score. I might also include a negative 
one "F" that just brings you back to the first level. I'm sure I might
think of more though.

### Something Extra
