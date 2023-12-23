package br.com.senior.burger_place.domain.board;

import br.com.senior.burger_place.domain.board.dto.BoardRegisterDTO;
import br.com.senior.burger_place.domain.board.dto.BoardUpdateDTO;
import br.com.senior.burger_place.domain.board.dto.ListingBoardDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    BoardRepository repository;

    public Board addBoard(BoardRegisterDTO data) {
        if (repository.existsByNumber(data.number())){
            throw new DuplicateKeyException("Já existe uma mesa cadastrada com esse número");
        }
        return repository.save(new Board(data));
    }

    public void updateBoard(Long id, BoardUpdateDTO data) {
        Optional<Board> optionalBoard = repository.findById(id);
        if (optionalBoard == null){
            throw new EntityNotFoundException("Mesa não cadastrada");
        }
        if (optionalBoard.isEmpty()){
            throw new EntityNotFoundException("Mesa não cadastrada");
        }
        Board board = optionalBoard.get();
        board.updateInformation(data);
    }
    public Board listBoardsById(Long id) {
        Board board = repository.getReferenceByIdAndActiveTrue(id);
        if (board == null){
            throw new EntityNotFoundException("Mesa não existe");
        }
        return board;
    }
    public Board verifyOccupiedBoard(Long id) {
        Board board = listBoardsById(id);
        if (repository.isBoardOccupied(id)){
            throw new EntityNotFoundException("A mesa já está ocupada");
        }
        return board;
    }

    public Page<ListingBoardDTO> listBoardsByLocation(BoardLocation location, Pageable pageable) {
        Page<Board> boards = repository.findByLocationAndActiveTrue(location, pageable);
        if (boards.isEmpty()){
            throw new EntityNotFoundException("Não encontrado mesas para a área informada!");
        }
        return boards.map(ListingBoardDTO::new);
    }

    public Page<Board> listAllBoards(Pageable pageable) {
        return repository.findAllBoardsAvailable(pageable);

    }
}
