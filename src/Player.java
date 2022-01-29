import java.util.ArrayList;
/**
 * Escreva uma descrição da classe Player aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Player
{
    
    private String name;
    private int maxWeightCarry;
    private ArrayList <Item> bolsa = new ArrayList<>();
    private Room currentRoom;

    public Player(String name)
    {
        this.name = name;
    }

    public void dropItem()
    {
        // escreva seu código aqui
    
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    public void setCurrentRoom(Room currentRoom){
        this.currentRoom = currentRoom;
    }
    
    public String getCurrentRoomDescription(){
        return currentRoom.getLongDescription();
    }
    
    public void pickUpItem()
    {
        
        bolsa.add(currentRoom.getItem());
        currentRoom.removeItemList();
    }
    
    public String getBag() 
    {
        String result = "";

        if (bolsa.isEmpty()) {
            return "sem itens aqui";
        }
        for (Item itemBag : bolsa) {
            result += itemBag.getItemName();
        }

        return result;
    }

}
