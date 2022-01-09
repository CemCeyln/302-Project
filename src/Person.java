import java.util.ArrayList;
import java.util.Date;

public class Person {
    private int ID;
    private String name;
    private String birthDate;
    private String gender; //0 is female, 1 is male
    private Relation parents;
    private ArrayList<Relation> relations;

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Relation getParents() {
        return parents;
    }

    public void setParents(Relation parents) {
        this.parents = parents;
    }

    public ArrayList<Relation> getRelations() {
        return relations;
    }

    public void setRelations(ArrayList<Relation> relations) {
        this.relations = relations;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
