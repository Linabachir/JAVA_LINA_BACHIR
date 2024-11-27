public class GameTime {
    private int timeRemaining; // Temps restant en secondes
    private boolean isGameOver = false;

    public GameTime(int initialTimeInSeconds) {
        this.timeRemaining = initialTimeInSeconds;
    }

    // Met à jour le timer (appelé périodiquement)
    public void update() {
        if (timeRemaining > 0) {
            timeRemaining--;
        } else {
            isGameOver = true;
        }
    }

    // Temps restant
    public int getTimeRemaining() {
        return timeRemaining;
    }

    // Vérifie si le jeu est terminé
    public boolean isGameOver() {
        return isGameOver;
    }
}