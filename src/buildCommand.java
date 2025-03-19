class BuildCommand implements Command {
    private static final int REQUIRED_GOLD = 100;

    @Override
    public void execute(Player player) {
        if (player.getMoney() >= REQUIRED_GOLD) {
            player.removeMoney(REQUIRED_GOLD);
            System.out.println("Gratulujeme! Máš dostatek zlata a můžeš postavit svou vlastní kovárnu.");
            System.out.println("Stavba byla úspěšně dokončena a nyní jsi hrdým majitelem kovárny!");
            System.out.println("Hra končí. Děkujeme za hraní!");
            System.exit(0);
        } else {
            System.out.println("Nemáš dostatek zlata na stavbu kovárny. Potřebuješ alespoň " + REQUIRED_GOLD + " zlatých.");
        }
    }
}