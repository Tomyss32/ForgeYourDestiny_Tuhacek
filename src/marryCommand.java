class MarryCommand implements Command {
    @Override
    public void execute(Player player) {
        if (player.getReputation() >= 100) {
            System.out.println("Gratulujeme! Jsi dostatečně vážený a můžeš se oženit.");
            System.out.println("Svatba proběhla s velkou slávou a nyní jsi oficiálně ženatý!");
            System.out.println("Hra končí. Děkujeme za hraní!");
            System.exit(0); // ✅ Ukončí hru po svatbě
        } else {
            System.out.println("Tvoje reputace není dostatečně vysoká na svatbu. Potřebuješ alespoň 100 reputace.");
        }
    }
}