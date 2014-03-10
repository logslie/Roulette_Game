## Roulette casino game.

On start-up, load a file which contains a list of player's names:

- Player1
- Player2

Once started, it should read lines from the console, each line will contain the player's name, what they want to bet on (either a number from 1-36, EVEN or ODD), and how much they want to bet:

- Player1 2 1.0
- Player2 EVEN 3.0

Every 30 seconds the game should choose a random number between 0 and 36 (inclusive) for the ball to land on. 

- If the number is 1-36 then any bet on that number wins, and the player wins 36x the bet's amount.
- If the number is even, any bet on EVEN wins, and the player wins 2x the bet's amount.
- If the number is odd, any bet on ODD wins, and the player wins 2x the bet's amount.
- All other bets lose.

The game should print to the console, the number, and for each bet, the player's name, the bet, whether they won or lost, how much they won:

Number: 4

| Player       | Bet  | OutCome | Winnings |
| -------------|-----:| -------:|---------:|
| Player1      | 2    | LOSE    | 0.0      |
| Player2      | EVEN | WIN     | 6.0      |


We'd like to print out the total amount a player has won and bet. To do this make the following changes. The file should have optional win and total bet columns:
Player1,1.0,2.0
Player2,2.0,1.0

After each number is chosen, also print out the totals in a tabular format:

| Player       | Bet  | OutCome | Winnings | Total Win | Total Bet |
| -------------|-----:| -------:|---------:| -------:|---------:|
| Player1      | 2    | LOSE    | 0.0      | 1.0     | 3.0      |
| Player2      | EVEN | WIN     | 6.0      | 8.0     | 4.0      |
