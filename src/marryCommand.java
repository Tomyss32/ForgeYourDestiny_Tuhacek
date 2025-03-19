class MarryCommand implements Command {
    @Override
    public void execute(Player player) {
        if (player.getReputation() >= 100) {
            System.out.println("Congratulations! You are respected enough and can get married.");
            System.out.println("The wedding took place with great celebration, and you are now officially married!");
            System.out.println("The game is over. Thank you for playing!");
            System.exit(0); // ✅ Ends the game after marriage
        } else {
            System.out.println("Your reputation is not high enough for marriage. You need at least 100 reputation.");
        }
    }
}