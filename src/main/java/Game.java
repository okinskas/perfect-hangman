public class Game {

    public Game() {
        DictionaryReader reader = new DictionaryFileReader();
        GuessBot bot = new GuessBot("beautiful", reader);
        while (!bot.isWordSolved()) {
            bot.makeGuess();
        }
        System.exit(1);
    }

    public static void main(String[] args) {
        new Game();
    }
}
