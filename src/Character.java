class Character {
    protected String name;

    public Character(String name) {
        this.name = name;
    }

    public void interact() {
        System.out.println(name + ": Hello, traveler!");
    }
}