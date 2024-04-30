package onetomany.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tictactoe")
public class TicTacToeController {

    private TicTacToeService ticTacToeService;

    @Autowired
    public TicTacToeController(TicTacToeService ticTacToeService) {
        this.ticTacToeService = ticTacToeService;
    }

    @PostMapping("/results")
    public ResponseEntity<Void> saveGameResult(@RequestParam String username, @RequestParam String result) {
        ticTacToeService.saveGameResult(username, result);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/scoreboard/{username}")
    public ResponseEntity<List<GameResult>> getUserScoreboard(@PathVariable String username) {
        List<GameResult> scoreboard = ticTacToeService.getUserScoreboard(username);
        return ResponseEntity.ok(scoreboard);
    }
}