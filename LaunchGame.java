import java.util.Random;
import java.util.Scanner;

class TicTacToe{
    static char[][] board;

    public TicTacToe()
    {
        board = new char[3][3];
        initBoard();
    }

    void initBoard()
    {
         for(int i=0; i<board.length; i++)
         {
            for(int j=0; j<board[i].length; j++)
             {
                board[i][j] = ' ';
             }
         }    
    }
        
    static void dispBoard()
    {
        System.out.println("-------------");
        for(int i=0; i<board.length; i++)
         {
            System.out.print("| ");
            for(int j=0; j<board[i].length; j++)
             {
                System.out.print(board[i][j] + " | ");
             }
             System.out.println();
             System.out.println("-------------");   
         }
    }


    static void placeMark(int row, int col, char mark)
    {
        if(row >= 0 && row <= 2 && col >= 0 && col <= 2)
        {
            board[row][col] = mark;
        }
        else{
            System.out.println("Invalid position");
        }
    }

    static boolean checkRowWin()
    {
        for(int i=0;i<=2;i++)
        {
            if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
            {
                return true;
            }
        }
        return false;
    }

    static boolean checkColWin()
    {
        for(int j=0;j<=2;j++)
        {
            if(board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
            {
                return true;
            }
        }
        return false;
    }


    static boolean checkDiaWin()
    {
        
            if(board[0][0] != ' ' &&  board[0][0] == board[1][1] && board[1][1] == board[2][2] || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            {
                return true;
            }
       
            else
            {
                return false;
            }   
    }

    static boolean checkWin() {
        return checkRowWin() || checkColWin() || checkDiaWin();
    }

    static boolean checkDraw()
    {
        for(int i=0;i<=2;i++)
        {
            for(int j=0;j<=2;j++)
            {
                if(board[i][j] == ' ')
                {
                    return false;
                }
            }
        }
        return true;
    }
}


abstract class Player
{
    String name;
    char mark;

    abstract void makeMove();

    boolean isValidMove( int row, int col)
    {
        if(row>=0 && row<=2 && col>=0 && col<=2)
        {
                return TicTacToe.board[row][col] == ' ';
        }
        return false;
    }
}

    


class HumanPlayer extends Player
{

    HumanPlayer(String name, char mark)
    {
        this.name = name;
        this.mark = mark;
    }

    void makeMove()
    {

       Scanner scan = new Scanner(System.in);
       scan.close();
       int row;
       int col;
       do
       {
        System.out.println("Enter your move (row and column between 0 and 2):");
        row = scan.nextInt();
        col = scan.nextInt();
       } while(!isValidMove(row, col));

       TicTacToe.placeMark(row, col, mark);
    }
}


class AIPlayer extends Player
{
    AIPlayer(String name, char mark)
    {
        this.name = name;
        this.mark = mark;
    }

    void makeMove()
    {
       Random r = new Random();
       int row;
       int col;
       do
       {
        row = r.nextInt(3);
        col = r.nextInt(3);
       } while(!isValidMove(row, col));
       
       System.out.println("AI chooses position: " + row + " " + col);
       TicTacToe.placeMark(row, col, mark);

    }
}


public class LaunchGame
{
    public static void main(String[] args)
    {
        TicTacToe t = new TicTacToe();

        HumanPlayer p1 = new HumanPlayer("You", 'X');
        AIPlayer p2 = new AIPlayer("AI", 'O');

        Player cp = p1;

        while(true)
        {
            System.out.println(cp.name + "'s turn");
            cp.makeMove();
            TicTacToe.dispBoard();

            if(TicTacToe.checkWin())
            {
            if(cp==p1)
            {
            System.out.println("You have won!");
            }
            else
            {
            System.out.println("AI has won!");
            }
            break;
            }
            else if(TicTacToe.checkDraw())
            {
                System.out.println("Game is a draw.");
                break;
            }
            else
            {
            if(cp == p1)
            {
                cp = p2;
            }
            else
            {
                cp = p1;
            }
            }
        }


    }
}
