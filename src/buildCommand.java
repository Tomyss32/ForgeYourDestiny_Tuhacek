class BuildCommand implements Command {
    private static final int REQUIRED_GOLD = 100;

    @Override
    public void execute(Player player) {
        if (player.getMoney() >= REQUIRED_GOLD) {
            player.removeMoney(REQUIRED_GOLD);
            System.out.println("Congratulations! You have enough gold to build your own forge.");
            System.out.println("The construction was successfully completed, and you are now the proud owner of a forge!");
            System.out.println("The game is over. Thank you for playing!");
            System.exit(0);
        } else {
            System.out.println("You don't have enough gold to build a forge. You need at least " + REQUIRED_GOLD + " gold.");
        }
    }
}