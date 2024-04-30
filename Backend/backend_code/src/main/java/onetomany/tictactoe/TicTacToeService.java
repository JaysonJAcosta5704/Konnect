package onetomany.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onetomany.Users.User;
import onetomany.Users.UserRepository;
import java.util.List;

@Service
public class TicTacToeService {

    private GameResultRepository gameResultRepository;
    private UserRepository userRepository;

    @Autowired
    public TicTacToeService(GameResultRepository gameResultRepository, UserRepository userRepository) {
        this.gameResultRepository = gameResultRepository;
        this.userRepository = userRepository;
    }

    public void saveGameResult(String username, String result) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            GameResult gameResult = new GameResult(user, result);
            gameResultRepository.save(gameResult);
        }
    }

    public List<GameResult> getUserScoreboard(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return gameResultRepository.findByUser(user);
        }
        return List.of();
    }
}