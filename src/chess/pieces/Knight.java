package chess.pieces;

import chess.board.Board;
import chess.utils.Pair;

import java.util.LinkedList;

public class Knight extends Piece{

    public Knight(Team team, Board board, Pair<Integer, Integer> position) {
        super(board, team, position);
        board.set_piece(position.get_val1(), position.get_val2(), this);
    }

    @Override
    public LinkedList<Pair<Integer, Integer>> get_moves(){
        LinkedList<Pair<Integer, Integer>> moves = new LinkedList<>();
        //Collect all moves, filter later
        moves.add(new Pair<>(position.get_val1() + 1, position.get_val2() + 2));
        moves.add(new Pair<>(position.get_val1() + 1, position.get_val2() - 2));
        moves.add(new Pair<>(position.get_val1() - 1, position.get_val2() + 2));
        moves.add(new Pair<>(position.get_val1() - 1, position.get_val2() - 2));
        moves.add(new Pair<>(position.get_val1() + 2, position.get_val2() + 1));
        moves.add(new Pair<>(position.get_val1() + 2, position.get_val2() - 1));
        moves.add(new Pair<>(position.get_val1() - 2, position.get_val2() + 1));
        moves.add(new Pair<>(position.get_val1() - 2, position.get_val2() - 1));

        //remove out of bounds
        moves.removeIf(i ->
                i.get_val1() < 0 || i.get_val1() > 8 ||
                i.get_val2() < 0 || i.get_val2() > 8
        );

        //remove if occupied by same color
        moves.removeIf(i ->
                board.get_piece(i.get_val1(), i.get_val2()) != null && board.get_piece(i.get_val1(), i.get_val2()).team == this.team
        );

        return moves;
    }

    @Override
    public void move(int x, int y) {
        Pair<Integer, Integer> move = new Pair<>(x, y);
        if (get_moves().contains(move)) {
            board.set_piece(position.get_val1(), position.get_val2(), null);
            this.position = move;
            board.set_piece(position.get_val1(), position.get_val2(), this);
        }
    }
}
