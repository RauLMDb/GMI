package sorteo;

public class Team {
    private String name;
    private String nation;
    private String pos;
    private String group;
    private String image;

    public Team(String name, String nation, String pos, String group,String image) {
        this.name = name;
        this.nation = nation;
        this.pos = pos;
        this.group = group;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getNation() {
        return nation;
    }

    public String getPos() {
        return pos;
    }

    public String getGroup() {
        return group;
    }

    public String getImage() {
        return image;
    }

    public String toString() {
        return name;
    }


    /**
     * Compare two teams kept in two vertex(nation, position, group)
     *
     * @param t1
     * @return true or false depending of teams are the same or not
     */
    public boolean equalsT(Team t1) {
        return name.equals(t1.getName())
                && nation.equals(t1.getNation())
                && pos.equals(t1.getPos())
                && group.equals(t1.getGroup());
    }
}
