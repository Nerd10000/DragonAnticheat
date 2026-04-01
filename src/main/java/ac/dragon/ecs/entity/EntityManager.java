package ac.dragon.ecs.entity;


public class EntityManager {
    private int nextId;

    public void setNextId(int id){
        nextId = id++;
    }
    public void createId(){
        nextId++;
        
    }
}
    