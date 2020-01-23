# DESIGN.md File

* Tyler Meier, tkm22

* My role in developing the project: designing and coding the 
entire project and coming up with a plan for it, doing all front
end and back end coding

* A lot of the design goals for the project deal with the 
things we talked about in class, such as making sure to not
have duplicated code or code that isn't needed, naming variables
so that a user could tell their exact use, making sure to
have instance variables and few local variables, etc. I wanted 
it to be easy to add power-ups and different types of bricks and
features to each of the other classes, 
by just going into the class and changing things or creating 
a different class for power-ups but with the same type of set up
as the other ones. 

* The purpose of each class is to mimic each main attribute of
the overall game and be able to easily  change properties of each specific 
thing, meaning a different class for the bouncer, paddle, brick,
and each power-up. I did this so it would be easier to just
go to that class and add a method or change something quickly.
This also organizes things in a way that a  user would know 
where code needed to be added/changed/or looked at to see a 
specific attribute about something. The main class is where all
of the game play is setup and actually updated and such, and also
where all of the screens are, but throughout the main class, the 
other classes are called upon to put them into the game play and to 
change their attributes. The other classes also work with each other 
in their respective classes as well, such as in the bouncer class, 
the paddle and brick classes are called to check if the ball hits 
either of them at any time. All of the classes are working 
together in a way to create the entire game.

* Again, adding multiple classes really helped to simplify the 
code and to allow the overall code be easy to read and more clean.
I also decided to split up the power-ups into separate classes 
because of how I was adding them into the game play, and I thought
that this might be better for the design, but it also could have 
been more clean to put them all into one class. To be quite honest,
I really thought I was going to drop the class halfway through the 
week, but near  the end of the week I got ahead on my code and realized
that I could finish a lot more than I had anticipated. However, because
of this, I was coding right up until the end and didn't really have time
to focus on making sure my design was simplified as much as possible
in the end and that the design methods were the best, so it could definitely
have an overall better design to it. This is something I am hoping to make sure
to do and plan for in future projects.

* So for the last power-up, in order to add, in my code I would
just make another class for my new power-up and implement it into the game play,
similar to how I did the other ones. To add new features to any of the already
established objects in the game (bouncer, paddle, brick) you could just add
new methods to the classes for each of these and call them when
needed in the main class to update or change them. Since I didn't finish
the status display, and tried to implement it quickly at the end, 
after asking around and doing more research online I would probably use something
like an Hbox and labels, and then update the score and lives in the step method like
most of the other things I update in there. For the bricks and the hit differences,
of each, I had a hard time implementing a way in which they each took a 
different amount of hits to disappear and so didn't get to that in time. However, now
that I have had time to think about it more, I've had a general idea that I would
need to create a global variable of some sort called numHits, and add a property/parameter
to the bricks themselves which takes in this numHits and updates it for each brick, just like 
it does for the x and y position of each. I would need to create another method as
well that subtracts the number of hits when a ball hits the bricks and updates/keeps track
of the hits already done for each specific brick (updating the numHits left for each one).
I also was not able to get to the something special that I wanted to do, such  as move the
bricks around or have the paddle disappear at certain points. If i wanted the bricks to move around, 
I could have possibly made a move method in the bricks class that allowed them to move on the screen,
and then call that in the main class. For having the paddle disappear, I could have had maybe after
a certain amount of bricks have been hit, make the ImageView of the paddle be null and disappear, and
in the keycode cheat codes, when the space bar is clicked, the imageView of the paddle is able to be 
seen again. I was able to implement all of my cheat codes, and if you wanted to add more
you could just go to the main class and under the handle key input method just
add what you want to happen when a certain key is pressed. I also got three
different size paddles in, and other features to the paddle could have been added 
in the paddle class. I never got to change the paddle where if it hit a certain side
of the paddle it would bounce in a certain direction because i had to know what direction
the ball was going in when it hit the paddle, something I couldn't figure out and 
didn't have enough time due to me focusing on getting the other basic implementations
done. However, to do this I would just need to have a variable set possibly that kept track
of the direction the ball was going in and if it hit the paddle on a certain side, check this variable
and respond in the right way that the ball would need ot go.


