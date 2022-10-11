class Weapon implements Item {

    @Override
    public Item drop() {
        System.out.println("you have dropped weapon: " + this.name);
        return this;
    }

}