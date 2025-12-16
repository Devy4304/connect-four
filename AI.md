> This is just a planning page for my AI system

## Interface

The plan for the model is to use a generic interface, basically so that the user may select a model when the program starts. This will enable difficulty selection and allow for easier improvement and experimentation of new or different models.

### Model Selection

When the program is run, a menu like this or something will be shown.
```
    MODEL NAME          DIFFICULTY   SPEED
===========================================
[1] Custom Model        Medium       Decent
[2] Random Choice       Easy         Fast
```
### Model Usage

Each model will be a separate class that each has the same methods and parameters. So that hopefully a super class can be made that will allow the models to easily make objects and for them to work.

### Model Commands

#### ***int getMove()*** - gets the move to make (column)
 - Returns an int that determines the location of the next move to make
 - Any AI code will be initiated / ran from here

***If I add more commands, they will be here**

## Reflection

This was a challenge. Maybe not the "random" model, but the Minimax algorithm? Not so easy, and I don't want to have to that ever again (although I probably will).

> **Minimax** is a decision rule for _minimizing_ the possible loss for a worst case (maximum loss) scenario. When dealing with gains, it is referred to as "maximin" – to maximize the minimum gain. Originally formulated for several-player zero-sum game theory, covering both the cases where players take alternate moves and those where they make simultaneous moves, it has also been extended to more complex games and to general decision-making in the presence of uncertainty.
> 
> \- **Wikipedia**