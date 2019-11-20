package CommunicateWithData.Friend;

import java.util.Objects;

public class Friend {

    private int id;
    private String brugernavn;

    public Friend(){}

    public Friend(int id, String brugernavn) {
        this.id = id;
        this.brugernavn = brugernavn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrugernavn() {
        return brugernavn;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friend friend = (Friend) o;
        return id == friend.id &&
                Objects.equals(brugernavn, friend.brugernavn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brugernavn);
    }

    @Override
    public String toString() {
        return  "id: " + id +
                "   brugernavn: '" + brugernavn +
                '\n';
    }
}
