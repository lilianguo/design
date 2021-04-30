/*
794. Valid Tic-Tac-Toe State
Medium

A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

Here are the rules of Tic-Tac-Toe:

    Players take turns placing characters into empty squares (" ").
    The first player always places "X" characters, while the second player always places "O" characters.
    "X" and "O" characters are always placed into empty squares, never filled ones.
    The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
    The game also ends if all squares are non-empty.
    No more moves can be played if the game is over.

Example 1:
Input: board = ["O  ", "   ", "   "]
Output: false
Explanation: The first player always plays "X".

Example 2:
Input: board = ["XOX", " X ", "   "]
Output: false
Explanation: Players take turns making moves.

Example 3:
Input: board = ["XXX", "   ", "OOO"]
Output: false

Example 4:
Input: board = ["XOX", "O O", "XOX"]
Output: true

Note:

    board is a length-3 array of strings, where each string board[i] has length 3.
    Each board[i][j] is a character in the set {" ", "X", "O"}.

注意理解题意: 可以达到当前的局面, 返回 true

只需要我们保证以下两点即可:

    轮流走棋, X先手, 所以棋盘中X的数量一定与O的数量相同或比它多1个
    只能有其中一人达到了胜利状态, 并且达到胜利状态的人一定是刚刚走过棋的

第1点很容易检查, 统计一下X和O的数量即可, 而第二点检查的方式比较多, 这里是这样的:

依次统计每条对角线, 每行每列的情况, 如果是X到达了胜利状态, 返回1, 如果是O, 返回-10, 把所有情况加和,

最后的结果只能是:

    0, 这代表还没有人胜利
    -10, 这代表O胜利了, 此时要求X和O的数量相同
    1或2, 这代表X胜利了, 有可能为2是这样的棋盘: ["XOX", "OXO", "XOX"], 也就是说X通过5个棋子达到了 "两连击". (不可能是两行或两列, 因为这样会花费6枚棋子, 无法通过第1点对数量的检查)

如果加和结果不是0, -10, 1, 2, 那么肯定是不可能达到的局面.

*/
class ValidTicTacToeState {
    public boolean validTicTacToe(String[] board) {
        int xCount = 0;
        int oCount = 0;
        char[] cArray = (board[0] + board[1] + board[2]).toCharArray();
        for (char c : cArray) {
            if (c == 'X') {
                xCount++;
            }
            if (c == 'O') {
                oCount++;
            }
        }
        if (oCount != xCount && oCount + 1 != xCount) {
            return false;
        }
        int winState = getWinState(cArray[0], cArray[4], cArray[8]);
        winState += getWinState(cArray[2], cArray[4], cArray[6]);
        for (int i = 0; i < 3; i++) {
            winState += getWinState(cArray[i * 3], cArray[i * 3 + 1], cArray[i * 3 + 2]);
            winState += getWinState(cArray[i], cArray[i + 3], cArray[i + 6]);
        }
        return winState == 0 || 
            ((winState == 1 || winState == 2) && oCount + 1 == xCount) || 
            (winState == -10 && oCount == xCount);
    }
    private int getWinState(char a, char b, char c) {
        if (a != ' ' && a == b && a == c) {
            return a == 'X' ? 1 : -10;
        } else {
            return 0;
        }
    }
}