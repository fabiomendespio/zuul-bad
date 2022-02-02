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
    private int maxWeightCarry = 5;
    private ArrayList <Item> bolsa = new ArrayList<>();
    private Room currentRoom;

    public Player(String name)
    {
        this.name = name;
    }

    public void dropItem(String soltaItem)
    {
        int i;
        for(i = 0; i< bolsa.size(); i++) {
            if (bolsa.get(i).getItemName().equals(soltaItem)) {
                System.out.println("Você soltou " + bolsa.get(i).getItemName());
                currentRoom.getItemList().add(bolsa.get(i));
                bolsa.remove(i);
            }
        }
    }

    public void pickUpItem(String pegaItem)
    {
        int i;
        for(i = 0; i< currentRoom.getItemList().size(); i++) {

            if (currentRoom.getItem(i).getItemName().equals(pegaItem)) {

                bolsa.add(currentRoom.getItem(i));
                System.out.println("Você pegou " + currentRoom.getItem(i).getItemName());
                currentRoom.removeItemList(i);

            }
        }
    }

    public int checkWeight (){
        int i;
        int pesoTotal = 0;
        for(i =0; i < bolsa.size(); i++){
            pesoTotal =+ bolsa.get(i).getItemWeight();
        }

        return pesoTotal;
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

    public int getMaxWeightCarry() {
        return maxWeightCarry;
    }

    public String getBag()
    {
        String result = "";
        if (bolsa.isEmpty()) {
            return "sem itens aqui";
        }
        for (Item itemBag : bolsa) {
            result += "[" + itemBag.getItemName() + "] ";
        }

        return result;
    }

}
